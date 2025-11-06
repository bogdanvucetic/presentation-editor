package raf.graffito.dsw.model;

import raf.graffito.dsw.core.GraffRepository;
import raf.graffito.dsw.model.composite.GraffNode;
import raf.graffito.dsw.model.composite.Presentation;
import raf.graffito.dsw.model.composite.Project;
import raf.graffito.dsw.model.factory.GraffNodeFactory;
import raf.graffito.dsw.model.composite.Workspace;
import raf.graffito.dsw.model.factory.PresentationFactory;
import raf.graffito.dsw.model.factory.ProjectFactory;
import raf.graffito.dsw.model.factory.SlideFactory;
import raf.graffito.dsw.utils.ChildTypePicker;

public class GraffRepositoryImplementation implements GraffRepository {
    private Workspace workspace;
    private GraffNodeFactory factory;

    @Override
    public void createFactory(GraffNode parent) {
        if (parent instanceof Workspace) {
            factory = new ProjectFactory();
        } else if (parent instanceof Project) {
            String type = ChildTypePicker.pickProjectChild();

            if (type == null)
                return;

            if (type.equals("Presentation"))
                factory = new PresentationFactory();
            else if (type.equals("Slide"))
                factory = new SlideFactory();
        } else if (parent instanceof Presentation) {
            factory = new SlideFactory();
        }
    }

    @Override
    public Workspace getWorkspace() {
        if (workspace == null) {
            workspace = new Workspace("Radni prostor");
        }

        return workspace;
    }

    @Override
    public GraffNodeFactory getFactory() {
        return factory;
    }
}
