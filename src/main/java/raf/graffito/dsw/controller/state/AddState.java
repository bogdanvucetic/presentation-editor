package raf.graffito.dsw.controller.state;

import raf.graffito.dsw.controller.commands.AddCommand;
import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.services.proxy.ImageProxy;
import raf.graffito.dsw.gui.swing.thumbnail.ImageThumbnail;
import raf.graffito.dsw.gui.swing.thumbnail.LogoThumbnail;
import raf.graffito.dsw.gui.swing.frames.SlideView;
import raf.graffito.dsw.model.composite.*;
import raf.graffito.dsw.model.composite.elements.Image;
import raf.graffito.dsw.model.composite.elements.Label;
import raf.graffito.dsw.model.composite.elements.Logo;
import raf.graffito.dsw.model.composite.elements.SlideElement;
import raf.graffito.dsw.services.events.message.MessageType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

public class AddState extends State {
    @Override
    public void onEnter() {
        Set<SlideElement> selectedElements = context.getSelectedElements();

        if (selectedElements.isEmpty()) {
            return;
        }

        List<SlideElement> toAdd = new ArrayList<>();

        for (SlideElement element : selectedElements) {
            if (!context.isNewElementsAllowed()) {
                ApplicationFramework.getInstance().getMessageGenerator().createMessage("Nedovoljno prostora na slajdu za novi element", MessageType.ERROR);
                break;
            }

            toAdd.add(element.clone());
        }

        if (!toAdd.isEmpty()) {
            AddCommand command = new AddCommand(context.getCurrentSlideView().getSlide(), toAdd);
            context.execute(command);
            context.setSelectedElements(new HashSet<>(toAdd));
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() != 2) {
            return;
        }

        if (!context.isNewElementsAllowed()) {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage("Nedovoljno prostora na slajdu za novi element", MessageType.ERROR);
            return;
        }

        SlideView sv = context.getCurrentSlideView();
        String text = JOptionPane.showInputDialog("Unesite tekst");
        if (text == null || text.trim().isEmpty()) {
            return;
        }

        String name = text.trim();
        if (sv.getSlide().isChildNameTaken(name)) {
            name = buildForTakenName(sv.getSlide(), name);
        }

        Point2D p = sv.convertToSlidePoint(e.getPoint());
        int sx = (int) Math.round(p.getX());
        int sy = (int) Math.round(p.getY());

        Font font = new Font("Arial", Font.PLAIN, 40);
        FontMetrics fm = sv.getFontMetrics(font);
        int paddingX = 6;
        int paddingY = 4;

        int w = fm.stringWidth(text) + paddingX * 2;
        int h = fm.getHeight() + paddingY * 2;

        Label label = new Label(
                text,
                sv.getSlide(),
                name,
                sx,
                sy,
                w,
                h
        );

        AddCommand command = new AddCommand(sv.getSlide(), List.of(label));
        context.execute(command);

        context.clearSelectedElements();
        context.addSelectedElement(label);
    }

    @Override
    public void thumbnailClicked(MouseEvent e) {
        if (!context.isNewElementsAllowed()) {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage("Nedovoljno prostora na slajdu za novi element", MessageType.ERROR);
            return;
        }

        SlideView sv = context.getCurrentSlideView();
        if (sv == null) return;

        if (e.getSource() instanceof ImageThumbnail thumbnail) {
            addImageToSlide(thumbnail, sv);
        } else if (e.getSource() instanceof LogoThumbnail) {
            addLogoToSlide(sv);
        }
    }

    private void addImageToSlide(ImageThumbnail thumbnail, SlideView sv) {
        ImageProxy imageProxy = thumbnail.getImageProxy();
        java.awt.Image fullResolutionImage = imageProxy.getImage();

        String name = buildImageName(thumbnail.getBaseName(), sv);

        int imgWidth = fullResolutionImage.getWidth(null);
        int imgHeight = fullResolutionImage.getHeight(null);

        Dimension slideSize = sv.getSlide().getResolution();
        int maxWidth = slideSize.width / 2;
        int maxHeight = slideSize.height / 2;

        if (imgWidth > maxWidth || imgHeight > maxHeight) {
            double scale = Math.min((double) maxWidth / imgWidth, (double) maxHeight / imgHeight);
            imgWidth = (int) (imgWidth * scale);
            imgHeight = (int) (imgHeight * scale);
        }

        Image image = new Image(
                imageProxy.getFilePath(),
                sv.getSlide(),
                name,
                50,
                50,
                imgWidth,
                imgHeight
        );

        AddCommand command = new AddCommand(sv.getSlide(), List.of(image));
        context.execute(command);

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

        AddCommand command = new AddCommand(sv.getSlide(), List.of(logo));
        context.execute(command);

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
