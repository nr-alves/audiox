package com.factory13.audiox;

import com.factory13.audiox.service.AudioService;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

@SpringBootApplication
public class AudioxApplication extends JFrame {

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JScrollPane scrollablePane;
    private JTable indexTable;
    private JButton readIndexButton;
    private JButton quitButton;

    private JFileChooser audioFileChooser;
    private JButton openAudioFileChooser;
    private JLabel audioFileLabel;
    // End of variables declaration//GEN-END:variables

    private final AudioService audioService;

    /**
     * Creates new form DemoFrame
     */
    public AudioxApplication(AudioService audioService) {
        this.audioService = audioService;

        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Spring Boot - GUI Application");
        setSize(800, 600);
        setLocationRelativeTo(null);

        createButtons();


        // Table
        createTable();
        createLayout();
    }// </editor-fold>//GEN-END:initComponents

    private void createTable() {
        String[][] trackList = {{"", ""}};

        String[] columnNames = {"Time", "Title"};
        indexTable = new JTable(trackList, columnNames);
        indexTable.setBounds(30, 40, 200, 300);
        scrollablePane = new JScrollPane(indexTable);
    }

    private void updateTable() {
        var trackList = audioService.getTrackMatrix();
        String[] columnNames = {"Time", "Title"};
        indexTable.setModel(new DefaultTableModel(trackList, columnNames));
    }

    private void createButtons() {
        readIndexButton = new JButton();
        readIndexButton.setText("Read Index");
        readIndexButton.addActionListener((ActionEvent event) -> {
            audioService.readIndexAndPopulateTrackList();
            updateTable();
        });

        quitButton = new JButton();
        quitButton.setText("Close");
        quitButton.addActionListener((ActionEvent event) -> {
            System.exit(0);
        });

        audioFileLabel = new JLabel();
        audioFileChooser = new JFileChooser();
        openAudioFileChooser = new JButton("Open audio");
        openAudioFileChooser.addActionListener((ActionEvent event) -> {
            // create an object of JFileChooser class
            JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

            // invoke the showsOpenDialog function to show the save dialog
            int r = j.showOpenDialog(null);

            // if the user selects a file
            if (r == JFileChooser.APPROVE_OPTION) {
                // set the label to the path of the selected file
                audioFileLabel.setText(j.getSelectedFile().getAbsolutePath());
            } else {
                // if the user cancelled the operation
                audioFileLabel.setText("the user cancelled the operation");
            }
        });
    }

    private void createLayout() {

        var pane = getContentPane();
        var gl = new GroupLayout(pane);
        pane.setLayout(gl);

        gl.setAutoCreateContainerGaps(true);

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(readIndexButton)
                .addComponent(scrollablePane)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(audioFileLabel)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(openAudioFileChooser)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(quitButton)
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addContainerGap()
                .addGap(18, 18, 18)
                .addComponent(readIndexButton)
                .addGap(18, 18, 18)
                .addComponent(scrollablePane)
                .addGap(18, 18, 18)
                .addComponent(audioFileLabel)
                .addGap(18, 18, 18)
                .addComponent(openAudioFileChooser)
                .addGap(18, 18, 18)
                .addComponent(quitButton)
                .addGap(20, 20, 20)
        );
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
