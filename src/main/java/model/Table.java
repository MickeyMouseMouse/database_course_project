package model;

import java.util.ArrayList;

public class Table {
    private final String name;
    private final ArrayList<String> header;
    private ArrayList<ArrayList<String>> rows; // cache

    public Table(String name) {
        this.name = name;
        this.header = DatabaseTables.getTableHeader(name);
    }

    public ArrayList<String> getHeader() { return header; }

    public ArrayList<ArrayList<String>> getRows() { return rows; }

    private void addRowForNewData() {
        final ArrayList<String> newRow = new ArrayList<>();
        for (int i = 0; i < header.size(); i++)
            newRow.add("");
        rows.add(newRow);
    }

    private void removeRowForNewData() {
        rows.remove(rows.size() - 1);
    }

    public ArrayList<String> getRowWithNewData() {
        return rows.get(rows.size() - 1);
    }

    public void addRow(ArrayList<String> newRow) {
        removeRowForNewData();
        rows.add(newRow);
        addRowForNewData();
    }

    public void updateAllRows() {
        rows = Tools.getEntireTableViaSQL(User.getDB(), name);
        addRowForNewData();
    }

    public void removeRow(String primaryKey) {
        for (int i = 0; i < rows.size() - 1; i++)
            if (rows.get(i).get(0).equals(primaryKey)) {
                rows.remove(i);
                break;
            }
    }

    public Object getRowPrimaryKey(int row) {
        if (row == rows.size() - 1)
            return null;
        else
            return rows.get(row).get(0);
    }

    public ArrayList<String> getOptions() {
        ArrayList<String> options = new ArrayList<>();
        for (int i = 0; i < rows.size() - 1; i++) {
            options.add(rows.get(i).get(0));
        }
        return options;
    }
}
