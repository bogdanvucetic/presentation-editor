package raf.graffito.dsw.controller.buttons;

import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.gui.swing.tree.GraffTreeElem;
import raf.graffito.dsw.gui.swing.view.MainFrame;
import raf.graffito.dsw.model.composite.*;
import raf.graffito.dsw.model.factory.GraffNodeFactory;
import raf.graffito.dsw.model.factory.ProjectFactory;
import raf.graffito.dsw.model.factory.SlideFactory;
import raf.graffito.dsw.observer.message.MessageType;
import raf.graffito.dsw.serializer.JacksonSerializer;
import raf.graffito.dsw.serializer.Serializer;
import raf.graffito.dsw.utils.ChildTypePicker;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.io.File;

public class CreateChildAction extends AbstractGraffAction {
    public CreateChildAction() {
        putValue(SMALL_ICON, loadIcon("/images/create.png")); // Postavljanje ikonice
        putValue(NAME, "Dodaj čvor"); // Ime akcije
        putValue(SHORT_DESCRIPTION, "Dodaj novi čvor"); // Tooltip
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GraffTreeElem selected = MainFrame.getInstance().getGraffTree().getSelectedNode();

        if (selected == null){
            ApplicationFramework.getInstance().getMessageGenerator().createMessage("Nijedan čvor nije izabran.", MessageType.WARNING);
            return;
        }

        if (!(selected.getGraffNode() instanceof GraffNodeComposite parent)) {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage("Izabrani čvor ne može da sadrži podčvorove.", MessageType.ERROR);
            return;
        }

        GraffNodeFactory factory = getFactoryFor(parent);

        if (factory == null) {
            return;
        }

        if (factory instanceof ProjectFactory) {
            if (JOptionPane.showConfirmDialog(
                    MainFrame.getInstance(),
                    new JLabel("Da li želite da napravite projekat na osnovu postojećeg šablona?"),
                    "",
                    JOptionPane.YES_NO_OPTION) == 0) {

                Serializer serializer = new JacksonSerializer();

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Otvori šablon");
                fileChooser.setFileFilter(new FileNameExtensionFilter("JSON", "json"));
                fileChooser.setCurrentDirectory(new File("src/main/resources/templates"));

                if (fileChooser.showOpenDialog(MainFrame.getInstance()) != JFileChooser.APPROVE_OPTION) {
                    return;
                }

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

                return;
            }
        }

        GraffNode child = factory.createGraffNode(parent);

        try {
            parent.addChild(child);
        } catch (Exception ex) {
            ApplicationFramework.getInstance().getMessageGenerator().createMessage(ex.getMessage(), MessageType.ERROR);
        }
    }

    private GraffNodeFactory getFactoryFor(GraffNode parent) {
        if (parent instanceof Workspace) {
            return new ProjectFactory();
        } else if (parent instanceof Project) {
            return ChildTypePicker.pickProjectChild();
        } else if (parent instanceof Presentation) {
            return new SlideFactory();
        }
        return null;
    }
}
