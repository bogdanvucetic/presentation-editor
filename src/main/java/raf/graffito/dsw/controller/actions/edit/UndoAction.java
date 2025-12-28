package raf.graffito.dsw.controller.actions.edit;

import raf.graffito.dsw.controller.EditorContext;
import raf.graffito.dsw.controller.actions.AbstractGraffAction;

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
