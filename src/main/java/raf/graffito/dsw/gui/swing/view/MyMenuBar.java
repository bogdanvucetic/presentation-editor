package raf.graffito.dsw.gui.swing.view;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MyMenuBar extends JMenuBar {
    public MyMenuBar() {
        JMenu fileMenu = new JMenu("Fajl");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        fileMenu.add(MainFrame.getInstance().getActionManager().getExitAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getCreateChildAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getRenameNodeAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getDeleteNodeAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getChangeProjectTitleAction());

        add(fileMenu);
    }
}
