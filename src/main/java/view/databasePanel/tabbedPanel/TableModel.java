package view.databasePanel.tabbedPanel;

import model.Table;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TableModel extends AbstractTableModel {
    private final Table table;

    public TableModel(String name) {
        this.table = new Table(name);
        updateAllRows();
    }

    public ArrayList<String> getRowWithNewData() { return table.getRowWithNewData(); }

    public void addRow(ArrayList<String> newRow) {
        table.addRow(newRow);
        fireTableRowsInserted(0, table.getRows().size());
    }

    public void updateAllRows() {
        table.updateAllRows();
        fireTableRowsInserted(0, table.getRows().size());
    }

    public void removeRow(String primaryKey) {
        table.removeRow(primaryKey);
        fireTableRowsDeleted(0, table.getRows().size());
    }

    @Override
    public int getRowCount() { return table.getRows().size(); }

    @Override
    public int getColumnCount() { return table.getHeader().size() + 1; }

    @Override
    public Class<?> getColumnClass(int column) { return String.class; }

    @Override
    public boolean isCellEditable(int row, int column) {
        return row == table.getRows().size() - 1;
    }

    @Override
    public String getColumnName(int column) {
        if (column == 0) return "№";
        return table.getHeader().get(column - 1);
    }

    @Override
    public Object getValueAt(int row, int column) {
        if (row < 0 || row >= table.getRows().size()) return null; // invalid values

        if (column == 0) // line numbering
            if (row == table.getRows().size() - 1)
                return "";
            else
                return row + 1;

        return table.getRows().get(row).get(column - 1); // table values
    }

    @Override
    public void setValueAt(Object value, int row, int column) {
        if (row == table.getRows().size() - 1 && column != 0) {
            table.getRows().get(row).set(column - 1, (String) value);
        }
    }

    public Object getRowPrimaryKey(int row) {
        if (row < 0) return null;
        return table.getRowPrimaryKey(row);
    }

    public ArrayList<String> getOptions() { return table.getOptions(); }
}