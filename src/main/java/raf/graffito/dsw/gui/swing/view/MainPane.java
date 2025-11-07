package raf.graffito.dsw.gui.swing.view;

import raf.graffito.dsw.model.composite.GraffNode;
import raf.graffito.dsw.model.composite.Presentation;
import raf.graffito.dsw.model.composite.Project;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainPane extends JPanel {
    public MainPane(List<Presentation> presentations) {
        setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();

        for (Presentation p : presentations) {
            tabbedPane.add(p.getName(), new JPanel());
        }

        add(tabbedPane, BorderLayout.CENTER);
        add(new JPanel(), BorderLayout.EAST);
    }
}
