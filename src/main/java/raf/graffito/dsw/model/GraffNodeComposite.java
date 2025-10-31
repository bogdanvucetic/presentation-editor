package raf.graffito.dsw.model;

import java.util.ArrayList;
import java.util.List;

public class GraffNodeComposite extends GraffNode {
    private List<GraffNode> children = new ArrayList<>();


    public GraffNodeComposite(String ime, int x, int y) {
        super(ime, x, y);
    }

    public void addChild(GraffNode node) {
        children.add(node);
    }

    public void removeChild(GraffNode node) {
        children.remove(node);
    }

    public List<GraffNode> getChildren() {
        return children;
    }

    @Override
    public void draw() {
        System.out.println("Crtam COMPOSITE \"" + ime + "\" na (" + x + ", " + y + ")");
        for (GraffNode child : children) {
            child.draw();

        }

    }

    @Override
    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
        for (GraffNode child : children) {
            child.move(dx, dy);
        }
    }

    @Override
    public GraffNode findByName(String searchName) {

        if (this.ime != null && this.ime.equals(searchName)) {
            return this;
        }

        for (GraffNode child : children) {
            GraffNode found = child.findByName(searchName);
            if (found != null) {
                return found;
            }
        }

        return null;
    }
}