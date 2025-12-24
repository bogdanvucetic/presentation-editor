package raf.graffito.dsw.controller.state;

import raf.graffito.dsw.gui.swing.thumbnail.ImageThumbnail;
import raf.graffito.dsw.gui.swing.thumbnail.LogoThumbnail;
import raf.graffito.dsw.gui.swing.view.MainFrame;
import raf.graffito.dsw.gui.swing.view.SlideView;
import raf.graffito.dsw.model.composite.*;
import raf.graffito.dsw.model.composite.Image;
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
        Set<SlideElement> selectedElements = context.getSelectedElements();
        Set<SlideElement> newSelectedElements = new HashSet<>();

        if (selectedElements.isEmpty()) {
            return;
        }

        for (SlideElement element : selectedElements) {
            SlideElement e = element.clone();
            context.getCurrentSlideView().getSlide().addChild(e);
            newSelectedElements.add(e);
        }

        context.setSelectedElements(newSelectedElements);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        SlideView sv = context.getCurrentSlideView();

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
        context.addSelectedElement(l);
    }

    @Override
    public void thumbnailClicked(MouseEvent e) {
        SlideView sv = context.getCurrentSlideView();
        if (sv == null) return;

        if (e.getSource() instanceof ImageThumbnail thumbnail) {
            addImageToSlide(thumbnail, sv);
        } else if (e.getSource() instanceof LogoThumbnail) {
            addLogoToSlide(sv);
        }
    }

    private void addImageToSlide(ImageThumbnail thumbnail, SlideView sv) {
        ImageIcon imageIcon = thumbnail.getImage();
        java.awt.Image img = imageIcon.getImage();

        String name = buildImageName(thumbnail.getBaseName(), sv);

        int imgWidth = imageIcon.getIconWidth();
        int imgHeight = imageIcon.getIconHeight();

        Dimension slideSize = sv.getSlide().getResolution();
        int maxWidth = slideSize.width / 2;
        int maxHeight = slideSize.height / 2;

        if (imgWidth > maxWidth || imgHeight > maxHeight) {
            double scale = Math.min((double) maxWidth / imgWidth, (double) maxHeight / imgHeight);
            imgWidth = (int) (imgWidth * scale);
            imgHeight = (int) (imgHeight * scale);
        }

        Image image = new Image(
                img,
                sv.getSlide(),
                name,
                50,
                50,
                imgWidth,
                imgHeight
        );

        sv.getSlide().addChild(image);
        context.clearSelectedElements();
        context.addSelectedElement(image);
    }

    private void addLogoToSlide(SlideView sv) {
        String name = buildImageName("Logo", sv);

        Dimension slideSize = sv.getSlide().getResolution();
        int size = Math.min(slideSize.width, slideSize.height) / 4;

        Logo logo = new Logo(
                sv.getSlide(),
                name,
                50,
                50,
                size,
                size
        );

        sv.getSlide().addChild(logo);
        context.clearSelectedElements();
        context.addSelectedElement(logo);
    }

    private String buildImageName(String baseName, SlideView sv) {
        String name = (baseName == null || baseName.trim().isEmpty()) ? "Slika" : baseName.trim();

        if (sv.getSlide().isChildNameTaken(name)) {
            name = buildForTakenName(sv.getSlide(), name);
        }
        return name;
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
