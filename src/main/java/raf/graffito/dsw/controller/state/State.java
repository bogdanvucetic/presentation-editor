package raf.graffito.dsw.controller.state;

import raf.graffito.dsw.controller.EditorContext;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public abstract class State {
    protected EditorContext context = EditorContext.getInstance();

    public void onEnter() {

    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseDragged(MouseEvent e) {

    }

    public void mouseWheelMoved(MouseWheelEvent e) {

    }

    public void thumbnailClicked(MouseEvent e) {

    }
}
