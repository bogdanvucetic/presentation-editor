package raf.graffito.dsw.gui.swing.view;

import raf.graffito.dsw.controller.AbstractGraffAction;
import raf.graffito.dsw.controller.ActionManager;
import raf.graffito.dsw.model.composite.GraffNode;
import raf.graffito.dsw.model.composite.Presentation;
import raf.graffito.dsw.model.composite.Project;
import raf.graffito.dsw.observer.model.ModelSubscriber;
import raf.graffito.dsw.observer.model.ModelEvent;
import raf.graffito.dsw.observer.model.ModelEventType;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel implements ModelSubscriber {
    private Project project;
    private Presentation presentation;
    private JLabel projectLabel = new JLabel("-");
    private JLabel presentationLabel = new JLabel("-");

    private JPanel imagesPanel;
    private JPanel actionsPanel;

    public InfoPanel() {
        setLayout(new BorderLayout(0, 8));

        buildHeaderPanel();
        buildImagePanel();
        buildToolPanel();
    }

    private void buildHeaderPanel() {
        JPanel top = new JPanel(new BorderLayout());

        JPanel header = new JPanel();
        header.setLayout(new GridLayout(2, 1, 5, 5));
        header.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        header.add(center(projectLabel));
        header.add(center(presentationLabel));

        top.add(header, BorderLayout.CENTER);
        top.add(new JSeparator(), BorderLayout.SOUTH);
        add(top, BorderLayout.NORTH);
    }

    private void buildImagePanel() {
        imagesPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        JScrollPane scroll = new JScrollPane(imagesPanel);
        scroll.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
        add(scroll, BorderLayout.CENTER);
    }

    private void buildToolPanel() {
        actionsPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        actionsPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        ActionManager actionManager = MainFrame.getInstance().getActionManager();
        actionsPanel.add(createToolButton(actionManager.getAddSlideElementsAction()));
        actionsPanel.add(createToolButton(actionManager.getSelectSlideElementsAction()));
        actionsPanel.add(createToolButton(actionManager.getResizeSlideElementsAction()));
        actionsPanel.add(createToolButton(actionManager.getMoveSlideElementsAction()));
        actionsPanel.add(createToolButton(actionManager.getZoomSlideAction()));
        actionsPanel.add(createToolButton(actionManager.getDeleteSlideElementsAction()));

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.add(new JSeparator(), BorderLayout.NORTH);
        bottom.add(actionsPanel, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
    }

    private Component center(JComponent c) {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        p.add(c);
        return p;
    }

    @Override
    public void update(ModelEvent event) {
        GraffNode node = event.getNode();
        ModelEventType type = event.getType();

        switch (type) {
            case ADD_CHILD -> { }
            case REMOVE_CHILD -> {
                if (node == project) {
                    setProject(null);
                    setPresentation(null);
                }

                else if (node == presentation) {
                    setPresentation(null);
                }
            } case RENAME -> {
                if (node == project) {
                    projectLabel.setText(project.getName());
                }

                else if (node == presentation) {
                    presentationLabel.setText(presentation.getName());
                }
            }
        }
    }

    private JButton createToolButton(AbstractGraffAction action) {
        JButton b = new JButton();
        b.setAction(action);
        b.setText("");
        b.setContentAreaFilled(false);

        Icon icon = (Icon) action.getValue(Action.SMALL_ICON);
        if (icon != null) {
            int size = Math.max(icon.getIconWidth(), icon.getIconHeight());
            size += 5;
            Dimension d = new Dimension(size, size);
            b.setPreferredSize(d);
        }

        return b;
    }

    public void setProject(Project project) {
        if (this.project != null){
            this.project.removeSubscriber(this);
        }

        this.project = project;

        if (project != null) {
            this.project.addSubscriber(this);
            projectLabel.setText(project.getName());
        } else {
            projectLabel.setText("-");
        }
    }

    public void setPresentation(Presentation presentation) {
        if (this.presentation != null){
            this.presentation.removeSubscriber(this);
        }

        this.presentation = presentation;

        if (presentation != null) {
            this.presentation.addSubscriber(this);
            presentationLabel.setText(presentation.getName());
        } else {
            presentationLabel.setText("-");
        }
    }

    public void setProjectLabel(String label) {
        projectLabel.setText(label);
    }
}
