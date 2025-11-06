package raf.graffito.dsw.gui.swing.Tabovi;

import javax.swing.*;
import java.awt.*;

/** Prikazuje: naziv prezentacije, naziv projekta i ime autora projekta. */
public class InfoPanel extends JPanel {
    private final JLabel lblPresentation = new JLabel("-");
    private final JLabel lblProject = new JLabel("-");
    private final JLabel lblAuthor = new JLabel("-");

    public InfoPanel() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder("Informacije"));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4, 8, 4, 8);
        c.anchor = GridBagConstraints.WEST;

        c.gridx = 0; c.gridy = 0; add(new JLabel("Prezentacija:"), c);
        c.gridx = 1; add(lblPresentation, c);

        c.gridx = 0; c.gridy = 1; add(new JLabel("Projekat:"), c);
        c.gridx = 1; add(lblProject, c);

        c.gridx = 0; c.gridy = 2; add(new JLabel("Autor:"), c);
        c.gridx = 1; add(lblAuthor, c);
    }

    public void showInfo(String presentation, String project, String author) {
        lblPresentation.setText(safe(presentation));
        lblProject.setText(safe(project));
        lblAuthor.setText(safe(author));
    }
    private String safe(String s) { return (s == null || s.isBlank()) ? "-" : s; }
}
