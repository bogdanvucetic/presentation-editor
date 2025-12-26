package raf.graffito.dsw.gui.swing.painter;

import raf.graffito.dsw.model.composite.Label;

import java.awt.*;

public class LabelPainter {
    public void paint(Graphics2D g2, Label label) {
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g2.setFont(new Font(label.getFontName(), label.getFontStyle(), label.getFontSize()));
        g2.setColor(new Color(label.getColorRGB()));

        int x = label.getXCoordinate();
        int yTop = label.getYCoordinate();

        FontMetrics fm = g2.getFontMetrics();
        int baselineY = yTop + fm.getAscent();

        g2.drawString(label.getText(), x, baselineY);
    }
}
