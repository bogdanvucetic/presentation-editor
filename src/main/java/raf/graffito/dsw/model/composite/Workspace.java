package raf.graffito.dsw.model.composite;

import raf.graffito.dsw.observer.model.ModelUpdate;
import raf.graffito.dsw.observer.model.UpdateType;

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
        if (isChildNameTaken(project.getName())) {
            project.setName("Novi " + project.getName());
        }

        getChildren().add(project);
        project.setParent(this);
        notifySubscribers(new ModelUpdate(UpdateType.PROJECT_OPENED, project));
    }
}
