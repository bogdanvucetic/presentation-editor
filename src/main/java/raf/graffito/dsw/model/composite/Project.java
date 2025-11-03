package raf.graffito.dsw.model.composite;

public class Project extends GraffNodeComposite {
    private String title;
    private int totalNumberOfSlides;

    public Project(GraffNode parent, String name) {
        super(parent, name);
        this.title = name;
        this.totalNumberOfSlides = 0;
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
        totalNumberOfSlides++;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTotalNumberOfSlides() {
        return totalNumberOfSlides;
    }
}
