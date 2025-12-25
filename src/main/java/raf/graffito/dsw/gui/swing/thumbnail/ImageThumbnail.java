package raf.graffito.dsw.gui.swing.thumbnail;

import raf.graffito.dsw.controller.listeners.ThumbnailListener;

import javax.swing.*;
import java.awt.*;

public class ImageThumbnail extends JPanel {
    private ImageIcon image;
    private String baseName;
    private boolean hovered;

    public ImageThumbnail(ImageIcon image, String baseName) {
        this.image = image;
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

        if (image == null || image.getImage() == null) return;

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        int panelWidth = getWidth() - 4;
        int panelHeight = getHeight() - 4;

        int imgWidth = image.getIconWidth();
        int imgHeight = image.getIconHeight();

        double scale = (double) panelWidth / imgWidth;
        int scaledWidth = panelWidth;
        int scaledHeight = (int) (imgHeight * scale);

        int y = (panelHeight - scaledHeight) / 2;
        int x = 2;

        g2.drawImage(image.getImage(), x, Math.max(2, y), scaledWidth, scaledHeight, null);

        g2.dispose();

        if (hovered) {
            setBorder(BorderFactory.createLineBorder(new Color(100, 150, 255), 2));
            setBackground(new Color(240, 245, 255));
        } else {
            setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            setBackground(Color.WHITE);
        }
    }

    public ImageIcon getImage() {
        return image;
    }

    public String getBaseName() {
        return baseName;
    }

    public void setHovered(boolean hovered) {
        this.hovered = hovered;
        repaint();
    }
}
