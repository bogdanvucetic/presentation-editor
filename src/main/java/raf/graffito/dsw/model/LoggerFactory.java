package raf.graffito.dsw.model;

public class LoggerFactory {
    public Logger createLogger(String name) {
        if (name.equalsIgnoreCase("console")) {
            return new ConsoleLogger();
        } else if (name.equalsIgnoreCase("file")) {
            return new FileLogger();
        }

        return null;
    }
}
