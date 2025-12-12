package raf.graffito.dsw.model.composite;

public class LabelElement extends SlideElement {
    public LabelElement(LabelElement elem) {
        super(elem);
    }

    @Override
    public SlideElement clone() {
        return new LabelElement(this);
    }
}
