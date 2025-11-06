package raf.graffito.dsw.controller;

import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.gui.swing.tree.GraffTreeElem;
import raf.graffito.dsw.gui.swing.view.MainFrame;
import raf.graffito.dsw.model.composite.GraffNode;
import raf.graffito.dsw.model.composite.GraffNodeComposite;
import raf.graffito.dsw.model.factory.GraffNodeFactory;
import raf.graffito.dsw.observer.MessageType;

import java.awt.event.ActionEvent;

public class CreateChildAction extends AbstractGraffAction {
    public CreateChildAction() {
        putValue(SMALL_ICON, loadIcon("/images/create.png")); // Postavljanje ikonice
        putValue(NAME, "Dodaj čvor"); // Ime akcije
        putValue(SHORT_DESCRIPTION, "Dodaj novi čvor"); // Tooltip
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GraffTreeElem selected = MainFrame.getInstance().getGraffTree().getSelectedNode();

        if (selected == null){
            ApplicationFramework.getInstance().getMessageGenerator().createMessage("Nijedan čvor nije izabran.", MessageType.WARNING);
            return;
        }

        if (!(selected.getGraffNode() instanceof GraffNodeComposite parent)) {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage("Izabrani čvor ne može da sadrži podčvorove.", MessageType.ERROR);
            return;
        }

        GraffNodeFactory factory = ApplicationFramework.getInstance().getGraffRepository().getFactoryFor(parent);
        GraffNode child = factory.createGraffNode(parent);

        parent.addChild(child);
        MainFrame.getInstance().getGraffTree().createChild(selected, child);
    }
}
