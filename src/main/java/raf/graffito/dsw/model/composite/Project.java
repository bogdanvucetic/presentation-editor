package raf.graffito.dsw.model.composite;

public class Project extends GraffNodeComposite {
    // title nije dodat jer već postoji jedinstven identifikator name
    private int numberOfSlides;

    public Project(GraffNode parent, String name) {
        super(parent, name);
        this.numberOfSlides = 0;
    }

    @Override
    public void addChild(GraffNode child) {
        if (child instanceof Presentation) {
            super.addChild(child);
        } else if (child instanceof Slide) {
            super.addChild(child);
            incrementNumberOfSlides();
        }
    }

    public void incrementNumberOfSlides(){
        numberOfSlides++;
    }
}
