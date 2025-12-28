package raf.graffito.dsw.controller.state;

import raf.graffito.dsw.controller.commands.DeleteCommand;
import raf.graffito.dsw.gui.swing.view.MainFrame;
import raf.graffito.dsw.model.composite.Slide;
import raf.graffito.dsw.model.composite.SlideElement;

import java.util.Set;

public class DeleteState extends State {
    @Override
    public void onEnter() {
        if (context.getCurrentSlideView() == null) {
            return;
        }

        Set<SlideElement> selectedElements = context.getSelectedElements();

        if (selectedElements.isEmpty()) {
            context.changeState(MainFrame.getInstance().getActionManager().getSelectSlideElementsAction().getState());
            return;
        }

        DeleteCommand command = new DeleteCommand(context.getCurrentSlideView().getSlide(), selectedElements);
        context.execute(command);

        context.clearSelectedElements();
        context.changeState(MainFrame.getInstance().getActionManager().getSelectSlideElementsAction().getState());
    }
}
