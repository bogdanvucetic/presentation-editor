package raf.graffito.dsw.gui.swing.view;

import raf.graffito.dsw.model.composite.GraffNode;
import raf.graffito.dsw.model.composite.Presentation;
import raf.graffito.dsw.model.composite.Slide;
import raf.graffito.dsw.observer.model.ModelSubscriber;
import raf.graffito.dsw.observer.model.ModelEvent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PresentationView extends JPanel implements ModelSubscriber {
    private Presentation presentation;
    private List<SlideView> slides;
    private SlideView currentSlideView;

    public PresentationView(Presentation presentation) {
        this.presentation = presentation;
        slides = new ArrayList<>();

        setLayout(new BorderLayout());
        presentation.addSubscriber(this);

        loadSlides();
    }

    public void setSelectedSlide(Slide slide) {
        SlideView target = findViewFor(slide);

        if (target == null) {
            return;
        }

        if (currentSlideView == target) {
            return;
        }

        // neophodno da bi brisanje slajdova ispravno radilo
        if (currentSlideView != null) {
            remove(currentSlideView);
        }

        currentSlideView = target;
        add(currentSlideView, BorderLayout.CENTER);

        // iz nekog razloga neophodno opet za ispravno iscrtavanje
        revalidate();
        repaint();
    }

    @Override
    public void update(ModelEvent event) {
        loadSlides();
    }

    private void loadSlides() {
        Slide previouslySelected = currentSlideView != null ? currentSlideView.getSlide() : null;

        removeAll();
        slides.clear();
        currentSlideView = null;

        for (GraffNode child : presentation.getChildren()) {
            if (child instanceof Slide s) {
                SlideView sv = new SlideView(s);
                slides.add(sv);

                if (previouslySelected == s && currentSlideView == null) {
                    currentSlideView = sv;
                }
            }
        }

        if (currentSlideView == null && !slides.isEmpty()) {
            currentSlideView = slides.getFirst();
        }

        if (currentSlideView != null) {
            add(currentSlideView, BorderLayout.CENTER);
        }

        revalidate();
        repaint();
    }

    private SlideView findViewFor(Slide slide) {
        for (SlideView slideView : slides) {
            if (slideView.getSlide() == slide) {
                return slideView;
            }
        }
        return null;
    }

    public Presentation getPresentation() {
        return presentation;
    }
}
