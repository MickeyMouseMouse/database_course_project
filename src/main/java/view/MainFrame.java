package view;

import controller.PanelController;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Database: Parking Logbook");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        PanelController.setMainFrame(this);
        PanelController.setAuthorizationPanel();

        setMinimumSize(new Dimension(getWidth(), getHeight()));
        setVisible(true);


        addWindowListener(new WindowListener() {
            @Override
            public void windowClosing(WindowEvent event) { User.logout(); }

            @Override
            public void windowOpened(WindowEvent e) { }

            @Override
            public void windowClosed(WindowEvent e) { }

            @Override
            public void windowIconified(WindowEvent e) { }

            @Override
            public void windowDeiconified(WindowEvent e) { }

            @Override
            public void windowActivated(WindowEvent e) { }

            @Override
            public void windowDeactivated(WindowEvent e) { }
        });
    }
}
