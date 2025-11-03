package raf.graffito.dsw.gui.swing.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ExitAction extends AbstractGraffAction {
    public ExitAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK)); // Ovo je prečica za izlaz
        putValue(SMALL_ICON, loadIcon("/images/exit.png")); // Postavljanje ikonice
        putValue(NAME, "Izađi"); // Ime akcije
        putValue(SHORT_DESCRIPTION, "Izađi iz aplikacije"); // Tooltip
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
