package raf.graffito.dsw.core.model;

public class Presentation extends GraffNodeComposite{
    public Presentation(String ime, int x, int y) {
        super(ime, x, y);
    }

    @Override
    public void addChild(GraffNode node) {
        if(node instanceof Slide) {
            super.addChild(node);
        }else{
            System.err.println("Presentation '" + this.ime + "' može sadržati samo Slide objekte.");
        }
    }

    @Override
    public void draw() {
        System.out.println("    Presentation: " + getIme());
        super.draw();
    }
}
