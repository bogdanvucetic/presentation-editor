package raf.graffito.dsw.gui.swing.view;

import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.model.composite.Presentation;
import raf.graffito.dsw.model.composite.Project;
import raf.graffito.dsw.observer.MessageGenerator;
import raf.graffito.dsw.observer.MessageType;
import raf.graffito.dsw.observer.Subscriber;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {
    private JLabel currentProject = new JLabel("-");
    private JLabel currentPresentation = new JLabel("-");

    public InfoPanel(Project project, Presentation presentation) {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder("Informacije"));

        currentProject.setText(project.getName());
        try {
            currentPresentation.setText(presentation.getName());
        } catch (Exception e) {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage("Projekat nema prezentacija", MessageType.INFO);
        }

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4, 8, 4, 8);
        c.anchor = GridBagConstraints.WEST;

        c.gridx = 0; c.gridy = 0; add(new JLabel("Prezentacija:"), c);
        c.gridx = 1; add(currentPresentation, c);

        c.gridx = 0; c.gridy = 1; add(new JLabel("Projekat:"), c);
        c.gridx = 1; add(currentProject, c);
    }
}
