package com.factory13.audiox.ui;

import com.factory13.audiox.service.AudioService;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.ActionEvent;

@Component
public class DemoFrame extends JFrame {

//    // Variables declaration - do not modify//GEN-BEGIN:variables
//    private JButton readIndexButton;
//    private JButton quitButton;
//    private JScrollPane jScrollPane1;
//    private JTextArea jTextArea1;
//    // End of variables declaration//GEN-END:variables
//
//    private final AudioService audioService;
//
//    /**
//     * Creates new form DemoFrame
//     */
//    public DemoFrame(AudioService audioService) {
//        this.audioService = audioService;
//
//        initComponents();
//    }
//
//    private void initComponents() {
//
//        quitButton = new JButton();
//        jScrollPane1 = new JScrollPane();
//        jTextArea1 = new JTextArea();
//
//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        setTitle("Spring Boot - GUI Application");
//        setSize(800, 600);
//        setLocationRelativeTo(null);
//
//        readIndexButton.setText("Read Index");
//        readIndexButton.addActionListener((ActionEvent event) -> {
//            audioService.readIndexAndPopulateTrackList();
//        });
//
//        quitButton.setText("Close");
//        quitButton.addActionListener((ActionEvent event) -> {
//            System.exit(0);
//        });
//
//        jTextArea1.setEditable(false);
//        jTextArea1.setColumns(20);
//        jTextArea1.setLineWrap(true);
//        jTextArea1.setRows(5);
//        jTextArea1.setText("This is a demonstration on how to write a simple Spring Boot application to display a GUI interface.");
//        jTextArea1.setWrapStyleWord(true);
//        jScrollPane1.setViewportView(jTextArea1);
//
//        createLayout();
//
//        pack();
//    }// </editor-fold>//GEN-END:initComponents
//
//    private void createLayout() {
//
//        GroupLayout layout = new GroupLayout(getContentPane());
//        getContentPane().setLayout(layout);
//        layout.setHorizontalGroup(
//                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//                        .addGroup(layout.createSequentialGroup()
//                                .addContainerGap()
//                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//                                        .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
//                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
//                                                .addGap(0, 0, Short.MAX_VALUE)
//                                                .addComponent(readIndexButton)
//                                                .addGap(0, 0, Short.MAX_VALUE)
//                                                .addComponent(quitButton)))
//                                .addContainerGap())
//        );
//        layout.setVerticalGroup(
//                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
//                                .addContainerGap()
//                                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
//                                .addGap(18, 18, 18)
//                                .addComponent(quitButton)
//                                .addGap(20, 20, 20))
//        );
//
//    }
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
//         */
//        try {
//            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(DemoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(DemoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(DemoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(DemoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new DemoFrame().setVisible(true);
//            }
//        });
//    }
}
