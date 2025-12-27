package raf.graffito.dsw.bridge;

import javax.swing.*;
import java.awt.*;

public class SmallWindowMode extends WindowMode {
    public SmallWindowMode(WindowSizeImplementor implementor) {
        super(implementor);
    }

    @Override
    public void apply(JFrame frame, Dimension size) {
        implementor.setSmallSize(frame, size);
    }
}
