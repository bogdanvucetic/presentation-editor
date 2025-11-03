package raf.graffito.dsw.gui.swing.model;

import raf.graffito.dsw.model.composite.*;
import raf.graffito.dsw.utils.ChildTypePicker;

import javax.swing.*;
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
    public void addChild(GraffTreeElem parent) {
        if (!(parent.getGraffNode() instanceof GraffNodeComposite))
            return;

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
        if (node == null || node.getParent() == null){
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

    }

    @Override
    public GraffTreeElem getSelectedNode(){
        return (GraffTreeElem) treeView.getLastSelectedPathComponent();
    }

    // todo: imam utisak da cela ova metoda može mnogo lepše da se napiše
    public GraffNode createChild(GraffNode parent) {
        int index = ((GraffNodeComposite) parent).getNumberOfChildren() + 1;

        if (parent instanceof Workspace) {
            return new Project(parent, "Project " + index);
        } else if (parent instanceof Project) {
            String type = ChildTypePicker.pickProjectChild();

            if (type == null){
                return null;
            }

            if (type.equals("Presentation")) {
                index = 1;

                for (GraffNode child : ((Project) parent).getChildren()){
                    if (child instanceof Presentation) {
                        index++;
                    }
                }

                return new Presentation(parent, "Presentation " + index);
            }

            else if (type.equals("Slide")) {
                index = 1;

                for (GraffNode child : ((Project) parent).getChildren()){
                    if (child instanceof Slide) {
                        index++;
                    }
                }

                return new Slide(parent, "Slide " + index);
            }

        } else if (parent instanceof Presentation) {
            return new Slide(parent, "Slide " + index);
        }

        return null;
    }
}
