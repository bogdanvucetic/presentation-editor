package raf.graffito.dsw.services.events.message.logger;

import raf.graffito.dsw.services.events.message.MessageGenerator;

public class ConsoleLogger implements Logger {
    @Override
    public void update(MessageGenerator message) {
        System.out.println(LogFormatter.format(message));
    }
}
