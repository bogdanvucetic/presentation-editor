package raf.graffito.dsw.model.composite;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Logo extends SlideElement {
    private List<Point2D> points;
    private Color color;

    public Logo() {}

    public Logo(GraffNode parent, String name, int xCoordinate, int yCoordinate, int width, int height) {
        super(parent, name, xCoordinate, yCoordinate, width, height);
        this.points = getDefaultPoints();
        this.color = getDefaultColor();
    }

    public Logo(Logo elem) {
        super(elem);
        this.points = getDefaultPoints();
        this.color = elem.getColor();
    }

    public static List<Point2D> getDefaultPoints() {
        List<Point2D> points = new ArrayList<>();
        points.add(new Point2D.Double(0, 0.17));
        points.add(new Point2D.Double(0.5, 0));
        points.add(new Point2D.Double(1, 0.33));
        points.add(new Point2D.Double(0.67, 1));
        points.add(new Point2D.Double(0.17, 0.83));
        return points;
    }

    public static Color getDefaultColor() {
        return Color.BLUE;
    }

    @Override
    public SlideElement clone() {
        return new Logo(this);
    }

    public List<Point2D> getPoints() {
        return points;
    }

    public Color getColor() {
        return color;
    }
}
