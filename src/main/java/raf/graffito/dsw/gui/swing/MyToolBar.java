package raf.graffito.dsw.gui.swing;

import raf.graffito.dsw.controller.ActionManager;
import raf.graffito.dsw.controller.ExitAction;

import javax.swing.*;

public class MyToolBar extends JToolBar {
    public MyToolBar(ActionManager actionManager) {
        super(HORIZONTAL);
        setFloatable(false);

        ExitAction exitAction = actionManager.getExitAction();
        add(exitAction);
    }
}
