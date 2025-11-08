package raf.graffito.dsw.observer.model;

public interface ModelPublisher {
    void addSubscriber(ModelSubscriber subscriber);
    void removeSubscriber(ModelSubscriber subscriber);
    void notifySubscribers(ModelEvent event);
}
