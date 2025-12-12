package raf.graffito.dsw.model;

import raf.graffito.dsw.model.composite.GraffNode;
import raf.graffito.dsw.model.composite.Workspace;
import raf.graffito.dsw.model.decorator.DecoratorRepository;
import raf.graffito.dsw.model.factory.GraffNodeFactory;

public interface GraffRepository {
    Workspace getWorkspace();
    DecoratorRepository getDecoratorRepository();
}
