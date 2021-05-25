package view.databasePanel;

import view.databasePanel.bottomPanel.BottomPanel;
import view.databasePanel.topPanel.TopPanel;
import view.databasePanel.tabbedPanel.TabbedPanel;

import javax.swing.*;
import java.awt.*;

public class DatabasePanel extends JPanel {
    public DatabasePanel() {
        final GridBagLayout layout = new GridBagLayout();
        setLayout(layout);

        final GridBagConstraints topPanelConstraints = new GridBagConstraints();
        topPanelConstraints.gridy = 0;
        topPanelConstraints.gridx = 0;
        topPanelConstraints.anchor = GridBagConstraints.NORTH;
        topPanelConstraints.fill = GridBagConstraints.HORIZONTAL;
        topPanelConstraints.weightx = 1;
        TopPanel topPanel = TopPanel.getInstance();
        topPanel.initGUI();
        add(topPanel, topPanelConstraints);

        final GridBagConstraints tabbedPanelConstraints = new GridBagConstraints();
        tabbedPanelConstraints.gridy = 1;
        tabbedPanelConstraints.gridx = 0;
        tabbedPanelConstraints.anchor = GridBagConstraints.SOUTH;
        tabbedPanelConstraints.fill = GridBagConstraints.BOTH;
        tabbedPanelConstraints.weightx = 1;
        tabbedPanelConstraints.weighty = 1;
        TabbedPanel tabbedPanel = TabbedPanel.getInstance();
        tabbedPanel.initGUI();
        add(tabbedPanel, tabbedPanelConstraints);

        final GridBagConstraints bottomPanelConstraints = new GridBagConstraints();
        bottomPanelConstraints.gridy = 2;
        bottomPanelConstraints.gridx = 0;
        bottomPanelConstraints.anchor = GridBagConstraints.SOUTH;
        bottomPanelConstraints.fill = GridBagConstraints.HORIZONTAL;
        bottomPanelConstraints.weightx = 1;
        BottomPanel bottomPanel = BottomPanel.getInstance();
        bottomPanel.initGUI();
        add(bottomPanel, bottomPanelConstraints);

        setPreferredSize(new Dimension(1000, 500));
    }
}