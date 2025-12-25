package raf.graffito.dsw.model.composite;

import java.awt.*;

public class Label extends SlideElement {
    private String text;
    private Font font;
    private Color color;

    public Label(String text, GraffNode parent, String name, int xCoordinate, int yCoordinate, int width, int height) {
        super(parent, name, xCoordinate, yCoordinate, width, height);
        this.text = text;
        this.font = new Font("Arial", Font.PLAIN, 40);
        this.color = Color.BLACK;
    }

    public Label(Label elem) {
        super(elem);
        this.text = elem.getText();
        this.font = elem.getFont();
        this.color = elem.getColor();
    }

    @Override
    public SlideElement clone() {
        return new Label(this);
    }

    public String getText() {
        return text;
    }

    public Font getFont() {
        return font;
    }

    public Color getColor() {
        return color;
    }


    public int getFontSize() {
        return font.getSize();
    }

    public void setFontSize(int newSize) {
        newSize = Math.max(8, newSize); // minimalni font
        font = font.deriveFont((float) newSize);
    }

}
