package raf.graffito.dsw.gui.swing.tree;

import raf.graffito.dsw.model.composite.Workspace;

import javax.swing.*;

public interface GraffTree {
    JTree generateTree(Workspace workspace);
    void createChild(GraffTreeElem parent);
    void deleteNode(GraffTreeElem node);
    void renameNode(GraffTreeElem node, String newName);
    void changeProjectTitle(GraffTreeElem node, String newTitle);
    GraffTreeElem getSelectedNode();
}
