package raf.graffito.dsw.model.composite;

import raf.graffito.dsw.observer.model.ModelEvent;
import raf.graffito.dsw.observer.model.ModelEventType;
import raf.graffito.dsw.observer.model.ModelPublisher;
import raf.graffito.dsw.observer.model.ModelSubscriber;

import java.util.LinkedList;
import java.util.List;

public abstract class GraffNode implements ModelPublisher {
    private GraffNode parent;
    private String name;
    private List<ModelSubscriber> subscribers;

    public GraffNode() {
        subscribers = new LinkedList<>();
    }

    public GraffNode(GraffNode parent, String name) {
        this.parent = parent;
        this.name = name;
        subscribers = new LinkedList<>();
    }

    public GraffNode findByName(String name) {
        if (getName() != null && getName().equals(name)) {
            return this;
        }
        return null;
    }

    @Override
    public void addSubscriber(ModelSubscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(ModelSubscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers(ModelEvent event) {
        for (ModelSubscriber s : subscribers) {
            s.update(event);
        }
    }

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
        notifySubscribers(new ModelEvent(ModelEventType.RENAME, this));
    }

    public GraffNode getParent() {
        return parent;
    }

    public void setParent(GraffNode parent) {
        this.parent = parent;
    }
}
