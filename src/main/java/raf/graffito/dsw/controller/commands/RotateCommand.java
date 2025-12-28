package raf.graffito.dsw.controller.commands;

import raf.graffito.dsw.model.composite.Slide;
import raf.graffito.dsw.model.composite.SlideElement;

import java.util.HashSet;
import java.util.Set;

public class RotateCommand extends Command {
    private Set<SlideElement> elements;
    private double deltaAngle;

    public RotateCommand(Slide slide, Set<SlideElement> elements, double deltaAngle) {
        super(slide);
        this.elements = new HashSet<>(elements);
        this.deltaAngle = deltaAngle;
    }

    @Override
    public void execute() {
        for (SlideElement element : elements) {
            double newAngle = element.getRotationAngle() + deltaAngle;
            element.setRotationAngle((newAngle % 360 + 360) % 360);
        }
    }

    @Override
    public void undo() {
        for (SlideElement element : elements) {
            double newAngle = element.getRotationAngle() - deltaAngle;
            element.setRotationAngle((newAngle % 360 + 360) % 360);
        }
    }
}
