package raf.graffito.dsw.model.composite;

import raf.graffito.dsw.observer.model.ModelPublisher;
import raf.graffito.dsw.observer.model.ModelSubscriber;
import raf.graffito.dsw.observer.model.ModelUpdateEvent;
import raf.graffito.dsw.observer.model.ModelUpdateType;

import java.util.ArrayList;
import java.util.List;

public class Presentation extends GraffNodeComposite implements ModelPublisher {
    List<ModelSubscriber> subscribers;

    public Presentation(GraffNode parent, String name) {
        super(parent, name);
        subscribers = new ArrayList<>();
    }

    // TODO MESSAGE
    @Override
    public void addChild(GraffNode child) {
        if (child instanceof Slide) {
            super.addChild(child);
            ((Project) getParent()).incrementNumberOfSlides();
            notifySubscribers(new ModelUpdateEvent(ModelUpdateType.TEMP, this));
        }
    }

    // TODO MESSAGE
    @Override
    public void removeChild(GraffNode child) {
        super.removeChild(child);
        notifySubscribers(new ModelUpdateEvent(ModelUpdateType.TEMP, this));
    }

    // TODO MESSAGE
    @Override
    public void setName(String name) {
        super.setName(name);
        notifySubscribers(new ModelUpdateEvent(ModelUpdateType.TEMP, this));
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
