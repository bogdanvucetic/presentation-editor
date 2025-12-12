package raf.graffito.dsw.gui.swing.view;

import raf.graffito.dsw.model.composite.Slide;
import raf.graffito.dsw.model.composite.SlideElement;

import javax.swing.*;
import java.awt.*;

public class SlideView extends JComponent {
    private Slide slide;

    private static final Dimension size = new Dimension(1600, 900);
    private static final int hPadding = 2;
    private static final int vPadding = 2;

    public SlideView(Slide slide) {
        this.slide = slide;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (slide == null) return;

        Graphics2D g2 = (Graphics2D) g.create();
        try {
            int compW = getWidth();
            int compH = getHeight();

            // Available area AFTER padding
            int availW = Math.max(1, compW - 2 * hPadding);
            int availH = Math.max(1, compH - 2 * vPadding);

            // scale so 1600x900 fits into the available space
            double sx = availW / (double) size.width;
            double sy = availH / (double) size.height;
            double scale = Math.min(sx, sy);

            int drawW = (int) (size.width * scale);
            int drawH = (int) (size.height * scale);
            int offsetX = hPadding + (availW - drawW) / 2;
            int offsetY = vPadding + (availH - drawH) / 2;

            // move to where the slide should appear and scale it
            g2.translate(offsetX, offsetY);
            g2.scale(scale, scale);

            // draw the slide itself (white rectangle)
            g2.setColor(Color.WHITE);
            g2.fillRect(0, 0, size.width, size.height);

            g2.setColor(Color.LIGHT_GRAY);
            g2.drawRect(0, 0, size.width - 1, size.height - 1);

            // now draw slide elements in logical coordinates (0..1600, 0..900)
            // for (SlideElement el : slide.getChildren()) {
            //     el.paint(g2);
            // }

        } finally {
            g2.dispose();
        }
    }

    public Slide getSlide() {
        return slide;
    }
}
