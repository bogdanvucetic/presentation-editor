package raf.graffito.dsw.mediator;

import raf.graffito.dsw.bridge.*;
import raf.graffito.dsw.gui.swing.view.MainFrame;

public class WindowModeMediator implements Mediator {
    private static WindowModeMediator instance;
    private WindowMode currentMode;
    private WindowSizeImplementor implementor;

    private WindowModeMediator() {
        this.implementor = new JFrameResizeImpl();
        this.currentMode = new NormalWindowMode(implementor);
    }

    public static WindowModeMediator getInstance() {
        if (instance == null) {
            instance = new WindowModeMediator();
        }
        return instance;
    }

    @Override
    public void changeMode(String modeType) {
        switch (modeType) {
            case "Normalno":
                currentMode = new NormalWindowMode(implementor);
                break;
            case "Pun ekran":
                currentMode = new FullscreenWindowMode(implementor);
                break;
            case "Mali":
                currentMode = new SmallWindowMode(implementor);
                break;
        }

        currentMode.apply(MainFrame.getInstance(), MainFrame.getInstance().getNormalDimension());
    }
}
