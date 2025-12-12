package raf.graffito.dsw.gui.swing.view;

import raf.graffito.dsw.controller.tree.GraffTreeListener;
import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.controller.ActionManager;
import raf.graffito.dsw.gui.swing.tree.GraffTree;
import raf.graffito.dsw.gui.swing.tree.GraffTreeElem;
import raf.graffito.dsw.gui.swing.tree.GraffTreeImplementation;
import raf.graffito.dsw.model.composite.GraffNode;
import raf.graffito.dsw.model.composite.Project;
import raf.graffito.dsw.model.composite.Slide;
import raf.graffito.dsw.observer.message.MessageGenerator;
import raf.graffito.dsw.observer.message.MessageType;
import raf.graffito.dsw.observer.message.MessageSubscriber;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.*;

public class MainFrame extends JFrame implements MessageSubscriber {
    private static MainFrame instance;
    private ActionManager actionManager;

    private JSplitPane mainPanel;
    private GraffTree graffTree;
    private ProjectView projectView;
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
        setSize(screenWidth / 2, screenHeight / 2);
        setLocationRelativeTo(null); // Centriranje prozora na ekranu
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Zatvaranje aplikacije pri zatvaranju prozora
        setTitle("Graffito"); // Naslov prozora

        MyMenuBar menu = new MyMenuBar(); // Kreiranje menija
        setJMenuBar(menu); // Postavljanje menija na prozor

        MyToolBar toolBar = new MyToolBar(); // Kreiranje toolbar-a
        add(toolBar, BorderLayout.NORTH); // Postavljanje toolbar-a na vrh prozora

        JTree tree = graffTree.generateTree(ApplicationFramework.getInstance().getGraffRepository().getWorkspace());
        tree.addMouseListener(new GraffTreeListener());
        JScrollPane treePane = new JScrollPane(tree);
        treePane.setPreferredSize(new Dimension(180, 0));

        projectView = new ProjectView(null);
        infoPanel = new InfoPanel();
        infoPanel.setPreferredSize(new Dimension(180, 0));

        mainPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, projectView, infoPanel);
        mainPanel.setOneTouchExpandable(true);
        mainPanel.setResizeWeight(1.0);

        add(treePane, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);
    }

    public void openSoloSlide(Slide slide) {
        int oldLocation = mainPanel.getDividerLocation();

        SlideView slideView = new SlideView(slide);
        mainPanel.setLeftComponent(slideView);
        this.projectView = null;

        infoPanel.setProject((Project) slide.getParent());

        SwingUtilities.invokeLater(() -> mainPanel.setDividerLocation(oldLocation));
    }

    public void openProject(ProjectView projectView) {
        int oldLocation = mainPanel.getDividerLocation();

        mainPanel.setLeftComponent(projectView);
        this.projectView = projectView;

        infoPanel.setProject(projectView.getProject());

        mainPanel.setDividerLocation(oldLocation);
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

    public Project getProjectFromPath(TreePath path) {
        for (int i = 0; i < path.getPathCount(); i++) {
            GraffNode node = ((GraffTreeElem) path.getPathComponent(i)).getGraffNode();

            if (node instanceof Project project) {
                return project;
            }
        }
        return null;
    }

    public ActionManager getActionManager() {
        return actionManager;
    }

    public GraffTree getGraffTree() {
        return graffTree;
    }

    public ProjectView getProjectView() {
        return projectView;
    }

    public InfoPanel getInfoPanel() {
        return infoPanel;
    }
}