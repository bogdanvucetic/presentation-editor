package raf.graffito.dsw.model.composite;

public abstract class SlideElement extends GraffLeaf {
    private int x;
    private int y;
    private int width;
    private int height;
    private double rotationAngle = 0;

    public SlideElement(GraffNode parent, String name, int x, int y, int width, int height) {
        super(parent, name);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double getRotationAngle() {
        return rotationAngle;
    }
}
