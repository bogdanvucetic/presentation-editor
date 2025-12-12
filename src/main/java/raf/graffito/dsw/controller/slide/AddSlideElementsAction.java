package raf.graffito.dsw.controller.slide;

import raf.graffito.dsw.controller.AbstractGraffAction;

import java.awt.event.ActionEvent;

public class AddSlideElementsAction extends AbstractGraffAction {
    public AddSlideElementsAction() {
        putValue(SMALL_ICON, loadIcon("/images/slideAdd.png")); // Postavljanje ikonice
        putValue(NAME, "Dodaj na slajd"); // Ime akcije
        putValue(SHORT_DESCRIPTION, "Dodaj novi element na slajd"); // Tooltip
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
