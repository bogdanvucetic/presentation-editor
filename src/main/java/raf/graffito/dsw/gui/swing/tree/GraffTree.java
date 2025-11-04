package raf.graffito.dsw.gui.swing.tree;

import raf.graffito.dsw.model.composite.Workspace;

public interface GraffTree {
    GraffTreeView generateTree(Workspace workspace);
    void addChild(GraffTreeElem parent);
    void deleteNode(GraffTreeElem node);
    void renameNode(GraffTreeElem node, String newName);
    void changeProjectTitle(GraffTreeElem node, String newTitle);
    GraffTreeElem getSelectedNode();
}
