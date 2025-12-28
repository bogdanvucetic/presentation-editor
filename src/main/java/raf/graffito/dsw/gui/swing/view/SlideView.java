package raf.graffito.dsw.gui.swing.view;

import raf.graffito.dsw.controller.EditorContext;
import raf.graffito.dsw.controller.listeners.SlideViewListener;
import raf.graffito.dsw.gui.swing.painter.ImagePainter;
import raf.graffito.dsw.gui.swing.painter.LabelPainter;
import raf.graffito.dsw.gui.swing.painter.LogoPainter;
import raf.graffito.dsw.model.composite.*;
import raf.graffito.dsw.model.composite.Image;
import raf.graffito.dsw.model.composite.Label;
import raf.graffito.dsw.observer.model.ModelUpdate;
import raf.graffito.dsw.observer.model.UpdateType;
import raf.graffito.dsw.observer.model.UpdateSubscriber;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class SlideView extends JComponent implements UpdateSubscriber {
    private Slide slide;

    private static final int hPadding = 2;
    private static final int vPadding = 2;
    private final ImagePainter imagePainter = new ImagePainter();
    private final LabelPainter labelPainter = new LabelPainter();
    private final LogoPainter logoPainter = new LogoPainter();

    private double zoomFactor = 1.0;

    private static final double MIN_ZOOM = 0.3;
    private static final double MAX_ZOOM = 3.0;
    private static final int RESIZE_HANDLE_SIZE = 20;




    public SlideView(Slide slide) {
        this.slide = slide;
        setOpaque(false);
        EditorContext.getInstance().setCurrentSlideView(this);

        SlideViewListener slideViewListener = new SlideViewListener();
        addMouseListener(slideViewListener);
        addMouseMotionListener(slideViewListener);
        addMouseWheelListener(slideViewListener);

        subscribeToAllNodes(slide);
    }

    private void subscribeToAllNodes(Slide slide) {
        slide.addSubscriber(this);

        for (GraffNode child : slide.getChildren()) {
            child.addSubscriber(this);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (slide == null) return;

        Dimension size = slide.getResolution();

        Graphics2D g2 = (Graphics2D) g.create();
        try {
            int totalWidth = getWidth();
            int totalHeight = getHeight();

            int availableWidth = Math.max(1, totalWidth - 2 * hPadding);
            int availableHeight = Math.max(1, totalHeight - 2 * vPadding);

            double sx = availableWidth / (double) size.width;
            double sy = availableHeight / (double) size.height;
//            double scale = Math.min(sx, sy);
            double scale = Math.min(sx, sy) * zoomFactor;


            int drawW = (int) (size.width * scale);
            int drawH = (int) (size.height * scale);
            int offsetX = hPadding + (availableWidth - drawW) / 2;
            int offsetY = vPadding + (availableHeight - drawH) / 2;

            g2.translate(offsetX, offsetY);
            g2.scale(scale, scale);

            g2.setColor(Color.WHITE);
            g2.fillRect(0, 0, size.width, size.height);

            g2.setColor(Color.LIGHT_GRAY);
            g2.drawRect(0, 0, size.width - 1, size.height - 1);

             for (GraffNode elem : slide.getChildren()) {
                 if (elem instanceof Label l) {
                     labelPainter.paint(g2, l);
                 } else if (elem instanceof Image img) {
                     imagePainter.paint(g2, img);
                 } else if (elem instanceof Logo l) {
                     logoPainter.paint(g2, l);
                 }
             }

             drawSelectionBoxes(g2);
        } finally {
            g2.dispose();
        }
    }

    private void drawSelectionBoxes(Graphics2D g2) {
        Set<SlideElement> selectedElements = EditorContext.getInstance().getSelectedElements();

        if (selectedElements.isEmpty()) {
            return;
        }

        Stroke oldStroke = g2.getStroke();
        Color oldColor = g2.getColor();

        g2.setColor(new Color(100, 150, 255));
        g2.setStroke(new BasicStroke(3f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0, new float[]{14, 6}, 0));

//        for (SlideElement element : selectedElements) {
//            g2.drawRect(element.getXCoordinate(), element.getYCoordinate(), element.getWidth(), element.getHeight());
//        }

        for (SlideElement element : selectedElements) {
            int x = element.getXCoordinate();
            int y = element.getYCoordinate();
            int w = element.getWidth();
            int h = element.getHeight();

            // okvir selekcije
            g2.drawRect(x, y, w, h);

            //  resize tačkica (donji desni ugao)
            int handleX = x + w - RESIZE_HANDLE_SIZE / 2;
            int handleY = y + h - RESIZE_HANDLE_SIZE / 2;

            g2.fillOval(handleX, handleY, RESIZE_HANDLE_SIZE, RESIZE_HANDLE_SIZE);
        }


        g2.setStroke(oldStroke);
        g2.setColor(oldColor);
    }

    public Point convertToSlidePoint(Point viewPoint) {
        if (slide == null) return new Point(0, 0);

        Dimension size = slide.getResolution();

        int totalWidth = getWidth();
        int totalHeight = getHeight();

        int availableWidth = Math.max(1, totalWidth - 2 * hPadding);
        int availableHeight = Math.max(1, totalHeight - 2 * vPadding);

        double sx = availableWidth / (double) size.width;
        double sy = availableHeight / (double) size.height;
        double scale = Math.min(sx, sy) * zoomFactor;

        int drawW = (int) (size.width * scale);
        int drawH = (int) (size.height * scale);
        int offsetX = hPadding + (availableWidth - drawW) / 2;
        int offsetY = vPadding + (availableHeight - drawH) / 2;

        int slideX = (int) Math.round((viewPoint.x - offsetX) / scale);
        int slideY = (int) Math.round((viewPoint.y - offsetY) / scale);

        slideX = Math.max(0, Math.min(size.width, slideX));
        slideY = Math.max(0, Math.min(size.height, slideY));

        return new Point(slideX, slideY);
    }

    public Slide getSlide() {
        return slide;
    }

    @Override
    public void update(ModelUpdate event) {
        UpdateType type = event.getType();
        GraffNode node = event.getNode();

        switch (type) {
            case CHILD_ADDED -> {
                node.addSubscriber(this);
            }
            case CHILD_REMOVED -> {
                node.removeSubscriber(this);
            }
            case RENAMED, PROJECT_OPENED, PROPERTY_UPDATED -> { }
        }

        repaint();
    }

    public void updateSelectedElements() {
        repaint();
    }

    public void zoom(double delta) {
        zoomFactor += delta;
        zoomFactor = Math.max(MIN_ZOOM, Math.min(MAX_ZOOM, zoomFactor));
        repaint();
    }


    public double getZoomFactor() {
        return zoomFactor;
    }
    public void setZoomFactor(double zoomFactor) {
        this.zoomFactor = zoomFactor;
    }
}


