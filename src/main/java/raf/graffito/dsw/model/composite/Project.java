package raf.graffito.dsw.model.composite;

import raf.graffito.dsw.observer.model.ModelSubscriber;

import java.util.ArrayList;
import java.util.List;

public class Project extends GraffNodeComposite {
    private String title;
    private int totalNumberOfSlides;
    private List<ModelSubscriber> subscribers;

    public Project(GraffNode parent, String name) {
        super(parent, name);
        this.title = name;
        this.totalNumberOfSlides = 0;
        this.subscribers = new ArrayList<>();
    }

    @Override
    public void addChild(GraffNode child) {
        if (child instanceof Presentation || child instanceof Slide) {
            super.addChild(child);

            if (child instanceof Slide) {
                incrementNumberOfSlides();
            }
        }
    }

    @Override
    public void removeChild(GraffNode child) {
        if (getChildren().size() <= 1) {
            throw new IllegalArgumentException("Projekat ne može biti prazan.");
        }

        super.removeChild(child);
    }

    public void incrementNumberOfSlides(){
        totalNumberOfSlides++;
    }

    public List<Presentation> getPresentations() {
        List<Presentation> presentations = new ArrayList<>();
        for (GraffNode child : getChildren()) {
            if (child instanceof Presentation) {
                presentations.add((Presentation) child);
            }
        }
        return presentations;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTotalNumberOfSlides() {
        return totalNumberOfSlides;
    }
}
