package raf.graffito.dsw.utils;

import raf.graffito.dsw.gui.swing.frames.MainFrame;
import raf.graffito.dsw.model.factory.GraffNodeFactory;
import raf.graffito.dsw.model.factory.PresentationFactory;
import raf.graffito.dsw.model.factory.SlideFactory;

import javax.swing.*;

public class ChildTypePicker {
    public static GraffNodeFactory pickProjectChild(){
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

        // neophodno jer bi se inače pravio čvor čak i kada korisnik odustane
        int result = JOptionPane.showConfirmDialog(MainFrame.getInstance(), panel, "Izaberi tip čvora", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            if (presentationButton.isSelected()) {
                return new PresentationFactory();
            } else if (slideButton.isSelected()) {
                return new SlideFactory();
            }
        }

        return null;
    }

}
