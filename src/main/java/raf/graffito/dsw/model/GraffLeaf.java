package raf.graffito.dsw.model;

public class GraffLeaf extends GraffNode {

    public GraffLeaf(String ime, int x, int y) {
        super(ime, x, y);
    }

    @Override
    public void draw() {
        System.out.println("Crtam LEAF \"" + ime + "\" na (" + x + ", " + y + ")");

    }

    @Override
    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;

    }
}
