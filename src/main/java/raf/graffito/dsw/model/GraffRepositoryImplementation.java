package raf.graffito.dsw.model;

public class GraffRepositoryImplementation implements GraffRepository {
    private Workspace workspace;

    @Override
    public Workspace getWorkspace() {
        return workspace;
    }

    @Override
    public void addChild(GraffNode parent, GraffNode child) {
        if (parent instanceof Workspace) {
            workspace.addChild(child);
        } else if (parent instanceof Project) {
            ((Project) parent).addChild(child);
        } else if (parent instanceof Presentation) {
            ((Presentation) parent).addChild(child);
        }
    }

    @Override
    public void removeChild(GraffNode parent, GraffNode child) {
        if (parent instanceof Workspace) {
            workspace.removeChild(child);
        } else if (parent instanceof Project) {
            ((Project) parent).removeChild(child);
        } else if (parent instanceof Presentation) {
            ((Presentation) parent).removeChild(child);
        }
    }
}
