package raf.graffito.dsw.gui.swing.view;

import raf.graffito.dsw.controller.EditorContext;
import raf.graffito.dsw.model.composite.GraffNode;
import raf.graffito.dsw.model.composite.Presentation;
import raf.graffito.dsw.model.composite.Slide;
import raf.graffito.dsw.observer.model.UpdateType;
import raf.graffito.dsw.observer.model.UpdateSubscriber;
import raf.graffito.dsw.observer.model.ModelUpdate;

import javax.swing.*;
import java.awt.*;

public class PresentationView extends JPanel implements UpdateSubscriber {
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
            currentSlideView.getSlide().removeSubscriber(this);
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
    public void update(ModelUpdate event) {
        UpdateType type = event.getType();
        GraffNode node = event.getNode();

        switch (type) {
            case CHILD_ADDED -> {
                if (node.getParent() == presentation) {
                    setSelectedSlide((Slide) node);
                }
            }
            case CHILD_REMOVED -> {
                if (currentSlideView != null && currentSlideView.getSlide() == node) {
                    if (presentation.getChildren().isEmpty()) {
                        setSelectedSlide(null);
                    } else {
                        setSelectedSlide((Slide) presentation.getChildren().getFirst());
                    }
                }
            }
            case RENAMED, PROJECT_OPENED, PROPERTY_UPDATED -> {}
        }
    }

    public Presentation getPresentation() {
        return presentation;
    }

    public SlideView getCurrentSlideView() {
        return currentSlideView;
    }
}
