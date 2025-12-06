package raf.graffito.dsw.model.composite;

import raf.graffito.dsw.model.Prototype;

public class ImageElement extends SlideElement implements Prototype {
    public ImageElement(GraffNode parent, String name, int x, int y, int width, int height) {
        super(parent, name, x, y, width, height);
    }

    @Override
    public Prototype clone() {
        return new LabelElement(getParent(),
                getName() + " KOPIRANO",
                getX() + 2,
                getY() + 2,
                getWidth(),
                getHeight());
    }
}
