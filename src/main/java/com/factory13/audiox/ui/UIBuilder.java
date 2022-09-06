package com.factory13.audiox.ui;

import com.factory13.audiox.service.AudioService;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

@Component
public class UIBuilder extends JFrame {

    public JButton createSplitAudioButton(AudioService audioService) {
        var splitAudioButton = new JButton("Split Audio");
        splitAudioButton.addActionListener((ActionEvent event) -> {
            audioService.splitAudioIntoChunks();
        });

        return splitAudioButton;
    }

    public JButton createQuitButton() {
        var quitButton = new JButton("Quit");
        quitButton.addActionListener((ActionEvent event) -> {
            System.exit(0);
        });

        return quitButton;
    }

    public JButton createAudioFilePicker(JLabel label, AudioService audioService) {
        var openAudioFileButton = new JButton("Open audio");
        openAudioFileButton.addActionListener((ActionEvent event) -> {
            // create an object of JFileChooser class
            var chooser = new JFileChooser(new File(System.getProperty("user.dir")));

            // invoke the showsOpenDialog function to show the save dialog
            int result = chooser.showOpenDialog(null);

            // if the user selects a file
            if (result == JFileChooser.APPROVE_OPTION) {
                // set the label to the path of the selected file
                var indexPath = chooser.getSelectedFile().getAbsolutePath();
                label.setText(indexPath);
                audioService.setAudioFileLocation(indexPath);
            } else {
                // if the user cancelled the operation
                label.setText("the user cancelled the operation");
            }
        });

        return openAudioFileButton;
    }

    public JButton createIndexFilePicker(JLabel label, AudioService audioService) {
        var openAudioFileButton = new JButton("Open index");
        openAudioFileButton.addActionListener((ActionEvent event) -> {
            // create an object of JFileChooser class
            var chooser = new JFileChooser(new File(System.getProperty("user.dir")));

            // invoke the showsOpenDialog function to show the save dialog
            int result = chooser.showOpenDialog(null);

            // if the user selects a file
            if (result == JFileChooser.APPROVE_OPTION) {
                // set the label to the path of the selected file
                var indexPath = chooser.getSelectedFile().getAbsolutePath();
                label.setText(indexPath);
                audioService.setIndexFileLocation(indexPath);
            } else {
                // if the user cancelled the operation
                label.setText("the user cancelled the operation");
            }
        });

        return openAudioFileButton;
    }

}
