package raf.graffito.dsw.gui.swing.view;

import raf.graffito.dsw.model.composite.GraffNode;
import raf.graffito.dsw.model.composite.Presentation;
import raf.graffito.dsw.model.composite.Project;
import raf.graffito.dsw.observer.model.ModelSubscriber;
import raf.graffito.dsw.observer.model.ModelEvent;
import raf.graffito.dsw.observer.model.ModelEventType;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel implements ModelSubscriber {
    private Project project;
    private Presentation currentPresentation;

    private JLabel projectLabel = new JLabel("-");
    private JLabel currentPresentationLabel = new JLabel("-");

    public InfoPanel() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder("Informacije"));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4, 8, 4, 8);
        c.anchor = GridBagConstraints.WEST;

        c.gridx = 0; c.gridy = 0; add(new JLabel("Prezentacija:"), c);
        c.gridx = 1; add(currentPresentationLabel, c);

        c.gridx = 0; c.gridy = 1; add(new JLabel("Projekat:"), c);
        c.gridx = 1; add(projectLabel, c);
    }

    public void setProject(Project project) {
        if (this.project != null){
            this.project.removeSubscriber(this);
        }

        this.project = project;

        if (project != null) {
            this.project.addSubscriber(this);
            projectLabel.setText(project.getName());
        } else {
            projectLabel.setText("-");
        }
    }

    public void setCurrentPresentation(Presentation currentPresentation) {
        if (this.currentPresentation != null){
            this.currentPresentation.removeSubscriber(this);
        }

        this.currentPresentation = currentPresentation;

        if (currentPresentation != null) {
            this.currentPresentation.addSubscriber(this);
            currentPresentationLabel.setText(currentPresentation.getName());
        } else {
            currentPresentationLabel.setText("-");
        }
    }

    public void setProjectLabel(String label) {
        projectLabel.setText(label);
    }

    @Override
    public void update(ModelEvent event) {
        GraffNode node = event.getNode();
        ModelEventType type = event.getType();

        if (node == project) {
            if (type == ModelEventType.RENAME) projectLabel.setText(project.getName());

            else if (type == ModelEventType.REMOVE_CHILD) {
                setProject(null);
                setCurrentPresentation(null);
            }
        }  else if (node == currentPresentation) {
            if (type == ModelEventType.RENAME) currentPresentationLabel.setText(currentPresentation.getName());

            else if (type == ModelEventType.REMOVE_CHILD) setCurrentPresentation(null);
        }
    }
}
