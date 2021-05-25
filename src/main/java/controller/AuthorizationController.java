package controller;

import model.Tools;
import model.User;

import java.sql.Connection;

public class AuthorizationController {
    public static boolean connect(String login, String password) {
        Connection db = Tools.connect();
        if (db == null) return false; // error

        String sql = "SELECT user_password FROM Database_Users WHERE user_login = '" + login + "'";
        String encodedPassword = Tools.getStringViaSQL(db, sql);
        if (encodedPassword == null) return false; // wrong login
        if (!Tools.checkPassword(password, encodedPassword)) return false; // wrong password

        sql = "SELECT accessLevel FROM Database_Users WHERE user_login = '" + login + "'";
        int result = Tools.getIntViaSQL(db, sql);
        switch (result) {
            case 0 -> User.setSettings(db, login, 0); // car check-in and check-out system
            case 1 -> User.setSettings(db, login, 1); // guard
            case 2 -> User.setSettings(db, login, 2); // administrator
            default -> { return false; } // error
        }
        DatabaseController.getInstance().init();
        PanelController.setDatabasePanel();
        return true; // success
    }
}