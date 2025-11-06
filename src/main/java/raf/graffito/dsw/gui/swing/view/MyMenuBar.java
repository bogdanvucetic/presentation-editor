package raf.graffito.dsw.gui.swing.view;



import raf.graffito.dsw.controller.*;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MyMenuBar extends JMenuBar {
    public MyMenuBar() {
        JMenu fileMenu = new JMenu("Fajl");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        ExitAction exitAction = MainFrame.getInstance().getActionManager().getExitAction();
        fileMenu.add(exitAction);

        CreateNodeAction createNodeAction = MainFrame.getInstance().getActionManager().getCreateNodeAction();
        fileMenu.add(createNodeAction);

        RenameNodeAction renameNodeAction = MainFrame.getInstance().getActionManager().getRenameNodeAction();
        fileMenu.add(renameNodeAction);

        DeleteNodeAction deleteNodeAction = MainFrame.getInstance().getActionManager().getDeleteNodeAction();
        fileMenu.add(deleteNodeAction);

        ChangeProjectTitleAction changeProjectTitleAction = MainFrame.getInstance().getActionManager().getChangeProjectTitleAction();
        fileMenu.add(changeProjectTitleAction);

        add(fileMenu);
    }
}
