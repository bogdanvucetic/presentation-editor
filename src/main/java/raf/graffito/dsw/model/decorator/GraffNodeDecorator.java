package raf.graffito.dsw.model.decorator;

import raf.graffito.dsw.model.composite.GraffNode;

public abstract class GraffNodeDecorator {
    private GraffNode node;

    public GraffNodeDecorator(GraffNode node) {
        this.node = node;
    }

    public GraffNode getNode() {
        return node;
    }

    public void setNode(GraffNode node) {
        this.node = node;
    }
}
