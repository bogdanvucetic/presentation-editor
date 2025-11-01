package raf.graffito.dsw.model;

public abstract class AbstractLogger implements Logger {
    public String format(Message message){
        return "[" + message.getType() + "][" + message.getTimestamp() + "] " + message.getContent();
    }
}
