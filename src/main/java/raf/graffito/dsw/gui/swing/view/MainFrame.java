package raf.graffito.dsw.gui.swing.view;

import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.controller.ActionManager;
import raf.graffito.dsw.gui.swing.tree.GraffTree;
import raf.graffito.dsw.gui.swing.tree.GraffTreeElem;
import raf.graffito.dsw.gui.swing.tree.GraffTreeImplementation;
import raf.graffito.dsw.model.composite.Project;
import raf.graffito.dsw.observer.message.MessageGenerator;
import raf.graffito.dsw.observer.message.MessageType;
import raf.graffito.dsw.observer.message.MessageSubscriber;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame implements MessageSubscriber {
    private static MainFrame instance;
    private ActionManager actionManager;
    private GraffTree graffTree;
    private ProjectView projectView;
    private JSplitPane splitPane;

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
        tree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() != 2) {
                    return;
                }

                TreePath path = tree.getPathForLocation(e.getX(), e.getY());

                if (path == null) {
                    return;
                }

                GraffTreeElem elem = (GraffTreeElem) path.getLastPathComponent();

                if (!(elem.getGraffNode() instanceof Project project)){
                    ApplicationFramework.getInstance().getMessageGenerator().createMessage("Otvaranje tabova je moguće samo za projekte.", MessageType.INFO);
                    return;
                }

                getActionManager().getOpenTabsAction().actionPerformed(project);
            }
        });

        projectView = new ProjectView(null);

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treePane, projectView);
        splitPane.setResizeWeight(0.1);

        add(splitPane, BorderLayout.CENTER);
    }

    public void openProject(ProjectView projectView) {
        splitPane.setRightComponent(projectView);
        this.projectView = projectView;
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
