package raf.graffito.dsw.model;

public class Workspace extends GraffNodeComposite{

    public Workspace(String ime, int x, int y) {
        super(ime, x, y);
    }

    @Override
    public void addChild(GraffNode node) {
        if(node instanceof Project){
            super.addChild(node);
        }else{
            System.err.println(" Workspace '" + this.ime + "' može sadržati samo Project objekte.");
        }

    }

    @Override
    public void draw() {
        System.out.println("Workspace: " + getIme());
        super.draw();
    }
}
