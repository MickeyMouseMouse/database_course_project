package view.databasePanel.topPanel;

import controller.DatabaseController;
import model.User;

import javax.swing.*;
import java.awt.*;

public class TopPanel extends JPanel { // singleton
    private static TopPanel instance;
    public static synchronized TopPanel getInstance() {
        if (instance == null) instance = new TopPanel();
        return instance;
    }

    final JLabel labelUser = new JLabel();
    final JButton buttonAdd = new JButton("Add");
    final JButton buttonRemove = new JButton("Remove");

    public TopPanel() {
        final GridBagLayout topPanelLayout = new GridBagLayout();

        setLayout(topPanelLayout);

        final JButton buttonLogOut = new JButton("Log out");

        buttonLogOut.addActionListener(e ->
                DatabaseController.getInstance().logout()
        );
        labelUser.setHorizontalAlignment(SwingConstants.CENTER);
        buttonRemove.addActionListener(e ->
                DatabaseController.getInstance().removeSelectedEntity()
        );
        buttonAdd.addActionListener(e ->
                DatabaseController.getInstance().addNewEntity()
        );

        final GridBagConstraints buttonLogOutConstraints = new GridBagConstraints();
        buttonLogOutConstraints.gridy = 0;
        buttonLogOutConstraints.gridx = 0;
        buttonLogOutConstraints.insets = new Insets(10,10,5,0);
        add(buttonLogOut, buttonLogOutConstraints);

        final GridBagConstraints LabelUserConstraints = new GridBagConstraints();
        LabelUserConstraints.gridy = 0;
        LabelUserConstraints.gridx = 1;
        LabelUserConstraints.anchor = GridBagConstraints.CENTER;
        LabelUserConstraints.fill = GridBagConstraints.HORIZONTAL;
        LabelUserConstraints.weightx = 1;
        LabelUserConstraints.insets = new Insets(10,0,5,0);
        add(labelUser, LabelUserConstraints);

        final GridBagConstraints buttonDeleteConstraints = new GridBagConstraints();
        buttonDeleteConstraints.gridy = 0;
        buttonDeleteConstraints.gridx = 2;
        buttonDeleteConstraints.insets = new Insets(10,0,5,0);
        add(buttonRemove, buttonDeleteConstraints);

        final GridBagConstraints buttonAddConstraints = new GridBagConstraints();
        buttonAddConstraints.gridy = 0;
        buttonAddConstraints.gridx = 3;
        buttonAddConstraints.insets = new Insets(10,0,5,10);
        add(buttonAdd, buttonAddConstraints);
    }

    public void initGUI() { labelUser.setText(User.getLogin()); }
}