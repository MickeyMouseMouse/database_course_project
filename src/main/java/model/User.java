package model;

import java.sql.Connection;

public class User {
    private static Connection db;
    private static String login;
    private static int accessLevel;
    private static boolean cacheEnabled = false;

    public static void setSettings(Connection db, String login, int accessLevel) {
        User.db = db;
        User.login = login;
        User.accessLevel = accessLevel;
    }

    public static Connection getDB() { return db; }

    public static String getLogin() { return login; }

    public static int getAccessLevel() { return accessLevel; }

    public static boolean isCacheEnabled() { return cacheEnabled; }

    public static void setCacheEnabled(boolean mode) { cacheEnabled = mode; }

    public static void logout() { if (db != null ) Tools.disconnect(db); }
}
