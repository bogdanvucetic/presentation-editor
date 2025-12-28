package raf.graffito.dsw.controller.actions.edit;

import raf.graffito.dsw.controller.EditorContext;
import raf.graffito.dsw.controller.actions.AbstractGraffAction;
import raf.graffito.dsw.controller.state.State;

import java.awt.event.ActionEvent;

public class ChangeToolAction extends AbstractGraffAction {
    private final State state;

    public ChangeToolAction(State state, String imagePath, String toolName, String tooltipText) {
        this.state = state;
        putValue(SMALL_ICON, loadIcon(imagePath));
        putValue(NAME, toolName);
        putValue(SHORT_DESCRIPTION, tooltipText);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        EditorContext.getInstance().changeState(state);
    }

    public State getState() {
        return state;
    }
}
