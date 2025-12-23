package raf.graffito.dsw.controller.state;

import raf.graffito.dsw.controller.EditorContext;
import raf.graffito.dsw.gui.swing.view.MainFrame;
import raf.graffito.dsw.model.composite.Slide;
import raf.graffito.dsw.model.composite.SlideElement;

import java.util.Set;

public class DeleteState extends State {
    @Override
    public void onEnter() {
        EditorContext context = EditorContext.getInstance();

        if (context.getCurrentSlideView() == null) {
            return;
        }

        Set<SlideElement> selectedElements = context.getSelectedElements();
        Slide slide = context.getCurrentSlideView().getSlide();

        for (SlideElement element : selectedElements) {
            slide.removeChild(element);
        }

        context.clearSelectedElements();
        context.changeState(MainFrame.getInstance().getActionManager().getSelectSlideElementsAction().getState());

        context.getCurrentSlideView().revalidate();
        context.getCurrentSlideView().repaint();
    }
}
