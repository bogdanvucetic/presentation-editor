package raf.graffito.dsw.model.composite;

import java.awt.*;

public abstract class SlideElement extends GraffNode {
    private int xCoordinate;
    private int yCoordinate;
    private Dimension dimension;
    private double rotationAngle;

    public SlideElement(GraffNode parent, String name, int xCoordinate, int yCoordinate, Dimension dimension) {
        super(parent, name);
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.dimension = dimension;
        this.rotationAngle = 0;
    }

    public SlideElement(SlideElement elem) {
        this(elem.getParent(), elem.getCloneName(), elem.getXCoordinate() + 2, elem.getYCoordinate() + 2, elem.getDimension());
        this.rotationAngle = elem.getRotationAngle();
    }

    public abstract SlideElement clone();

    private String getCloneName() {
        String name = getName();
        int counter = 0;

        for (GraffNode node : ((GraffNodeComposite)getParent()).getChildren()){
            if (node.getName().equals(name)){
                counter++;
            }
        }

        return name + counter;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public double getRotationAngle() {
        return rotationAngle;
    }
}
