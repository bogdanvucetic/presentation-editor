package raf.graffito.dsw.controller.actions.file;

import raf.graffito.dsw.controller.actions.AbstractGraffAction;
import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.gui.swing.frames.MainFrame;
import raf.graffito.dsw.gui.swing.frames.ProjectView;
import raf.graffito.dsw.model.composite.Project;
import raf.graffito.dsw.services.events.message.MessageType;
import raf.graffito.dsw.services.serializer.JacksonSerializer;
import raf.graffito.dsw.services.serializer.Serializer;
import raf.graffito.dsw.utils.FolderPreferences;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

public class SaveProjectAction extends AbstractGraffAction {
    public SaveProjectAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        putValue(NAME, "Sačuvaj projekat");
        putValue(SHORT_DESCRIPTION, "Sačuvaj trenutno otvoreni projekat na trenutnoj putanji");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ProjectView currentProjectView = MainFrame.getInstance().getCurrentProjectView();

        if (currentProjectView == null || currentProjectView.getProject() == null) {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage("Nijedan projekat trenutno nije otvoren", MessageType.ERROR);
            return;
        }

        Project project = currentProjectView.getProject();

        if (!project.isChanged()) {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage("Projekat nije promenjen", MessageType.ERROR);
            return;
        }

        Serializer serializer = new JacksonSerializer();
        String defaultFolderPath = FolderPreferences.getDefaultFolderPath();
        File fileToSave;

        if (defaultFolderPath == null) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Izaberi folder gde će se projekti čuvati");
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fileChooser.setAcceptAllFileFilterUsed(false);

            if (fileChooser.showSaveDialog(MainFrame.getInstance()) != JFileChooser.APPROVE_OPTION) {
                return;
            }

            File selectedFolder = fileChooser.getSelectedFile();
            FolderPreferences.setDefaultFolderPath(selectedFolder.getAbsolutePath());

            String projectName = project.getName().replaceAll("\\s+", "_");
            fileToSave = new File(selectedFolder, projectName + ".json");
        } else {
            String projectName = project.getName().replaceAll("\\s+", "_");
            fileToSave = new File(defaultFolderPath, projectName + ".json");
        }

        serializer.saveProject(project, fileToSave);
        project.setChanged(false);
    }
}
