package raf.graffito.dsw.controller.listeners;

import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.gui.swing.tree.GraffTreeElem;
import raf.graffito.dsw.gui.swing.view.MainFrame;
import raf.graffito.dsw.gui.swing.view.ProjectView;
import raf.graffito.dsw.model.composite.GraffNode;
import raf.graffito.dsw.model.composite.Project;
import raf.graffito.dsw.model.composite.SlideElement;
import raf.graffito.dsw.model.decorator.ColorDecorator;
import raf.graffito.dsw.model.decorator.DecoratorRepository;
import raf.graffito.dsw.observer.message.MessageType;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GraffTreeListener implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() != 2) {
            return;
        }

        TreePath path = ((JTree) MainFrame.getInstance().getGraffTree()).getPathForLocation(e.getX(), e.getY());

        if (path == null) {
            return;
        }

        // todo fix: dodao samo kako bih izbegao bag kada se ne otvara ispravan slajd kada kliknemo na SlideElement, vrlo glupa implementacija
        if (((GraffTreeElem) path.getLastPathComponent()).getGraffNode() instanceof SlideElement) {
            return;
        }

        openTabFor(((GraffTreeElem) path.getLastPathComponent()).getGraffNode());
    }

    private void openTabFor(GraffNode graffNode) {
        Project targetProject = findTargetProject(graffNode);

        if (targetProject == null) {
            return;
        }

        ProjectView currentView = MainFrame.getInstance().getCurrentProjectView();

        if (currentView == null || currentView.getProject() != targetProject) {
            openProject(targetProject);
            currentView = MainFrame.getInstance().getCurrentProjectView();
        }

        if (currentView != null && !(graffNode instanceof Project)) {
            currentView.openTabFor(graffNode);
        }
    }

    private void openProject(Project project) {
        DecoratorRepository decoratorRepository = ApplicationFramework.getInstance().getGraffRepository().getDecoratorRepository();

        ColorDecorator decorator = decoratorRepository.getColorDecoratorFor(project);
        if (decorator != null) {
            MainFrame.getInstance().openProject(new ProjectView(project, decorator.getColor()));
            return;
        }

        Color color = JColorChooser.showDialog(MainFrame.getInstance(), "Izaberi boju tabova projekta", Color.WHITE);

        if (color == null) {
            return;
        }

        if (decoratorRepository.isUsed(color)) {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage("Izabrana boja je iskorišćena.", MessageType.ERROR);
            return;
        }

        decoratorRepository.addDecorator(new ColorDecorator(project, color));
        MainFrame.getInstance().openProject(new ProjectView(project, color));
    }

    private Project findTargetProject(GraffNode node) {
        GraffNode current = node;

        while (current != null && !(current instanceof Project)) {
            current = current.getParent();
        }

        return (Project) current;
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
