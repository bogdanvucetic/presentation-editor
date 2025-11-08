package raf.graffito.dsw.controller;

import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.gui.swing.view.MainFrame;
import raf.graffito.dsw.gui.swing.view.ProjectView;
import raf.graffito.dsw.model.composite.Project;
import raf.graffito.dsw.model.decorator.ColorDecorator;
import raf.graffito.dsw.model.decorator.DecoratorRepository;
import raf.graffito.dsw.observer.message.MessageType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class OpenTabsAction extends AbstractGraffAction {
    public void actionPerformed(Project project) {
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

    @Override
    public void actionPerformed(ActionEvent e) { }
}
