package raf.graffito.dsw.controller.state;

import raf.graffito.dsw.gui.swing.view.SlideView;
import raf.graffito.dsw.model.composite.SlideElement;
import raf.graffito.dsw.model.composite.Label;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Set;

public class ResizeState extends State {
    private SlideElement resizingElement = null;
    private Point lastPoint = null;

    private static final int HANDLE_SIZE = 10;

    @Override
    public void mousePressed(MouseEvent e) {
        SlideView sv = context.getCurrentSlideView();
        if (sv == null) return;

        Point p = sv.convertToSlidePoint(e.getPoint());

        resizingElement = getResizeElement(p, context.getSelectedElements());
        lastPoint = (resizingElement != null) ? p : null;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (resizingElement == null || lastPoint == null) return;

        SlideView sv = context.getCurrentSlideView();
        if (sv == null) return;

        Point current = sv.convertToSlidePoint(e.getPoint());

        int dx = current.x - lastPoint.x;
        int dy = current.y - lastPoint.y;

        if (resizingElement instanceof Label label) {

            int delta = dx + dy;
            int newFontSize = label.getFontSize() + delta / 4;
            label.setFontSize(newFontSize);

        } else {

            resizingElement.setWidth(resizingElement.getWidth() + dx);
            resizingElement.setHeight(resizingElement.getHeight() + dy);
        }


        lastPoint = current;

        sv.repaint();
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        resizingElement = null;
        lastPoint = null;
    }
    private SlideElement getResizeElement(Point p, Set<SlideElement> selected) {
        for (SlideElement el : selected) {
            int x = el.getXCoordinate();
            int y = el.getYCoordinate();
            int w = el.getWidth();
            int h = el.getHeight();

            Rectangle handle = new Rectangle(
                    x + w - HANDLE_SIZE,
                    y + h - HANDLE_SIZE,
                    HANDLE_SIZE,
                    HANDLE_SIZE
            );

            if (handle.contains(p)) {
                return el;
            }
        }
        return null;
    }
}
