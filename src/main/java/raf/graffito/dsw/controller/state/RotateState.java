package raf.graffito.dsw.controller.state;

import raf.graffito.dsw.model.composite.SlideElement;

import java.awt.event.MouseEvent;

public class RotateState extends State {
    @Override
    public void mouseDragged(MouseEvent e) {

    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if (context.getSelectedElements().isEmpty()) return;

        for (SlideElement element : context.getSelectedElements()) {
            element.rotateLeft();
        }

        if (context.getCurrentSlideView() != null) {
            context.getCurrentSlideView().repaint();
        }
    }
}
