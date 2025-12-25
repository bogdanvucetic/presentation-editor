package raf.graffito.dsw.controller.state;


import raf.graffito.dsw.gui.swing.view.SlideView;
import raf.graffito.dsw.model.composite.SlideElement;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Set;

public class MoveState extends State {
    private SlideElement draggingElement = null;
    private Point lastPoint = null;
    @Override
    public void mousePressed(MouseEvent e) {
        SlideView sv = context.getCurrentSlideView();
        if (sv == null) return;

        Point p = sv.convertToSlidePoint(e.getPoint());

        // pomeramo samo ako smo kliknuli na NEKI selektovan element
        draggingElement = getElementUnderPoint(p, context.getSelectedElements());
        lastPoint = (draggingElement != null) ? p : null;
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        if (draggingElement == null || lastPoint == null) return;

        SlideView sv = context.getCurrentSlideView();
        if (sv == null) return;

        Point current = sv.convertToSlidePoint(e.getPoint());

        int dx = current.x - lastPoint.x;
        int dy = current.y - lastPoint.y;

        draggingElement.translate(dx, dy);

        lastPoint = current;

        // najprostije osvezavanje (view)
        sv.repaint();
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        draggingElement = null;
        lastPoint = null;
    }

    private SlideElement getElementUnderPoint(Point p, Set<SlideElement> selected) {
        // Ako ima više selektovanih, uzmi prvi na koji si kliknula
        for (SlideElement el : selected) {
            Rectangle r = new Rectangle(el.getXCoordinate(), el.getYCoordinate(), el.getWidth(), el.getHeight());
            if (r.contains(p)) {
                return el;
            }
        }
        return null;
    }
}
