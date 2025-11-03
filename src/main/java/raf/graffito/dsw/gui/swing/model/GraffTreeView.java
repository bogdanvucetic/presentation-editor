package raf.graffito.dsw.gui.swing.model;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

public class GraffTreeView extends JTree {
    public GraffTreeView(DefaultTreeModel treeModel) {
        setModel(treeModel);
        setEditable(true);
    }
}
