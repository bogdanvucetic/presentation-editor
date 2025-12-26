package raf.graffito.dsw.controller;

import raf.graffito.dsw.controller.state.SelectState;
import raf.graffito.dsw.controller.state.State;
import raf.graffito.dsw.gui.swing.view.SlideView;
import raf.graffito.dsw.model.composite.SlideElement;
import raf.graffito.dsw.strategy.EvalStrategy;
import raf.graffito.dsw.strategy.SimpleEvalStrategy;

import java.util.HashSet;
import java.util.Set;

public class EditorContext {
    private static EditorContext instance;

    private State currentState;
    private EvalStrategy currentEvalStrategy;
    private SlideView currentSlideView;
    private Set<SlideElement> selectedElements;

    private EditorContext() {
        currentState = null;
        currentEvalStrategy = new SimpleEvalStrategy();
        selectedElements = new HashSet<>();
    }

    public void changeState(State newState) {
        currentState = newState;
        currentState.onEnter();
    }

    public boolean isNewElementsAllowed() {
        return currentEvalStrategy.computeRemainingSpacePercentage(currentSlideView.getSlide().getResolution(), currentSlideView.getSlide().getChildren()) >= 0.2;
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

    public void setCurrentEvalStrategy(EvalStrategy currentEvalStrategy) {
        this.currentEvalStrategy = currentEvalStrategy;
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
