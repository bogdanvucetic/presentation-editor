package raf.graffito.dsw.controller.commands;

import raf.graffito.dsw.model.composite.Slide;

public abstract class Command {
    protected Slide slide;

    public Command(Slide slide) {
        this.slide = slide;
    }

    public abstract void execute();

    public abstract void undo();
}
