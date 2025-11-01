package raf.graffito.dsw.model;

public class Workspace extends GraffNodeComposite{
    public Workspace(String name) {
        super(null, name);
    }

    @Override
    public void addChild(GraffNode child) {
        if (child instanceof Project) {
            super.addChild(child);
        }
    }
}
