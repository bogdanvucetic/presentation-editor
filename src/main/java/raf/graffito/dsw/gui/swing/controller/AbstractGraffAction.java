package raf.graffito.dsw.gui.swing.controller;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public abstract class AbstractGraffAction extends AbstractAction {
    // Metoda za učitavanje ikonice iz resursa
    protected Icon loadIcon(String path) {
        URL ImageURL = getClass().getResource(path); // URL služi za pronalaženje resursa unutar JAR fajla ili klase

        if (ImageURL == null) {
            System.err.println("File " + path + " not found");
            return null;
        }

        Image img = new ImageIcon(ImageURL).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Napravimo Image iz ImageIcon
        return new ImageIcon(img);
    }
}
