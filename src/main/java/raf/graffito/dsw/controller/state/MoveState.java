package raf.graffito.dsw.controller.state;


import raf.graffito.dsw.controller.commands.MoveCommand;
import raf.graffito.dsw.gui.swing.view.SlideView;
import raf.graffito.dsw.model.composite.SlideElement;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;

public class MoveState extends State {
    private Point startPoint = null;
    private Point lastPoint = null;
    private Set<SlideElement> movingElements = null;

    @Override
    public void mousePressed(MouseEvent e) {
        SlideView sv = context.getCurrentSlideView();
        if (sv == null) return;

        Point p = sv.convertToSlidePoint(e.getPoint());
        SlideElement clickedElement = getElementUnderPoint(p, context.getSelectedElements());

        if (clickedElement != null) {
            startPoint = p;
            lastPoint = p;
            movingElements = new HashSet<SlideElement>(context.getSelectedElements());
        }
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        if (movingElements == null || lastPoint == null) return;

        SlideView sv = context.getCurrentSlideView();
        if (sv == null) return;

        Point current = sv.convertToSlidePoint(e.getPoint());

        int dx = current.x - lastPoint.x;
        int dy = current.y - lastPoint.y;

        for (SlideElement element : movingElements) {
            element.translate(dx, dy);
        }

        lastPoint = current;
        sv.repaint();
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        if (startPoint == null || movingElements == null) return;

        SlideView sv = context.getCurrentSlideView();
        if (sv == null) return;

        Point endPoint = sv.convertToSlidePoint(e.getPoint());
        int totalDx = endPoint.x - startPoint.x;
        int totalDy = endPoint.y - startPoint.y;

        if (totalDx != 0 || totalDy != 0) {
            for (SlideElement element : movingElements) {
                element.translate(-totalDx, -totalDy);
            }

            MoveCommand cmd = new MoveCommand(sv.getSlide(), movingElements, totalDx, totalDy);
            context.execute(cmd);
        }

        startPoint = null;
        lastPoint = null;
        movingElements = null;
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
