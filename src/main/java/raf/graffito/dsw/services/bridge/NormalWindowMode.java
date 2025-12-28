package raf.graffito.dsw.services.bridge;

import javax.swing.*;
import java.awt.*;

public class NormalWindowMode extends WindowMode {
    public NormalWindowMode(WindowSizeImplementor implementor) {
        super(implementor);
    }

    @Override
    public void apply(JFrame frame, Dimension size) {
        implementor.setNormalSize(frame, size);
    }
}
