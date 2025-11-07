package raf.graffito.dsw.gui.swing.tabs;

import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.observer.MessageGenerator;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Desni panel sa tabovima.
 * Dvoklik na PROJECT u stablu -> otvara tab za SVAKU njegovu prezentaciju (leaf).
 * Svaka prezentacija bira svoju boju (ako je nema), a već iskorišćene boje nisu dozvoljene.
 * Boji se samo GORNJI DEO (header) taba, ne i sadržaj.
 *
 * + InfoPanel integracija: pokazuje naziv prezentacije, projekat i autora projekta.
 */
public class TabsPanel extends JPanel {

    private final JTabbedPane tabovi = new JTabbedPane();
    private final JTree tree;
    private final DecoratorRegistry registry;
    private final MessageGenerator messages = ApplicationFramework.getInstance().getMessageGenerator();

    // referenca na info panel i lista čvorova po tabovima ——
    private final InfoPanel infoPanel;
    private final List<DefaultMutableTreeNode> currentPrezentacije = new ArrayList<>();

    public TabsPanel(JTree tree, DecoratorRegistry registry, InfoPanel infoPanel) {
        this.tree = tree;
        this.registry = registry;
        this.infoPanel = infoPanel;

        setLayout(new BorderLayout());
        add(tabovi, BorderLayout.CENTER);

        // osvežavanje Info panela pri promeni selektovanog taba
        tabovi.addChangeListener(e -> osveziInfoPoSelektovanomTabu());

        //dvoklik na čvor u stablu
        tree.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() != 2) return;

                TreePath path = tree.getPathForLocation(e.getX(), e.getY());
                if (path == null) return;

                DefaultMutableTreeNode cvor = (DefaultMutableTreeNode) path.getLastPathComponent();
                if (jeProjekat(cvor)) otvoriSvePrezentacije(cvor);
            }
        }); ///ovu metodu chat gpt za dvoklik
    }

    // Project: nije list i ima bar jedno leaf dete (prezentaciju)
    private boolean jeProjekat(DefaultMutableTreeNode cvor) {
        if (cvor == null || cvor.isLeaf()) return false;
        for (int i = 0; i < cvor.getChildCount(); i++)
            if (jePrezentacija((DefaultMutableTreeNode) cvor.getChildAt(i))) return true;
        return false;
    }

    // Prezentacija je leaf
    private boolean jePrezentacija(DefaultMutableTreeNode cvor) {
        return cvor != null && cvor.isLeaf();
    }

    // obezbedi boje, pa napravi tabove
    private void otvoriSvePrezentacije(DefaultMutableTreeNode projekat) {
        // 1) Skupi sve prezentacije (leaf-ove)
        List<DefaultMutableTreeNode> prez = new ArrayList<>();
        for (int i = 0; i < projekat.getChildCount(); i++) {
            TreeNode det = projekat.getChildAt(i);
            if (det instanceof DefaultMutableTreeNode n && jePrezentacija(n)) prez.add(n);
        }
        if (prez.isEmpty()) return;

        // 2) Za svaku prezentaciju koja nema boju – pitaj dok ne izabere slobodnu
        for (DefaultMutableTreeNode p : prez) {
            ColorDecorator cd = registry.colorOf(p); // ključ je čvor (stabilno)
            if (cd.getColor() != null) continue;

            while (true) {
                Color izabrana = JColorChooser.showDialog(this,
                        "Izaberi boju za: " + tekstZaPrikaz(p), null);
                if (izabrana == null) break; // Cancel -> nema taba za ovu prezentaciju

                if (registry.isColorUsed(izabrana)) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Ta boja je već iskorišćena. Morate izabrati drugu boju.",
                            "Zauzeta boja",
                            JOptionPane.WARNING_MESSAGE
                    );
                } else {
                    cd.setColor(izabrana);
                    registry.markUsed(izabrana);
                    break;
                }
            }
        }

        // 3) Očisti i dodaj tabove samo onima koje imaju boju
        currentPrezentacije.clear();
        tabovi.removeAll();

        for (DefaultMutableTreeNode p : prez) {
            Color boja = registry.colorOf(p).getColor();
            if (boja == null) continue; // korisnik odustao

            String naslov = tekstZaPrikaz(p);

            // upiši mapping tabIndex -> node
            currentPrezentacije.add(p);

            // sadržaj taba (ostaje beo)
            JPanel sadrzaj = new JPanel(new BorderLayout());
            sadrzaj.add(new JLabel("Presentation: " + naslov, SwingConstants.CENTER),
                    BorderLayout.CENTER);

            tabovi.addTab(naslov, sadrzaj);
            int idx = tabovi.getTabCount() - 1;

            // Header kao panel da se oboji ceo gornji deo
            JPanel header = new JPanel(new BorderLayout());
            header.setOpaque(true);
            header.setBackground(boja);
            JLabel lbl = new JLabel(naslov);
            int lum = (int)(0.299*boja.getRed() + 0.587*boja.getGreen() + 0.114*boja.getBlue());
            lbl.setForeground(lum < 140 ? Color.WHITE : Color.BLACK);
            lbl.setBorder(BorderFactory.createEmptyBorder(2, 8, 2, 8));
            header.add(lbl, BorderLayout.CENTER);
            tabovi.setTabComponentAt(idx, header);
        }

        if (tabovi.getTabCount() > 0) tabovi.setSelectedIndex(0);

        // osveži info panel sada
        osveziInfoPoSelektovanomTabu();
    }

    // InfoPanel pomoćne metode

    /** Upiši info o trenutno selektovanom tabu u infoPanel. */
    private void osveziInfoPoSelektovanomTabu() {
        if (infoPanel == null) return;

        int idx = tabovi.getSelectedIndex();
        if (idx < 0 || idx >= currentPrezentacije.size()) {
            infoPanel.showInfo("-", "-", "-");
            return;
        }

        DefaultMutableTreeNode prezNode = currentPrezentacije.get(idx);
        String presentationName = tekstZaPrikaz(prezNode);

        DefaultMutableTreeNode projectNode = nadjiProjekat(prezNode);
        String projectName = projectNode != null ? tekstZaPrikaz(projectNode) : "-";
        String authorName = projectNode != null ? autorIzProjekta(projectNode) : "-";

        infoPanel.showInfo(presentationName, projectName,authorName);
    }

    /** Popni se uz roditelje dok ne dođeš do prvog koji nije leaf (tretiramo kao projekat). */
    private DefaultMutableTreeNode nadjiProjekat(DefaultMutableTreeNode od) {
        DefaultMutableTreeNode cur = od;
        while (cur != null && cur.getParent() instanceof DefaultMutableTreeNode parent) {
            if (!parent.isLeaf()) return parent;
            cur = parent;
        }
        return null;
    }

    //posto nema autora za sada ovo ce se sve zanemariti
    private String autorIzProjekta(DefaultMutableTreeNode projectNode) {
        Object uo = projectNode.getUserObject();
        if (uo == null) return "-";
        try {
            for (String m : new String[]{"getAuthor"}) {
                try {
                    Method mm = uo.getClass().getMethod(m);
                    Object val = mm.invoke(uo);
                    if (val != null) return String.valueOf(val);
                } catch (NoSuchMethodException ignore) { /* probaj sledeći */ }
            }
        } catch (Exception ignore) { }
        return "-";
    }

    // Vrati isti tekst kao u stablu (radi i sa custom rendererom)
    private String tekstZaPrikaz(DefaultMutableTreeNode cvor) {
        return tree.convertValueToText(cvor, false, false, cvor.isLeaf(), -1, false);
    }
}
