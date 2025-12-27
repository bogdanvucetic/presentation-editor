package raf.graffito.dsw.bridge;

import javax.swing.*;
import java.awt.*;

public interface WindowSizeImplementor {
    void setNormalSize(JFrame frame, Dimension size);
    void setFullscreen(JFrame frame);
    void setSmallSize(JFrame frame, Dimension size);
}
