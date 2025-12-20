package raf.graffito.dsw.model.composite;

public class Label extends SlideElement {
    public Label(Label elem) {
        super(elem);
    }

    @Override
    public SlideElement clone() {
        return new Label(this);
    }
}
