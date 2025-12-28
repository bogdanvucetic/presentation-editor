package raf.graffito.dsw.controller.buttons;

import raf.graffito.dsw.controller.EditorContext;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class UndoAction extends AbstractGraffAction {
    public UndoAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        putValue(NAME, "Poništi");
        putValue(SHORT_DESCRIPTION, "Poništi poslednju izmenu elemenata na slajdu");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        EditorContext.getInstance().undo();
    }
}
