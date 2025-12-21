package raf.graffito.dsw.controller;

import raf.graffito.dsw.controller.state.*;
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

    private ChangeToolAction addSlideElementsAction;
    private ChangeToolAction selectSlideElementsAction;
    private ChangeToolAction resizeSlideElementsAction;
    private ChangeToolAction rotateSlideElementsAction;
    private ChangeToolAction moveSlideElementsAction;
    private ChangeToolAction zoomSlideAction;
    private ChangeToolAction deleteSlideElementsAction;

    public ActionManager() {
        this.exitAction = new ExitAction();
        this.aboutUsAction = new AboutUsAction();
        this.createChildAction = new CreateChildAction();
        this.renameNodeAction = new RenameNodeAction();
        this.deleteNodeAction = new DeleteNodeAction();
        this.changeProjectTitleAction = new ChangeProjectTitleAction();

        this.addSlideElementsAction = new ChangeToolAction(new AddState(), "/images/slideAdd.png", "Dodaj na slajd", "Dodaj novi element na slajd");
        this.selectSlideElementsAction = new ChangeToolAction(new SelectState(), "/images/slideSelect.png", "Izaberi elemente", "Izaberi elemente na slajdu");
        this.resizeSlideElementsAction = new ChangeToolAction(new ResizeState(), "/images/slideResize.png", "Promeni veličinu elemenata", "Promeni veličinu elemenata na slajdu");
        this.rotateSlideElementsAction = new ChangeToolAction(new RotateState(), "/images/rotate.png", "Rotiraj", "Rotiraj elemente slajda");
        this.moveSlideElementsAction = new ChangeToolAction(new MoveState(), "/images/slideMove.png", "Pomeri elemente", "Pomeri elemente na slajdu");
        this.zoomSlideAction = new ChangeToolAction(new ZoomState(), "/images/slideZoom.png", "Zumiraj", "Zumiraj slajd");
        this.deleteSlideElementsAction = new ChangeToolAction(new DeleteState(), "/images/slideDelete.png", "Obriši elemente", "Obriši elemente sa slajda");
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

    public ChangeToolAction getAddSlideElementsAction() {
        return addSlideElementsAction;
    }

    public ChangeToolAction getSelectSlideElementsAction() {
        return selectSlideElementsAction;
    }

    public ChangeToolAction getResizeSlideElementsAction() {
        return resizeSlideElementsAction;
    }

    public ChangeToolAction getRotateSlideElementsAction() {
        return rotateSlideElementsAction;
    }

    public ChangeToolAction getMoveSlideElementsAction() {
        return moveSlideElementsAction;
    }

    public ChangeToolAction getZoomSlideAction() {
        return zoomSlideAction;
    }

    public ChangeToolAction getDeleteSlideElementsAction() {
        return deleteSlideElementsAction;
    }
}
