package raf.graffito.dsw.controller.buttons;

import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.gui.swing.view.MainFrame;
import raf.graffito.dsw.gui.swing.view.ProjectView;
import raf.graffito.dsw.observer.message.MessageType;
import raf.graffito.dsw.serializer.JacksonSerializer;
import raf.graffito.dsw.serializer.Serializer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class SaveProjectAsAction extends AbstractGraffAction {
    public SaveProjectAsAction() {
        putValue(NAME, "Sačuvaj projekat kao");
        putValue(SHORT_DESCRIPTION, "Sačuvaj trenutno otvoreni projekat na novoj putanji");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ProjectView currentProjectView = MainFrame.getInstance().getCurrentProjectView();
        Serializer serializer = new JacksonSerializer();

        if (currentProjectView == null || currentProjectView.getProject() == null) {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage("Nijedan projekat trenutno nije otvoren", MessageType.ERROR);
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Sačuvaj projekat kao");
        fileChooser.setSelectedFile(new File(currentProjectView.getProject().getName().replaceAll("\\s+", "_") + ".json"));

        if (fileChooser.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            if (!fileToSave.getName().toLowerCase().endsWith(".json")) {
                fileToSave = new File(fileToSave.getAbsolutePath() + ".json");
            }

            serializer.saveProject(currentProjectView.getProject(), fileToSave);
        }
    }
}
