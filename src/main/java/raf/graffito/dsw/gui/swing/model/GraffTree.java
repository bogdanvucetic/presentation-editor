package raf.graffito.dsw.gui.swing.model;

import raf.graffito.dsw.model.composite.GraffNode;
import raf.graffito.dsw.model.composite.Workspace;

import javax.swing.*;

public interface GraffTree {
    GraffTreeView generateTree(Workspace workspace);
    void addChild(GraffTreeElem parent);
    void deleteNode(GraffTreeElem node);
    void renameNode(GraffTreeElem node, String newName);
    void changeProjectTitle(GraffTreeElem node, String newTitle);
    GraffTreeElem getSelectedNode();
}
