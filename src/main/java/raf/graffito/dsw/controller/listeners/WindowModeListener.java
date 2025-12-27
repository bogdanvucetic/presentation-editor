package raf.graffito.dsw.controller.listeners;

import raf.graffito.dsw.mediator.WindowModeMediator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowModeListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = ((JRadioButton) e.getSource()).getText();
        WindowModeMediator.getInstance().changeMode(command);
    }
}
