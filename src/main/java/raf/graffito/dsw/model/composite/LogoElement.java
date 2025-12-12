package raf.graffito.dsw.model.composite;

public class LogoElement extends SlideElement {
    public LogoElement(LogoElement elem) {
        super(elem);
    }

    @Override
    public SlideElement clone() {
        return new LogoElement(this);
    }
}
