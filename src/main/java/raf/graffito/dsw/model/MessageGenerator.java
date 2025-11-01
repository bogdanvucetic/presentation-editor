package raf.graffito.dsw.model;

import raf.graffito.dsw.gui.swing.MainFrame;

import java.util.ArrayList;
import java.util.List;

public class MessageGenerator implements Publisher {
    private List<Subscriber> subscribers;

    public MessageGenerator(List<Subscriber> subscribers) {
        this.subscribers = subscribers;
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
            // dodao ovu proveru jer se navodi:
            // - da Logger prima poruke bilo kog tipa od MessageGenerator-a
            // - da MainFrame prima samo greške i obaveštenja
            // pa ovde ima prostora i da se doda slučaj ako dobijemo neki treći tip subskrajbera
            // možda bespotrebna apstrakcija unapred ali nmvz
            if (s instanceof MainFrame) {
                if (message.getType().equals(MessageType.ERROR) || message.getType().equals(MessageType.WARNING)) {
                    s.update(message);
                }
            } else if (s instanceof Logger) {
                s.update(message);
            }
        }
    }

    // todo
    //  metoda za kreiranje poruke i onda poziv notifySubscribers
}
