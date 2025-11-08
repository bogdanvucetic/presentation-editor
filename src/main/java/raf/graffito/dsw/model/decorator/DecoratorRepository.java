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

    public void addDecorator(GraffNodeDecorator decorator) {
        decorators.add(decorator);
    }

    public void removeDecorator(GraffNodeDecorator decorator) {
        decorators.remove(decorator);
    }

    public ColorDecorator getColorDecoratorFor(GraffNode node) {
        for (GraffNodeDecorator decorator : decorators) {
            if (decorator instanceof ColorDecorator colorDecorator && colorDecorator.getNode() == node) {
                return colorDecorator;
            }
        }
        return null;
    }

    public void removeColorDecoratorFor(GraffNode node) {
        for (GraffNodeDecorator decorator : decorators) {
            if (decorator instanceof ColorDecorator colorDecorator && colorDecorator.getNode() == node) {
                decorators.remove(colorDecorator);
            }
        }
    }

    public boolean isUsed(Color color) {
        for (GraffNodeDecorator d : decorators) {
            if (d instanceof ColorDecorator cd && color.equals(cd.getColor())){
                return true;
            }
        }
        return false;
    }

    public List<GraffNodeDecorator> getDecorators() {
        return decorators;
    }
}
