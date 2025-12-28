package raf.graffito.dsw.gui.swing.painter;

import raf.graffito.dsw.model.composite.elements.Label;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class LabelPainter {
    public void paint(Graphics2D g2, Label label) {
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        AffineTransform oldTransform = g2.getTransform();

        g2.setFont(new Font(label.getFontName(), label.getFontStyle(), label.getFontSize()));
        g2.setColor(new Color(label.getColorRGB()));

        int x = label.getXCoordinate();
        int yTop = label.getYCoordinate();

        FontMetrics fm = g2.getFontMetrics();

        int textWidth = fm.stringWidth(label.getText());
        int textHeight = fm.getHeight();

        if (label.getWidth() != textWidth || label.getHeight() != textHeight) {
            label.setWidth(textWidth);
            label.setHeight(textHeight);
        }

        int baselineY = yTop + fm.getAscent();
        //  ROTACIJA OKO CENTRA TEKSTA
        double angle = Math.toRadians(label.getRotationAngle());
        double cx = x + textWidth / 2.0;
        double cy = yTop + textHeight / 2.0;

        g2.rotate(angle, cx, cy);

        g2.drawString(label.getText(), x, baselineY);
        g2.setTransform(oldTransform);
    }
}
