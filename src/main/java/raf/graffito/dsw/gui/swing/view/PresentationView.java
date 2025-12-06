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

    public PresentationView(Presentation presentation) {
        this.presentation = presentation;
        presentation.addSubscriber(this);
        setLayout(new BorderLayout());
    }

    public Presentation getPresentation() {
        return presentation;
    }

    @Override
    public void update(ModelEvent event) {
        GraffNode node = event.getNode();
        ModelEventType type = event.getType();
    }
}
