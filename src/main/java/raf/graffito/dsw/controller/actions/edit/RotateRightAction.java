package raf.graffito.dsw.controller.actions.edit;

import raf.graffito.dsw.controller.EditorContext;
import raf.graffito.dsw.controller.actions.AbstractGraffAction;
import raf.graffito.dsw.controller.commands.RotateCommand;

import java.awt.event.ActionEvent;

public class RotateRightAction extends AbstractGraffAction {
    public RotateRightAction() {
        putValue(SMALL_ICON, loadIcon("/images/rotateRight.png"));
        putValue(NAME, "Rotiraj udesno");
        putValue(SHORT_DESCRIPTION, "Rotiraj izabrane elemente slajda za 90° udeseno");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        EditorContext context = EditorContext.getInstance();

        if (context.getSelectedElements().isEmpty()) return;
        if (context.getCurrentSlideView() == null) return;

        RotateCommand command = new RotateCommand(context.getCurrentSlideView().getSlide(), context.getSelectedElements(), -90);
        context.execute(command);
    }
}
