package raf.graffito.dsw.services.events.message.logger;

import raf.graffito.dsw.services.events.message.MessageGenerator;

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
