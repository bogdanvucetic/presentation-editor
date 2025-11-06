package raf.graffito.dsw.model.composite;

public abstract class GraffNodeFactory {
    public abstract GraffNode createGraffNode(GraffNode parent, String name);
}
