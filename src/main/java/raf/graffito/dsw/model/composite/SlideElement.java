package raf.graffito.dsw.model.composite;

public abstract class SlideElement extends GraffNode {
    private int x;
    private int y;
    private int width;
    private int height;
    private double rotationAngle;

    public SlideElement(GraffNode parent, String name, int x, int y, int width, int height) {
        super(parent, name);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.rotationAngle = 0;
    }

    public SlideElement(SlideElement elem) {
        this(elem.getParent(), elem.getCloneName(), elem.getX() + 2, elem.getY() + 2, elem.getWidth(), elem.getHeight());
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
