package raf.graffito.dsw.controller.listeners;

import raf.graffito.dsw.controller.EditorContext;

import java.awt.event.*;

public class SlideViewListener implements MouseListener, MouseMotionListener, MouseWheelListener {
    private EditorContext context = EditorContext.getInstance();

    @Override
    public void mouseClicked(MouseEvent e) {
        context.getCurrentState().mouseClicked(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        context.getCurrentState().mouseDragged(e);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        context.getCurrentState().mouseWheelMoved(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        context.getCurrentState().mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        context.getCurrentState().mouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
