package raf.graffito.dsw.controller;

import raf.graffito.dsw.controller.state.SelectState;
import raf.graffito.dsw.controller.state.State;
import raf.graffito.dsw.gui.swing.view.SlideView;
import raf.graffito.dsw.model.composite.SlideElement;

import java.util.HashSet;
import java.util.Set;

public class EditorContext {
    private static EditorContext instance;

    private State currentState;
    private SlideView currentSlideView;
    private Set<SlideElement> selectedElements;

    private EditorContext() {
        currentState = null;
        selectedElements = new HashSet<>();
    }

    public void changeState(State newState) {
        currentState = newState;
        currentState.onEnter();
    }

    public static EditorContext getInstance() {
        if (instance == null) {
            instance = new EditorContext();
            instance.currentState = new SelectState();
        }
        return instance;
    }

    public State getCurrentState() {
        return currentState;
    }

    public SlideView getCurrentSlideView() {
        return currentSlideView;
    }

    public void setCurrentSlideView(SlideView currentSlideView) {
        this.currentSlideView = currentSlideView;
        clearSelectedElements();
    }

    public Set<SlideElement> getSelectedElements() {
        return Set.copyOf(selectedElements);
    }

    public void addSelectedElement(SlideElement selectedElement) {
        selectedElements.add(selectedElement);
        currentSlideView.updateSelectedElements();
    }

    public void setSelectedElements(Set<SlideElement> selectedElements) {
        this.selectedElements = new HashSet<>(selectedElements);
        currentSlideView.updateSelectedElements();
    }

    public void clearSelectedElements() {
        selectedElements.clear();
        if (currentSlideView != null) {
            currentSlideView.updateSelectedElements();
        }
    }
}
