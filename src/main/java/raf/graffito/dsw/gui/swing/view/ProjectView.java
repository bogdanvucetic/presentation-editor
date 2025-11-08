package raf.graffito.dsw.gui.swing.view;

import raf.graffito.dsw.model.composite.Presentation;
import raf.graffito.dsw.model.composite.Project;
import raf.graffito.dsw.observer.model.ModelSubscriber;
import raf.graffito.dsw.observer.model.ModelUpdateEvent;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ProjectView extends JPanel implements ModelSubscriber {
    private JTabbedPane tabs;

    public ProjectView(List<Presentation> presentations, Project project) {
        setLayout(new BorderLayout());

        tabs = new JTabbedPane();
        Presentation firstPresentation = null;

        for (Presentation p : presentations) {
            if (firstPresentation == null){
                firstPresentation = p;
            }
            tabs.add(p.getName(), new JPanel());
        }

        add(tabs, BorderLayout.CENTER);
        add(new InfoPanel(project, firstPresentation), BorderLayout.EAST);
    }

    @Override
    public void update(ModelUpdateEvent event) {

    }
}
