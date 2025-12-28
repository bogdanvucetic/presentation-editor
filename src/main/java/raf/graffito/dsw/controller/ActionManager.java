package raf.graffito.dsw.controller;

import raf.graffito.dsw.controller.actions.*;
import raf.graffito.dsw.controller.actions.edit.*;
import raf.graffito.dsw.controller.actions.file.*;
import raf.graffito.dsw.controller.state.*;

public class ActionManager {
    private ExitAction exitAction;
    private AboutUsAction aboutUsAction;
    private CreateChildAction createChildAction;
    private RenameNodeAction renameNodeAction;
    private DeleteNodeAction deleteNodeAction;
    private ChangeProjectTitleAction changeProjectTitleAction;
    private LoadImageAction loadImageAction;
    private OpenProjectAction openProjectAction;
    private SaveProjectAction saveProjectAction;
    private SaveProjectAsAction saveProjectAsAction;
    private SaveAsTemplateAction saveAsTemplateAction;
    private UndoAction undoAction;
    private RedoAction redoAction;
    private RotateLeftAction rotateLeftAction;
    private RotateRightAction rotateRightAction;

    private ChangeToolAction addSlideElementsAction;
    private ChangeToolAction selectSlideElementsAction;
    private ChangeToolAction resizeSlideElementsAction;
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
        this.loadImageAction = new LoadImageAction();
        this.openProjectAction = new OpenProjectAction();
        this.saveProjectAction = new SaveProjectAction();
        this.saveProjectAsAction = new SaveProjectAsAction();
        this.saveAsTemplateAction = new SaveAsTemplateAction();
        this.undoAction = new UndoAction();
        this.redoAction = new RedoAction();
        this.rotateLeftAction = new RotateLeftAction();
        this.rotateRightAction = new RotateRightAction();

        this.addSlideElementsAction = new ChangeToolAction(new AddState(), "/images/slideAdd.png", "Dodaj na slajd", "Dodaj novi element na slajd");
        this.selectSlideElementsAction = new ChangeToolAction(new SelectState(), "/images/slideSelect.png", "Izaberi elemente", "Izaberi elemente na slajdu");
        this.resizeSlideElementsAction = new ChangeToolAction(new ResizeState(), "/images/slideResize.png", "Promeni veličinu elemenata", "Promeni veličinu elemenata na slajdu");
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

    public LoadImageAction getLoadImageAction() {
        return loadImageAction;
    }

    public OpenProjectAction getOpenProjectAction() {
        return openProjectAction;
    }

    public SaveProjectAction getSaveProjectAction() {
        return saveProjectAction;
    }

    public SaveProjectAsAction getSaveProjectAsAction() {
        return saveProjectAsAction;
    }

    public SaveAsTemplateAction getSaveAsTemplateAction() {
        return saveAsTemplateAction;
    }

    public UndoAction getUndoAction() {
        return undoAction;
    }

    public RedoAction getRedoAction() {
        return redoAction;
    }

    public RotateLeftAction getRotateLeftAction() {
        return rotateLeftAction;
    }

    public RotateRightAction getRotateRightAction() {
        return rotateRightAction;
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
