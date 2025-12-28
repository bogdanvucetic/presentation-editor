package raf.graffito.dsw.services.events.model;

public interface UpdatePublisher {
    void addSubscriber(UpdateSubscriber subscriber);
    void removeSubscriber(UpdateSubscriber subscriber);
    void notifySubscribers(ModelUpdate event);
}
