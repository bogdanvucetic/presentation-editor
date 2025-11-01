package raf.graffito.dsw.model;

public abstract class GraffLeaf extends GraffNode {
    public GraffLeaf(GraffNode parent, String name) {
        super(parent, name);
    }

    @Override
    public GraffNode findByName(String name) {
        if (getName() != null && getName().equals(name)) {
            return this;
        }
        return null;
    }
}
