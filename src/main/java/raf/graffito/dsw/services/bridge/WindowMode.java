package raf.graffito.dsw.services.bridge;

import javax.swing.*;
import java.awt.*;

public abstract class WindowMode {
    protected WindowSizeImplementor implementor;

    public WindowMode(WindowSizeImplementor implementor) {
        this.implementor = implementor;
    }

    public abstract void apply(JFrame frame, Dimension size);
}
