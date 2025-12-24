package raf.graffito.dsw.gui.swing.view;

import javax.swing.*;

public class MyToolBar extends JToolBar {
    public MyToolBar() {
        super(HORIZONTAL);
        setFloatable(false);

        add(MainFrame.getInstance().getActionManager().getExitAction());
        add(MainFrame.getInstance().getActionManager().getAboutUsAction());
        add(MainFrame.getInstance().getActionManager().getCreateChildAction());
        add(MainFrame.getInstance().getActionManager().getRenameNodeAction());
        add(MainFrame.getInstance().getActionManager().getDeleteNodeAction());
        add(MainFrame.getInstance().getActionManager().getChangeProjectTitleAction());
        add(MainFrame.getInstance().getActionManager().getLoadImageAction());
        add(MainFrame.getInstance().getActionManager().getRotateSlideElementsAction());
    }
}
