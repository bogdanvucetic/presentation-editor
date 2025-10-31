package raf.graffito.dsw.gui.swing;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

public class OnamaProzor extends JFrame {

    public OnamaProzor() {

        setTitle("O nama");
        setSize(600, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] kolone = {"Fotografija", "Ime i prezime", "Broj indeksa"};

        DefaultTableModel model = new DefaultTableModel(kolone, 0) {

            @Override
            public Class<?> getColumnClass(int kolona) {

                if (kolona == 0) return ImageIcon.class;

                return String.class;
            }

            @Override
            public boolean isCellEditable(int red, int kolona) {

                return false;
            }
        };

        Icon s1 = ucitajIkonicu("/images/andrea.jpg", 60, 60);
        Icon s2 = ucitajIkonicu ("/images/bogdan.jpg", 60, 60);

        model.addRow(new Object[]{s1, "Andrea Dučić", "RN 81/2025"});
        model.addRow(new Object[]{s2, "Bogdan Vučetić", "RN 78/2025"});

        JTable tabela = new JTable(model);
        tabela.setRowHeight(70);
        tabela.setFont(new Font("SansSerif", Font.PLAIN, 14));
        DefaultTableCellRenderer centar = new DefaultTableCellRenderer();
        centar.setHorizontalAlignment(SwingConstants.CENTER);
        tabela.setRowHeight(70);
        tabela.getColumnModel().getColumn(1).setCellRenderer(centar);
        tabela.getColumnModel().getColumn(2).setCellRenderer(centar);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(220);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(120);
        add(new JScrollPane(tabela), BorderLayout.CENTER);


    }
    private Icon ucitajIkonicu(String putanja, int sirina, int visina) {
        URL ImageURL = getClass().getResource(putanja); // URL služi za pronalaženje resursa unutar JAR fajla ili klase

        if (ImageURL == null) {
            System.err.println("File " + putanja + " not found");
            return null;
        }

        Image img = new ImageIcon(ImageURL).getImage().getScaledInstance(sirina, visina, Image.SCALE_SMOOTH); // Napravimo Image iz ImageIcon
        return new ImageIcon(img);
    }


}
