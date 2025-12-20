package raf.graffito.dsw.model.composite;

public class Image extends SlideElement {
    public Image(Image elem) {
        super(elem);
    }

    @Override
    public SlideElement clone() {
        return new Image(this);
    }
}
