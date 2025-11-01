package raf.graffito.dsw.gui.swing;

import raf.graffito.dsw.controller.ActionManager;
import raf.graffito.dsw.model.Message;
import raf.graffito.dsw.model.MessageType;
import raf.graffito.dsw.model.Subscriber;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame implements Subscriber {

    // Buduća polja za sve komponente view-a na glavnom prozoru

    private static MainFrame instance;
    private ActionManager actionManager;

    private MainFrame() {
        actionManager = new ActionManager();
    }

    private void initialize() {
        Toolkit kit = Toolkit.getDefaultToolkit(); // Toolkit omogućava interakciju sa platformom
        Dimension screenSize = kit.getScreenSize(); // Veličina ekrana
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 2, screenHeight / 2);
        setLocationRelativeTo(null); // Centriranje prozora na ekranu
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Zatvaranje aplikacije pri zatvaranju prozora
        setTitle("Graffito"); // Naslov prozora

        MyMenuBar menu = new MyMenuBar(); // Kreiranje menija
        setJMenuBar(menu); // Postavljanje menija na prozor

        MyToolBar toolBar = new MyToolBar(); // Kreiranje toolbar-a
        add(toolBar, BorderLayout.NORTH); // Postavljanje toolbar-a na vrh prozora
    }

    public static MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
            instance.initialize();
        }

        return instance;
    }

    public ActionManager getActionManager() {
        return actionManager;
    }

    @Override
    public void update(Message message) {
        if (message.getType().equals(MessageType.ERROR)) {
            JOptionPane.showMessageDialog(null, message.getContent(), "Greška", JOptionPane.ERROR_MESSAGE);
        } else if (message.getType().equals(MessageType.WARNING)) {
            JOptionPane.showMessageDialog(null, message.getContent(), "Upozorenje", JOptionPane.WARNING_MESSAGE);
        }
    }
}
