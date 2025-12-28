package raf.graffito.dsw.model.repository;

import raf.graffito.dsw.model.composite.Workspace;
import raf.graffito.dsw.model.decorator.DecoratorRepository;

public class GraffRepositoryImpl implements GraffRepository {
    private Workspace workspace;
    private DecoratorRepository decoratorRepository;

    public GraffRepositoryImpl() {
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
