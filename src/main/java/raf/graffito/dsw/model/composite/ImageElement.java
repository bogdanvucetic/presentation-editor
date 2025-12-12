package raf.graffito.dsw.model.composite;

public class ImageElement extends SlideElement {
    public ImageElement(ImageElement elem) {
        super(elem);
    }

    @Override
    public SlideElement clone() {
        return new ImageElement(this);
    }
}
