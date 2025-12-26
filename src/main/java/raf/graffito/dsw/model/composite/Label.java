package raf.graffito.dsw.model.composite;

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
        markProjectAsChanged();
    }

    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
        markProjectAsChanged();
    }

    public int getFontStyle() {
        return fontStyle;
    }

    public void setFontStyle(int fontStyle) {
        this.fontStyle = fontStyle;
        markProjectAsChanged();
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
        markProjectAsChanged();
    }

    public int getColorRGB() {
        return colorRGB;
    }

    public void setColorRGB(int colorRGB) {
        this.colorRGB = colorRGB;
        markProjectAsChanged();
    }
}
