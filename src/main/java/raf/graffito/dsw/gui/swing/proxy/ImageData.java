package raf.graffito.dsw.gui.swing.proxy;

import java.awt.*;

public interface ImageData {
    Image getImage();
    Image getThumbnail();
    int getWidth();
    int getHeight();
    String getFilePath();
}
