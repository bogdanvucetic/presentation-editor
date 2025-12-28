package raf.graffito.dsw.controller.commands;

import raf.graffito.dsw.model.composite.Slide;
import raf.graffito.dsw.model.composite.SlideElement;

import java.util.ArrayList;
import java.util.List;

public class MoveCommand extends Command {
    private List<SlideElement> elements;
    private int dx;
    private int dy;

    public MoveCommand(Slide slide, List<SlideElement> elements, int dx, int dy) {
        super(slide);
        this.elements = new ArrayList<>(elements);
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
