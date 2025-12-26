package raf.graffito.dsw.gui.swing.proxy;

import javax.swing.*;
import java.awt.*;

public class ImageProxy implements ImageData {
    private final String filePath;
    private Image thumbnailImage;
    private Image fullResolutionImage;

    private static final int thumbnailMaxWidth = 200;
    private static final int thumbnailMaxHeight = 200;

    public ImageProxy(String filePath) {
        this.filePath = filePath;
        loadThumbnail();
    }

    private void loadThumbnail() {
        ImageIcon originalIcon = new ImageIcon(filePath);
        Image originalImage = originalIcon.getImage();

        int originalWidth = originalIcon.getIconWidth();
        int originalHeight = originalIcon.getIconHeight();

        double scale = Math.min((double) thumbnailMaxWidth / originalWidth, (double) thumbnailMaxHeight / originalHeight);

        int thumbWidth = (int) (originalWidth * scale);
        int thumbHeight = (int) (originalHeight * scale);

        thumbnailImage = originalImage.getScaledInstance(
                thumbWidth,
                thumbHeight,
                Image.SCALE_SMOOTH
        );
    }

    private void loadFullResolution() {
        if (fullResolutionImage != null) {
            return;
        }

        ImageIcon fullIcon = new ImageIcon(filePath);
        fullResolutionImage = fullIcon.getImage();
    }

    @Override
    public Image getImage() {
        if (fullResolutionImage == null) {
            loadFullResolution();
        }
        return fullResolutionImage;
    }

    @Override
    public Image getThumbnail() {
        return thumbnailImage;
    }

    @Override
    public int getWidth() {
        if (thumbnailImage != null) {
            return thumbnailImage.getWidth(null);
        }
        return 0;
    }

    @Override
    public int getHeight() {
        if (thumbnailImage != null) {
            return thumbnailImage.getHeight(null);
        }
        return 0;
    }

    @Override
    public String getFilePath() {
        return filePath;
    }
}
