package raf.graffito.dsw.controller.buttons;

import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.gui.swing.view.MainFrame;
import raf.graffito.dsw.gui.swing.view.ProjectView;
import raf.graffito.dsw.observer.message.MessageType;
import raf.graffito.dsw.serializer.JacksonSerializer;
import raf.graffito.dsw.serializer.Serializer;
import raf.graffito.dsw.utils.FolderPreferences;

import java.awt.event.ActionEvent;
import java.io.File;

public class SaveAsTemplateAction extends AbstractGraffAction {
    public SaveAsTemplateAction() {
        putValue(NAME, "Sačuvaj kao šablon");
        putValue(SHORT_DESCRIPTION, "Sačuvaj trenutno otvoreni projekat kao šablon za buduće korišćenje");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ProjectView currentProjectView = MainFrame.getInstance().getCurrentProjectView();
        Serializer serializer = new JacksonSerializer();

        if (currentProjectView == null || currentProjectView.getProject() == null) {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage("Nijedan projekat trenutno nije otvoren", MessageType.ERROR);
            return;
        }

        String defaultFolderPath = "src/main/resources/templates";
        File templateDir = new File(defaultFolderPath);

        if (!(templateDir.exists() && templateDir.isDirectory())) {
            ApplicationFramework.getInstance().getMessageGenerator()
                    .createMessage("Nemoguće napraviti fajl u: " + defaultFolderPath, MessageType.ERROR);
            return;
        }

        File fileToSave = nextTemplateFile(templateDir);

        serializer.saveProject(currentProjectView.getProject(), fileToSave);
        ApplicationFramework.getInstance().getMessageGenerator().createMessage("Šablon uspešno sačuvan", MessageType.INFO);
    }

    private File nextTemplateFile(File templateDir) {
        int i = 1;
        while (true) {
            File candidate = new File(templateDir, "template_" + i + ".json");
            if (!candidate.exists()) {
                return candidate;
            }
            i++;
        }
    }
}
