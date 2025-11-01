package raf.graffito.dsw.model;

public abstract class GraffNode {
    private GraffNode parent;
    private String name;

    public GraffNode(GraffNode parent, String name) {
        this.parent = parent;
        this.name = name;
    }

    public abstract GraffNode findByName(String name);

    public String getName() {
        return name;
    }
}
