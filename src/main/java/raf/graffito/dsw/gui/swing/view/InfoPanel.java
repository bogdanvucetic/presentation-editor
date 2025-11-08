package raf.graffito.dsw.gui.swing.view;

import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.model.composite.GraffNode;
import raf.graffito.dsw.model.composite.Presentation;
import raf.graffito.dsw.model.composite.Project;
import raf.graffito.dsw.observer.message.MessageType;
import raf.graffito.dsw.observer.model.ModelSubscriber;
import raf.graffito.dsw.observer.model.ModelUpdateEvent;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel implements ModelSubscriber {
    private Project project;
    private Presentation currentPresentation;

    private JLabel projectLabel = new JLabel("-");
    private JLabel currentPresentationLabel = new JLabel("-");

    public InfoPanel(Project project, Presentation presentation) {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder("Informacije"));

        projectLabel.setText(project.getName());
        try {
            currentPresentationLabel.setText(presentation.getName());
        } catch (Exception e) {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage("Projekat nema prezentacija", MessageType.INFO);
        }

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4, 8, 4, 8);
        c.anchor = GridBagConstraints.WEST;

        c.gridx = 0; c.gridy = 0; add(new JLabel("Prezentacija:"), c);
        c.gridx = 1; add(currentPresentationLabel, c);

        c.gridx = 0; c.gridy = 1; add(new JLabel("Projekat:"), c);
        c.gridx = 1; add(projectLabel, c);
    }

    @Override
    public void update(ModelUpdateEvent event) {

    }
}
