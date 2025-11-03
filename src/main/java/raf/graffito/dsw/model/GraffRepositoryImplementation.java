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
    public Workspace getWorkspace() {
        return workspace;
    }
}
