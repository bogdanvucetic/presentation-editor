package raf.graffito.dsw.bridge;

import javax.swing.*;
import java.awt.*;

public class FullscreenWindowMode extends WindowMode {
    public FullscreenWindowMode(WindowSizeImplementor implementor) {
        super(implementor);
    }

    @Override
    public void apply(JFrame frame, Dimension size) {
        implementor.setFullscreen(frame);
    }
}
