package com.factory13.audiox.ui;

import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.ActionEvent;

@Component
public class UIBuilder extends JFrame{

    public JComponent createQuitButton() {
        var quitButton = new JButton("Quit");

        quitButton.addActionListener((ActionEvent event) -> {
            System.exit(0);
        });

        return quitButton;
    }

}
