package raf.graffito.dsw.model;

import raf.graffito.dsw.gui.swing.MainFrame;

import java.util.ArrayList;
import java.util.List;

public class MessageGenerator implements Publisher {
    private List<Subscriber> subscribers;

    public MessageGenerator() {
        this.subscribers = new ArrayList<>();
    }

    @Override
    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers(Message message) {
        for (Subscriber s : subscribers) {
            s.update(message);
        }
    }

    // todo
    //  metoda za kreiranje poruke i onda poziv notifySubscribers
}
