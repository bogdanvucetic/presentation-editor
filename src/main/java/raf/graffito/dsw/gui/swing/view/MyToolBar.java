package raf.graffito.dsw.gui.swing.view;

import javax.swing.*;
import java.awt.*;

public class MyToolBar extends JToolBar {
    public MyToolBar() {
        super(HORIZONTAL);
        setFloatable(false);

        add(MainFrame.getInstance().getActionManager().getExitAction());
        add(MainFrame.getInstance().getActionManager().getAboutUsAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getCreateChildAction());
        add(MainFrame.getInstance().getActionManager().getRenameNodeAction());
        add(MainFrame.getInstance().getActionManager().getDeleteNodeAction());
        add(MainFrame.getInstance().getActionManager().getChangeProjectTitleAction());
        add(MainFrame.getInstance().getActionManager().getLoadImageAction());
        add(MainFrame.getInstance().getActionManager().getRotateSlideElementsAction());

        add(Box.createHorizontalGlue());
        JPanel selectorsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        selectorsPanel.setOpaque(false);
        selectorsPanel.add(new StrategySelector());
        selectorsPanel.add(new WindowModeSelector());
        add(selectorsPanel);
    }
}
