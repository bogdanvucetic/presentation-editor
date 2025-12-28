package raf.graffito.dsw.controller.commands;

import raf.graffito.dsw.model.composite.Slide;
import raf.graffito.dsw.model.composite.SlideElement;

import java.util.ArrayList;
import java.util.List;

public class AddCommand extends Command {
    private List<SlideElement> elements;

    public AddCommand(Slide slide, List<SlideElement> elements) {
        super(slide);
        this.elements = new ArrayList<>(elements);
    }

    @Override
    public void execute() {
        for (SlideElement element : elements) {
            slide.addChild(element);
        }
    }

    @Override
    public void undo() {
        for (SlideElement element : elements) {
            slide.removeChild(element);
        }
    }
}
