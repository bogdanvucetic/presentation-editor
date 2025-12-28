package raf.graffito.dsw.controller.actions.file;

import raf.graffito.dsw.controller.actions.AbstractGraffAction;
import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.gui.swing.frames.MainFrame;
import raf.graffito.dsw.gui.swing.frames.ProjectView;
import raf.graffito.dsw.services.events.message.MessageType;
import raf.graffito.dsw.services.serializer.JacksonSerializer;
import raf.graffito.dsw.services.serializer.Serializer;
import raf.graffito.dsw.utils.FolderPreferences;

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

        String defaultFolderPath = FolderPreferences.getDefaultFolderPath();
        if (defaultFolderPath != null) {
            fileChooser.setCurrentDirectory(new File(defaultFolderPath));
        }

        fileChooser.setDialogTitle("Sačuvaj projekat kao");
        fileChooser.setSelectedFile(new File(currentProjectView.getProject().getName().replaceAll("\\s+", "_") + ".json"));

        if (!(fileChooser.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION)) {
            return;
        }

        File fileToSave = fileChooser.getSelectedFile();

        if (!fileToSave.getName().toLowerCase().endsWith(".json")) {
            fileToSave = new File(fileToSave.getAbsolutePath() + ".json");
        }

        serializer.saveProject(currentProjectView.getProject(), fileToSave);
        FolderPreferences.setDefaultFolderPath(fileToSave.getParentFile().getAbsolutePath());
        ApplicationFramework.getInstance().getMessageGenerator().createMessage("Projekat uspešno sačuvan", MessageType.INFO);
    }
}
