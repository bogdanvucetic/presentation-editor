package raf.graffito.dsw.gui.swing.view;

import raf.graffito.dsw.model.composite.Presentation;
import raf.graffito.dsw.model.composite.Project;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainPanel extends JPanel {
    private JTabbedPane tabs;

    public MainPanel(List<Presentation> presentations, Project project) {
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
}
