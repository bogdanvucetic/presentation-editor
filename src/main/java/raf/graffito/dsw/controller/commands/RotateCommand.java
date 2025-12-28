package raf.graffito.dsw.controller.commands;

import raf.graffito.dsw.model.composite.Slide;
import raf.graffito.dsw.model.composite.SlideElement;

import java.util.ArrayList;
import java.util.List;

public class RotateCommand extends Command {
    private List<SlideElement> elements;
    private double deltaAngle;

    public RotateCommand(Slide slide, List<SlideElement> elements, double deltaAngle) {
        super(slide);
        this.elements = new ArrayList<>(elements);
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
