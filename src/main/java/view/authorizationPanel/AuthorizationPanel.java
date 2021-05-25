package view.authorizationPanel;

import controller.AuthorizationController;

import javax.swing.*;
import java.awt.*;

public class AuthorizationPanel extends JPanel {
    public AuthorizationPanel() {
        final JLabel labelLogin = new JLabel("Login", SwingConstants.LEFT);
        final JTextField fieldLogin = new JTextField();
        final JLabel labelPassword = new JLabel("Password", SwingConstants.LEFT);
        final JTextField fieldPassword = new JTextField();
        final JButton buttonConnect = new JButton("Connect");
        final GridBagLayout layout = new GridBagLayout();

        fieldLogin.setPreferredSize(new Dimension(180, 20));
        fieldPassword.setPreferredSize(new Dimension(180, 20));
        buttonConnect.addActionListener(e -> {
            if (!AuthorizationController
                    .connect(fieldLogin.getText(), fieldPassword.getText()))
                JOptionPane.showMessageDialog(null, "Connection error");
        });

        setLayout(layout);

        final GridBagConstraints labelLoginConstraints = new GridBagConstraints();
        labelLoginConstraints.gridy = 0;
        labelLoginConstraints.gridx = 0;
        labelLoginConstraints.anchor = GridBagConstraints.WEST;
        labelLoginConstraints.insets = new Insets(10, 10, 5, 5);
        add(labelLogin, labelLoginConstraints);

        final GridBagConstraints fieldLoginConstraints = new GridBagConstraints();
        fieldLoginConstraints.gridy = 0;
        fieldLoginConstraints.gridx = 1;
        fieldLoginConstraints.fill = GridBagConstraints.HORIZONTAL;
        fieldLoginConstraints.weightx = 1;
        fieldLoginConstraints.insets = new Insets(10, 5, 5, 10);
        add(fieldLogin, fieldLoginConstraints);

        final GridBagConstraints labelPasswordConstraints = new GridBagConstraints();
        labelPasswordConstraints.gridy = 1;
        labelPasswordConstraints.gridx = 0;
        labelPasswordConstraints.anchor = GridBagConstraints.WEST;
        labelPasswordConstraints.insets = new Insets(0, 10, 0, 5);
        add(labelPassword, labelPasswordConstraints);

        final GridBagConstraints fieldPasswordConstraints = new GridBagConstraints();
        fieldPasswordConstraints.gridy = 1;
        fieldPasswordConstraints.gridx = 1;
        fieldPasswordConstraints.fill = GridBagConstraints.HORIZONTAL;
        fieldPasswordConstraints.weightx = 1;
        fieldPasswordConstraints.insets = new Insets(0, 5, 0, 10);
        add(fieldPassword, fieldPasswordConstraints);

        final GridBagConstraints buttonConnectConstraints = new GridBagConstraints();
        buttonConnectConstraints.gridy = 2;
        buttonConnectConstraints.gridx = 0;
        buttonConnectConstraints.gridwidth = 2;
        buttonConnectConstraints.fill = GridBagConstraints.HORIZONTAL;
        buttonConnectConstraints.weightx = 1;
        buttonConnectConstraints.insets = new Insets(10, 50, 10, 50);
        add(buttonConnect, buttonConnectConstraints);
    }
}