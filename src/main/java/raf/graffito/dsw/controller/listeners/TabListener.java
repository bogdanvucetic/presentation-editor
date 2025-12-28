package raf.graffito.dsw.controller.listeners;

import raf.graffito.dsw.controller.EditorContext;
import raf.graffito.dsw.gui.swing.components.InfoPanel;
import raf.graffito.dsw.gui.swing.frames.MainFrame;
import raf.graffito.dsw.gui.swing.frames.PresentationView;
import raf.graffito.dsw.gui.swing.frames.SlideView;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class TabListener implements ChangeListener {
    @Override
    public void stateChanged(ChangeEvent e) {
        Component selected = ((JTabbedPane) e.getSource()).getSelectedComponent();
        EditorContext context = EditorContext.getInstance();
        InfoPanel infoPanel = MainFrame.getInstance().getInfoPanel();

        if (selected instanceof PresentationView pv) {
            infoPanel.setPresentation(pv.getPresentation());
            context.setCurrentSlideView(pv.getCurrentSlideView());
        } else if (selected instanceof SlideView sv) {
            infoPanel.setPresentation(null);
            context.setCurrentSlideView(sv);
        } else {
            infoPanel.setPresentation(null);
            context.setCurrentSlideView(null);
        }
    }
}
