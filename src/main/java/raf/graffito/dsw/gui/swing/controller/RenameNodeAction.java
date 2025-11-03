package raf.graffito.dsw.gui.swing.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class RenameNodeAction extends AbstractGraffAction {
    public RenameNodeAction() {
        putValue(SMALL_ICON, loadIcon("/images/rename.png")); // Postavljanje ikonice
        putValue(NAME, "Promeni ime čvora"); // Ime akcije
        putValue(SHORT_DESCRIPTION, "Promeni ime izabranog čvora"); // Tooltip
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
