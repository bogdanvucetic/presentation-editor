package raf.graffito.dsw.gui.swing.tree;

import raf.graffito.dsw.model.composite.GraffNode;
import raf.graffito.dsw.model.composite.Workspace;

import javax.swing.*;

public interface GraffTree {
    JTree generateTree(Workspace workspace);
    void createChild(GraffTreeElem parent, GraffNode child);
    void deleteNode(GraffTreeElem nodeElem);
    void refresh();
    GraffTreeElem getSelectedNode();
}
