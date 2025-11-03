package raf.graffito.dsw.gui.swing.controller;

public class ActionManager {
    private ExitAction exitAction;
    private AboutUsAction aboutUsAction;
    private CreateNodeAction createNodeAction;
    private RenameNodeAction renameNodeAction;
    private DeleteNodeAction deleteNodeAction;

    public ActionManager() {
        this.exitAction = new ExitAction();
        this.aboutUsAction = new AboutUsAction();
        this.createNodeAction = new CreateNodeAction();
        this.renameNodeAction = new RenameNodeAction();
        this.deleteNodeAction = new DeleteNodeAction();
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
}
