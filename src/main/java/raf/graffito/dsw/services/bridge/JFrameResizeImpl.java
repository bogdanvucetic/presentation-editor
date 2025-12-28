package raf.graffito.dsw.services.bridge;

import javax.swing.*;
import java.awt.*;

public class JFrameResizeImpl implements WindowSizeImplementor {
    @Override
    public void setNormalSize(JFrame frame, Dimension size) {
        frame.setExtendedState(JFrame.NORMAL);
        frame.setSize(size);
        frame.setLocationRelativeTo(null);
    }

    @Override
    public void setFullscreen(JFrame frame) {
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    @Override
    public void setSmallSize(JFrame frame, Dimension size) {
        frame.setExtendedState(JFrame.NORMAL);
        frame.setSize(size.width / 2, size.height / 2);
        frame.setLocationRelativeTo(null);
    }
}
