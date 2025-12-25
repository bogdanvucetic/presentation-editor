package raf.graffito.dsw.gui.swing.thumbnail;

import raf.graffito.dsw.controller.listeners.ThumbnailListener;
import raf.graffito.dsw.model.composite.Logo;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.List;

public class LogoThumbnail extends JPanel {
    private boolean hovered;

    public LogoThumbnail() {
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

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        List<Point2D.Double> pts = Logo.getDefaultPoints();
        Color color = new Color(Logo.getDefaultColorRGB());

        int panelWidth = getWidth() - 4;
        int panelHeight = getHeight() - 4;

        int size = Math.min(panelWidth, panelHeight);

        int x = 2 + (panelWidth - size) / 2;
        int y = 2 + (panelHeight - size) / 2;

        Path2D path = new Path2D.Double();
        Point2D first = pts.get(0);
        path.moveTo(x + first.getX() * size, y + first.getY() * size);

        for (int i = 1; i < pts.size(); i++) {
            Point2D p = pts.get(i);
            path.lineTo(x + p.getX() * size, y + p.getY() * size);
        }
        path.closePath();

        g2.setColor(color);
        g2.fill(path);

        if (hovered) {
            setBorder(BorderFactory.createLineBorder(new Color(100, 150, 255), 2));
            setBackground(new Color(240, 245, 255));
        } else {
            setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            setBackground(Color.WHITE);
        }
    }

    public void setHovered(boolean hovered) {
        this.hovered = hovered;
        repaint();
    }
}
