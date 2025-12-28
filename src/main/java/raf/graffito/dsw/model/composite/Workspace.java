package raf.graffito.dsw.model.composite;

import raf.graffito.dsw.services.events.model.ModelUpdate;
import raf.graffito.dsw.services.events.model.UpdateType;

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
        project.setName(getDefaultName());
        getChildren().add(project);
        project.setParent(this);
        notifySubscribers(new ModelUpdate(UpdateType.PROJECT_OPENED, project));
    }

    private String getDefaultName() {
        int index = 0;
        for (GraffNode child : getChildren()) {
            if (child.getName().matches("Projekat \\d+")){
                String[] parts = child.getName().split(" ");
                if (Integer.parseInt(parts[1]) > index){
                    index = Integer.parseInt(parts[1]);
                }
            }
        }

        return "Projekat " + (index + 1);
    }
}
