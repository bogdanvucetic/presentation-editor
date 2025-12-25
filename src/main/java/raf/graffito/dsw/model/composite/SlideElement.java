package raf.graffito.dsw.model.composite;

public abstract class SlideElement extends GraffNode {
    private int xCoordinate;
    private int yCoordinate;
    private int width;
    private int height;
    private double rotationAngle;

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

    public int getYCoordinate() {
        return yCoordinate;
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

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public void setWidth(int width) {
        this.width = Math.max(5, width);
    }

    public void setHeight(int height) {
        this.height = Math.max(5, height);
    }

    public void setRotationAngle(double rotationAngle) {
        this.rotationAngle = rotationAngle;
    }
    public void setPosition(int x, int y) {
        this.xCoordinate = x;
        this.yCoordinate = y;
    }

    public void translate(int dx, int dy) {
        this.xCoordinate += dx;
        this.yCoordinate += dy;
    }
    public void rotateLeft() {
        rotationAngle -= 90;
        rotationAngle = (rotationAngle % 360 + 360) % 360;
    }

}
