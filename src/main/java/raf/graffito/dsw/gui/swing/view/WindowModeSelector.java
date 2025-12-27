package raf.graffito.dsw.gui.swing.view;

import javax.swing.*;
import java.awt.*;

public class WindowModeSelector extends JPanel {
    private JRadioButton normalRadio;
    private JRadioButton fullscreenRadio;
    private JRadioButton smallRadio;

    public WindowModeSelector() {
        setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        setOpaque(false);

        JLabel modeLabel = new JLabel("Režim prozora: ");
        add(modeLabel);

        normalRadio = new JRadioButton("Normalan");
        normalRadio.setOpaque(false);

        fullscreenRadio = new JRadioButton("Pun ekran");
        fullscreenRadio.setOpaque(false);

        smallRadio = new JRadioButton("Mali");
        smallRadio.setOpaque(false);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(normalRadio);
        buttonGroup.add(fullscreenRadio);
        buttonGroup.add(smallRadio);

        normalRadio.setSelected(true);

        add(normalRadio);
        add(fullscreenRadio);
        add(smallRadio);
    }
}
