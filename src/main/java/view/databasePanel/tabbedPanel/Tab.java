package view.databasePanel.tabbedPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Tab {
    private final String name;
    private final TableModel tableModel;
    private final JTable tableGUI;
    private final ArrayList<CellComboBox> cellComboBoxes = new ArrayList<>();

    public Tab(String name) {
        this.name = name;
        tableModel = new TableModel(name);
        tableGUI = new JTable(tableModel);
        tableGUI.setGridColor(Color.BLACK);
        tableGUI.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
    }

    public String getName() { return name; }

    public TableModel getTableModel() { return tableModel; }

    public void setCellEditor(int columnIndex, CellComboBox cellComboBox) {
        cellComboBoxes.add(cellComboBox);
        tableGUI.getColumnModel()
                .getColumn(columnIndex)
                .setCellEditor(new DefaultCellEditor(cellComboBox));
    }

    public void updateComboBoxes() {
        for (CellComboBox cellComboBox: cellComboBoxes)
            cellComboBox.updateItems();
    }

    public JScrollPane getPanel() { return new JScrollPane(tableGUI); }

    public String getSelectedRowPrimaryKey() {
        return (String) tableModel.getRowPrimaryKey(tableGUI.getSelectedRow());
    }

    public void repaintTable() { tableGUI.repaint(); }
}