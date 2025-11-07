package raf.graffito.dsw.gui.swing.view;

import raf.graffito.dsw.gui.swing.tabs.InfoPanel;
import raf.graffito.dsw.model.composite.Presentation;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainPanel extends JPanel {
    public MainPanel(List<Presentation> presentations) {
        setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();

        for (Presentation p : presentations) {
            tabbedPane.add(p.getName(), new JPanel());
        }

        add(tabbedPane, BorderLayout.CENTER);
        add(new InfoPanel(), BorderLayout.EAST);
    }
}
