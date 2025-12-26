package raf.graffito.dsw;

import raf.graffito.dsw.core.ApplicationFramework;

import javax.swing.*;

public class AppCore {
    public static void main(String[] args) {
        ApplicationFramework appCore = ApplicationFramework.getInstance();
        SwingUtilities.invokeLater(() -> appCore.initialize());
    }
}