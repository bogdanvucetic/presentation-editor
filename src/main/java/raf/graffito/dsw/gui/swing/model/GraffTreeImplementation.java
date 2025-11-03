package raf.graffito.dsw.gui.swing.model;

import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.model.composite.*;
import raf.graffito.dsw.model.observer.MessageType;
import raf.graffito.dsw.utils.ChildTypePicker;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.util.Random;

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
    public void addChild(GraffTreeElem parent) {
        if (parent == null){
            ApplicationFramework.getInstance().getMessageGenerator().createMessage("Nijedan čvor nije izabran.", MessageType.WARNING);
            return;
        }

        if (!(parent.getGraffNode() instanceof GraffNodeComposite)) {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage("Izabrani čvor ne može da sadrži podčvorove.", MessageType.ERROR);
            return;
        }

        GraffNode child = createChild(parent.getGraffNode());
        GraffTreeElem childElem = new GraffTreeElem(child);
        parent.add(childElem);
        ((GraffNodeComposite)parent.getGraffNode()).addChild(child);

        // todo: vrv. bi moglo da se doda neko objašnjenje korisniku zašto
        //  odmah biva promptovan i da izabere kada napravi projekat
        if (child instanceof Project) {
            addChild(childElem);
        }

        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
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

        SwingUtilities.updateComponentTreeUI(treeView);
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

        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public GraffTreeElem getSelectedNode(){
        return (GraffTreeElem) treeView.getLastSelectedPathComponent();
    }

    // todo: imam utisak da cela ova metoda može mnogo lepše da se napiše
    public GraffNode createChild(GraffNode parent) {
        if (parent instanceof Workspace) {
            return new Project(parent, "Project " + new Random().nextInt(100));
        } else if (parent instanceof Project) {
            String type = ChildTypePicker.pickProjectChild();

            if (type == null){
                return null;
            }

            if (type.equals("Presentation")) {
                return new Presentation(parent, "Presentation " + new Random().nextInt(100));
            } else if (type.equals("Slide")) {
                return new Slide(parent, "Slide " + new Random().nextInt(100));
            }

        } else if (parent instanceof Presentation) {
            return new Slide(parent, "Slide " + new Random().nextInt(100));
        }

        return null;
    }
}
