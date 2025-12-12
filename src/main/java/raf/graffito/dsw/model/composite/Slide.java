package raf.graffito.dsw.model.composite;

public class Slide extends GraffNodeComposite {
    public Slide(GraffNode parent, String name) {
        super(parent, name);
    }

    @Override
    public void addChild(GraffNode child) {
        if (!(child instanceof SlideElement)) {
            throw new IllegalArgumentException("ELement ne može da se doda na slajd.");
        }

        super.addChild(child);
    }
}
