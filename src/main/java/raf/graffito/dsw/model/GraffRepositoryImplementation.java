package raf.graffito.dsw.model;

import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.model.composite.GraffNode;
import raf.graffito.dsw.model.composite.Presentation;
import raf.graffito.dsw.model.composite.Project;
import raf.graffito.dsw.model.decorator.ColorDecorator;
import raf.graffito.dsw.model.decorator.DecoratorRepository;
import raf.graffito.dsw.model.decorator.GraffNodeDecorator;
import raf.graffito.dsw.model.factory.GraffNodeFactory;
import raf.graffito.dsw.model.composite.Workspace;
import raf.graffito.dsw.model.factory.PresentationFactory;
import raf.graffito.dsw.model.factory.ProjectFactory;
import raf.graffito.dsw.model.factory.SlideFactory;
import raf.graffito.dsw.utils.ChildTypePicker;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GraffRepositoryImplementation implements GraffRepository {
    private Workspace workspace;
    private DecoratorRepository decoratorRepository;

    @Override
    public GraffNodeFactory getFactoryFor(GraffNode parent) {
        if (parent instanceof Workspace) {
            return new ProjectFactory();
        } else if (parent instanceof Project) {
            return ChildTypePicker.pickProjectChild();
        } else if (parent instanceof Presentation) {
            return new SlideFactory();
        }
        return null;
    }

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
