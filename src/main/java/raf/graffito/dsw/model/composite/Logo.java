package raf.graffito.dsw.model.composite;

public class Logo extends SlideElement {
    public Logo(Logo elem) {
        super(elem);
    }

    @Override
    public SlideElement clone() {
        return new Logo(this);
    }
}
