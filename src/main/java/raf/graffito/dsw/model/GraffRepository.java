package raf.graffito.dsw.model;

import raf.graffito.dsw.model.composite.Workspace;
import raf.graffito.dsw.model.decorator.DecoratorRepository;

public interface GraffRepository {
    Workspace getWorkspace();
    DecoratorRepository getDecoratorRepository();
}
