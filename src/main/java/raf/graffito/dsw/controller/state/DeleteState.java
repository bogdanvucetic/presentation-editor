package raf.graffito.dsw.controller.state;

import raf.graffito.dsw.controller.EditorContext;
import raf.graffito.dsw.gui.swing.view.MainFrame;
import raf.graffito.dsw.model.composite.Slide;
import raf.graffito.dsw.model.composite.SlideElement;

import java.util.Set;

public class DeleteState extends State {
    @Override
    public void onEnter() {
        if (EditorContext.getInstance().getCurrentSlideView() == null) {
            return;
        }

        Set<SlideElement> selectedElements = EditorContext.getInstance().getSelectedElements();
        Slide slide = EditorContext.getInstance().getCurrentSlideView().getSlide();

        for (SlideElement element : selectedElements) {
            slide.removeChild(element);
        }

        EditorContext.getInstance().clearSelectedElements();
        EditorContext.getInstance().changeState(MainFrame.getInstance().getActionManager().getSelectSlideElementsAction().getState());

        EditorContext.getInstance().getCurrentSlideView().revalidate();
        EditorContext.getInstance().getCurrentSlideView().repaint();
    }
}
