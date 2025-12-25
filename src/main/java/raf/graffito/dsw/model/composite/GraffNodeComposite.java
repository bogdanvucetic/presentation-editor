package raf.graffito.dsw.model.composite;

import raf.graffito.dsw.observer.model.ModelEvent;
import raf.graffito.dsw.observer.model.ModelEventType;

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
            throw new IllegalArgumentException("Ime je zauzeto.");
        }

        children.add(child);
        child.setParent(this);
        notifySubscribers(new ModelEvent(ModelEventType.ADD_CHILD, child));
    }

    public void removeChild(GraffNode child) {
        children.remove(child);
        child.setParent(null);
        notifySubscribers(new ModelEvent(ModelEventType.REMOVE_CHILD, child));
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
}