package raf.graffito.dsw.gui.swing.view;

import raf.graffito.dsw.core.ApplicationFramework;
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
    private JTabbedPane tabs;
    private Color tabColor;
    private InfoPanel infoPanel;

    public ProjectView(Project project) {
        this(project, null);
    }

    public ProjectView(Project project, Color tabColor) {
        this.project = project;
        this.tabColor = tabColor;
        initialize();
    }

    private void initialize() {
        setLayout(new BorderLayout());

        infoPanel = new InfoPanel();
        infoPanel.setProject(project);

        tabs = new JTabbedPane();
        tabs.addChangeListener(e -> {
            if (tabs.getSelectedIndex() != -1) {
                PresentationView view = (PresentationView) tabs.getSelectedComponent();
                infoPanel.setCurrentPresentation(view.getPresentation());
            } else {
                infoPanel.setCurrentPresentation(null);
            }
        });

        if (project != null) {
            project.getParent().addSubscriber(this);
            project.addSubscriber(this);

            List<Presentation> presentations = project.getPresentations();

            if (!presentations.isEmpty()) {
                infoPanel.setCurrentPresentation(presentations.getFirst());
            }

            createTabsFor(presentations);
        }

        if (tabColor != null) {
            tabs.setBackground(tabColor);
        }

        add(tabs, BorderLayout.CENTER);
        add(infoPanel, BorderLayout.EAST);
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

        if (type == ModelEventType.ADD_CHILD) {
            if (node instanceof Presentation p) {
                p.addSubscriber(this);
                PresentationView view = new PresentationView(p);
                tabs.add(p.getName(), view);

                if (tabColor != null) {
                    tabs.setBackground(tabColor);
                }
            }
        } else if (type == ModelEventType.REMOVE_CHILD) {
            if (node == project) {
                tabs.removeAll();
                infoPanel.setProject(null);
                infoPanel.setCurrentPresentation(null);
            } else if (node instanceof Presentation p) {
                removePresentationTab(p);
            }
        } else if (type == ModelEventType.RENAME) {
            if (node == project) {
                infoPanel.setProjectLabel(project.getName());
            } else if (node instanceof Presentation p) {
                renamePresentationTab(p);
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
}
