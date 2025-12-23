package raf.graffito.dsw.controller;

import java.awt.event.*;

public class SlideViewListener implements MouseListener, MouseMotionListener, MouseWheelListener {
    private EditorContext editorContext = EditorContext.getInstance();

    @Override
    public void mouseClicked(MouseEvent e) {
        editorContext.getCurrentState().mouseClicked(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        editorContext.getCurrentState().mouseDragged(e);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        editorContext.getCurrentState().mouseWheelMoved(e);
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
    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
