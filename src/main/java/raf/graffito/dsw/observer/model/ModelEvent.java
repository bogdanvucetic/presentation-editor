package raf.graffito.dsw.observer.model;

import raf.graffito.dsw.model.composite.GraffNode;

public class ModelEvent {
    private ModelEventType type;
    private GraffNode node;

    public ModelEvent(ModelEventType type, GraffNode node) {
        this.type = type;
        this.node = node;
    }

    public ModelEventType getType() {
        return type;
    }

    public GraffNode getNode() {
        return node;
    }
}
