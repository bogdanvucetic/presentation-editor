package raf.graffito.dsw.gui.swing.painter;

import raf.graffito.dsw.model.composite.Label;

import java.awt.*;

public class SlideLabelPainteer {
    public void paint(Graphics2D g2, Label label) {
//        g2.setFont(label.getFont());
//        g2.setColor(label.getColor());
//        g2.drawString(label.getText(), label.getXCoordinate(), label.getYCoordinate());

        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g2.setFont(label.getFont());
        g2.setColor(label.getColor());

        int x = label.getXCoordinate();
        int yTop = label.getYCoordinate();

        FontMetrics fm = g2.getFontMetrics();
        int baselineY = yTop + fm.getAscent();

        g2.drawString(label.getText(), x, baselineY);

        // (Optional) bounds for debugging/selection
//        Stroke old = g2.getStroke();
//        Color oldC = g2.getColor();
//        try {
//            g2.setColor(Color.BLUE);
//            g2.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{6}, 0));
//            g2.drawRect(x, yTop, label.getWidth(), label.getHeight());
//        } finally {
//            g2.setStroke(old);
//            g2.setColor(oldC);
//        }

        // hitbox render
//        Stroke old = g2.getStroke();
//
//        g2.setColor(Color.BLUE);
//        g2.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{6}, 0));
//
//        g2.drawRect(label.getXCoordinate(), label.getYCoordinate(), // kod teksta Y je baseline
//                label.getWidth(),
//                label.getHeight()
//        );
//
//        g2.setStroke(old);
    }
}
