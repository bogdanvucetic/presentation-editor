package raf.graffito.dsw.controller.state;

import raf.graffito.dsw.controller.EditorContext;

import java.awt.event.MouseEvent;

public class SelectState extends State {
    @Override
    public void mouseClicked(MouseEvent e) {
        EditorContext context = EditorContext.getInstance();
        context.clearSelectedElements();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        EditorContext context = EditorContext.getInstance();
        context.clearSelectedElements();
    }
}
