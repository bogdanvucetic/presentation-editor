package raf.graffito.dsw.controller.listeners;

import raf.graffito.dsw.bridge.*;
import raf.graffito.dsw.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowModeListener implements ActionListener {
    private WindowMode currentMode;
    private WindowSizeImplementor implementor;

    public WindowModeListener() {
        this.implementor = new JFrameResizeImpl();
        this.currentMode = new NormalWindowMode(implementor);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JRadioButton source = (JRadioButton) e.getSource();

        if (source.getText().equals("Normalno")) {
            currentMode = new NormalWindowMode(implementor);
        } else if (source.getText().equals("Pun ekran")) {
            currentMode = new FullscreenWindowMode(implementor);
        } else if (source.getText().equals("Mali")) {
            currentMode = new SmallWindowMode(implementor);
        }

        currentMode.apply(MainFrame.getInstance(), MainFrame.getInstance().getNormalDimension());
    }
}
