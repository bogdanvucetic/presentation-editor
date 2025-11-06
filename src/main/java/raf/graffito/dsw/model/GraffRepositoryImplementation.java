package raf.graffito.dsw.model;

import raf.graffito.dsw.core.GraffRepository;
import raf.graffito.dsw.model.composite.Workspace;

public class GraffRepositoryImplementation implements GraffRepository {
    private Workspace workspace;

    @Override
    public Workspace getWorkspace() {
        if (workspace == null) {
            workspace = new Workspace("Radni prostor");
        }

        return workspace;
    }
}
