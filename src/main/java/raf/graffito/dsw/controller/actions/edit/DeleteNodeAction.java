package raf.graffito.dsw.controller.actions.edit;

import raf.graffito.dsw.controller.actions.AbstractGraffAction;
import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.gui.swing.tree.GraffTreeElem;
import raf.graffito.dsw.gui.swing.frames.MainFrame;
import raf.graffito.dsw.model.composite.GraffNodeComposite;
import raf.graffito.dsw.model.composite.Project;
import raf.graffito.dsw.services.events.message.MessageType;

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
            if (selected.getGraffNode() instanceof Project) {
                ApplicationFramework.getInstance().getGraffRepository().getDecoratorRepository().removeColorDecoratorFor(selected.getGraffNode());
            }

            parent.removeChild(selected.getGraffNode());
        } catch (Exception ex) {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(ex.getMessage(), MessageType.ERROR);
        }
    }
}
