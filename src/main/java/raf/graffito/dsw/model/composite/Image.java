package raf.graffito.dsw.model.composite;

public class Image extends SlideElement {
    private java.awt.Image image;

    public Image(java.awt.Image image, GraffNode parent, String name, int xCoordinate, int yCoordinate, int width, int height) {
        super(parent, name, xCoordinate, yCoordinate, width, height);
        this.image = image;
    }

    public Image(Image elem) {
        super(elem);
        this.image = elem.getImage();
    }

    @Override
    public SlideElement clone() {
        return new Image(this);
    }

    public java.awt.Image getImage() {
        return image;
    }
}
