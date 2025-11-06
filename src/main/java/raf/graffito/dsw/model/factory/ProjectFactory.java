package raf.graffito.dsw.model.factory;

import raf.graffito.dsw.model.composite.GraffNode;
import raf.graffito.dsw.model.composite.Project;

public class ProjectFactory extends GraffNodeFactory {
    @Override
    public GraffNode createGraffNode(GraffNode parent) {
        return new Project(parent, getDefaultName(parent));
    }

    @Override
    public String getDefaultName(GraffNode parent) {
        return "New Project";
    }
}
