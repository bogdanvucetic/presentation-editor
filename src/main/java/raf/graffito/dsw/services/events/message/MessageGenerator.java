package raf.graffito.dsw.services.events.message;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MessageGenerator implements MessagePublisher {
    private List<MessageSubscriber> subscribers;

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
    public void addSubscriber(MessageSubscriber messageSubscriber) {
        subscribers.add(messageSubscriber);
    }

    @Override
    public void removeSubscriber(MessageSubscriber messageSubscriber) {
        subscribers.remove(messageSubscriber);
    }

    @Override
    public void notifySubscribers() {
        for (MessageSubscriber s : subscribers) {
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
