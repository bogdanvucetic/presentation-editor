package raf.graffito.dsw.model.composite.elements;

import raf.graffito.dsw.model.composite.GraffNode;

public class Image extends SlideElement {
    private String imagePath;

    public Image() {}

    public Image(String imagePath, GraffNode parent, String name, int xCoordinate, int yCoordinate, int width, int height) {
        super(parent, name, xCoordinate, yCoordinate, width, height);
        this.imagePath = imagePath;
    }

    public Image(Image elem) {
        super(elem);
        this.imagePath = elem.getImagePath();
    }

    @Override
    public SlideElement clone() {
        return new Image(this);
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
