package raf.graffito.dsw.controller;

public class ActionManager {
    private ExitAction exitAction;
    private AboutUsAction aboutUsAction;
    private CreateChildAction createChildAction;
    private RenameNodeAction renameNodeAction;
    private DeleteNodeAction deleteNodeAction;
    private ChangeProjectTitleAction changeProjectTitleAction;
    private DoubleClickAction doubleClickAction;

    public ActionManager() {
        this.exitAction = new ExitAction();
        this.aboutUsAction = new AboutUsAction();
        this.createChildAction = new CreateChildAction();
        this.renameNodeAction = new RenameNodeAction();
        this.deleteNodeAction = new DeleteNodeAction();
        this.changeProjectTitleAction = new ChangeProjectTitleAction();
        this.doubleClickAction = new DoubleClickAction();
    }

    public ExitAction getExitAction() {
        return exitAction;
    }

    public AboutUsAction getAboutUsAction() {
        return aboutUsAction;
    }

    public CreateChildAction getCreateChildAction() {
        return createChildAction;
    }

    public RenameNodeAction getRenameNodeAction() {
        return renameNodeAction;
    }

    public DeleteNodeAction getDeleteNodeAction() {
        return deleteNodeAction;
    }

    public ChangeProjectTitleAction getChangeProjectTitleAction() {
        return changeProjectTitleAction;
    }

    public DoubleClickAction getDoubleClickAction() {
        return doubleClickAction;
    }
}
