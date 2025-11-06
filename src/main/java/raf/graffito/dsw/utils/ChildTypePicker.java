package raf.graffito.dsw.utils;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

public class ChildTypePicker {
    public static String pickProjectChild(){
        JRadioButton presentationButton = new JRadioButton("Prezentacija");
        JRadioButton slideButton = new JRadioButton("Slajd");
        presentationButton.setSelected(true);

        ButtonGroup group = new ButtonGroup();
        group.add(presentationButton);
        group.add(slideButton);

        JPanel panel = new JPanel();
        panel.add(new JLabel("Izaberi tip čvora projekta koji želiš da dodaš:"));
        panel.add(presentationButton);
        panel.add(slideButton);

        int result = JOptionPane.showConfirmDialog(null, panel, "Izaberi tip čvora", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            if (presentationButton.isSelected()) {
                return "Presentation";
            } else if (slideButton.isSelected()) {
                return "Slide";
            }
        }

        return null;
    }

}
