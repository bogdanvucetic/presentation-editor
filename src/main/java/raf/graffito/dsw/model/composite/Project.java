package raf.graffito.dsw.model.composite;

import com.fasterxml.jackson.annotation.JsonIgnore;
import raf.graffito.dsw.services.events.model.ModelUpdate;
import raf.graffito.dsw.services.events.model.UpdateSubscriber;
import raf.graffito.dsw.services.events.model.UpdateType;

public class Project extends GraffNodeComposite implements UpdateSubscriber {
    private String title;
    private int totalNumberOfSlides;
    @JsonIgnore
    private boolean changed;

    public Project() {
        changed = false;
    }

    public Project(GraffNode parent, String name) {
        super(parent, name);
        this.title = name;
        this.totalNumberOfSlides = 0;
        this.changed = false;
    }

    @Override
    public void addChild(GraffNode child) {
        if (!(child instanceof Presentation || child instanceof Slide)) {
            throw new IllegalArgumentException("ELement ne može da se doda na projekat.");
        }

        super.addChild(child);
        child.addSubscriber(this);

        if (child instanceof Slide) {
            incrementNumberOfSlides();
        }
    }

    @Override
    public void removeChild(GraffNode child) {
        if (getChildren().size() <= 1) {
            throw new IllegalArgumentException("Projekat ne može biti prazan.");
        }

        super.removeChild(child);
        child.removeSubscriber(this);
    }

    public void incrementNumberOfSlides(){
        totalNumberOfSlides++;
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifySubscribers(new ModelUpdate(UpdateType.PROPERTY_UPDATED, this));
    }

    public int getTotalNumberOfSlides() {
        return totalNumberOfSlides;
    }

    public void setTotalNumberOfSlides(int totalNumberOfSlides) {
        this.totalNumberOfSlides = totalNumberOfSlides;
    }

    @Override
    public void update(ModelUpdate event) {
        UpdateType type = event.getType();
        GraffNode node = event.getNode();

        switch (type) {
            case CHILD_ADDED -> node.addSubscriber(this);
            case CHILD_REMOVED -> node.removeSubscriber(this);
        }

        changed = true;
    }
}
