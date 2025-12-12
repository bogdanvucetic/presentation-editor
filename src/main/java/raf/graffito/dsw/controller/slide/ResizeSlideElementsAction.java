package raf.graffito.dsw.controller.slide;

import raf.graffito.dsw.controller.AbstractGraffAction;

import java.awt.event.ActionEvent;

public class ResizeSlideElementsAction extends AbstractGraffAction {
    public ResizeSlideElementsAction() {
        putValue(SMALL_ICON, loadIcon("/images/slideResize.png")); // Postavljanje ikonice
        putValue(NAME, "Promeni veličinu elemenata"); // Ime akcije
        putValue(SHORT_DESCRIPTION, "Promeni veličinu elemenata na slajdu"); // Tooltip
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}