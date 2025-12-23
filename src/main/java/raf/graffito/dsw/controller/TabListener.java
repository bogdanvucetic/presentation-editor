package raf.graffito.dsw.controller;

import raf.graffito.dsw.gui.swing.view.InfoPanel;
import raf.graffito.dsw.gui.swing.view.MainFrame;
import raf.graffito.dsw.gui.swing.view.PresentationView;
import raf.graffito.dsw.gui.swing.view.SlideView;

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
            context.setCurrentSlideView(null);
        }
    }
}
