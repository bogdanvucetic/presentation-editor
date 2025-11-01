package raf.graffito.dsw.model;

public class GraffRepositoryImplementation implements GraffRepository {
    // TODO
    //  implementacija kreiranja workspace-a
    private Workspace workspace;

    @Override
    public Workspace getWorkspace() {
        return workspace;
    }

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
}
