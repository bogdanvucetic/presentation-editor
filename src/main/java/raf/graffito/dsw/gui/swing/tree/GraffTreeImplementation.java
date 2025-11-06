package raf.graffito.dsw.gui.swing.tree;

import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.model.composite.*;
import raf.graffito.dsw.model.factory.GraffNodeFactory;
import raf.graffito.dsw.observer.MessageType;
import raf.graffito.dsw.utils.ChildTypePicker;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.util.Random;

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
    public void createChild(GraffTreeElem parentElem) {
        GraffNodeComposite parent = (GraffNodeComposite) parentElem.getGraffNode();

        ApplicationFramework.getInstance().getGraffRepository().createFactory(parent);
        GraffNodeFactory factory = ApplicationFramework.getInstance().getGraffRepository().getFactory();

        GraffNode child = factory.createGraffNode(parent);

        parent.addChild(child);
        parentElem.add(new GraffTreeElem(child));

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
        newName = newName.trim();
        GraffNode nodeToRename = node.getGraffNode();

        nodeToRename.setName(newName);

        SwingUtilities.updateComponentTreeUI(this);
    }

    @Override
    public void changeProjectTitle(GraffTreeElem node, String newTitle) {
        newTitle = newTitle.trim();
        GraffNode project = node.getGraffNode();

        ((Project)project).setTitle(newTitle);

        SwingUtilities.updateComponentTreeUI(this);
    }

    @Override
    public GraffTreeElem getSelectedNode(){
        return (GraffTreeElem)getLastSelectedPathComponent();
    }
}
