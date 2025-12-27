package raf.graffito.dsw.gui.swing.view;

import raf.graffito.dsw.controller.listeners.GraffTreeListener;
import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.controller.ActionManager;
import raf.graffito.dsw.gui.swing.tree.GraffTree;
import raf.graffito.dsw.gui.swing.tree.GraffTreeImplementation;
import raf.graffito.dsw.observer.message.MessageGenerator;
import raf.graffito.dsw.observer.message.MessageType;
import raf.graffito.dsw.observer.message.MessageSubscriber;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame implements MessageSubscriber {
    private static MainFrame instance;
    private ActionManager actionManager;

    private Dimension normalDimension;
    private JSplitPane workspaceSplitPane;
    private GraffTree graffTree;
    private ProjectView currentProjectView;
    private InfoPanel infoPanel;

    private MainFrame() {
        actionManager = new ActionManager();
        graffTree = new GraffTreeImplementation();
    }

    private void initialize() {
        Toolkit kit = Toolkit.getDefaultToolkit(); // Toolkit omogućava interakciju sa platformom
        Dimension screenSize = kit.getScreenSize(); // Veličina ekrana
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        normalDimension = new Dimension(screenWidth / 2, screenHeight / 2);
        setSize(normalDimension);
        setLocationRelativeTo(null); // Centriranje prozora na ekranu
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Zatvaranje aplikacije pri zatvaranju prozora
        setTitle("Graffito"); // Naslov prozora

        MyMenuBar menu = new MyMenuBar(); // Kreiranje menija
        setJMenuBar(menu); // Postavljanje menija na prozor

        MyToolBar toolBar = new MyToolBar(); // Kreiranje toolbar-a
        add(toolBar, BorderLayout.NORTH); // Postavljanje toolbar-a na vrh prozora

        buildTreePanel();
        buildWorkspaceSplitPane();
    }

    private void buildTreePanel() {
        JTree tree = graffTree.generateTree(ApplicationFramework.getInstance().getGraffRepository().getWorkspace());
        tree.addMouseListener(new GraffTreeListener());
        JScrollPane treePane = new JScrollPane(tree);
        treePane.setPreferredSize(new Dimension(180, 0));

        add(treePane, BorderLayout.WEST);
    }

    private void buildWorkspaceSplitPane() {
        currentProjectView = new ProjectView(null);
        infoPanel = new InfoPanel();
        infoPanel.setPreferredSize(new Dimension(180, 0));

        workspaceSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, currentProjectView, infoPanel);
        workspaceSplitPane.setOneTouchExpandable(true);
        workspaceSplitPane.setResizeWeight(1.0);

        add(workspaceSplitPane, BorderLayout.CENTER);
    }

    public void openProject(ProjectView projectView) {
        int oldDividerLocation = workspaceSplitPane.getDividerLocation();

        workspaceSplitPane.setLeftComponent(projectView);
        this.currentProjectView = projectView;

        infoPanel.setProject(projectView.getProject());

        workspaceSplitPane.setDividerLocation(oldDividerLocation);
    }

    @Override
    public void update(MessageGenerator message) {
        if (message.getType().equals(MessageType.ERROR)) {
            JOptionPane.showMessageDialog(MainFrame.getInstance(), message.getContent(), "Greška", JOptionPane.ERROR_MESSAGE);
        } else if (message.getType().equals(MessageType.WARNING)) {
            JOptionPane.showMessageDialog(MainFrame.getInstance(), message.getContent(), "Upozorenje", JOptionPane.WARNING_MESSAGE);
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

    public ProjectView getCurrentProjectView() {
        return currentProjectView;
    }

    public InfoPanel getInfoPanel() {
        return infoPanel;
    }

    public Dimension getNormalDimension() {
        return normalDimension;
    }
}