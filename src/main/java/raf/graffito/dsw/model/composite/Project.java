package raf.graffito.dsw.model.composite;

import com.fasterxml.jackson.annotation.JsonIgnore;
import raf.graffito.dsw.observer.model.ModelUpdate;
import raf.graffito.dsw.observer.model.UpdateType;

public class Project extends GraffNodeComposite {
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

        if (child instanceof Slide) {
            incrementNumberOfSlides();
        }

        markAsChanged();
    }

    @Override
    public void removeChild(GraffNode child) {
        if (getChildren().size() <= 1) {
            throw new IllegalArgumentException("Projekat ne može biti prazan.");
        }

        super.removeChild(child);
        markAsChanged();
    }

    public void incrementNumberOfSlides(){
        totalNumberOfSlides++;
        markAsChanged();
    }

    public void markAsChanged() {
        this.changed = true;
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
        markAsChanged();
    }

    public int getTotalNumberOfSlides() {
        return totalNumberOfSlides;
    }

    public void setTotalNumberOfSlides(int totalNumberOfSlides) {
        this.totalNumberOfSlides = totalNumberOfSlides;
    }
}
