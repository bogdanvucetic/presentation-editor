package raf.graffito.dsw.controller.commands;

import raf.graffito.dsw.model.composite.Slide;
import raf.graffito.dsw.model.composite.elements.SlideElement;

import java.util.HashSet;
import java.util.Set;

public class DeleteCommand extends Command {
    private Set<SlideElement> elements;

    public DeleteCommand(Slide slide, Set<SlideElement> elements) {
        super(slide);
        this.elements = new HashSet<>(elements);
    }

    @Override
    public void execute() {
        for (SlideElement element : elements) {
            slide.removeChild(element);
        }
    }

    @Override
    public void undo() {
        for (SlideElement element : elements) {
            slide.addChild(element);
        }
    }
}
