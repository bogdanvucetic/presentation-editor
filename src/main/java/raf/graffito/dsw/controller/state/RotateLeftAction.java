package raf.graffito.dsw.controller.state;

import raf.graffito.dsw.controller.EditorContext;
import raf.graffito.dsw.controller.buttons.AbstractGraffAction;
import raf.graffito.dsw.controller.commands.RotateCommand;

import java.awt.event.ActionEvent;

public class RotateLeftAction extends AbstractGraffAction {
    public RotateLeftAction() {
        putValue(SMALL_ICON, loadIcon("/images/rotateLeft.png"));
        putValue(NAME, "Rotiraj ulevo");
        putValue(SHORT_DESCRIPTION, "Rotiraj izabrane elemente slajda za 90° ulevo");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        EditorContext context = EditorContext.getInstance();

        if (context.getSelectedElements().isEmpty()) return;
        if (context.getCurrentSlideView() == null) return;

        RotateCommand command = new RotateCommand(context.getCurrentSlideView().getSlide(), context.getSelectedElements(), 90);
        context.execute(command);
    }
}
