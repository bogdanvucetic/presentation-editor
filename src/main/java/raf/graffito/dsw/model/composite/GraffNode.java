package raf.graffito.dsw.model.composite;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import raf.graffito.dsw.observer.model.ModelEvent;
import raf.graffito.dsw.observer.model.ModelEventType;
import raf.graffito.dsw.observer.model.ModelPublisher;
import raf.graffito.dsw.observer.model.ModelSubscriber;

import java.util.LinkedList;
import java.util.List;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)

@JsonSubTypes({
        @JsonSubTypes.Type(value = raf.graffito.dsw.model.composite.Project.class, name = "project"),
        @JsonSubTypes.Type(value = raf.graffito.dsw.model.composite.Presentation.class, name = "presentation"),
        @JsonSubTypes.Type(value = raf.graffito.dsw.model.composite.Slide.class, name = "slide"),
        @JsonSubTypes.Type(value = raf.graffito.dsw.model.composite.Logo.class, name = "logo"),
        @JsonSubTypes.Type(value = raf.graffito.dsw.model.composite.Label.class, name = "label"),
        @JsonSubTypes.Type(value = raf.graffito.dsw.model.composite.Image.class, name = "image"),
})

public abstract class GraffNode implements ModelPublisher {
    @JsonIgnore
    private GraffNode parent;
    private String name;
    @JsonIgnore
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
        markProjectAsChanged();
    }

    protected void markProjectAsChanged() {
        GraffNode current = this;
        while (current != null && !(current instanceof Project)) {
            current = current.getParent();
        }

        if (current instanceof Project proj) {
            proj.markAsChanged();
        }
    }

    public GraffNode getParent() {
        return parent;
    }

    public void setParent(GraffNode parent) {
        this.parent = parent;
    }
}
