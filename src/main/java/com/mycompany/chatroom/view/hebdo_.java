/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.chatroom.view;
import com.mycompany.chatroom.control.ClientHandle;
import com.mycompany.chatroom.models.User;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.io.PrintWriter;

/**
 *
 * @author abdel
 */
public class hebdo_ extends javax.swing.JFrame {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(hebdo_.class.getName());
      private final String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
    private final String[] timeSlots = {"10:00-12:00", "12:00-14:00", "14:00-16:00", "16:00-18:00", "18:00-20:00", "20:00-22:00"};
    
    // List to hold all the JPanels in the grid for easy access/looping
     private List<JPanel> scheduleCells;
    private JPanel scheduleGrid;
    /**
     * Creates new form hebdo_
     */
    public hebdo_() {
        initComponents();
        
        // 1. Create the Header Panel (Days of the week)
        JPanel headerPanel = createHeaderPanel();
        
        // 2. Create the Time Label Panel (Left column)
        JPanel timeLabelPanel = createTimeLabelPanel();

        // 3. Create the Schedule Grid Panel (The main black rectangle area)
        JPanel scheduleGrid = createScheduleGrid();
        
        // 4. Combine Time Labels and Schedule Grid into a Content Panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(timeLabelPanel, BorderLayout.WEST);
        contentPanel.add(scheduleGrid, BorderLayout.CENTER);
        
        // 5. Create top header container
        JPanel topHeaderContainer = new JPanel(new BorderLayout());
        JLabel cornerSpace = new JLabel(" ");
        cornerSpace.setPreferredSize(new Dimension(80, 30));
        topHeaderContainer.add(cornerSpace, BorderLayout.WEST);
        topHeaderContainer.add(headerPanel, BorderLayout.CENTER);

        // 6. Create main panel with all components
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(topHeaderContainer, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(createNavigationPanel(), BorderLayout.SOUTH);
        
        // 7. Set the main panel as the view in the res JScrollPane
        res.setViewportView(mainPanel);
        
        // Load and display reservations
         try {
             // Parse and populate schedule with reservations
             
             String po=new ClientHandle(User.bf,User.pw).get_posts("2025-11-11");
             jLabel1.setText(po);
         } catch (IOException ex) {
             System.getLogger(hebdo_.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
         }
         
    }
    private List<String> parseReservations(String reservationData) {
        List<String> appointments = new ArrayList<>();
        
        if (reservationData != null && !reservationData.trim().isEmpty()) {
            String[] lines = reservationData.split("\n");
            for (String line : lines) {
                appointments.add(line.trim());
            }
        }
        
        // Fill remaining cells with empty strings
        int totalCells = days.length * timeSlots.length;
        while (appointments.size() < totalCells) {
            appointments.add("");
        }
        
        return appointments;
    }
    
    /**
     * Creates the JPanel for the day labels (Sun, Mon, Tue, etc.)
     */
     private JPanel createHeaderPanel() {
        JPanel panel = new JPanel(new GridLayout(1, days.length)); 
        panel.setPreferredSize(new Dimension(800, 30));
        
        for (String day : days) {
            JLabel dayLabel = new JLabel(day, SwingConstants.CENTER);
            dayLabel.setFont(dayLabel.getFont().deriveFont(Font.BOLD, 12f));
            dayLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            panel.add(dayLabel);
        }
        return panel;
    }
      
    /**
     * Creates the JPanel for the time labels (10:00-12:00, etc.)
     */
    private JPanel createTimeLabelPanel() {
        JPanel panel = new JPanel(new GridLayout(timeSlots.length, 1));
        
        for (String time : timeSlots) {
            JLabel timeLabel = new JLabel("  " + time, SwingConstants.LEFT);
            timeLabel.setPreferredSize(new Dimension(80, 50));
            timeLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            panel.add(timeLabel);
        }
        return panel;
    }
    /**
     * Creates the main grid of schedule cells.
     * All cells are stored in the 'scheduleCells' list.
     */
    private JPanel createScheduleGrid() {
        JPanel gridPanel = new JPanel(new GridLayout(timeSlots.length, days.length));
        scheduleCells = new ArrayList<>();
        
        int rows = timeSlots.length;
        int cols = days.length;
        
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                JPanel cellPanel = new JPanel(new BorderLayout());
                
                cellPanel.setBackground(Color.BLACK);
                cellPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                cellPanel.setPreferredSize(new Dimension(100, 50));
                
                scheduleCells.add(cellPanel);
                gridPanel.add(cellPanel);
            }
        }
        return gridPanel;
    }
    
    /**
     * Creates the panel for the Forward and Next buttons.
     */
     private JPanel createNavigationPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(new JButton("BACKWARD"));
        panel.add(new JButton("NEXT"));
        return panel;
    }

    /**
     * *** This is the part that fulfills your looping requirement ***
     * Inserts data into the schedule cells.
     */
    public void populateScheduleData(List<String> appointments) {
        int maxIndex = Math.min(appointments.size(), scheduleCells.size());
        
        for (int i = 0; i < maxIndex; i++) {
            JPanel cell = scheduleCells.get(i);
            String appointmentText = appointments.get(i);
            
            cell.removeAll();
            
            if (appointmentText != null && !appointmentText.trim().isEmpty()) {
                JLabel contentLabel = new JLabel(appointmentText, SwingConstants.CENTER);
                contentLabel.setForeground(Color.CYAN);
                contentLabel.setToolTipText(appointmentText);
                cell.add(contentLabel, BorderLayout.CENTER);
                cell.setBackground(new Color(50, 50, 150));
            } else {
                cell.setBackground(Color.BLACK);
            }
            cell.revalidate();
            cell.repaint();
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        res = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(res)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(315, 315, 315)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(377, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(res, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new hebdo_().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane res;
    // End of variables declaration//GEN-END:variables
}
