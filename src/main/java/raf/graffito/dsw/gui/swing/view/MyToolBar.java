package raf.graffito.dsw.gui.swing.view;

import raf.graffito.dsw.gui.swing.controller.*;

import javax.swing.*;

public class MyToolBar extends JToolBar {
    public MyToolBar() {
        super(HORIZONTAL);
        setFloatable(false);

        ExitAction exitAction = MainFrame.getInstance().getActionManager().getExitAction();
        add(exitAction);

        AboutUsAction aboutUsAction = MainFrame.getInstance().getActionManager().getAboutUsAction();
        add(aboutUsAction);

        CreateNodeAction createNodeAction = MainFrame.getInstance().getActionManager().getCreateNodeAction();
        add(createNodeAction);

        RenameNodeAction renameNodeAction = MainFrame.getInstance().getActionManager().getRenameNodeAction();
        add(renameNodeAction);

        DeleteNodeAction deleteNodeAction = MainFrame.getInstance().getActionManager().getDeleteNodeAction();
        add(deleteNodeAction);

        ChangeProjectTitleAction changeProjectTitleAction = MainFrame.getInstance().getActionManager().getChangeProjectTitleAction();
        add(changeProjectTitleAction);
    }
}
