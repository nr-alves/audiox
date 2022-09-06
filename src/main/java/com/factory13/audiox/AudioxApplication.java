package com.factory13.audiox;

import com.factory13.audiox.service.AudioService;
import com.factory13.audiox.ui.UIBuilder;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

@SpringBootApplication
public class AudioxApplication extends JFrame {

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JScrollPane scrollablePane;
    private JTable indexTable;
    private JButton readIndexButton;
    private JButton splitAudioButton;
    private JButton quitButton;

    private JButton openAudioFileChooser;
    private JLabel audioFileLabel;

    private JButton openIndexFileChooser;
    private JLabel indexFileLabel;
    // End of variables declaration//GEN-END:variables

    private final AudioService audioService;
    private final UIBuilder uiBuilder;

    /**
     * Creates new form DemoFrame
     */
    public AudioxApplication(AudioService audioService,
                             UIBuilder uiBuilder) {
        this.audioService = audioService;
        this.uiBuilder = uiBuilder;

        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("AudioX Splitter");
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Create Buttons
        createButtons();

        // Create Table
        createTable();

        // Create layout
        createLayout();
    }

    private void createTable() {
        String[][] trackList = {{"", ""}};

        String[] columnNames = {"Time", "Title"};
        indexTable = new JTable(trackList, columnNames);
        indexTable.setBounds(30, 40, 200, 300);
        scrollablePane = new JScrollPane(indexTable);
    }

    private void createButtons() {
        readIndexButton = new JButton("Read Index");
        readIndexButton.addActionListener((ActionEvent event) -> {
            audioService.readIndexAndPopulateTrackList();
            updateTable();
        });

        splitAudioButton = uiBuilder.createSplitAudioButton(audioService);
        quitButton = uiBuilder.createQuitButton();

        audioFileLabel = new JLabel(audioService.getAudioFileLocation());
        openAudioFileChooser = uiBuilder.createAudioFilePicker(audioFileLabel, audioService);

        indexFileLabel = new JLabel(audioService.getIndexFileLocation());
        openIndexFileChooser = uiBuilder.createIndexFilePicker(indexFileLabel, audioService);
    }

    private void updateTable() {
        var trackList = audioService.getTrackMatrix();
        String[] columnNames = {"Time", "Title"};
        indexTable.setModel(new DefaultTableModel(trackList, columnNames));
    }

    private void createLayout() {
        setLayout(new GridBagLayout());

        var gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(2, 2, 2, 2);

        // X0 Y0
        add(readIndexButton, gbc);
        gbc.gridy++;
        // X0 Y1-6
        gbc.gridheight = 7;
        add(scrollablePane, gbc);

        gbc.gridy = gbc.gridy + gbc.gridheight;
        // X0 Y7
        gbc.gridheight = 1;
        add(splitAudioButton, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 0;
        gbc.gridx++;
        // X1 Y0
        add(new JLabel("Audio File"), gbc);
        gbc.gridy++;
        // X1 Y1
        add(audioFileLabel, gbc);
        gbc.gridy++;
        // X1 Y2
        add(openAudioFileChooser, gbc);

        gbc.insets = new Insets(30, 2, 2, 2);
        gbc.gridy++;
        // X1 Y3
        add(new JLabel("Index File"), gbc);
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.gridy++;
        // X1 Y4
        add(indexFileLabel, gbc);
        gbc.gridy++;
        // X1 Y5
        add(openIndexFileChooser, gbc);

        gbc.gridy++;
        // X1 Y4
        gbc.insets = new Insets(100, 2, 2, 2);
        add(quitButton, gbc);
    }

    public static void main(String[] args) {
        var ctx = new SpringApplicationBuilder(AudioxApplication.class)
                .headless(false)
                .web(WebApplicationType.NONE)
                .run(args);

        EventQueue.invokeLater(() -> {
            var ex = ctx.getBean(AudioxApplication.class);
            ex.setVisible(true);
        });
    }

}
