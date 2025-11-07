package raf.graffito.dsw.controller;

import raf.graffito.dsw.gui.swing.tree.GraffTreeElem;
import raf.graffito.dsw.gui.swing.view.MainFrame;
import raf.graffito.dsw.gui.swing.view.MainPane;
import raf.graffito.dsw.model.composite.GraffNode;
import raf.graffito.dsw.model.composite.Presentation;
import raf.graffito.dsw.model.composite.Project;

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
            return;
        }

        executeAction(project);
    }

    public void executeAction(Project project) {
        List<Presentation> presentations = new ArrayList<>();

        for (GraffNode child : project.getChildren()){
            if (child instanceof Presentation){
                presentations.add((Presentation) child);
            }
        }

        MainFrame.getInstance().updateMainPane(new MainPane(presentations));
    }
}
