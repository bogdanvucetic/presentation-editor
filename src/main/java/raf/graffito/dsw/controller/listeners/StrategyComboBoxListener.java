package raf.graffito.dsw.controller.listeners;

import raf.graffito.dsw.controller.EditorContext;
import raf.graffito.dsw.strategy.PreciseEvalStrategy;
import raf.graffito.dsw.strategy.SimpleEvalStrategy;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StrategyComboBoxListener implements ActionListener {
    private final JComboBox<String> strategyComboBox;

    public StrategyComboBoxListener(JComboBox<String> strategyComboBox) {
        this.strategyComboBox = strategyComboBox;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedIndex = strategyComboBox.getSelectedIndex();
        if (selectedIndex == 0) {
            EditorContext.getInstance().setCurrentEvalStrategy(new SimpleEvalStrategy());
        } else {
            EditorContext.getInstance().setCurrentEvalStrategy(new PreciseEvalStrategy());
        }
    }
}
