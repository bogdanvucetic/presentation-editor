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

    public boolean isColorUsedForDifferentProject(ColorDecorator decorator, Color color) {
        for (GraffNodeDecorator d : decorators) {
            if (decorator == d) {
                continue;
            }

            if (d instanceof ColorDecorator && color == ((ColorDecorator)d).getColor()){
                return true;
            }
        }
        return false;
    }
}
