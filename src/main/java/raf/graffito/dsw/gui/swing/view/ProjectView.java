package raf.graffito.dsw.gui.swing.view;

import raf.graffito.dsw.controller.EditorContext;
import raf.graffito.dsw.controller.listeners.TabListener;
import raf.graffito.dsw.model.composite.GraffNode;
import raf.graffito.dsw.model.composite.Presentation;
import raf.graffito.dsw.model.composite.Project;
import raf.graffito.dsw.model.composite.Slide;
import raf.graffito.dsw.observer.model.ModelEventType;
import raf.graffito.dsw.observer.model.ModelSubscriber;
import raf.graffito.dsw.observer.model.ModelEvent;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
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
        EditorContext.getInstance().setCurrentSlideView(getDisplayedSlideView());
    }

    private void initialize() {
        if (project == null) {
            return;
        }

        TabListener tabListener = new TabListener();
        tabs.addChangeListener(tabListener);

        project.getParent().addSubscriber(this);
        project.addSubscriber(this);

        createTabsFor(project.getChildren());

        if (tabColor != null) {
            tabs.setBackground(tabColor);
        }

        add(tabs, BorderLayout.CENTER);

        tabListener.stateChanged(new ChangeEvent(tabs));
    }

    public SlideView getDisplayedSlideView() {
        if (tabs.getSelectedComponent() instanceof PresentationView pv) {
            return pv.getCurrentSlideView();
        } else if (tabs.getSelectedComponent() instanceof SlideView sv) {
            return sv;
        }
        return null;
    }

    public void openTabFor(GraffNode node) {
        for (int i = 0; i < tabs.getTabCount(); i++) {
            Component c = tabs.getComponentAt(i);

            if (c instanceof PresentationView pv && (pv.getPresentation() == node || pv.getPresentation() == node.getParent())) {
                tabs.setSelectedIndex(i);
                if (node instanceof Slide s && pv.getCurrentSlideView().getSlide() != s) {
                    pv.setSelectedSlide(s);
                }
                return;
            }

            if (c instanceof SlideView sv && sv.getSlide() == node) {
                tabs.setSelectedIndex(i);
                return;
            }
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
                    tabs.add(p.getName(), new PresentationView(p));
                } else if (node instanceof Slide s && s.getParent() == project) {
                    s.addSubscriber(this);
                    tabs.add(s.getName(), new SlideView(s));
                }
            }
            case REMOVE_CHILD -> {
                if (node == project) {
                    tabs.removeAll();
                } else if (node instanceof Presentation || node instanceof Slide) {
                    removeNodeTab(node);
                }
            }
            case RENAME -> {
                if (node instanceof Presentation || node instanceof Slide) {
                    renameNodeTab(node);
                }
            }
        }
    }

    private void createTabsFor(List<GraffNode> children) {
        tabs.removeAll();

        for (GraffNode node : children) {
            node.addSubscriber(this);

            if (node instanceof Slide s) {
                SlideView view = new SlideView(s);
                tabs.addTab(s.getName(), view);
            } else if (node instanceof Presentation p) {
                PresentationView view = new PresentationView(p);
                tabs.addTab(p.getName(), view);
            }
        }
    }

    private void removeNodeTab(GraffNode node) {
        for (Component c : tabs.getComponents()) {
            if (c instanceof PresentationView pv && pv.getPresentation() == node) {
                if (pv.getCurrentSlideView() != null) {
                    pv.getCurrentSlideView().getSlide().removeSubscriber(pv.getCurrentSlideView());
                }
                tabs.remove(pv);
                break;
            } else if (c instanceof SlideView sv && sv.getSlide() == node) {
                sv.getSlide().removeSubscriber(sv);
                tabs.remove(sv);
                break;
            }
        }
    }

    private void renameNodeTab(GraffNode node) {
        for (int i = 0; i < tabs.getTabCount(); i++) {
            Component c = tabs.getComponentAt(i);
            if ((c instanceof PresentationView pv && pv.getPresentation() == node) ||
                    (c instanceof SlideView sv && sv.getSlide() == node)) {
                tabs.setTitleAt(i, node.getName());
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
