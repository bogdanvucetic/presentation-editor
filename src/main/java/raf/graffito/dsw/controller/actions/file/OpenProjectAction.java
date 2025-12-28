package raf.graffito.dsw.controller.actions.file;

import raf.graffito.dsw.controller.actions.AbstractGraffAction;
import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.gui.swing.frames.MainFrame;
import raf.graffito.dsw.model.composite.Project;
import raf.graffito.dsw.services.events.message.MessageType;
import raf.graffito.dsw.services.serializer.JacksonSerializer;
import raf.graffito.dsw.services.serializer.Serializer;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

public class OpenProjectAction extends AbstractGraffAction {
    public OpenProjectAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        putValue(NAME, "Otvori projekat");
        putValue(SHORT_DESCRIPTION, "Otvori sačuvan projekat");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Serializer serializer = new JacksonSerializer();

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setDialogTitle("Otvori projekat");
        fileChooser.setFileFilter(new FileNameExtensionFilter("JSON", "json"));

        if (fileChooser.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            Project openedProject = serializer.openProject(file);

            if (openedProject == null) {
                return;
            }

            try {
                ApplicationFramework.getInstance().getGraffRepository().getWorkspace().openProject(openedProject);
            } catch (Exception ex) {
                ApplicationFramework.getInstance().getMessageGenerator().createMessage("Postoji projekat sa istim imenom.", MessageType.ERROR);
            }
        }
    }
}
