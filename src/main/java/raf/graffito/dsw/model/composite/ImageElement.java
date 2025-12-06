package raf.graffito.dsw.model.composite;

import raf.graffito.dsw.model.Prototype;

public class ImageElement extends SlideElement implements Prototype {
    public ImageElement(GraffNode parent, String name, int x, int y, int width, int height) {
        super(parent, name, x, y, width, height);
    }

    @Override
    public Prototype clone() {
        return new ImageElement(getParent(),
                getCloneName(),
                getX() + 2,
                getY() + 2,
                getWidth(),
                getHeight());
    }

    private String getCloneName() {
        String name = getName();
        int counter = 0;

        for (GraffNode node : ((GraffNodeComposite)getParent()).getChildren()){
            if (node.getName().equals(name)){
                counter++;
            }
        }

        return name + counter;
    }
}
