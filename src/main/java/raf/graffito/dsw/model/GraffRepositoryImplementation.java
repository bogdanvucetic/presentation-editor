package raf.graffito.dsw.model;

import raf.graffito.dsw.model.composite.Workspace;
import raf.graffito.dsw.model.decorator.DecoratorRepository;

public class GraffRepositoryImplementation implements GraffRepository {
    private Workspace workspace;
    private DecoratorRepository decoratorRepository;

    public GraffRepositoryImplementation() {
        workspace = new Workspace("Radni prostor");
        decoratorRepository = new DecoratorRepository();
    }

    @Override
    public Workspace getWorkspace() {
        return workspace;
    }

    public DecoratorRepository getDecoratorRepository() {
        return decoratorRepository;
    }
}
