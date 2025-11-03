package raf.graffito.dsw.gui.swing.model;

import raf.graffito.dsw.model.composite.GraffNode;

import javax.swing.*;
import javax.swing.tree.TreeNode;

public class GraffTree extends JTree {
    private GraffTreeNode root;

    public GraffTree(GraffTreeNode root) {
        super(root);
        this.root = root;
        // todo
        //  addTreeSelectionListener()
        //  setCellEditor()
        //  setEditable()?
    }

    public GraffTreeNode getRoot() {
        return root;
    }
}
