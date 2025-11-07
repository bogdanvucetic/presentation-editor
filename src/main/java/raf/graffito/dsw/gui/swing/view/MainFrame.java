package raf.graffito.dsw.gui.swing.view;

import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.gui.swing.tabs.InfoPanel;
import raf.graffito.dsw.controller.ActionManager;
import raf.graffito.dsw.gui.swing.tree.GraffTree;
import raf.graffito.dsw.gui.swing.tree.GraffTreeImplementation;
import raf.graffito.dsw.observer.MessageGenerator;
import raf.graffito.dsw.observer.MessageType;
import raf.graffito.dsw.observer.Subscriber;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame implements Subscriber {

    // Buduća polja za sve komponente view-a na glavnom prozoru

    private static MainFrame instance;
    private ActionManager actionManager;
    // todo u konkretnu klasu?
    private GraffTree graffTree;

    private MainFrame() {
        actionManager = new ActionManager();
        graffTree = new GraffTreeImplementation();
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

        JTree tree = graffTree.generateTree(ApplicationFramework.getInstance().getGraffRepository().getWorkspace());
        JScrollPane treePane = new JScrollPane(tree);

//        DecoratorRegistry registry = ApplicationFramework.getInstance().getDecoratorRegistry();
//        InfoPanel infoPanel = new InfoPanel();
//        TabsPanel tabsPanel = new TabsPanel(tree, registry, infoPanel);

        JPanel centerPanel = new JPanel();
        InfoPanel infoPanel = new InfoPanel();
        JSplitPane right = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, centerPanel, infoPanel);
        right.setResizeWeight(1);

        JSplitPane all = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treePane, right);
        all.setResizeWeight(0.1);
        add(all, BorderLayout.CENTER);
    }

    @Override
    public void update(MessageGenerator message) {
        if (message.getType().equals(MessageType.ERROR)) {
            JOptionPane.showMessageDialog(null, message.getContent(), "Greška", JOptionPane.ERROR_MESSAGE);
        } else if (message.getType().equals(MessageType.WARNING)) {
            JOptionPane.showMessageDialog(null, message.getContent(), "Upozorenje", JOptionPane.WARNING_MESSAGE);
        }
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

    public GraffTree getGraffTree() {
        return graffTree;
    }
}
