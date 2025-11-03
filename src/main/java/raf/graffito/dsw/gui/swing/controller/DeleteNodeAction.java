package raf.graffito.dsw.gui.swing.controller;

import raf.graffito.dsw.gui.swing.model.GraffTreeElem;
import raf.graffito.dsw.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class DeleteNodeAction extends AbstractGraffAction {
    public DeleteNodeAction() {
        putValue(SMALL_ICON, loadIcon("/images/delete.png")); // Postavljanje ikonice
        putValue(NAME, "Obriši čvor"); // Ime akcije
        putValue(SHORT_DESCRIPTION, "Obriši ime izabranog čvora"); // Tooltip
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GraffTreeElem selected = MainFrame.getInstance().getGraffTree().getSelectedNode();
        MainFrame.getInstance().getGraffTree().deleteNode(selected);
    }
}
