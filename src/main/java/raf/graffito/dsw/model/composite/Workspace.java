package raf.graffito.dsw.model.composite;

public class Workspace extends GraffNodeComposite {
    public Workspace(String name) {
        super(null, name);
    }

    @Override
    public void addChild(GraffNode child) {
        if (!(child instanceof Project)) {
            throw new IllegalArgumentException("ELement ne može da se doda na radno okuženje.");
        }

        super.addChild(child);
    }
}
