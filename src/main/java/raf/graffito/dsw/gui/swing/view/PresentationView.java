package raf.graffito.dsw.gui.swing.view;

import raf.graffito.dsw.model.composite.GraffNode;
import raf.graffito.dsw.model.composite.Presentation;
import raf.graffito.dsw.observer.model.ModelEventType;
import raf.graffito.dsw.observer.model.ModelSubscriber;
import raf.graffito.dsw.observer.model.ModelEvent;

import javax.swing.*;
import java.awt.*;

public class PresentationView extends JPanel implements ModelSubscriber {
    private Presentation presentation;
    private JLabel label;

    public PresentationView(Presentation presentation) {
        this.presentation = presentation;
        presentation.addSubscriber(this);
        label = new JLabel("Editovanje: " + presentation.getName());
        setLayout(new BorderLayout());
        add(label, BorderLayout.CENTER);
    }

    public Presentation getPresentation() {
        return presentation;
    }

    @Override
    public void update(ModelEvent event) {
        GraffNode node = event.getNode();
        ModelEventType type = event.getType();

        if (type == ModelEventType.RENAME && node == presentation) {
            label.setText("Editovanje: " + node.getName());
        }
    }
}
