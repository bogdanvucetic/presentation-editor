package raf.graffito.dsw.controller.state;

import java.awt.event.MouseEvent;

public class SelectState extends State {
    @Override
    public void mouseClicked(MouseEvent e) {
        context.clearSelectedElements();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        context.clearSelectedElements();
    }
}
