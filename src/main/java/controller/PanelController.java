package controller;

import view.authorizationPanel.AuthorizationPanel;
import view.databasePanel.DatabasePanel;

import javax.swing.*;

public class PanelController {
    private static JFrame frame;
    public static void setMainFrame(JFrame frame) {
        PanelController.frame = frame;
    }

    public static void setAuthorizationPanel() {
        frame.setContentPane(new AuthorizationPanel());
        frame.pack();
    }

    public static void setDatabasePanel() {
        frame.setContentPane(new DatabasePanel());
        frame.pack();
    }
}
