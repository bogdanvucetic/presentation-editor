package raf.graffito.dsw.controller.slide;

import raf.graffito.dsw.controller.AbstractGraffAction;

import java.awt.event.ActionEvent;

public class MoveSlideElementsAction extends AbstractGraffAction {
    public MoveSlideElementsAction() {
        putValue(SMALL_ICON, loadIcon("/images/slideMove.png")); // Postavljanje ikonice
        putValue(NAME, "Pomeri elemente"); // Ime akcije
        putValue(SHORT_DESCRIPTION, "Pomeri elemente na slajdu"); // Tooltip
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}