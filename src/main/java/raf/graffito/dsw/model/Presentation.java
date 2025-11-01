package raf.graffito.dsw.model;

public class Presentation extends GraffNodeComposite{
    public Presentation(GraffNode parent, String name) {
        super(parent, name);
    }

    @Override
    public void addChild(GraffNode child) {
        if (child instanceof Slide) {
            super.addChild(child);
            ((Project) getParent()).incrementNumberOfSlides();
        }
    }
}
