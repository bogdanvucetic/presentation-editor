package raf.graffito.dsw.model;

import raf.graffito.dsw.model.composite.Workspace;
import raf.graffito.dsw.model.decorator.DecoratorRepository;

public class GraffRepositoryImplementation implements GraffRepository {
    private Workspace workspace;
    private DecoratorRepository decoratorRepository;

    @Override
    public Workspace getWorkspace() {
        if (workspace == null) {
            workspace = new Workspace("Radni prostor");
        }

        return workspace;
    }

    public DecoratorRepository getDecoratorRepository() {
        if (decoratorRepository == null) {
            decoratorRepository = new DecoratorRepository();
        }
        return decoratorRepository;
    }
}
