package raf.graffito.dsw.controller.actions.edit;

import raf.graffito.dsw.controller.actions.AbstractGraffAction;
import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.gui.swing.tree.GraffTreeElem;
import raf.graffito.dsw.gui.swing.frames.MainFrame;
import raf.graffito.dsw.model.composite.Project;
import raf.graffito.dsw.services.events.message.MessageType;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ChangeProjectTitleAction extends AbstractGraffAction {
    public ChangeProjectTitleAction() {
        putValue(SMALL_ICON, loadIcon("/images/changeTitle.png")); // Postavljanje ikonice
        putValue(NAME, "Promeni naslov"); // Ime akcije
        putValue(SHORT_DESCRIPTION, "Promeni naslov projekta"); // Tooltip
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GraffTreeElem selected = MainFrame.getInstance().getGraffTree().getSelectedNode();

        if (selected == null){
            ApplicationFramework.getInstance().getMessageGenerator().createMessage("Nijedan čvor nije izabran.", MessageType.WARNING);
            return;
        }

        if (!(selected.getGraffNode() instanceof Project)) {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage("Izabrani čvor nije projekat.", MessageType.ERROR);
            return;
        }

        String input = JOptionPane.showInputDialog(MainFrame.getInstance(), "Unesi novi naslov:", ((Project)selected.getGraffNode()).getTitle());

        // kada korisnik klikne "cancel"
        if (input == null) {
            return;
        }

        input = input.trim();

        if (input.isEmpty()){
            ApplicationFramework.getInstance().getMessageGenerator().createMessage("Naslov ne može biti prazan.", MessageType.ERROR);
            return;
        }

        ((Project) selected.getGraffNode()).setTitle(input);
    }
}
