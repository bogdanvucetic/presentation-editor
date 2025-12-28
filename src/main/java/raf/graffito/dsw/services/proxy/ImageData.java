package raf.graffito.dsw.services.proxy;

import java.awt.*;

public interface ImageData {
    Image getImage();
    Image getThumbnail();
    int getWidth();
    int getHeight();
    String getFilePath();
}
