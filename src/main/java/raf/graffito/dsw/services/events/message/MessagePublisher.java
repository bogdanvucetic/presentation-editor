package raf.graffito.dsw.services.events.message;

public interface MessagePublisher {
    void addSubscriber(MessageSubscriber messageSubscriber);
    void removeSubscriber(MessageSubscriber messageSubscriber);
    void notifySubscribers();
}
