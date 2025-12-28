package raf.graffito.dsw.model.composite;

import raf.graffito.dsw.observer.model.ModelUpdate;
import raf.graffito.dsw.observer.model.UpdateType;

import java.awt.*;

public class Label extends SlideElement {
    private String text;
    private String fontName;
    private int fontStyle;
    private int fontSize;
    private int colorRGB;

    public Label() {}

    public Label(String text, GraffNode parent, String name, int xCoordinate, int yCoordinate, int width, int height) {
        super(parent, name, xCoordinate, yCoordinate, width, height);
        this.text = text;
        this.fontName = "Arial";
        this.fontStyle = Font.PLAIN;
        this.fontSize = 40;
        this.colorRGB = Color.BLACK.getRGB();
    }

    public Label(Label elem) {
        super(elem);
        this.text = elem.getText();
        this.fontName = elem.getFontName();
        this.fontStyle = elem.getFontStyle();
        this.fontSize = elem.getFontSize();
        this.colorRGB = elem.getColorRGB();
    }

    @Override
    public SlideElement clone() {
        return new Label(this);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        notifySubscribers(new ModelUpdate(UpdateType.PROPERTY_UPDATED, this));
        markProjectAsChanged();
    }

    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
        notifySubscribers(new ModelUpdate(UpdateType.PROPERTY_UPDATED, this));
        markProjectAsChanged();
    }

    public int getFontStyle() {
        return fontStyle;
    }

    public void setFontStyle(int fontStyle) {
        this.fontStyle = fontStyle;
        notifySubscribers(new ModelUpdate(UpdateType.PROPERTY_UPDATED, this));
        markProjectAsChanged();
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = Math.max(8, fontSize);
        notifySubscribers(new ModelUpdate(UpdateType.PROPERTY_UPDATED, this));
        markProjectAsChanged();
    }

    public int getColorRGB() {
        return colorRGB;
    }

    public void setColorRGB(int colorRGB) {
        this.colorRGB = colorRGB;
        notifySubscribers(new ModelUpdate(UpdateType.PROPERTY_UPDATED, this));
        markProjectAsChanged();
    }
}
