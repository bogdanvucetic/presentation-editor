package raf.graffito.dsw.gui.swing.view;

import raf.graffito.dsw.controller.listeners.StrategyComboBoxListener;

import javax.swing.*;
import java.awt.*;

public class StrategySelector extends JPanel {
    public StrategySelector() {
        setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        setOpaque(false);

        JLabel strategyLabel = new JLabel("Računanje prostora: ");
        add(strategyLabel);

        String[] strategies = {"Prosto (Brzo)", "Kompleksano (Precizno)"};
        JComboBox<String> strategyComboBox = new JComboBox<>(strategies);
        strategyComboBox.setSelectedIndex(0);
        strategyComboBox.addActionListener(new StrategyComboBoxListener(strategyComboBox));

        add(strategyComboBox);
    }
}
