package raf.graffito.dsw.controller;

import raf.graffito.dsw.controller.state.SlideEditorSession;

import java.awt.event.*;

public class SlideViewListener implements MouseListener, MouseMotionListener, MouseWheelListener {
    private SlideEditorSession slideEditorSession = SlideEditorSession.getInstance();

    @Override
    public void mouseClicked(MouseEvent e) {
        slideEditorSession.getCurrentState().mouseClicked(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        slideEditorSession.getCurrentState().mouseDragged(e);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        slideEditorSession.getCurrentState().mouseWheelMoved(e);
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
