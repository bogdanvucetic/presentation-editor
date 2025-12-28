package raf.graffito.dsw.controller.state;

import raf.graffito.dsw.model.composite.GraffNode;
import raf.graffito.dsw.model.composite.elements.SlideElement;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

public class SelectState extends State {
    private Point2D dragStartPoint;
    private Point2D dragCurrentPoint;

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
    public void mousePressed(MouseEvent e) {
        Point2D p = context.getCurrentSlideView().convertToSlidePoint(e.getPoint());
        dragStartPoint = p;
        dragCurrentPoint = null;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point2D p = context.getCurrentSlideView().convertToSlidePoint(e.getPoint());
        dragCurrentPoint = p;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (dragStartPoint != null && dragCurrentPoint != null) {
            int x1 = (int) Math.round(Math.min(dragStartPoint.getX(), dragCurrentPoint.getX()));
            int y1 = (int) Math.round(Math.min(dragStartPoint.getY(), dragCurrentPoint.getY()));
            int x2 = (int) Math.round(Math.max(dragStartPoint.getX(), dragCurrentPoint.getX()));
            int y2 = (int) Math.round(Math.max(dragStartPoint.getY(), dragCurrentPoint.getY()));

            context.clearSelectedElements();

            for (GraffNode child : context.getCurrentSlideView().getSlide().getChildren()) {
                if (!(child instanceof SlideElement elem)) {
                    continue;
                }

                if (intersects(elem, x1, y1, x2, y2)) {
                    context.addSelectedElement(elem);
                }
            }
        }

        dragStartPoint = null;
        dragCurrentPoint = null;
    }

    private boolean contains(SlideElement e, int x, int y) {
        int xCoordinate = e.getXCoordinate();
        int yCoordinate = e.getYCoordinate();
        int width = e.getWidth();
        int height = e.getHeight();

        return xCoordinate <= x && x <= xCoordinate + width && yCoordinate <= y && y <= yCoordinate + height;
    }

    private boolean intersects(SlideElement elem, int boxX1, int boxY1, int boxX2, int boxY2) {
        int elemX1 = elem.getXCoordinate();
        int elemY1 = elem.getYCoordinate();
        int elemX2 = elemX1 + elem.getWidth();
        int elemY2 = elemY1 + elem.getHeight();

        return !(elemX2 < boxX1 || elemX1 > boxX2 || elemY2 < boxY1 || elemY1 > boxY2);
    }

}
