package raf.graffito.dsw.gui.swing.painter;

import raf.graffito.dsw.model.composite.Logo;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.List;

public class LogoPainter {
    public void paint(Graphics2D g2, Logo logo) {
        if (logo == null) return;

        int x = logo.getXCoordinate();
        int y = logo.getYCoordinate();
        int w = logo.getWidth();
        int h = logo.getHeight();

        if (w <= 0 || h <= 0) return;

        List<Point2D.Double> pts = logo.getPoints();
        if (pts == null || pts.size() < 3) return;

        AffineTransform oldTx = g2.getTransform();
        Composite oldComposite = g2.getComposite();
        RenderingHints oldHints = g2.getRenderingHints();

        try {
            RenderingHints hints = new RenderingHints(null);
            hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2.addRenderingHints(hints);

            g2.setComposite(AlphaComposite.SrcOver);

            double cx = x + w / 2.0;
            double cy = y + h / 2.0;

//            double angle = logo.getRotationAngle();
            double angle = Math.toRadians(logo.getRotationAngle());

            g2.rotate(angle, cx, cy);

            Path2D path = new Path2D.Double();
            Point2D first = pts.getFirst();
            path.moveTo(x + first.getX() * w, y + first.getY() * h);

            for (int i = 1; i < pts.size(); i++) {
                Point2D p = pts.get(i);
                path.lineTo(x + p.getX() * w, y + p.getY() * h);
            }
            path.closePath();

            g2.setColor(new Color(logo.getColorRGB()));
            g2.fill(path);
        } finally {
            g2.setTransform(oldTx);
            g2.setComposite(oldComposite);
            g2.setRenderingHints(oldHints);
        }
    }
}
