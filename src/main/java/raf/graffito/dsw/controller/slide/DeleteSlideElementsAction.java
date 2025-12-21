package raf.graffito.dsw.controller.slide;

import raf.graffito.dsw.controller.AbstractGraffAction;
import raf.graffito.dsw.controller.state.DeleteState;
import raf.graffito.dsw.controller.state.SlideEditorSession;

import java.awt.event.ActionEvent;

public class DeleteSlideElementsAction extends AbstractGraffAction {
    public DeleteSlideElementsAction() {
        putValue(SMALL_ICON, loadIcon("/images/slideDelete.png")); // Postavljanje ikonice
        putValue(NAME, "Оbriši elemente"); // Ime akcije
        putValue(SHORT_DESCRIPTION, "Obriši elemente sa slajda"); // Tooltip
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SlideEditorSession.getInstance().changeState(new DeleteState());
    }
}
