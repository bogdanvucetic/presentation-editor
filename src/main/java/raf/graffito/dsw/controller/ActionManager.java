package raf.graffito.dsw.controller;

import raf.graffito.dsw.controller.slide.*;
import raf.graffito.dsw.controller.tree.ChangeProjectTitleAction;
import raf.graffito.dsw.controller.tree.CreateChildAction;
import raf.graffito.dsw.controller.tree.DeleteNodeAction;
import raf.graffito.dsw.controller.tree.RenameNodeAction;

public class ActionManager {
    private ExitAction exitAction;
    private AboutUsAction aboutUsAction;
    private CreateChildAction createChildAction;
    private RenameNodeAction renameNodeAction;
    private DeleteNodeAction deleteNodeAction;
    private ChangeProjectTitleAction changeProjectTitleAction;

    private AddSlideElementsAction addSlideElementsAction;
    private SelectSlideElementsAction selectSlideElementsAction;
    private ResizeSlideElementsAction resizeSlideElementsAction;
    private RotateSlideElementsAction rotateSlideElementsAction;
    private MoveSlideElementsAction moveSlideElementsAction;
    private ZoomSlideAction zoomSlideAction;
    private DeleteSlideElementsAction deleteSlideElementsAction;

    public ActionManager() {
        this.exitAction = new ExitAction();
        this.aboutUsAction = new AboutUsAction();
        this.createChildAction = new CreateChildAction();
        this.renameNodeAction = new RenameNodeAction();
        this.deleteNodeAction = new DeleteNodeAction();
        this.changeProjectTitleAction = new ChangeProjectTitleAction();

        this.addSlideElementsAction = new AddSlideElementsAction();
        this.selectSlideElementsAction = new SelectSlideElementsAction();
        this.resizeSlideElementsAction = new ResizeSlideElementsAction();
        this.rotateSlideElementsAction = new RotateSlideElementsAction();
        this.moveSlideElementsAction = new MoveSlideElementsAction();
        this.zoomSlideAction = new ZoomSlideAction();
        this.deleteSlideElementsAction = new DeleteSlideElementsAction();
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

    public AddSlideElementsAction getAddSlideElementsAction() {
        return addSlideElementsAction;
    }

    public SelectSlideElementsAction getSelectSlideElementsAction() {
        return selectSlideElementsAction;
    }

    public ResizeSlideElementsAction getResizeSlideElementsAction() {
        return resizeSlideElementsAction;
    }

    public RotateSlideElementsAction getRotateSlideElementsAction() {
        return rotateSlideElementsAction;
    }

    public MoveSlideElementsAction getMoveSlideElementsAction() {
        return moveSlideElementsAction;
    }

    public ZoomSlideAction getZoomSlideAction() {
        return zoomSlideAction;
    }

    public DeleteSlideElementsAction getDeleteSlideElementsAction() {
        return deleteSlideElementsAction;
    }
}
