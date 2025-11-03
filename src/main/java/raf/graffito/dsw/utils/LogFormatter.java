package raf.graffito.dsw.utils;

import raf.graffito.dsw.model.observer.MessageGenerator;

import java.time.format.DateTimeFormatter;

public class LogFormatter {
    public static String format(MessageGenerator message) {
        return "[" +
                message.getType() +
                "][" +
                message.getTimestamp().format(DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss")) +
                "] " +
                message.getContent();
    }
}
