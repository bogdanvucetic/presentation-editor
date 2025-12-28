package raf.graffito.dsw.controller.buttons;

import raf.graffito.dsw.controller.EditorContext;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class RedoAction extends AbstractGraffAction {
    public RedoAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
        putValue(NAME, "Vrati");
        putValue(SHORT_DESCRIPTION, "Ponovi poništenu izmenu elemenata na slajdu");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        EditorContext.getInstance().redo();
    }
}
