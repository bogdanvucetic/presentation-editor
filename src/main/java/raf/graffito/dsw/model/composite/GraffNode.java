package raf.graffito.dsw.model.composite;

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

    public void setName(String name) {
        if (parent != null) {
            if (((GraffNodeComposite)parent).isChildNameTaken(name)) {
                throw new IllegalArgumentException("Ime je zauzeto.");
            }
        }

        this.name = name;
    }

    public GraffNode getParent() {
        return parent;
    }

    public void setParent(GraffNode parent) {
        this.parent = parent;
    }
}
