package raf.graffito.dsw.gui.swing.controller;

import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.gui.swing.model.GraffTreeElem;
import raf.graffito.dsw.gui.swing.view.MainFrame;
import raf.graffito.dsw.model.observer.MessageType;

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
        GraffTreeElem selected = MainFrame.getInstance().getGraffTree().getSelectedNode();

        if (selected == null){
            ApplicationFramework.getInstance().getMessageGenerator().createMessage("Nijedan čvor nije izabran.", MessageType.WARNING);
            return;
        }

        String input = JOptionPane.showInputDialog("Unesi novo ime:", selected.getGraffNode().getName());

        // kada korisnik klikne "cancel"
        if (input == null)
            return;

        MainFrame.getInstance().getGraffTree().renameNode(selected, input);
    }
}
