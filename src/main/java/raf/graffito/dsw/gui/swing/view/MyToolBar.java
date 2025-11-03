package raf.graffito.dsw.gui.swing.view;

import raf.graffito.dsw.gui.swing.controller.AboutUsAction;
import raf.graffito.dsw.gui.swing.controller.ExitAction;

import javax.swing.*;

public class MyToolBar extends JToolBar {
    public MyToolBar() {
        super(HORIZONTAL);
        setFloatable(false);

        ExitAction exitAction = MainFrame.getInstance().getActionManager().getExitAction();
        add(exitAction);

        AboutUsAction aboutUsAction = MainFrame.getInstance().getActionManager().getAboutUsAction();
        add(aboutUsAction);
    }
}
