package raf.graffito.dsw.gui.swing.tree;

import raf.graffito.dsw.model.composite.Workspace;
import raf.graffito.dsw.services.events.model.UpdateSubscriber;

import javax.swing.*;

public interface GraffTree extends UpdateSubscriber {
    JTree generateTree(Workspace workspace);
    GraffTreeElem getSelectedNode();
}
