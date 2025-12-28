package raf.graffito.dsw.observer.model;

import raf.graffito.dsw.model.composite.GraffNode;

public class ModelUpdate {
    private UpdateType type;
    private GraffNode node;

    public ModelUpdate(UpdateType type, GraffNode node) {
        this.type = type;
        this.node = node;
    }

    public UpdateType getType() {
        return type;
    }

    public GraffNode getNode() {
        return node;
    }
}
