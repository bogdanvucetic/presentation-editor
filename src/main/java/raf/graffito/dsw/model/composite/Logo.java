package raf.graffito.dsw.model.composite;

import raf.graffito.dsw.observer.model.ModelUpdate;
import raf.graffito.dsw.observer.model.UpdateType;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Logo extends SlideElement {
    private List<Point2D.Double> points;
    private int colorRGB;

    public Logo() {}

    public Logo(GraffNode parent, String name, int xCoordinate, int yCoordinate, int width, int height) {
        super(parent, name, xCoordinate, yCoordinate, width, height);
        this.points = getDefaultPoints();
        this.colorRGB = getDefaultColorRGB();
    }

    public Logo(Logo elem) {
        super(elem);
        this.points = getDefaultPoints();
        this.colorRGB = elem.getColorRGB();
    }

    public static List<Point2D.Double> getDefaultPoints() {
        List<Point2D.Double> points = new ArrayList<>();
        points.add(new Point2D.Double(0, 0.17));
        points.add(new Point2D.Double(0.5, 0));
        points.add(new Point2D.Double(1, 0.33));
        points.add(new Point2D.Double(0.67, 1));
        points.add(new Point2D.Double(0.17, 0.83));
        return points;
    }

    @Override
    public SlideElement clone() {
        return new Logo(this);
    }

    public static int getDefaultColorRGB() {
        return Color.BLUE.getRGB();
    }

    public List<Point2D.Double> getPoints() {
        return points;
    }

    public void setPoints(List<Point2D.Double> points) {
        this.points = points;
        notifySubscribers(new ModelUpdate(UpdateType.PROPERTY_UPDATED, this));
        markProjectAsChanged();
    }

    public int getColorRGB() {
        return colorRGB;
    }

    public void setColorRGB(int colorRGB) {
        this.colorRGB = colorRGB;
        notifySubscribers(new ModelUpdate(UpdateType.PROPERTY_UPDATED, this));
        markProjectAsChanged();
    }
}
