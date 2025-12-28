package raf.graffito.dsw.controller.commands;

import raf.graffito.dsw.model.composite.elements.Label;
import raf.graffito.dsw.model.composite.Slide;
import raf.graffito.dsw.model.composite.elements.SlideElement;

public class ResizeCommand extends Command {
    private SlideElement element;
    private int oldWidth;
    private int oldHeight;
    private int newWidth;
    private int newHeight;

    private Integer oldFontSize;
    private Integer newFontSize;

    public ResizeCommand(Slide slide, SlideElement element, int newWidth, int newHeight) {
        super(slide);
        this.element = element;
        this.oldWidth = element.getWidth();
        this.oldHeight = element.getHeight();
        this.newWidth = newWidth;
        this.newHeight = newHeight;

        if (element instanceof Label) {
            this.oldFontSize = ((Label) element).getFontSize();
        }
    }

    public ResizeCommand(Slide slide, Label label, int newFontSize) {
        super(slide);
        this.element = label;
        this.oldFontSize = label.getFontSize();
        this.newFontSize = newFontSize;
    }

    @Override
    public void execute() {
        if (element instanceof Label && newFontSize != null) {
            ((Label) element).setFontSize(newFontSize);
        } else {
            element.setWidth(newWidth);
            element.setHeight(newHeight);
        }
    }

    @Override
    public void undo() {
        if (element instanceof Label && oldFontSize != null) {
            ((Label) element).setFontSize(oldFontSize);
        } else {
            element.setWidth(oldWidth);
            element.setHeight(oldHeight);
        }
    }
}
