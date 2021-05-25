package controller;

import model.*;
import view.databasePanel.bottomPanel.BottomPanel;
import view.databasePanel.tabbedPanel.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Objects;

public class DatabaseController { // singleton
    private static DatabaseController instance;
    public static synchronized DatabaseController getInstance() {
        if (instance == null) instance = new DatabaseController();
        return instance;
    }

    private final ArrayList<Tab> tabs = new ArrayList<>();

    public Tab getTab(String tabName) {
        for (Tab tab: tabs) {
            if (tab.getName().equals(tabName))
                return tab;
        }
        return null;
    }

    public Tab getSelectedTab() {
        return getTab(Objects.requireNonNull(DatabaseTables
                .getAvailableTables()).get(TabbedPanel.getInstance().getSelectedTabIndex()));
    }

    public TableModel getTableModel(String tableName) {
        for (Tab tab: tabs) {
            if (tab.getName().equals(tableName))
                return tab.getTableModel();
        }
        return null;
    }

    public void init() {
        for (int i = 0; i < DatabaseTables.tableCount; i++)
            tabs.add(new Tab(DatabaseTables.getTableName(i)));

        for (Tab tab: tabs) {
            switch (tab.getName()) {
                case "Parking_Logbook" -> {
                    tab.setCellEditor(2, new CellComboBox(getTableModel("Working_Shifts")));
                    tab.setCellEditor(6, new CellComboBox(getTableModel("Places")));
                    tab.setCellEditor(7, new CellComboBox(getTableModel("Tariffs")));
                    tab.setCellEditor(8, new CellComboBox(getTableModel("Owner_to_car")));
                }
                case "Working_Shifts" ->
                        tab.setCellEditor(6, new CellComboBox(getTableModel("Security_Guards")));
                case "Owner_to_car" -> {
                    tab.setCellEditor(2, new CellComboBox(getTableModel("Car_Numbers")));
                    tab.setCellEditor(3, new CellComboBox(getTableModel("Owners")));
                }
                case "Car_Numbers" -> {
                    tab.setCellEditor(2, new CellComboBox(getTableModel("Models")));
                    tab.setCellEditor(3, new CellComboBox(getTableModel("Colors")));
                }
            }
        }
    }

    public void updateComboBoxes(int tabIndex) {
        if (!tabs.isEmpty())
                getTab(Objects.requireNonNull(DatabaseTables
                        .getAvailableTables()).get(tabIndex)).updateComboBoxes();
    }

    public void removeSelectedEntity() {
        Tab tab = getSelectedTab();
        String primaryKey = tab.getSelectedRowPrimaryKey();
        if (primaryKey == null) return; // nothing selected
        String sql = "";
        switch (tab.getName()) {
            case "Parking_Logbook" -> sql = "DELETE FROM Parking_Logbook WHERE log_id = " + primaryKey;
            case "Places" -> sql = "DELETE FROM Places WHERE place_id = " + primaryKey;
            case "Tariffs" -> sql = "DELETE FROM Tariffs WHERE tariff_id = " + primaryKey;
            case "Working_Shifts" -> sql = "DELETE FROM Working_Shifts WHERE working_shift_id = " + primaryKey;
            case "Owner_to_car" -> sql = "DELETE FROM Owner_to_car WHERE owner_to_car_id = " + primaryKey;
            case "Security_Guards" -> sql = "DELETE FROM Security_Guards WHERE guard_id = " + primaryKey;
            case "Car_Numbers" -> sql = "DELETE FROM Car_Numbers WHERE car_number = '" + primaryKey + "'";
            case "Owners" -> sql = "DELETE FROM Owners WHERE driver_license_number = '" + primaryKey + "'";
            case "Models" -> sql = "DELETE FROM Models WHERE model_id = " + primaryKey;
            case "Colors" -> sql = "DELETE FROM Colors WHERE color_id = " + primaryKey;
            case "Database_Users" -> sql = "DELETE FROM Database_Users WHERE user_login = '" + primaryKey + "'";
        }
        remove(tab, sql, primaryKey);
    }

