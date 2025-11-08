package raf.graffito.dsw.gui.swing.view;

import raf.graffito.dsw.model.composite.Presentation;
import raf.graffito.dsw.observer.model.ModelSubscriber;
import raf.graffito.dsw.observer.model.ModelEvent;

import javax.swing.*;
import java.awt.*;

public class PresentationView extends JPanel implements ModelSubscriber {
    private Presentation presentation;

    public PresentationView(Presentation presentation) {
        this.presentation = presentation;
        setLayout(new BorderLayout());
        add(new JLabel("(prostor za uređivanje slajdova)"), BorderLayout.CENTER);
    }

    public Presentation getPresentation() {
        return presentation;
    }

    @Override
    public void update(ModelEvent event) {

    }
}
