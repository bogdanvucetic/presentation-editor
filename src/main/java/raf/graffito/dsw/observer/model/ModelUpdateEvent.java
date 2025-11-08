package raf.graffito.dsw.observer.model;

import raf.graffito.dsw.model.composite.GraffNode;

public class ModelUpdateEvent {
    private ModelUpdateType type;
    private GraffNode node;

    public ModelUpdateEvent(ModelUpdateType type, GraffNode node) {
        this.type = type;
        this.node = node;
    }

    public ModelUpdateType getType() {
        return type;
    }

    public GraffNode getNode() {
        return node;
    }
}