    private void remove(Tab tab, String sql, String primaryKey) {
        if (Tools.executeUpdate(User.getDB(), sql)) {
            long time = System.nanoTime(); // timer
            if (User.isCacheEnabled())
                tab.getTableModel().removeRow(primaryKey);
            else
                tab.getTableModel().updateAllRows();
            BottomPanel.getInstance().setTime("Remove: " + (System.nanoTime() - time + " ns")); // timer
            tab.repaintTable();
        }
        else
            JOptionPane.showMessageDialog(null, "Removal failed");
    }

    public void addNewEntity() {
        Tab tab = getSelectedTab();
        ArrayList<String> newRow = tab.getTableModel().getRowWithNewData();
        String sql = "";
        switch (tab.getName()) {
            case "Parking_Logbook" -> sql = "INSERT INTO Parking_Logbook (working_shift_id, log_date, log_time, " +
                    "entry_exit, place_id, tariff_id, owner_to_car_id) VALUES (" + newRow.get(1) + ",'" + newRow.get(2) +
                    "','" + newRow.get(3) + "'," + newRow.get(4) + "," + newRow.get(5) + "," + newRow.get(6) + "," +
                    newRow.get(7) + ") RETURNING log_id";
            case "Places" -> sql = "INSERT INTO Places (place_floor, place_sector, place_number) VALUES (" +
                    newRow.get(1) + "," + newRow.get(2) + "," + newRow.get(3) + ") RETURNING place_id";
            case "Tariffs" -> sql = "INSERT INTO Tariffs (tariff_name, tariff_price, tariff_interval) VALUES ('" +
                    newRow.get(1) + "'," + newRow.get(2) + "," + newRow.get(3) + ") RETURNING tariff_id";
            case "Working_Shifts" -> sql = "INSERT INTO Working_Shifts (work_date_start, time_start," +
                    "work_date_end, time_end, guard_id) VALUES ('" + newRow.get(1) + "','" + newRow.get(2) + "','" +
                    newRow.get(3) + "','" + newRow.get(4) + "'," + newRow.get(5) + ") RETURNING working_shift_id";
            case "Owner_to_car" -> sql = "INSERT INTO Owner_to_car (car_number, driver_license_number) VALUES ('" +
                    newRow.get(1) + "','" + newRow.get(2) + "') RETURNING Owner_to_car_id";
            case "Security_Guards" -> sql = "INSERT INTO Security_Guards (name, surname, patronymic) VALUES ('" +
                    newRow.get(1) + "','" + newRow.get(2) + "','" + newRow.get(3) + "') RETURNING guard_id";
            case "Car_Numbers" -> sql = "INSERT INTO Car_Numbers (car_number, model_id, color_id) VALUES ('" +
                    newRow.get(0) + "'," + newRow.get(1) + "," + newRow.get(2) + ") RETURNING car_number";
            case "Owners" -> sql = "INSERT INTO Owners (driver_license_number, name, surname, patronymic) VALUES ('" +
                    newRow.get(0) + "','" + newRow.get(1) + "','" + newRow.get(2) + "','" + newRow.get(3) +
                    "') RETURNING driver_license_number";
            case "Models" -> sql = "INSERT INTO Models (model_name) VALUES ('" + newRow.get(1) + "') RETURNING model_id";
            case "Colors" -> sql = "INSERT INTO Colors (color_name) VALUES ('" + newRow.get(1) + "') RETURNING color_id";
            case "Database_Users" -> sql = "INSERT INTO Database_Users (user_login, user_password, accessLevel) VALUES ('" +
                    newRow.get(0) + "','" + newRow.get(1) + "'," + newRow.get(2) + ") RETURNING user_login";
        }
        add(tab, sql, newRow);
    }

    private void add(Tab tab, String sql, ArrayList<String> newRow) {
        String newRowID = Tools.getStringViaSQL(User.getDB(), sql);
        if (newRowID != null) {
            long time = System.nanoTime(); // timer
            if (User.isCacheEnabled()) {
                newRow.set(0, newRowID);
                tab.getTableModel().addRow(newRow);
            }
            else
                tab.getTableModel().updateAllRows();
            BottomPanel.getInstance().setTime("Add: " + (System.nanoTime() - time + " ns")); // timer
            tab.repaintTable();
        }
        else
            JOptionPane.showMessageDialog(null, "Insertion failed");
    }

    public void logout() {
        User.logout();
        tabs.clear();
        TabbedPanel.getInstance().clearAllTables();
        PanelController.setAuthorizationPanel();
    }
}
