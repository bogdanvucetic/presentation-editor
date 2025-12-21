package raf.graffito.dsw.controller.slide;

import raf.graffito.dsw.controller.AbstractGraffAction;
import raf.graffito.dsw.controller.state.SlideEditorSession;
import raf.graffito.dsw.controller.state.ZoomState;

import java.awt.event.ActionEvent;

public class ZoomSlideAction extends AbstractGraffAction {
    public ZoomSlideAction() {
        putValue(SMALL_ICON, loadIcon("/images/slideZoom.png")); // Postavljanje ikonice
        putValue(NAME, "Zumiraj"); // Ime akcije
        putValue(SHORT_DESCRIPTION, "Zumiraj slajd"); // Tooltip
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SlideEditorSession.getInstance().changeState(new ZoomState());
    }
}