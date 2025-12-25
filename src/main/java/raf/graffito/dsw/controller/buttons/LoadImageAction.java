package raf.graffito.dsw.controller.buttons;

import raf.graffito.dsw.gui.swing.view.MainFrame;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.io.File;

public class LoadImageAction extends AbstractGraffAction {
    public LoadImageAction() {
        putValue(SMALL_ICON, loadIcon("/images/loadImage.png"));
        putValue(NAME, "Učitaj sliku");
        putValue(SHORT_DESCRIPTION, "Učitaj sliku sa sistema");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setDialogTitle("Otvori sliku");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Images", "jpg", "jpeg", "png", "bmp"));

        if (fileChooser.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
            for (File file : fileChooser.getSelectedFiles()) {
                ImageIcon icon = new ImageIcon(file.getAbsolutePath());
                String baseName = file.getName().replaceFirst("[.][^.]+$", "");
                MainFrame.getInstance().getInfoPanel().getImagePanel().addImageToPanel(icon, baseName);
            }
        }
    }
}
