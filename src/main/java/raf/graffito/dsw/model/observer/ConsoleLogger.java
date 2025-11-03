package raf.graffito.dsw.model.observer;

import raf.graffito.dsw.utils.LogFormatter;

public class ConsoleLogger implements Logger {
    @Override
    public void update(MessageGenerator message) {
        System.out.println(LogFormatter.format(message));
    }
}
