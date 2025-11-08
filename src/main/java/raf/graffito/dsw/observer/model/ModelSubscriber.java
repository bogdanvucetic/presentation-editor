package raf.graffito.dsw.observer.model;

public interface ModelSubscriber {
    void update(ModelUpdateEvent event);
}
