package raf.graffito.dsw.model.composite;

import raf.graffito.dsw.core.ApplicationFramework;

public class Workspace extends GraffNodeComposite {
    public Workspace(String name) {
        super(null, name);
    }

    @Override
    public void addChild(GraffNode child) {
        if (child instanceof Project) {
            super.addChild(child);
        }
    }

    @Override
    public void removeChild(GraffNode child) {
        super.removeChild(child);
        ApplicationFramework.getInstance().getGraffRepository().getDecoratorRepository().removeColorDecoratorFor(child);
    }
}
