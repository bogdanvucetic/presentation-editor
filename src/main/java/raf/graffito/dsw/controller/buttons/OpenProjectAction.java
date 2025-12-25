package raf.graffito.dsw.controller.buttons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class OpenProjectAction extends AbstractGraffAction {
    public OpenProjectAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        putValue(NAME, "Otvori projekat");
        putValue(SHORT_DESCRIPTION, "Otvori sačuvan projekat");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
