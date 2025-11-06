package raf.graffito.dsw.gui.swing.Tabovi;

import java.awt.Color;

/** Jednostavan čuvar boje za prezentaciju. */
public class ColorDecorator {
    private Color color;

    public ColorDecorator(Color color) { this.color = color; }

    public Color getColor() { return color; }

    public void setColor(Color color) { this.color = color; }
}
