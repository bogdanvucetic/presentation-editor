package raf.graffito.dsw.gui.swing.controller;

import raf.graffito.dsw.gui.swing.tree.GraffTreeElem;
import raf.graffito.dsw.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class CreateNodeAction extends AbstractGraffAction {
    public CreateNodeAction() {
        putValue(SMALL_ICON, loadIcon("/images/create.png")); // Postavljanje ikonice
        putValue(NAME, "Dodaj čvor"); // Ime akcije
        putValue(SHORT_DESCRIPTION, "Dodaj novi čvor"); // Tooltip
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GraffTreeElem selected = MainFrame.getInstance().getGraffTree().getSelectedNode();
        MainFrame.getInstance().getGraffTree().addChild(selected);
    }
}
