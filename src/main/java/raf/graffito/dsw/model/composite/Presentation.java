package raf.graffito.dsw.model.composite;

public class Presentation extends GraffNodeComposite {
    public Presentation(GraffNode parent, String name) {
        super(parent, name);
    }

    @Override
    public void addChild(GraffNode child) {
        if (!(child instanceof Slide)) {
            throw new IllegalArgumentException("ELement ne može da se doda na prezentaciju.");
        }

        super.addChild(child);
        ((Project) getParent()).incrementNumberOfSlides();
    }
}
