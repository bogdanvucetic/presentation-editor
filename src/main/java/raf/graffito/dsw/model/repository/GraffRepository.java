package raf.graffito.dsw.model.repository;

import raf.graffito.dsw.model.composite.Workspace;
import raf.graffito.dsw.model.decorator.DecoratorRepository;

public interface GraffRepository {
    Workspace getWorkspace();
    DecoratorRepository getDecoratorRepository();
}
