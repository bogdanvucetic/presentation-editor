package raf.graffito.dsw.model.composite;

public class Presentation extends GraffNodeComposite {
    public Presentation(GraffNode parent, String name) {
        super(parent, name);
    }

    // TODO
    //  pretpostavljada je parent Project što po trenutnom dodavanju i ne mora da bude
    @Override
    public void addChild(GraffNode child) {
        if (child instanceof Slide) {
            super.addChild(child);
            ((Project) getParent()).incrementNumberOfSlides();
        }
    }
}
