package raf.graffito.dsw.controller.state;

import raf.graffito.dsw.controller.EditorContext;
import raf.graffito.dsw.gui.swing.view.SlideView;
import raf.graffito.dsw.model.composite.*;
import raf.graffito.dsw.model.composite.Label;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.Set;

public class AddState extends State {
    @Override
    public void onEnter() {
        Set<SlideElement> selectedElements = EditorContext.getInstance().getSelectedElements();
        Set<SlideElement> newSelectedElements = new HashSet<>();

        if (selectedElements.isEmpty()) {
            return;
        }

        for (SlideElement element : selectedElements) {
            SlideElement e = element.clone();
            EditorContext.getInstance().getCurrentSlideView().getSlide().addChild(e);
            newSelectedElements.add(e);
        }

        EditorContext.getInstance().setSelectedElements(newSelectedElements);

        EditorContext.getInstance().getCurrentSlideView().revalidate();
        EditorContext.getInstance().getCurrentSlideView().repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        SlideView sv = EditorContext.getInstance().getCurrentSlideView();

        if (e.getClickCount() != 2) {
            return;
        }

        String text = JOptionPane.showInputDialog("Unesite tekst");

        if (text == null) {
            return;
        }

        text = text.trim();
        String name = text;

        if (text.isEmpty()) {
            return;
        }

        if (sv.getSlide().isChildNameTaken(name)) {
            name = buildForTakenName(sv.getSlide(), name);
        }

        Point2D p = sv.convertToSlidePoint(e.getPoint());
        int sx = (int) Math.round(p.getX());
        int sy = (int) Math.round(p.getY());

        // todo fix
        Font font = new Font("Arial", Font.PLAIN, 40);
        FontMetrics fm = sv.getFontMetrics(font);

        int paddingX = 6;
        int paddingY = 4;

        int w = fm.stringWidth(text) + paddingX * 2;
        int h = fm.getHeight() + paddingY * 2;

        Label l = new Label(
                text,
                sv.getSlide(),
                name,
                sx,
                sy,
                w,
                h
        );

        sv.getSlide().addChild(l);

        EditorContext.getInstance().addSelectedElement(l);

        EditorContext.getInstance().getCurrentSlideView().revalidate();
        EditorContext.getInstance().getCurrentSlideView().repaint();
    }

    @Override
    public void imageClicked() {

    }

    private String buildForTakenName(Slide parent, String name) {
        int index = 0;
        for (GraffNode child : parent.getChildren()) {
            if (child.getName().matches(name + " \\d+")){
                String[] parts = child.getName().split(" ");
                if (Integer.parseInt(parts[1]) > index){
                    index = Integer.parseInt(parts[1]);
                }
            }
        }

        return name + " " + (index + 1);
    }
}
