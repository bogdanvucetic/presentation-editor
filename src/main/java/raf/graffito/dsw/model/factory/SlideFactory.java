package raf.graffito.dsw.model.factory;

import raf.graffito.dsw.model.composite.GraffNode;
import raf.graffito.dsw.model.composite.GraffNodeFactory;
import raf.graffito.dsw.model.composite.Slide;

public class SlideFactory extends GraffNodeFactory {
    @Override
    public GraffNode createGraffNode(GraffNode parent, String name) {
        return new Slide(parent, name);
    }
}
