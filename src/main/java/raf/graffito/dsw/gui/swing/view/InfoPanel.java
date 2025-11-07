package raf.graffito.dsw.gui.swing.view;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {
    private JLabel currentPresentation = new JLabel("-");
    private JLabel currentProject = new JLabel("-");

    public InfoPanel() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder("Informacije"));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4, 8, 4, 8);
        c.anchor = GridBagConstraints.WEST;

        c.gridx = 0; c.gridy = 0; add(new JLabel("Prezentacija:"), c);
        c.gridx = 1; add(currentPresentation, c);

        c.gridx = 0; c.gridy = 1; add(new JLabel("Projekat:"), c);
        c.gridx = 1; add(currentProject, c);
    }
}
