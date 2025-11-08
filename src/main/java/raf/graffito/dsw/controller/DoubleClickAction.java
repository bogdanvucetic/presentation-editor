package raf.graffito.dsw.controller;

import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.gui.swing.tree.GraffTreeElem;
import raf.graffito.dsw.gui.swing.view.MainFrame;
import raf.graffito.dsw.gui.swing.view.ProjectView;
import raf.graffito.dsw.model.composite.GraffNode;
import raf.graffito.dsw.model.composite.Presentation;
import raf.graffito.dsw.model.composite.Project;
import raf.graffito.dsw.observer.message.MessageType;

import javax.swing.tree.TreePath;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class DoubleClickAction extends MouseAdapter {
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() != 2) {
            return;
        }

        TreePath path = MainFrame.getInstance().getTree().getPathForLocation(e.getX(), e.getY());

        if (path == null) {
            return;
        }

        GraffTreeElem elem = (GraffTreeElem) path.getLastPathComponent();

        if (!(elem.getGraffNode() instanceof Project project)){
            ApplicationFramework.getInstance().getMessageGenerator().createMessage("Otvaranje tabova je moguće samo za projekte.", MessageType.INFO);
            return;
        }

        List<Presentation> presentations = new ArrayList<>();

        for (GraffNode child : project.getChildren()){
            if (child instanceof Presentation){
                presentations.add((Presentation) child);
            }
        }

        MainFrame.getInstance().updateMainPanel(new ProjectView(presentations, project));
    }
}
