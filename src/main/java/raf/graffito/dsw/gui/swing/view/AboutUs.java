package raf.graffito.dsw.gui.swing.view;

import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.observer.MessageType;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.URL;

public class AboutUs extends JFrame {

    public AboutUs() {

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

        Icon s1 = loadIcon("/images/andrea.jpg", 60, 60);
        Icon s2 = loadIcon("/images/bogdan.jpg", 60, 60);

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

    private Icon loadIcon(String path, int width, int height) {
        // URL služi za pronalaženje resursa unutar JAR fajla ili klase
        URL ImageURL = getClass().getResource(path);

        if (ImageURL == null) {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage("Ikonica nije pronađena: " + path, MessageType.INFO);
            return null;
        }

        Image img = new ImageIcon(ImageURL).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH); // Napravimo Image iz ImageIcon
        return new ImageIcon(img);
    }


}
