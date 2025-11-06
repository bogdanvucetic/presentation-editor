package raf.graffito.dsw.core;

import raf.graffito.dsw.model.composite.GraffNode;
import raf.graffito.dsw.model.composite.Workspace;
import raf.graffito.dsw.model.factory.GraffNodeFactory;

public interface GraffRepository {
    void createFactory(GraffNode parent);
    Workspace getWorkspace();
    GraffNodeFactory getFactory();
}
