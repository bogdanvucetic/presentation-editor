package raf.graffito.dsw.controller;

import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.gui.swing.tree.GraffTreeElem;
import raf.graffito.dsw.gui.swing.view.MainFrame;
import raf.graffito.dsw.observer.MessageType;

import javax.swing.*;
import java.awt.event.ActionEvent;

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

        input = input.trim();

        if (input.isEmpty()){
            ApplicationFramework.getInstance().getMessageGenerator().createMessage("Naziv ne može biti prazan.", MessageType.ERROR);
            return;
        }

        selected.getGraffNode().setName(input);
        MainFrame.getInstance().getGraffTree().refresh();
    }
}
