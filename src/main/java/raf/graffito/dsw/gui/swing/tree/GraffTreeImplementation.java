package raf.graffito.dsw.gui.swing.tree;

import raf.graffito.dsw.model.composite.*;
import raf.graffito.dsw.observer.model.ModelEvent;
import raf.graffito.dsw.observer.model.ModelEventType;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

public class GraffTreeImplementation extends JTree implements GraffTree {
    private DefaultTreeModel treeModel;

    @Override
    public JTree generateTree(Workspace workspace) {
        GraffTreeElem root = new GraffTreeElem(workspace);
        treeModel = new DefaultTreeModel(root);

        setModel(treeModel);
        setToggleClickCount(0);

        subscribeToAllNodes(root);

        return this;
    }

    private void subscribeToAllNodes(GraffTreeElem elem) {
        GraffNode node = elem.getGraffNode();
        node.addSubscriber(this);

        if (node instanceof GraffNodeComposite composite) {
            for (GraffNode child : composite.getChildren()) {
                subscribeToAllNodes(new GraffTreeElem(child));
            }
        }
    }

    @Override
    public GraffTreeElem getSelectedNode(){
        return (GraffTreeElem)getLastSelectedPathComponent();
    }

    @Override
    public void update(ModelEvent event) {
        ModelEventType type = event.getType();
        GraffNode node = event.getNode();

        switch (type) {
            case ADD_CHILD -> {
                GraffNode parent = node.getParent();
                GraffTreeElem parentElem = findTreeElemFor(parent);
                if (parentElem != null) {
                    addChildToTree(parentElem, node);
                }
            }
            case REMOVE_CHILD -> {
                GraffTreeElem nodeElem = findTreeElemFor(node);
                if (nodeElem != null) {
                    removeNodeFromTree(nodeElem);
                }
            }
            case RENAME -> {
                GraffTreeElem nodeElem = findTreeElemFor(node);
                if (nodeElem != null) {
                    treeModel.nodeChanged(nodeElem);
                }
            }
        }
    }

    private GraffTreeElem findTreeElemFor(GraffNode target) {
        return recursiveFindTreeElemFor(target, (GraffTreeElem) treeModel.getRoot());
    }

    private GraffTreeElem recursiveFindTreeElemFor(GraffNode target, GraffTreeElem current) {
        if (current.getGraffNode() == target) {
            return current;
        }

        for (int i = 0; i < current.getChildCount(); i++) {
            GraffTreeElem child = (GraffTreeElem) current.getChildAt(i);
            GraffTreeElem result = recursiveFindTreeElemFor(target, child);
            if (result != null) {
                return result;
            }
        }

        return null;
    }

    private void addChildToTree(GraffTreeElem parentElem, GraffNode child) {
        GraffTreeElem childElem = new GraffTreeElem(child);
        parentElem.add(childElem);

        child.addSubscriber(this);

        expandPath(getSelectionPath());
        refresh();
    }

    private void removeNodeFromTree(GraffTreeElem nodeElem) {
        unsubscribeFromNode(nodeElem.getGraffNode());
        treeModel.removeNodeFromParent(nodeElem);
    }

    private void unsubscribeFromNode(GraffNode node) {
        node.removeSubscriber(this);

        if (node instanceof GraffNodeComposite composite) {
            for (GraffNode child : composite.getChildren()) {
                unsubscribeFromNode(child);
            }
        }
    }

    private void refresh() {
        SwingUtilities.updateComponentTreeUI(this);
    }
}
