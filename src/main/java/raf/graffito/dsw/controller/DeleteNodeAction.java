package raf.graffito.dsw.controller;

import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.gui.swing.tree.GraffTreeElem;
import raf.graffito.dsw.gui.swing.view.MainFrame;
import raf.graffito.dsw.model.composite.GraffNodeComposite;
import raf.graffito.dsw.observer.message.MessageType;

import java.awt.event.ActionEvent;

public class DeleteNodeAction extends AbstractGraffAction {
    public DeleteNodeAction() {
        putValue(SMALL_ICON, loadIcon("/images/delete.png")); // Postavljanje ikonice
        putValue(NAME, "Obriši čvor"); // Ime akcije
        putValue(SHORT_DESCRIPTION, "Obriši izabrani čvor"); // Tooltip
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GraffTreeElem selected = MainFrame.getInstance().getGraffTree().getSelectedNode();

        if (selected == null){
            ApplicationFramework.getInstance().getMessageGenerator().createMessage("Nijedan čvor nije izabran.", MessageType.WARNING);
            return;
        }

        GraffNodeComposite parent = (GraffNodeComposite) selected.getGraffNode().getParent();

        if (parent == null){
            ApplicationFramework.getInstance().getMessageGenerator().createMessage("Workspace ne može da se obriše.", MessageType.ERROR);
            return;
        }
        
        try {
            parent.removeChild(selected.getGraffNode());
            MainFrame.getInstance().getGraffTree().removeNodeFromView(selected);
        } catch (Exception ex) {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(ex.getMessage(), MessageType.ERROR);
        }
    }
}
