package raf.graffito.dsw.controller.slide;

import raf.graffito.dsw.controller.AbstractGraffAction;
import raf.graffito.dsw.controller.state.SelectState;
import raf.graffito.dsw.controller.state.SlideEditorSession;

import java.awt.event.ActionEvent;

public class SelectSlideElementsAction extends AbstractGraffAction {
    public SelectSlideElementsAction() {
        putValue(SMALL_ICON, loadIcon("/images/slideSelect.png")); // Postavljanje ikonice
        putValue(NAME, "Izaberi elemente"); // Ime akcije
        putValue(SHORT_DESCRIPTION, "Izaberi elemente na slajdu"); // Tooltip
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SlideEditorSession.getInstance().changeState(new SelectState());
    }
}