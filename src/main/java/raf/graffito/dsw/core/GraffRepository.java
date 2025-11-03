package raf.graffito.dsw.core;

import raf.graffito.dsw.model.composite.GraffNode;
import raf.graffito.dsw.model.composite.Workspace;

public interface GraffRepository {
    Workspace getWorkspace();

    void addChild(GraffNode parent, GraffNode child);

    void removeChild(GraffNode parent, GraffNode child);
}
