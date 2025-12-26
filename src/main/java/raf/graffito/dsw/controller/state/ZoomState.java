package raf.graffito.dsw.controller.state;

import raf.graffito.dsw.gui.swing.view.SlideView;

import java.awt.event.MouseWheelEvent;

public class ZoomState extends State {
    private static final double ZOOM_STEP = 0.1;
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        SlideView sv = context.getCurrentSlideView();
        if (sv == null) return;

        int rotation = e.getWheelRotation();

        if (rotation < 0) {
            // točkić gore → zoom in
            sv.zoom(ZOOM_STEP);
        } else {
            // točkić dole → zoom out
            sv.zoom(-ZOOM_STEP);
        }

    }
}
