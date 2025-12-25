package raf.graffito.dsw.model.composite;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Image extends SlideElement {
    @JsonIgnore
    private java.awt.Image image;

    public Image() {}

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

    public void setImage(java.awt.Image image) {
        this.image = image;
    }
}
