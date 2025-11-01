package raf.graffito.dsw.utils;

import raf.graffito.dsw.model.Message;

public class LogFormatter {
    public static String format(Message message) {
        return "[" + message.getType() + "][" + message.getTimestamp() + "] " + message.getContent();
    }
}
