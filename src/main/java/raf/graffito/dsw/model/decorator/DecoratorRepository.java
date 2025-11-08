package raf.graffito.dsw.model.decorator;

import raf.graffito.dsw.model.composite.GraffNode;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class DecoratorRepository {
    private List<GraffNodeDecorator> decorators;

    public DecoratorRepository() {
        decorators = new LinkedList<>();
    }

    public List<GraffNodeDecorator> getDecorators() {
        return decorators;
    }

    public void addDecorator(GraffNodeDecorator decorator) {
        decorators.add(decorator);
    }

    public void removeDecorator(GraffNodeDecorator decorator) {
        decorators.remove(decorator);
    }

    public ColorDecorator getColorDecoratorFor(GraffNode node) {
        for (GraffNodeDecorator decorator : decorators) {
            if (decorator instanceof ColorDecorator cd && cd.getNode() == node) {
                return cd;
            }
        }
        return null;
    }

    public void removeColorDecoratorFor(GraffNode node) {
        for (GraffNodeDecorator decorator : decorators) {
            if (decorator instanceof ColorDecorator cd && cd.getNode() == node) {
                decorators.remove(cd);
            }
        }
    }

    public boolean isColorUsed(Color color) {
        for (GraffNodeDecorator d : decorators) {
            if (d instanceof ColorDecorator cd && color.equals(cd.getColor())){
                return true;
            }
        }
        return false;
    }
}
