package raf.graffito.dsw.gui.swing.tabs;

import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.Color;
import java.util.HashSet;
import java.util.Set;
import java.util.WeakHashMap;

/**
 * Registry vezan za TreeNode:
 * - čuva ColorDecorator po leaf čvoru (prezentaciji)
 * - globalno pamti zauzete boje (zabrana duplikata).
 */
public class DecoratorRegistry {

    /** Boje po čvoru (WeakHashMap da ne sprečava GC ako se drvo rekonstruiše). */
    private final WeakHashMap<DefaultMutableTreeNode, ColorDecorator> byNode = new WeakHashMap<>();

    /** Skup svih zauzetih boja (globalno). */
    private final Set<Color> usedColors = new HashSet<>();

    /** Vrati (ili kreiraj) dekorator za dati leaf čvor. */
    public ColorDecorator colorOf(DefaultMutableTreeNode node) {
        if (node == null) return new ColorDecorator(null);
        return byNode.computeIfAbsent(node, n -> new ColorDecorator(null));
    }

    /** Da li je boja već zauzeta (ne dozvoljavamo duplikate)? */
    public boolean isColorUsed(Color c) {
        return c != null && usedColors.contains(c);
    }

    /** Obeleži boju kao zauzetu čim je korisnik potvrdi. */
    public void markUsed(Color c) {
        if (c != null) usedColors.add(c);
    }

    /**  skini boju iz skupa – ako dodaš "promeni/ukloni boju". */
    public void unmarkUsed(Color c) {
        if (c != null) usedColors.remove(c);
    }

    /** Očisti sve (npr. pri resetu projekta/aplikacije). */
    public void clear() {
        byNode.clear();
        usedColors.clear();
    }
}
