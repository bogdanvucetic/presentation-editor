package raf.graffito.dsw.controller;

public class ActionManager {
    private ExitAction exitAction;
    private AboutUsAction aboutUsAction;

    public ActionManager() {
        this.exitAction = new ExitAction();
        this.aboutUsAction = new AboutUsAction();
    }

    public ExitAction getExitAction() {
        return exitAction;
    }

    public AboutUsAction getAboutUsAction() {
        return aboutUsAction;
    }

}
