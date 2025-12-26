package raf.graffito.dsw.gui.swing.thumbnail;

import raf.graffito.dsw.controller.listeners.ThumbnailListener;
import raf.graffito.dsw.gui.swing.proxy.ImageData;
import raf.graffito.dsw.gui.swing.proxy.ImageProxy;

import javax.swing.*;
import java.awt.*;

public class ImageThumbnail extends JPanel {
    private ImageProxy imageProxy;
    private String baseName;
    private boolean hovered;

    public ImageThumbnail(ImageProxy imageProxy, String baseName) {
        this.imageProxy = imageProxy;
        this.baseName = baseName;
        hovered = false;

        setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));
        setPreferredSize(new Dimension(100, 150));
        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        setBackground(Color.WHITE);

        addMouseListener(new ThumbnailListener());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (imageProxy == null || imageProxy.getImage() == null) return;

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        int panelWidth = getWidth() - 4;
        int panelHeight = getHeight() - 4;

        Image thumbnail = imageProxy.getThumbnail();
        int imgWidth = thumbnail.getWidth(null);
        int imgHeight = thumbnail.getHeight(null);

        double scale = (double) panelWidth / imgWidth;
        int scaledWidth = panelWidth;
        int scaledHeight = (int) (imgHeight * scale);

        int y = (panelHeight - scaledHeight) / 2;
        int x = 2;

        g2.drawImage(thumbnail, x, Math.max(2, y), scaledWidth, scaledHeight, null);

        g2.dispose();

        if (hovered) {
            setBorder(BorderFactory.createLineBorder(new Color(100, 150, 255), 2));
            setBackground(new Color(240, 245, 255));
        } else {
            setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            setBackground(Color.WHITE);
        }
    }

    public ImageProxy getImageProxy() {
        return imageProxy;
    }

    public String getBaseName() {
        return baseName;
    }

    public void setHovered(boolean hovered) {
        this.hovered = hovered;
        repaint();
    }
}
