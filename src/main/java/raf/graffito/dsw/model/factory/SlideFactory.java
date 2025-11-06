package raf.graffito.dsw.model.factory;

import raf.graffito.dsw.model.composite.GraffNode;
import raf.graffito.dsw.model.composite.GraffNodeComposite;
import raf.graffito.dsw.model.factory.GraffNodeFactory;
import raf.graffito.dsw.model.composite.Slide;

public class SlideFactory extends GraffNodeFactory {
    @Override
    public GraffNode createGraffNode(GraffNode parent) {
        return new Slide(parent, getDefaultName());
    }

    @Override
    public String getDefaultName() {
        return "New Slide";
    }
}
