package raf.graffito.dsw.model;

import java.util.ArrayList;
import java.util.List;

public abstract class GraffNodeComposite extends GraffNode {
    private List<GraffNode> children;

    @Override
    public GraffNode findByName(String name) {
        if (getName() != null && getName().equals(name)) {
            return this;
        }

        for (GraffNode child : children) {
            GraffNode result = child.findByName(name);
            if (result != null) {
                return result;
            }
        }

        return null;
    }
}