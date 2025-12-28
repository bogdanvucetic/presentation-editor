package raf.graffito.dsw.gui.swing.painter;

import raf.graffito.dsw.model.composite.elements.Image;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class ImagePainter {
    public void paint(Graphics2D g2, Image element) {
        if (element == null || element.getImagePath() == null) return;

        int x = element.getXCoordinate();
        int y = element.getYCoordinate();
        int w = element.getWidth();
        int h = element.getHeight();

        Composite oldComposite = g2.getComposite();
        RenderingHints oldHints = g2.getRenderingHints();
        AffineTransform oldTransform = g2.getTransform();

        try {
            g2.setComposite(AlphaComposite.SrcOver);

            RenderingHints hints = new RenderingHints(null);
            hints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.addRenderingHints(hints);

            double angle = Math.toRadians(element.getRotationAngle());
            double cx = x + w / 2.0;
            double cy = y + h / 2.0;

            g2.rotate(angle, cx, cy);

            ImageIcon imageIcon = new ImageIcon(element.getImagePath());
            g2.drawImage(imageIcon.getImage(), x, y, w, h, null);
        } finally {
            g2.setComposite(oldComposite);
            g2.setRenderingHints(oldHints);
            g2.setTransform(oldTransform);
        }
    }
}
