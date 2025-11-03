package raf.graffito.dsw.gui.swing.view;



import raf.graffito.dsw.gui.swing.controller.ExitAction;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MyMenuBar extends JMenuBar {
    public MyMenuBar() {
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        ExitAction exitAction = MainFrame.getInstance().getActionManager().getExitAction();
        fileMenu.add(exitAction);
        add(fileMenu);
    }
}
