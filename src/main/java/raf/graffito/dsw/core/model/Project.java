package raf.graffito.dsw.core.model;

public class Project extends GraffNodeComposite{

    private String title;
    private String author;
    private int number;



    public Project(String ime, int x, int y, String title, String author, int number) {
        super(ime, x, y);
        this.title = title;
        this.author = author;
        this.number = number;

    }

    @Override
    public void addChild(GraffNode node) {
        if(node instanceof Presentation || node instanceof Slide) {
            super.addChild(node);
        }else {
            System.err.println("Project '" + this.ime + "' može sadržati samo Presentation ili Slide objekte.");
        }
    }


    public boolean isValid(){
        boolean imaPresentation = false;
        boolean imaSlide = false;

        for (GraffNode child : getChildren()) {
            if(child instanceof Presentation){
                imaPresentation = true;
            }
            if(child instanceof Slide) {
                imaSlide = true;
            }

        }
        return imaPresentation || imaSlide;
    }

    @Override
    public void draw() {
        System.out.println("  Project: " + getIme()
                + " | title=" + title
                + ", author=" + author
                + ", slides=" + number);
        super.draw();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
