package raf.graffito.dsw.gui.swing.view;

import raf.graffito.dsw.gui.swing.thumbnail.ImageThumbnail;
import raf.graffito.dsw.gui.swing.thumbnail.LogoThumbnail;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {
    public ImagePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(new LogoThumbnail());
    }

    public void addImageToPanel(ImageIcon image, String baseName) {
        ImageThumbnail thumbnail = new ImageThumbnail(image, baseName);
        add(thumbnail);
        add(Box.createRigidArea(new Dimension(0, 5)));
        revalidate();
        repaint();
    }
}
