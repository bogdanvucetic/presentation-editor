package raf.graffito.dsw.gui.swing.components;

import raf.graffito.dsw.gui.swing.frames.MainFrame;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MyMenuBar extends JMenuBar {
    public MyMenuBar() {
        JMenu fileMenu = new JMenu("Fajl");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        fileMenu.add(MainFrame.getInstance().getActionManager().getExitAction());
        fileMenu.addSeparator();
        fileMenu.add(MainFrame.getInstance().getActionManager().getUndoAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getRedoAction());
        fileMenu.addSeparator();
        fileMenu.add(MainFrame.getInstance().getActionManager().getOpenProjectAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getSaveProjectAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getSaveProjectAsAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getSaveAsTemplateAction());
        fileMenu.addSeparator();
        fileMenu.add(MainFrame.getInstance().getActionManager().getCreateChildAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getRenameNodeAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getDeleteNodeAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getChangeProjectTitleAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getLoadImageAction());

        add(fileMenu);
    }
}
