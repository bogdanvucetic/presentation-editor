package raf.graffito.dsw.model.composite;

import raf.graffito.dsw.observer.model.ModelPublisher;
import raf.graffito.dsw.observer.model.ModelSubscriber;
import raf.graffito.dsw.observer.model.ModelUpdateEvent;
import raf.graffito.dsw.observer.model.ModelUpdateType;

import java.util.ArrayList;
import java.util.List;

public class Project extends GraffNodeComposite implements ModelPublisher {
    private String title;
    private int totalNumberOfSlides;
    private List<ModelSubscriber> subscribers;

    public Project(GraffNode parent, String name) {
        super(parent, name);
        this.title = name;
        this.totalNumberOfSlides = 0;
        this.subscribers = new ArrayList<>();
    }

    // TODO MESSAGE
    @Override
    public void addChild(GraffNode child) {
        if (child instanceof Presentation || child instanceof Slide) {
            super.addChild(child);

            if (child instanceof Slide) {
                incrementNumberOfSlides();
            }

            notifySubscribers(new ModelUpdateEvent(ModelUpdateType.TEMP, this));
        }
    }

    // TODO MESSAGE
    @Override
    public void removeChild(GraffNode child) {
        if (getChildren().size() <= 1) {
            throw new IllegalArgumentException("Projekat ne može biti prazan.");
        }

        super.removeChild(child);
        notifySubscribers(new ModelUpdateEvent(ModelUpdateType.TEMP, this));
    }

    // TODO MESSAGE
    @Override
    public void setName(String name) {
        super.setName(name);
        notifySubscribers(new ModelUpdateEvent(ModelUpdateType.TEMP, this));
    }

    public void incrementNumberOfSlides(){
        totalNumberOfSlides++;
    }

    public String getTitle() {
        return title;
    }

    // TODO MESSAGE
    public void setTitle(String title) {
        this.title = title;
        notifySubscribers(new ModelUpdateEvent(ModelUpdateType.TEMP, this));
    }

    public int getTotalNumberOfSlides() {
        return totalNumberOfSlides;
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
    public void notifySubscribers(ModelUpdateEvent event) {
        for (ModelSubscriber s : subscribers) {
            s.update(event);
        }
    }
}
