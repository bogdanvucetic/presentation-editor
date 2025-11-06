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

//        setDragEnabled(true);
//        setDropMode(DropMode.INSERT);
//        setTransferHandler(new GraffTransferHandler());

        return this;
    }

    @Override
    public void addChild(GraffTreeElem parent) {
        if (parent == null){
            ApplicationFramework.getInstance().getMessageGenerator().createMessage("Nijedan čvor nije izabran.", MessageType.WARNING);
            return;
        }

        if (!(parent.getGraffNode() instanceof GraffNodeComposite)) {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage("Izabrani čvor ne može da sadrži podčvorove.", MessageType.ERROR);
            return;
        }

        GraffNodeComposite parentModel = (GraffNodeComposite) parent.getGraffNode();
        ApplicationFramework.getInstance().getGraffRepository().createFactory(parentModel);
        GraffNodeFactory factory = ApplicationFramework.getInstance().getGraffRepository().getFactory();

        GraffNode child = factory.createGraffNode(parentModel);

        GraffTreeElem childElem = new GraffTreeElem(child);
        parent.add(childElem);
        parentModel.addChild(child);

        expandPath(getSelectionPath());
        SwingUtilities.updateComponentTreeUI(this);
    }

    @Override
    public void deleteNode(GraffTreeElem node) {
        if (node == null){
            ApplicationFramework.getInstance().getMessageGenerator().createMessage("Nijedan čvor nije izabran.", MessageType.WARNING);
            return;
        }

        if (node.getParent() == null){
            ApplicationFramework.getInstance().getMessageGenerator().createMessage("Workspace ne može da se obriše.", MessageType.ERROR);
            return;
        }

        GraffTreeElem parentElem = (GraffTreeElem)node.getParent();

        GraffNode child = node.getGraffNode();
        GraffNode parent = parentElem.getGraffNode();

        if (parent instanceof GraffNodeComposite) {
            ((GraffNodeComposite)parent).removeChild(child);
        }

        treeModel.removeNodeFromParent(node);

        SwingUtilities.updateComponentTreeUI(this);
    }

    @Override
    public void renameNode(GraffTreeElem node, String newName) {
        if (node == null){
            ApplicationFramework.getInstance().getMessageGenerator().createMessage("Nijedan čvor nije izabran.", MessageType.WARNING);
            return;
        }

        if (newName == null || newName.isEmpty()){
            ApplicationFramework.getInstance().getMessageGenerator().createMessage("Naziv ne može biti prazan.", MessageType.ERROR);
            return;
        }

        newName = newName.trim();

        GraffNode nodeToRename = node.getGraffNode();
        nodeToRename.setName(newName);

        SwingUtilities.updateComponentTreeUI(this);
    }

    @Override
    public void changeProjectTitle(GraffTreeElem node, String newTitle) {
        if (node == null){
            ApplicationFramework.getInstance().getMessageGenerator().createMessage("Nijedan čvor nije izabran.", MessageType.WARNING);
            return;
        }

        if (!(node.getGraffNode() instanceof Project)) {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage("Izabrani čvor nije projekat.", MessageType.ERROR);
            return;
        }

        if (newTitle == null || newTitle.isEmpty()){
            ApplicationFramework.getInstance().getMessageGenerator().createMessage("Naslov ne može biti prazan.", MessageType.ERROR);
            return;
        }

        newTitle = newTitle.trim();

        GraffNode projectToChangeTitle = node.getGraffNode();
        ((Project)projectToChangeTitle).setTitle(newTitle);

        SwingUtilities.updateComponentTreeUI(this);
    }

    @Override
    public GraffTreeElem getSelectedNode(){
        return (GraffTreeElem)getLastSelectedPathComponent();
    }
}
