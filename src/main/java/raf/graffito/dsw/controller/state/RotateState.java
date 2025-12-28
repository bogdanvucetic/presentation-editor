package raf.graffito.dsw.controller.state;

import raf.graffito.dsw.controller.commands.RotateCommand;
import raf.graffito.dsw.model.composite.SlideElement;

import java.awt.event.MouseEvent;

public class RotateState extends State {
    @Override
    public void onEnter() {
        if (context.getSelectedElements().isEmpty()) return;
        if (context.getCurrentSlideView() == null) return;

        RotateCommand command = new RotateCommand(context.getCurrentSlideView().getSlide(), context.getSelectedElements(), -90);
        context.execute(command);
    }
}
