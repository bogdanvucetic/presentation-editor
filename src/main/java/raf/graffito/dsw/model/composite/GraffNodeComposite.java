package raf.graffito.dsw.model.composite;

import java.util.ArrayList;
import java.util.List;

public abstract class GraffNodeComposite extends GraffNode {
    private List<GraffNode> children;

    public GraffNodeComposite(GraffNode parent, String name) {
        super(parent, name);
        children = new ArrayList<>();
    }

    @Override
    public GraffNode findByName(String name) {
        if (getName() != null && getName().equals(name)) {
            return this;
        }

        for (GraffNode child : children) {
            GraffNode result = child.findByName(name);
            if (result != null) {
                return result;
            }
        }

        return null;
    }

    public void addChild(GraffNode child) {
        children.add(child);
        child.setParent(this);
    }

    public void removeChild(GraffNode child) {
        children.remove(child);
        child.setParent(null);
    }
}