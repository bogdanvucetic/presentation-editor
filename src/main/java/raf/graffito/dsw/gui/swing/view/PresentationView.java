package raf.graffito.dsw.gui.swing.view;

import raf.graffito.dsw.controller.EditorContext;
import raf.graffito.dsw.model.composite.GraffNode;
import raf.graffito.dsw.model.composite.Presentation;
import raf.graffito.dsw.model.composite.Slide;
import raf.graffito.dsw.observer.model.ModelEventType;
import raf.graffito.dsw.observer.model.ModelSubscriber;
import raf.graffito.dsw.observer.model.ModelEvent;

import javax.swing.*;
import java.awt.*;

public class PresentationView extends JPanel implements ModelSubscriber {
    private Presentation presentation;
    private SlideView currentSlideView;

    public PresentationView(Presentation presentation) {
        this.presentation = presentation;

        setLayout(new BorderLayout());
        presentation.addSubscriber(this);

        if (!presentation.getChildren().isEmpty()) {
            setSelectedSlide((Slide) presentation.getChildren().getFirst());
        }
    }

    public void setSelectedSlide(Slide slide) {
        if (currentSlideView != null) {
            remove(currentSlideView);
        }

        if (slide != null) {
            currentSlideView = new SlideView(slide);
            add(currentSlideView, BorderLayout.CENTER);
        } else {
            currentSlideView = null;
        }

        EditorContext.getInstance().setCurrentSlideView(currentSlideView);

        revalidate();
        repaint();
    }

    @Override
    public void update(ModelEvent event) {
        ModelEventType type = event.getType();
        GraffNode node = event.getNode();

        switch (type) {
            case ADD_CHILD -> {
                if (node.getParent() == presentation) {
                    setSelectedSlide((Slide) node);
                }
            }
            case REMOVE_CHILD -> {
                if (currentSlideView != null && currentSlideView.getSlide() == node) {
                    if (presentation.getChildren().isEmpty()) {
                        setSelectedSlide(null);
                    } else {
                        setSelectedSlide((Slide) presentation.getChildren().getFirst());
                    }
                }
            }
            case RENAME -> {}
        }
    }

    public Presentation getPresentation() {
        return presentation;
    }

    public SlideView getCurrentSlideView() {
        return currentSlideView;
    }
}
