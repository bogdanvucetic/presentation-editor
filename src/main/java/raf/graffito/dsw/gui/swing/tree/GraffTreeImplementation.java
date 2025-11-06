package raf.graffito.dsw.gui.swing.tree;

import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.model.composite.*;
import raf.graffito.dsw.model.factory.GraffNodeFactory;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

public class GraffTreeImplementation extends JTree implements GraffTree {
    private DefaultTreeModel treeModel;

    @Override
    public JTree generateTree(Workspace workspace) {
        GraffTreeElem root = new GraffTreeElem(workspace);
        treeModel = new DefaultTreeModel(root);

        setModel(treeModel);

        return this;
    }

    @Override
    public void addChildToView(GraffTreeElem parentElem, GraffNode child) {
        GraffTreeElem childElem = new GraffTreeElem(child);
        parentElem.add(childElem);

        expandPath(getSelectionPath());
        refresh();
    }

    @Override
    public void removeNodeFromView(GraffTreeElem nodeElem) {
        treeModel.removeNodeFromParent(nodeElem);
    }

    public void refresh() {
        SwingUtilities.updateComponentTreeUI(this);
    }

    @Override
    public GraffTreeElem getSelectedNode(){
        return (GraffTreeElem)getLastSelectedPathComponent();
    }
}
