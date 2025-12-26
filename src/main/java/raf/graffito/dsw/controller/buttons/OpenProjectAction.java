package raf.graffito.dsw.controller.buttons;

import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.gui.swing.view.MainFrame;
import raf.graffito.dsw.model.composite.Project;
import raf.graffito.dsw.observer.message.MessageType;
import raf.graffito.dsw.serializer.JacksonSerializer;
import raf.graffito.dsw.serializer.Serializer;

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
