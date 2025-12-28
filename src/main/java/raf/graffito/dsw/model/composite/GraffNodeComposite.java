package raf.graffito.dsw.model.composite;

import raf.graffito.dsw.observer.model.ModelUpdate;
import raf.graffito.dsw.observer.model.UpdateType;

import java.util.ArrayList;
import java.util.List;

public abstract class GraffNodeComposite extends GraffNode {
    private List<GraffNode> children;

    public GraffNodeComposite() {
        children = new ArrayList<>();
    }

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
        if (isChildNameTaken(child.getName())) {
            throw new IllegalStateException("Ime je zauzeto.");
        }

        children.add(child);
        child.setParent(this);
        notifySubscribers(new ModelUpdate(UpdateType.CHILD_ADDED, child));
        markProjectAsChanged();
    }

    public void removeChild(GraffNode child) {
        children.remove(child);
        child.setParent(null);
        notifySubscribers(new ModelUpdate(UpdateType.CHILD_REMOVED, child));
        markProjectAsChanged();
    }

    public boolean isChildNameTaken(String name) {
        for (GraffNode child : children) {
            if (child.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public List<GraffNode> getChildren() {
        return children;
    }

    public void setChildren(List<GraffNode> children) {
        this.children = children;
    }
}