package raf.graffito.dsw.controller.tree;

import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.gui.swing.tree.GraffTreeElem;
import raf.graffito.dsw.gui.swing.view.MainFrame;
import raf.graffito.dsw.gui.swing.view.PresentationView;
import raf.graffito.dsw.gui.swing.view.ProjectView;
import raf.graffito.dsw.model.composite.GraffNode;
import raf.graffito.dsw.model.composite.Presentation;
import raf.graffito.dsw.model.composite.Project;
import raf.graffito.dsw.model.composite.Slide;
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

        GraffNode lastNodeInPath = ((GraffTreeElem) path.getLastPathComponent()).getGraffNode();

        if (lastNodeInPath instanceof Project p) {
            openProjectTabs(p);
        } else if (lastNodeInPath instanceof Presentation p) {
            openPresentationTab(p);
        } else if (lastNodeInPath instanceof Slide s) {
            openSlideView(s);
        }
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

    private void openProjectTabs(Project project) {
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

    private void openPresentationTab(Presentation presentation) {
        Project p = (Project) presentation.getParent();
        ProjectView currentProjectView = MainFrame.getInstance().getProjectView();

        if (currentProjectView == null || currentProjectView.getProject() != p) {
            openProjectTabs(p);
        }

        JTabbedPane tabs = MainFrame.getInstance().getProjectView().getTabs();
        int idx = -1;

        for (int i = 0; i < tabs.getTabCount(); i++) {
            if (((PresentationView) tabs.getComponentAt(i)).getPresentation() == presentation) {
                idx = i;
                break;
            }
        }

        if (idx == -1) {
            return;
        }

        MainFrame.getInstance().getProjectView().getTabs().setSelectedIndex(idx);
    }

    private void openSlideView(Slide slide) {
        GraffNode parent = slide.getParent();

        if (parent instanceof Project) {
            MainFrame.getInstance().openSoloSlide(slide);
        } else if (parent instanceof Presentation p) {
            openPresentationTab(p);
            PresentationView presentationView = (PresentationView) MainFrame.getInstance().getProjectView().getTabs().getSelectedComponent();

            if (presentationView == null) {
                return;
            }

            presentationView.setSelectedSlide(slide);
        }
    }
}
