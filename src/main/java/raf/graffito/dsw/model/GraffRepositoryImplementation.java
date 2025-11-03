package raf.graffito.dsw.model;

import raf.graffito.dsw.core.GraffRepository;
import raf.graffito.dsw.model.composite.GraffNode;
import raf.graffito.dsw.model.composite.GraffNodeComposite;
import raf.graffito.dsw.model.composite.Workspace;

public class GraffRepositoryImplementation implements GraffRepository {
    // TODO
    //  implementacija kreiranja workspace-a
    private Workspace workspace = new Workspace("Radni prostor");

    @Override
    public void addChild(GraffNode parent, GraffNode child) {
        if (parent instanceof GraffNodeComposite) {
            ((GraffNodeComposite) parent).addChild(child);
        }
    }

    @Override
    public void removeChild(GraffNode parent, GraffNode child) {
        if (parent instanceof GraffNodeComposite) {
            ((GraffNodeComposite) parent).removeChild(child);
        }
    }

    @Override
    public Workspace getWorkspace() {
        return workspace;
    }
}
