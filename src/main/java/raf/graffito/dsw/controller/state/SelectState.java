package raf.graffito.dsw.controller.state;

import raf.graffito.dsw.model.composite.GraffNode;
import raf.graffito.dsw.model.composite.elements.SlideElement;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

public class SelectState extends State {
    @Override
    public void mouseClicked(MouseEvent e) {
        context.clearSelectedElements();

        Point2D p = context.getCurrentSlideView().convertToSlidePoint(e.getPoint());
        int sx = (int) Math.round(p.getX());
        int sy = (int) Math.round(p.getY());

        for (GraffNode child : context.getCurrentSlideView().getSlide().getChildren()) {
            if (!(child instanceof SlideElement elem)) {
                continue;
            }

            if (contains(elem, sx, sy)) {
                context.addSelectedElement(elem);
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        context.clearSelectedElements();
    }

    private boolean contains(SlideElement e, int x, int y) {
        int xCoordinate = e.getXCoordinate();
        int yCoordinate = e.getYCoordinate();
        int width = e.getWidth();
        int height = e.getHeight();

        return xCoordinate <= x && x <= xCoordinate + width && yCoordinate <= y && y <= yCoordinate + height;
    }
}
