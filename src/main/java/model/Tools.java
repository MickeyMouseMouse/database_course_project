package model;

import java.sql.*;
import java.util.ArrayList;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Tools {
    public static Connection connect() {
        final String url = "jdbc:postgresql://localhost:5432/Logbook";
        Connection result = null;
        try {
             result = DriverManager.getConnection(url, "user1", "user1");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void disconnect(Connection db) {
        try {
            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean executeUpdate(Connection db, String sql) {
        try {
            final Statement st = db.createStatement();
            st.executeUpdate(sql);
            st.close();
        } catch (SQLException e) {
            return false; // error
        }
        return true; // success
    }

    public static int getIntViaSQL(Connection db, String sql) {
        int result;
        try {
            final Statement st = db.createStatement();
            final ResultSet rs = st.executeQuery(sql);
            rs.next();
            result = rs.getInt(1);
            rs.close();
            st.close();
        } catch (SQLException e) { result = -1; }
        return result;
    }

    public static String getStringViaSQL(Connection db, String sql) {
        final String result;
        try {
            final Statement st = db.createStatement();
            final ResultSet rs = st.executeQuery(sql);
            rs.next();
            result = rs.getString(1);
            rs.close();
            st.close();
        } catch (SQLException e) { return null; }
        return result;
    }

    public static ArrayList<String> getTableHeader(Connection db, String tableName) {
        final ArrayList<String> result = new ArrayList<>();
        try {
            final Statement st = db.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM " + tableName);
            final ResultSetMetaData rsmd = rs.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++ )
                result.add(rsmd.getColumnName(i));
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static ArrayList<ArrayList<String>> getEntireTableViaSQL(Connection db, String tableName) {
        final ArrayList<ArrayList<String>> result = new ArrayList<>();
        try {
            final Statement st = db.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM " + tableName);
            final ResultSetMetaData rsmd = rs.getMetaData();
            final int columnsNumber = rsmd.getColumnCount();

            while (rs.next()) {
                ArrayList<String> line = new ArrayList<>();
                for (int i = 0; i < columnsNumber; i++)
                    line.add(rs.getString(i + 1));
                result.add(line);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getEncodePassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    public static boolean checkPassword(String password, String encodedPassword) {
        return new BCryptPasswordEncoder().matches(password, encodedPassword);
    }
}