package raf.graffito.dsw.services.events.message.logger;

import raf.graffito.dsw.services.events.message.MessageGenerator;

import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements Logger {
    @Override
    public void update(MessageGenerator message) {
        try (FileWriter fw = new FileWriter("src/main/resources/log.txt", true)) {
            fw.write(LogFormatter.format(message));
            fw.write("\n");
        } catch (IOException e) {
            System.err.println("Neuspešno upisivanje u log.txt.");
        }
    }
}
