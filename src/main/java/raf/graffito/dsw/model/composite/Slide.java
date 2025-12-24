package raf.graffito.dsw.model.composite;

import java.awt.*;

public class Slide extends GraffNodeComposite {
    private static final Dimension resolution = new Dimension(1600, 900);

    public Slide(GraffNode parent, String name) {
        super(parent, name);
    }

    @Override
    public void addChild(GraffNode child) {
        if (!(child instanceof SlideElement)) {
            throw new IllegalArgumentException("ELement ne može da se doda na slajd.");
        }

        super.addChild(child);
    }

    public Dimension getResolution() {
        return resolution;
    }
}
