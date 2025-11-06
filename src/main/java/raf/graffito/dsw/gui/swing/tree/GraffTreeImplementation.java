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
    public void createChild(GraffTreeElem parentElem, GraffNode child) {
        GraffTreeElem childElem = new GraffTreeElem(child);
        parentElem.add(childElem);

        expandPath(getSelectionPath());
        SwingUtilities.updateComponentTreeUI(this);
    }

    @Override
    public void deleteNode(GraffTreeElem node) {
        GraffNodeComposite parent = (GraffNodeComposite) node.getGraffNode().getParent();

        parent.removeChild(node.getGraffNode());
        treeModel.removeNodeFromParent(node);

        SwingUtilities.updateComponentTreeUI(this);
    }

    @Override
    public void renameNode(GraffTreeElem node, String newName) {
        GraffNode nodeToRename = node.getGraffNode();

        nodeToRename.setName(newName);

        SwingUtilities.updateComponentTreeUI(this);
    }

    @Override
    public void changeProjectTitle(GraffTreeElem node, String newTitle) {
        GraffNode project = node.getGraffNode();

        ((Project)project).setTitle(newTitle);

        SwingUtilities.updateComponentTreeUI(this);
    }

    @Override
    public GraffTreeElem getSelectedNode(){
        return (GraffTreeElem)getLastSelectedPathComponent();
    }
}
