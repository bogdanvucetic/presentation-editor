package raf.graffito.dsw.controller.commands;

import raf.graffito.dsw.model.composite.Slide;
import raf.graffito.dsw.model.composite.elements.SlideElement;

import java.util.HashSet;
import java.util.Set;

public class MoveCommand extends Command {
    private Set<SlideElement> elements;
    private int dx;
    private int dy;

    public MoveCommand(Slide slide, Set<SlideElement> elements, int dx, int dy) {
        super(slide);
        this.elements = new HashSet<>(elements);
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public void execute() {
        for (SlideElement element : elements) {
            element.translate(dx, dy);
        }
    }

    @Override
    public void undo() {
        for (SlideElement element : elements) {
            element.translate(-dx, -dy);
        }
    }
}
