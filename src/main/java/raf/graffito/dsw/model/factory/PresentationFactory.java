package raf.graffito.dsw.model.factory;

import raf.graffito.dsw.model.composite.GraffNode;
import raf.graffito.dsw.model.composite.Presentation;

public class PresentationFactory extends GraffNodeFactory {
    @Override
    public GraffNode createGraffNode(GraffNode parent) {
        return new Presentation(parent, getDefaultName(parent));
    }

    @Override
    public String getDefaultName(GraffNode parent) {
        return "New Presentation";
    }
}