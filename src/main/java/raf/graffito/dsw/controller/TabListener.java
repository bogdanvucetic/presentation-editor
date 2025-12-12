package raf.graffito.dsw.controller;

import raf.graffito.dsw.gui.swing.view.InfoPanel;
import raf.graffito.dsw.gui.swing.view.MainFrame;
import raf.graffito.dsw.gui.swing.view.PresentationView;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TabListener implements ChangeListener {
    @Override
    public void stateChanged(ChangeEvent e) {
        if (MainFrame.getInstance().getProjectView() == null) {
            return;
        }

        JTabbedPane tabs = MainFrame.getInstance().getProjectView().getTabs();
        InfoPanel infoPanel = MainFrame.getInstance().getInfoPanel();

        if (tabs.getSelectedIndex() != -1) {
            infoPanel.setPresentation(((PresentationView) tabs.getSelectedComponent()).getPresentation());
        } else {
            infoPanel.setPresentation(null);
        }
    }
}
