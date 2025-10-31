package raf.graffito.dsw.model;

public class Slide extends GraffLeaf{


    public Slide(String ime, int x, int y) {
        super(ime, x, y);
    }
    @Override
    public void draw() {
        System.out.println("    Slide: " + getIme());
        super.draw();
    }
}
