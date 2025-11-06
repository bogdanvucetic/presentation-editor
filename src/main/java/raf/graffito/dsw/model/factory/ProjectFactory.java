package raf.graffito.dsw.model.factory;

import raf.graffito.dsw.model.composite.GraffNode;
import raf.graffito.dsw.model.composite.GraffNodeComposite;
import raf.graffito.dsw.model.composite.Project;

public class ProjectFactory extends GraffNodeFactory {
    @Override
    public GraffNode createGraffNode(GraffNode parent) {
        return new Project(parent, getDefaultName(parent));
    }

    @Override
    public String getDefaultName(GraffNode parent) {
        int index = 0;
        for (GraffNode child : ((GraffNodeComposite) parent).getChildren()) {
            if (child.getName().matches("Projekat \\d*")){
                String[] parts = child.getName().split(" ");
                if (Integer.parseInt(parts[1]) > index){
                    index = Integer.parseInt(parts[1]);
                }
            }
        }

        return "Projekat " + (index + 1);
    }
}
