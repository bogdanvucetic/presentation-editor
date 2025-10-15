package raf.graffito.dsw.controller;

public class ActionManager {
    private ExitAction exitAction;

    public ActionManager() {
        this.exitAction = new ExitAction();
    }

    public ExitAction getExitAction() {
        return exitAction;
    }
}
