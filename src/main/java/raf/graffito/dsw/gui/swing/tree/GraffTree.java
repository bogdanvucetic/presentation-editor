package raf.graffito.dsw.gui.swing.tree;

import raf.graffito.dsw.model.composite.Workspace;
import raf.graffito.dsw.observer.model.ModelSubscriber;

import javax.swing.*;

public interface GraffTree extends ModelSubscriber {
    JTree generateTree(Workspace workspace);
    GraffTreeElem getSelectedNode();
}
