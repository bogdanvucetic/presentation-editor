package raf.graffito.dsw.controller.state;

import raf.graffito.dsw.model.composite.SlideElement;

import java.util.HashSet;
import java.util.Set;

public class SlideEditorSession {
    private static SlideEditorSession instance;

    private State currentState;
    private Set<SlideElement> selectedElements;

    private SlideEditorSession() {
        currentState = new SelectState();
        selectedElements = new HashSet<SlideElement>();
    }

    public void changeState(State newState) {
        currentState = newState;
    }

    public static SlideEditorSession getInstance() {
        if (instance == null) {
            instance = new SlideEditorSession();
        }
        return instance;
    }

    public void addSelectedElement(SlideElement newElement) {
        selectedElements.add(newElement);
    }

    public State getCurrentState() {
        return currentState;
    }
}
