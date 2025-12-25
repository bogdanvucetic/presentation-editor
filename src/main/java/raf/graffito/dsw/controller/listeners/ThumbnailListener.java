package raf.graffito.dsw.controller.listeners;

import raf.graffito.dsw.controller.EditorContext;
import raf.graffito.dsw.gui.swing.thumbnail.ImageThumbnail;
import raf.graffito.dsw.gui.swing.thumbnail.LogoThumbnail;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ThumbnailListener implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        EditorContext.getInstance().getCurrentState().thumbnailClicked(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() instanceof ImageThumbnail imageThumbnail) {
            imageThumbnail.setHovered(true);
        } else if (e.getSource() instanceof LogoThumbnail logoThumbnail) {
            logoThumbnail.setHovered(true);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() instanceof ImageThumbnail imageThumbnail) {
            imageThumbnail.setHovered(false);
        } else if (e.getSource() instanceof LogoThumbnail logoThumbnail) {
            logoThumbnail.setHovered(false);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }
}
