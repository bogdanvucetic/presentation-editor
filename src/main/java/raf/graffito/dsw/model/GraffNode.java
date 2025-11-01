package raf.graffito.dsw.model;

public abstract class GraffNode {
    private GraffNode parent;
    private String name;

    public abstract GraffNode findByName(String name);
}
