package raf.graffito.dsw.observer;

import raf.graffito.dsw.utils.LogFormatter;

import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements Logger {
    @Override
    public void update(MessageGenerator message) {
        try (FileWriter fw = new FileWriter("src/main/resources/log.txt", true)) {
            fw.write(LogFormatter.format(message));
            fw.write("\n");
        } catch (IOException e) {
            System.err.println("Neuspešno upisivanje u log.txt: " + e.getMessage());
        }
    }
}
