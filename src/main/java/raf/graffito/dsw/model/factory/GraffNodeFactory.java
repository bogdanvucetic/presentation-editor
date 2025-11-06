package raf.graffito.dsw.model.factory;

import raf.graffito.dsw.model.composite.GraffNode;

public abstract class GraffNodeFactory {
    public abstract GraffNode createGraffNode(GraffNode parent);
    public abstract String getDefaultName(GraffNode parent);
}
