package raf.graffito.dsw.gui.swing.tree;

import raf.graffito.dsw.model.composite.*;
import raf.graffito.dsw.observer.model.ModelUpdate;
import raf.graffito.dsw.observer.model.UpdateType;

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

        subscribeToAllNodes(root.getGraffNode());

        return this;
    }

    private void subscribeToAllNodes(GraffNode node) {
        node.addSubscriber(this);

        if (node instanceof GraffNodeComposite composite) {
            for (GraffNode child : composite.getChildren()) {
                subscribeToAllNodes(child);
            }
        }
    }

    @Override
    public GraffTreeElem getSelectedNode(){
        return (GraffTreeElem)getLastSelectedPathComponent();
    }

    @Override
    public void update(ModelUpdate event) {
        UpdateType type = event.getType();
        GraffNode node = event.getNode();

        switch (type) {
            case CHILD_ADDED -> {
                GraffTreeElem parentElem = findTreeElemFor(node.getParent());

                if (parentElem != null) {
                    addChildToTree(parentElem, node);
                }
            }
            case CHILD_REMOVED -> {
                GraffTreeElem nodeElem = findTreeElemFor(node);

                if (nodeElem != null) {
                    removeNodeFromTree(nodeElem);
                }
            }
            case RENAMED -> {
                GraffTreeElem nodeElem = findTreeElemFor(node);

                if (nodeElem != null) {
                    treeModel.nodeChanged(nodeElem);
                }
            }
            case PROJECT_OPENED -> {
                GraffTreeElem parentElem = findTreeElemFor(node.getParent());

                if (parentElem == null) {
                    return;
                }

                addMultipleChildrenToTree(findTreeElemFor(node.getParent()), node);
                subscribeToAllNodes(node);

                treeModel.reload(parentElem);
            }
            case PROPERTY_UPDATED -> { }
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

    private void addMultipleChildrenToTree(GraffTreeElem parentElem, GraffNode node) {
        GraffTreeElem elem = new GraffTreeElem(node);
        parentElem.add(elem);

        if (node instanceof GraffNodeComposite composite) {
            for (GraffNode child : composite.getChildren()) {
                addMultipleChildrenToTree(elem, child);
            }
        }
    }
}
