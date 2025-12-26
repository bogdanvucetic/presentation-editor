package raf.graffito.dsw.model.composite;

public abstract class SlideElement extends GraffNode {
    private int xCoordinate;
    private int yCoordinate;
    private int width;
    private int height;
    private double rotationAngle;

    public SlideElement() {}

    public SlideElement(GraffNode parent, String name, int xCoordinate, int yCoordinate, int width, int height) {
        super(parent, name);
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.width = width;
        this.height = height;
        this.rotationAngle = 0;
    }

    public SlideElement(SlideElement elem) {
        this(elem.getParent(), elem.getCloneName(), elem.getXCoordinate() + 20, elem.getYCoordinate() + 20, elem.getWidth(), elem.getHeight());
        this.rotationAngle = elem.getRotationAngle();
    }

    public abstract SlideElement clone();

    private String getCloneName() {
        String currentName = getName();
        String baseName = currentName;
        int maxIndex = 0;

        int lastSpace = currentName.lastIndexOf(" ");
        if (lastSpace != -1) {
            String suffix = currentName.substring(lastSpace + 1);
            if (suffix.matches("\\d+")) {
                baseName = currentName.substring(0, lastSpace);
            }
        }

        GraffNodeComposite parent = (GraffNodeComposite) getParent();
        for (GraffNode child : parent.getChildren()) {
            String childName = child.getName();

            if (childName.startsWith(baseName + " ")) {
                try {
                    String numberPart = childName.substring(baseName.length() + 1);
                    int existingIndex = Integer.parseInt(numberPart);

                    if (existingIndex > maxIndex) {
                        maxIndex = existingIndex;
                    }
                } catch (NumberFormatException _) {

                }
            }
        }

        return baseName + " " + (maxIndex + 1);
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public void setXCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
        markProjectAsChanged();
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public void setYCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
        markProjectAsChanged();
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
        markProjectAsChanged();
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
        markProjectAsChanged();
    }

    public double getRotationAngle() {
        return rotationAngle;
    }

    public void translate(int dx, int dy) {
        this.xCoordinate += dx;
        this.yCoordinate += dy;
        markProjectAsChanged();
    }

    public void rotateLeft() {
        rotationAngle -= 90;
        rotationAngle = (rotationAngle % 360 + 360) % 360;
        markProjectAsChanged();
    }

    public void setRotationAngle(double rotationAngle) {
        this.rotationAngle = rotationAngle;
        markProjectAsChanged();
    }
}
