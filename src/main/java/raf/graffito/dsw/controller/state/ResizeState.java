package raf.graffito.dsw.controller.state;

import raf.graffito.dsw.controller.commands.ResizeCommand;
import raf.graffito.dsw.gui.swing.view.SlideView;
import raf.graffito.dsw.model.composite.SlideElement;
import raf.graffito.dsw.model.composite.Label;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Set;

public class ResizeState extends State {
    private SlideElement resizingElement = null;
    private Point lastPoint = null;

    private int originalWidth;
    private int originalHeight;
    private int originalFontSize;

    private static final int HANDLE_SIZE = 10;

    @Override
    public void mousePressed(MouseEvent e) {
        SlideView sv = context.getCurrentSlideView();
        if (sv == null) return;

        Point p = sv.convertToSlidePoint(e.getPoint());

        resizingElement = getResizeElement(p, context.getSelectedElements());

        if (resizingElement != null) {
            lastPoint = p;

            originalWidth = resizingElement.getWidth();
            originalHeight = resizingElement.getHeight();
            if (resizingElement instanceof Label label) {
                originalFontSize = label.getFontSize();
            }

            sv.setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
        }
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
        if (resizingElement == null) return;

        SlideView sv = context.getCurrentSlideView();
        if (sv == null) return;

        int finalWidth = resizingElement.getWidth();
        int finalHeight = resizingElement.getHeight();
        Integer finalFontSize = (resizingElement instanceof Label l) ? l.getFontSize() : null;

        resizingElement.setWidth(originalWidth);
        resizingElement.setHeight(originalHeight);
        if (resizingElement instanceof Label label) {
            label.setFontSize(originalFontSize);
        }

        if (finalWidth != originalWidth || finalHeight != originalHeight || (finalFontSize != null && finalFontSize != originalFontSize)) {
            ResizeCommand command;
            if (resizingElement instanceof Label label && finalFontSize != null) {
                command = new ResizeCommand(sv.getSlide(), label, finalFontSize);
            } else {
                command = new ResizeCommand(sv.getSlide(), resizingElement, finalWidth, finalHeight);
            }
            context.execute(command);
        }

        resizingElement = null;
        lastPoint = null;
        sv.setCursor(Cursor.getDefaultCursor());
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
