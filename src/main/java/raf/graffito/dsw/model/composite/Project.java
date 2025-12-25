package raf.graffito.dsw.model.composite;

public class Project extends GraffNodeComposite {
    private String title;
    private int totalNumberOfSlides;

    public Project() {}

    public Project(GraffNode parent, String name) {
        super(parent, name);
        this.title = name;
        this.totalNumberOfSlides = 0;
    }

    @Override
    public void addChild(GraffNode child) {
        if (!(child instanceof Presentation || child instanceof Slide)) {
            throw new IllegalArgumentException("ELement ne može da se doda na projekat.");
        }

        super.addChild(child);

        if (child instanceof Slide) {
            incrementNumberOfSlides();
        }
    }

    @Override
    public void removeChild(GraffNode child) {
        if (getChildren().size() <= 1) {
            throw new IllegalArgumentException("Projekat ne može biti prazan.");
        }

        super.removeChild(child);
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

    public void setTotalNumberOfSlides(int totalNumberOfSlides) {
        this.totalNumberOfSlides = totalNumberOfSlides;
    }
}
