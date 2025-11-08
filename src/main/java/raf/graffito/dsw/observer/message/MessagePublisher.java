package raf.graffito.dsw.observer.message;

public interface MessagePublisher {
    void addSubscriber(MessageSubscriber messageSubscriber);
    void removeSubscriber(MessageSubscriber messageSubscriber);
    void notifySubscribers();
}
