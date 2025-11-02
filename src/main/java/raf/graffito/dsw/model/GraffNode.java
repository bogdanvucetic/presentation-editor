package raf.graffito.dsw.model;

public abstract class GraffNode {
    private GraffNode parent;
    private String name;

    public GraffNode(GraffNode parent, String name) {
        this.parent = parent;
        this.name = name;
    }

    // profesor rekao da ovde bude apstraktna i potklase da implementiraju
    public abstract GraffNode findByName(String name);

    public String getName() {
        return name;
    }

    public GraffNode getParent() {
        return parent;
    }

    public void setParent(GraffNode parent) {
        this.parent = parent;
    }
}
