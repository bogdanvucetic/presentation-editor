package raf.graffito.dsw.gui.swing.model;

import raf.graffito.dsw.model.composite.Workspace;

import javax.swing.tree.DefaultTreeModel;

public class GraffTreeImplementation implements GraffTree {
    private GraffTreeView treeView;
    private DefaultTreeModel treeModel;

    @Override
    public GraffTreeView generateTree(Workspace workspace) {
        GraffTreeElem root = new GraffTreeElem(workspace);
        treeModel = new DefaultTreeModel(root);
        treeView = new GraffTreeView(treeModel);
        return treeView;
    }

    @Override
    public void createChild(GraffTreeElem parent) {

    }

    @Override
    public void deleteNode(GraffTreeElem node) {

    }

    @Override
    public void renameNode(GraffTreeElem node, String newName) {

    }
}
