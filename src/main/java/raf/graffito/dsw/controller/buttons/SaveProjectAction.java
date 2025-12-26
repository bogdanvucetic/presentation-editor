package raf.graffito.dsw.controller.buttons;

import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.gui.swing.view.MainFrame;
import raf.graffito.dsw.gui.swing.view.ProjectView;
import raf.graffito.dsw.observer.message.MessageType;
import raf.graffito.dsw.serializer.JacksonSerializer;
import raf.graffito.dsw.serializer.Serializer;
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
        Serializer serializer = new JacksonSerializer();

        if (currentProjectView == null || currentProjectView.getProject() == null) {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage("Nijedan projekat trenutno nije otvoren", MessageType.ERROR);
            return;
        }

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

            String projectName = currentProjectView.getProject().getName().replaceAll("\\s+", "_");
            fileToSave = new File(selectedFolder, projectName + ".json");
        } else {
            String projectName = currentProjectView.getProject().getName().replaceAll("\\s+", "_");
            fileToSave = new File(defaultFolderPath, projectName + ".json");
        }

        serializer.saveProject(currentProjectView.getProject(), fileToSave);
        ApplicationFramework.getInstance().getMessageGenerator().createMessage("Projekat uspešno sačuvan", MessageType.INFO);
    }
}
