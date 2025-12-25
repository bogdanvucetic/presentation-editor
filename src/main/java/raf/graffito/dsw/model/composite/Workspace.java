package raf.graffito.dsw.model.composite;

import raf.graffito.dsw.observer.model.ModelEvent;
import raf.graffito.dsw.observer.model.ModelEventType;

public class Workspace extends GraffNodeComposite {
    public Workspace(String name) {
        super(null, name);
    }

    @Override
    public void addChild(GraffNode child) {
        if (!(child instanceof Project)) {
            throw new IllegalArgumentException("ELement ne može da se doda na radno okuženje.");
        }

        super.addChild(child);
    }

    public void openProject(Project project) {
        getChildren().add(project);
        project.setParent(this);
        notifySubscribers(new ModelEvent(ModelEventType.OPEN_PROJECT, project));
    }
}
