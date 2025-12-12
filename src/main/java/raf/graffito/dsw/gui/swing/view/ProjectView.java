package raf.graffito.dsw.gui.swing.view;

import raf.graffito.dsw.controller.TabListener;
import raf.graffito.dsw.model.composite.GraffNode;
import raf.graffito.dsw.model.composite.Presentation;
import raf.graffito.dsw.model.composite.Project;
import raf.graffito.dsw.observer.model.ModelEventType;
import raf.graffito.dsw.observer.model.ModelSubscriber;
import raf.graffito.dsw.observer.model.ModelEvent;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ProjectView extends JPanel implements ModelSubscriber {
    private Project project;
    private Color tabColor;
    private JTabbedPane tabs;

    public ProjectView(Project project) {
        this(project, null);
    }

    public ProjectView(Project project, Color tabColor) {
        this.project = project;
        this.tabColor = tabColor;
        tabs = new JTabbedPane();

        setLayout(new BorderLayout());
        initialize();
    }

    private void initialize() {
        if (project == null) {
            return;
        }

        project.getParent().addSubscriber(this);
        project.addSubscriber(this);

        tabs.addChangeListener(new TabListener());

        MainFrame.getInstance().getInfoPanel().setProject(project);
        List<Presentation> presentations = project.getPresentations();

        if (!presentations.isEmpty()) {
            MainFrame.getInstance().getInfoPanel().setPresentation(presentations.getFirst());
            createTabsFor(presentations);
        }

        if (tabColor != null) {
            tabs.setBackground(tabColor);
        }

        add(tabs, BorderLayout.CENTER);
    }

    private void createTabsFor(List<Presentation> presentations) {
        tabs.removeAll();

        for (Presentation p : presentations) {
            p.addSubscriber(this);
            PresentationView view = new PresentationView(p);
            tabs.add(p.getName(), view);
        }
    }

    @Override
    public void update(ModelEvent event) {
        GraffNode node = event.getNode();
        ModelEventType type = event.getType();

        switch (type) {
            case ADD_CHILD -> {
                if (node instanceof Presentation p) {
                    p.addSubscriber(this);
                    PresentationView view = new PresentationView(p);
                    tabs.add(p.getName(), view);

                    if (tabColor != null) {
                        tabs.setBackground(tabColor);
                    }
                }
            }
            case REMOVE_CHILD -> {
                if (node == project) {
                    tabs.removeAll();
                    MainFrame.getInstance().getInfoPanel().setProject(null);
                    MainFrame.getInstance().getInfoPanel().setPresentation(null);
                } else if (node instanceof Presentation p) {
                    removePresentationTab(p);
                }
            }
            case RENAME -> {
                if (node == project) {
                    MainFrame.getInstance().getInfoPanel().setProjectLabel(project.getName());
                } else if (node instanceof Presentation p) {
                    renamePresentationTab(p);
                }
            }
        }
    }

    private void removePresentationTab(Presentation p) {
        for (Component c : tabs.getComponents()){
            if (c instanceof PresentationView pv && pv.getPresentation() == p){
                tabs.remove(c);
                break;
            }
        }
    }

    private void renamePresentationTab(Presentation p) {
        for (int i = 0; i < tabs.getTabCount(); i++) {
            Component c = tabs.getComponentAt(i);
            if (c instanceof PresentationView pv && pv.getPresentation() == p) {
                tabs.setTitleAt(i, p.getName());
                break;
            }
        }
    }

    public Project getProject() {
        return project;
    }

    public JTabbedPane getTabs() {
        return tabs;
    }
}
