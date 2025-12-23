package raf.graffito.dsw.model.composite;

public class Logo extends SlideElement {
    public Logo(GraffNode parent, String name, int xCoordinate, int yCoordinate, int width, int height) {
        super(parent, name, xCoordinate, yCoordinate, width, height);
    }

    public Logo(Logo elem) {
        super(elem);
    }

    @Override
    public SlideElement clone() {
        return new Logo(this);
    }
}
