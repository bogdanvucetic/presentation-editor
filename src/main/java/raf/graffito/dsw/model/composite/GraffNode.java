package raf.graffito.dsw.model.composite;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import raf.graffito.dsw.model.composite.elements.Image;
import raf.graffito.dsw.model.composite.elements.Label;
import raf.graffito.dsw.model.composite.elements.Logo;
import raf.graffito.dsw.services.events.model.ModelUpdate;
import raf.graffito.dsw.services.events.model.UpdateType;
import raf.graffito.dsw.services.events.model.UpdatePublisher;
import raf.graffito.dsw.services.events.model.UpdateSubscriber;

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
        @JsonSubTypes.Type(value = Logo.class, name = "logo"),
        @JsonSubTypes.Type(value = Label.class, name = "label"),
        @JsonSubTypes.Type(value = Image.class, name = "image"),
})

public abstract class GraffNode implements UpdatePublisher {
    @JsonIgnore
    private GraffNode parent;
    private String name;
    @JsonIgnore
    private List<UpdateSubscriber> subscribers;

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
    public void addSubscriber(UpdateSubscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(UpdateSubscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers(ModelUpdate event) {
        for (UpdateSubscriber s : subscribers) {
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
        notifySubscribers(new ModelUpdate(UpdateType.RENAMED, this));
    }

    public GraffNode getParent() {
        return parent;
    }

    public void setParent(GraffNode parent) {
        this.parent = parent;
    }
}
