package raf.graffito.dsw.model;

public interface GraffRepository {
    Workspace getWorkspace();

    void addChild(GraffNode parent, GraffNode child);

    void removeChild(GraffNode parent, GraffNode child);
}
