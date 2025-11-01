package raf.graffito.dsw.model;

public class Project extends GraffNodeComposite{
    // title nije dodat jer već postoji jedinstven identifikator name
    private String author;
    private int numberOfSlides = 0;

    public Project(GraffNode parent, String name, String author) {
        super(parent, name);
        this.author = author;
        this.numberOfSlides = 0;
    }
}
