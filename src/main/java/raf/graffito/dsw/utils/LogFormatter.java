package raf.graffito.dsw.utils;

import raf.graffito.dsw.model.Message;

import java.time.format.DateTimeFormatter;

public class LogFormatter {
    public static String format(Message message) {
        return "[" +
                message.getType() +
                "][" +
                message.getTimestamp().format(DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm")) +
                "] " +
                message.getContent();
    }
}
