package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class DatabaseTables {
    public static final int tableCount = 11;

    public static String getTableName(int index) {
        switch (index) {
            case 0 -> { return "Parking_Logbook"; }
            case 1 -> { return "Places"; }
            case 2 -> { return "Tariffs"; }
            case 3 -> { return "Working_Shifts"; }
            case 4 -> { return "Owner_to_car"; }
            case 5 -> { return "Security_Guards"; }
            case 6 -> { return "Car_Numbers"; }
            case 7 -> { return "Owners"; }
            case 8 -> { return "Models"; }
            case 9 -> { return "Colors"; }
            case 10 -> { return "Database_Users"; }
            default -> { return ""; }
        }
    }

    public static ArrayList<String> getTableHeader(String tableName) {
        return Tools.getTableHeader(User.getDB(), tableName);
    }

    public static ArrayList<String> getAvailableTables() {
        switch (User.getAccessLevel()) {
            case 0 -> { return new ArrayList<>(Collections.singletonList("Parking_Logbook")); }
            case 1 -> { return new ArrayList<>(Arrays.asList("Owner_to_car",
                    "Car_Numbers", "Owners")); }
            case 2 -> { return new ArrayList<>(Arrays.asList("Parking_Logbook",
                    "Places", "Tariffs", "Working_Shifts", "Owner_to_car",
                    "Security_Guards", "Car_Numbers", "Owners", "Models",
                    "Colors", "Database_Users")); }
        }
        return null;
    }
}
