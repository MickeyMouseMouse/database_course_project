package view.databasePanel.bottomPanel;

import model.User;

import javax.swing.*;
import java.awt.*;

public class BottomPanel extends JPanel { // singleton
    private static BottomPanel instance;
    public static synchronized BottomPanel getInstance() {
        if (instance == null) instance = new BottomPanel();
        return instance;
    }

    private final JLabel labelIsCacheEnabled = new JLabel();
    private final JLabel labelTime = new JLabel();

    public BottomPanel() {
        final GridBagLayout bottomPanelLayout = new GridBagLayout();
        setLayout(bottomPanelLayout);

        final JButton buttonCacheOnOff = new JButton("Cache");
        buttonCacheOnOff.addActionListener(e -> cacheOnOff());

        final GridBagConstraints buttonCacheOnOffConstraints = new GridBagConstraints();
        buttonCacheOnOffConstraints.gridy = 0;
        buttonCacheOnOffConstraints.gridx = 0;
        buttonCacheOnOffConstraints.insets = new Insets(0,10,5,0);
        add(buttonCacheOnOff, buttonCacheOnOffConstraints);

        final GridBagConstraints LabelIsCacheEnabledConstraints = new GridBagConstraints();
        LabelIsCacheEnabledConstraints.gridy = 0;
        LabelIsCacheEnabledConstraints.gridx = 1;
        LabelIsCacheEnabledConstraints.anchor = GridBagConstraints.CENTER;
        LabelIsCacheEnabledConstraints.fill = GridBagConstraints.HORIZONTAL;
        LabelIsCacheEnabledConstraints.weightx = 1;
        LabelIsCacheEnabledConstraints.insets = new Insets(0,0,5,0);
        add(labelIsCacheEnabled, LabelIsCacheEnabledConstraints);

        final GridBagConstraints labelTimeConstraints = new GridBagConstraints();
        labelTimeConstraints.gridy = 0;
        labelTimeConstraints.gridx = 2;
        labelTimeConstraints.insets = new Insets(0,0,5,10);
        add(labelTime, labelTimeConstraints);
    }

    public void initGUI() {
        setCacheEnabled(true);
        setTime("");
    }

    public void cacheOnOff() { setCacheEnabled(!User.isCacheEnabled()); }

    public void setCacheEnabled(boolean mode) {
        User.setCacheEnabled(mode);
        if (mode)
            labelIsCacheEnabled.setText("On");
        else
            labelIsCacheEnabled.setText("Off");
    }

    public void setTime(String str) { labelTime.setText(str); }
}
