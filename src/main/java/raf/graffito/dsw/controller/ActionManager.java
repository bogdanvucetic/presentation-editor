package raf.graffito.dsw.controller;

public class ActionManager {
    private ExitAction exitAction;
    private AboutUsAction aboutUsAction;
    private CreateNodeAction createNodeAction;
    private RenameNodeAction renameNodeAction;
    private DeleteNodeAction deleteNodeAction;
    private ChangeProjectTitleAction changeProjectTitleAction;

    public ActionManager() {
        this.exitAction = new ExitAction();
        this.aboutUsAction = new AboutUsAction();
        this.createNodeAction = new CreateNodeAction();
        this.renameNodeAction = new RenameNodeAction();
        this.deleteNodeAction = new DeleteNodeAction();
        this.changeProjectTitleAction = new ChangeProjectTitleAction();
    }

    public ExitAction getExitAction() {
        return exitAction;
    }

    public AboutUsAction getAboutUsAction() {
        return aboutUsAction;
    }

    public CreateNodeAction getCreateNodeAction() {
        return createNodeAction;
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
}
