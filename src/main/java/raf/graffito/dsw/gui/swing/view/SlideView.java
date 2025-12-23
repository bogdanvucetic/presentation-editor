package raf.graffito.dsw.gui.swing.view;

import raf.graffito.dsw.controller.SlideViewListener;
import raf.graffito.dsw.gui.swing.painter.SlideLabelPainteer;
import raf.graffito.dsw.model.composite.GraffNode;
import raf.graffito.dsw.model.composite.Label;
import raf.graffito.dsw.model.composite.Slide;

import javax.swing.*;
import java.awt.*;

public class SlideView extends JComponent {
    private Slide slide;

    private static final int hPadding = 2;
    private static final int vPadding = 2;
    private final SlideLabelPainteer labelPainter = new SlideLabelPainteer();

    public SlideView(Slide slide) {
        this.slide = slide;
        setOpaque(false);

        SlideViewListener slideViewListener = new SlideViewListener();
        addMouseListener(slideViewListener);
        addMouseMotionListener(slideViewListener);
        addMouseWheelListener(slideViewListener);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (slide == null) return;

        Dimension size = slide.getResolution();

        Graphics2D g2 = (Graphics2D) g.create();
        try {
            int totalWidth = getWidth();
            int totalHeight = getHeight();

            // Available area AFTER padding
            int availableWidth = Math.max(1, totalWidth - 2 * hPadding);
            int availableHeight = Math.max(1, totalHeight - 2 * vPadding);

            // scale so 1600x900 fits into the available space
            double sx = availableWidth / (double) size.width;
            double sy = availableHeight / (double) size.height;
            double scale = Math.min(sx, sy);

            int drawW = (int) (size.width * scale);
            int drawH = (int) (size.height * scale);
            int offsetX = hPadding + (availableWidth - drawW) / 2;
            int offsetY = vPadding + (availableHeight - drawH) / 2;

            // move to where the slide should appear and scale it
            g2.translate(offsetX, offsetY);
            g2.scale(scale, scale);

            // draw the slide itself (white rectangle)
            g2.setColor(Color.WHITE);
            g2.fillRect(0, 0, size.width, size.height);

            g2.setColor(Color.LIGHT_GRAY);
            g2.drawRect(0, 0, size.width - 1, size.height - 1);

             for (GraffNode elem : slide.getChildren()) {
                 if (elem instanceof Label l) {
                     labelPainter.paint(g2, l);
                 }
             }

        } finally {
            g2.dispose();
        }
    }

    public Point convertToSlidePoint(Point viewPoint) {
        if (slide == null) return new Point(0, 0);

        Dimension size = slide.getResolution();

        int totalWidth = getWidth();
        int totalHeight = getHeight();

        int availableWidth = Math.max(1, totalWidth - 2 * hPadding);
        int availableHeight = Math.max(1, totalHeight - 2 * vPadding);

        double sx = availableWidth / (double) size.width;
        double sy = availableHeight / (double) size.height;
        double scale = Math.min(sx, sy);

        int drawW = (int) (size.width * scale);
        int drawH = (int) (size.height * scale);
        int offsetX = hPadding + (availableWidth - drawW) / 2;
        int offsetY = vPadding + (availableHeight - drawH) / 2;

        int slideX = (int) Math.round((viewPoint.x - offsetX) / scale);
        int slideY = (int) Math.round((viewPoint.y - offsetY) / scale);

        // Optional: clamp so you never go outside slide
        slideX = Math.max(0, Math.min(size.width, slideX));
        slideY = Math.max(0, Math.min(size.height, slideY));

        return new Point(slideX, slideY);
    }

    public Slide getSlide() {
        return slide;
    }
}
