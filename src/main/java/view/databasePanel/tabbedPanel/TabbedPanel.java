package view.databasePanel.tabbedPanel;

import controller.DatabaseController;
import model.DatabaseTables;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class TabbedPanel extends JTabbedPane { // singleton
    private static TabbedPanel instance;
    public static synchronized TabbedPanel getInstance() {
        if (instance == null) instance = new TabbedPanel();
        return instance;
    }


    public TabbedPanel() {
        setForeground(Color.BLACK);
        addChangeListener(e ->
            DatabaseController.getInstance().updateComboBoxes(getSelectedIndex())
        );
    }

    public int getSelectedTabIndex() { return getSelectedIndex(); }

    public void initGUI() {
        final DatabaseController cnt = DatabaseController.getInstance();
        for (String tabName: Objects.requireNonNull(DatabaseTables.getAvailableTables())) {
            add(tabName, cnt.getTab(tabName).getPanel());
        }

        setSelectedIndex(0);
    }

    public void clearAllTables() { removeAll(); }
 }
