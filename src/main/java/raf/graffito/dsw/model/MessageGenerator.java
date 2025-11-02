package raf.graffito.dsw.model;

import raf.graffito.dsw.gui.swing.MainFrame;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MessageGenerator implements Publisher {
    private List<Subscriber> subscribers;

    private String content;
    private MessageType type;
    private LocalDateTime timestamp;

    public MessageGenerator() {
        this.subscribers = new ArrayList<>();
    }

    public void createMessage(String content, MessageType type) {
        this.content = content;
        this.type = type;
        this.timestamp = LocalDateTime.now();
        notifySubscribers();
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
    public void notifySubscribers() {
        for (Subscriber s : subscribers) {
            s.update(this);
        }
    }

    public String getContent() {
        return content;
    }

    public MessageType getType() {
        return type;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
