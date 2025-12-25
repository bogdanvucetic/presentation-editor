package raf.graffito.dsw.controller.buttons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class SaveProjectAction extends AbstractGraffAction {
    public SaveProjectAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        putValue(NAME, "Sačuvaj projekat");
        putValue(SHORT_DESCRIPTION, "Sačuvaj projekat na trenutnoj putanji");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
