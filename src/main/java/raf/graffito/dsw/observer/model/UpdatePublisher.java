package raf.graffito.dsw.observer.model;

public interface UpdatePublisher {
    void addSubscriber(UpdateSubscriber subscriber);
    void removeSubscriber(UpdateSubscriber subscriber);
    void notifySubscribers(ModelUpdate event);
}
