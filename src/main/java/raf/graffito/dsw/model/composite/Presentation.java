package raf.graffito.dsw.model.composite;

import raf.graffito.dsw.observer.model.ModelSubscriber;

import java.util.ArrayList;
import java.util.List;

public class Presentation extends GraffNodeComposite {
    List<ModelSubscriber> subscribers;

    public Presentation(GraffNode parent, String name) {
        super(parent, name);
        subscribers = new ArrayList<>();
    }

    @Override
    public void addChild(GraffNode child) {
        if (child instanceof Slide) {
            super.addChild(child);
            ((Project) getParent()).incrementNumberOfSlides();
        }
    }
}
