package raf.graffito.dsw.services.events.message;

public interface MessageSubscriber {
    void update(MessageGenerator message);
}
