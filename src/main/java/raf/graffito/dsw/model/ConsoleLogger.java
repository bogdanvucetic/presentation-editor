package raf.graffito.dsw.model;

public class ConsoleLogger extends AbstractLogger {
    @Override
    public void update(Message message) {
        System.out.println(format(message));
    }
}
