package view.databasePanel.tabbedPanel;

import javax.swing.*;
import java.util.ArrayList;

public class CellComboBox extends JComboBox<String> {
    private final TableModel tableModel;

    public CellComboBox(TableModel tableModel) {
        this.tableModel = tableModel;
    }

    public void updateItems() {
        removeAllItems();
        addItem("");
        ArrayList<String> items = tableModel.getOptions();
        for (String item : items)
            addItem(item);
    }
}
