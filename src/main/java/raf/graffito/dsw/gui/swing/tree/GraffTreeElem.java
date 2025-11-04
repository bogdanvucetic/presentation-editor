package raf.graffito.dsw.gui.swing.tree;

import raf.graffito.dsw.model.composite.GraffNode;

import javax.swing.tree.DefaultMutableTreeNode;

public class GraffTreeElem extends DefaultMutableTreeNode {
    private GraffNode graffNode;

    public GraffTreeElem(GraffNode graffNode) {
        this.graffNode = graffNode;
    }

    public GraffNode getGraffNode() {
        return graffNode;
    }

    // bez ove implementacije čvorovi ne bi pokazivali ime
    @Override
    public String toString() {
        return graffNode.getName();
    }
}
