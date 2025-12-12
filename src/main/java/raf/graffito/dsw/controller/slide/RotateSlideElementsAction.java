package raf.graffito.dsw.controller.slide;

import raf.graffito.dsw.controller.AbstractGraffAction;

import java.awt.event.ActionEvent;

public class RotateSlideElementsAction extends AbstractGraffAction {
    public RotateSlideElementsAction() {
        putValue(SMALL_ICON, loadIcon("/images/rotate.png")); // Postavljanje ikonice
        putValue(NAME, "Rotiraj"); // Ime akcije
        putValue(SHORT_DESCRIPTION, "Rotiraj elemente slajda"); // Tooltip
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
