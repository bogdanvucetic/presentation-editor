package raf.graffito.dsw.model.composite;

import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.model.factory.GraffNodeFactory;
import raf.graffito.dsw.observer.MessageType;

public class Project extends GraffNodeComposite {
    private String title;
    private int totalNumberOfSlides;

    public Project(GraffNode parent, String name) {
        super(parent, name);
        this.title = name;
        this.totalNumberOfSlides = 0;
    }

    @Override
    public void addChild(GraffNode child) {
        if (child instanceof Presentation) {
            super.addChild(child);
        } else if (child instanceof Slide) {
            super.addChild(child);
            incrementNumberOfSlides();
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
