package raf.graffito.dsw.controller.actions.file;

import raf.graffito.dsw.controller.actions.AbstractGraffAction;
import raf.graffito.dsw.services.proxy.ImageProxy;
import raf.graffito.dsw.gui.swing.frames.MainFrame;

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

        if (!(fileChooser.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION)) {
            return;
        }

        for (File file : fileChooser.getSelectedFiles()) {
            ImageProxy proxy = new ImageProxy(file.getAbsolutePath());
            String baseName = file.getName().replaceFirst("[.][^.]+$", "");
            MainFrame.getInstance().getInfoPanel().getImagePanel().addImageToPanel(proxy, baseName);
        }
    }
}
