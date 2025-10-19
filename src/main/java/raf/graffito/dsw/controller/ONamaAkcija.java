package raf.graffito.dsw.controller;

import raf.graffito.dsw.gui.swing.OnamaProzor;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ONamaAkcija extends AbstractGraffAction {

    public ONamaAkcija() {

        putValue(SMALL_ICON, loadIcon("/images/oNama.png")); // Postavljanje ikonice
        putValue(NAME, "O nama"); // Ime akcije
        putValue(SHORT_DESCRIPTION, "Prikazi informacije o timu"); // Tooltip

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        new OnamaProzor().setVisible(true);

    }

}
