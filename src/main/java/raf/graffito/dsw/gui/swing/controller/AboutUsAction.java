package raf.graffito.dsw.gui.swing.controller;

import raf.graffito.dsw.gui.swing.view.AboutUs;

import java.awt.event.ActionEvent;

public class AboutUsAction extends AbstractGraffAction {
    public AboutUsAction() {
        putValue(SMALL_ICON, loadIcon("/images/aboutUs.png")); // Postavljanje ikonice
        putValue(NAME, "O nama"); // Ime akcije
        putValue(SHORT_DESCRIPTION, "Prikaži informacije o timu"); // Tooltip
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new AboutUs().setVisible(true);
    }
}
