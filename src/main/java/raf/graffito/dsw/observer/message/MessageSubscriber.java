package raf.graffito.dsw.observer.message;

public interface MessageSubscriber {
    void update(MessageGenerator message);
}
