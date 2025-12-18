/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gamehub.view.main;

import gamehub.control.ClientHandle;
import gamehub.models.User;
import gamehub.view.mensuel.JourCellule;
import gamehub.view.mensuel.eventLabel;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;

/**
 *
 * @author abdel
 */
public class hebdo_ extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(hebdo_.class.getName());
    private final String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
    public final String[] timeSlots = {"10:00-12:00", "12:00-14:00", "14:00-16:00", "16:00-18:00", "18:00-20:00", "20:00-22:00"};
    private JPanel gridPanel;
    private JButton backwardButton;
    private JButton nextButton;
    // List to hold all the JPanels in the grid for easy access/looping
    private List<JPanel> scheduleCells;
    private JPanel scheduleGrid;
    /**
     * Creates new form hebdo_
     */
    LocalDate currentDate = LocalDate.now();
    LocalDate Dat = LocalDate.now();

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
     * Creates the main grid of schedule cells. All cells are stored in the
     * 'scheduleCells' list.
     */
    private JPanel createScheduleGrid() {
        gridPanel = new JPanel(new GridLayout(timeSlots.length, days.length));
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
        return panel;

    }

    private void reservs(String date1) {
        // 1. Clear the grid before loading new data
        for (int i = 0; i < gridPanel.getComponentCount(); i++) {
            JPanel cell = (JPanel) gridPanel.getComponent(i);
            cell.removeAll();
            cell.revalidate();
            cell.repaint();
        }

        String tt = new ClientHandle(User.bf, User.pw).get_reserv_wek(User.username, date1);

        if (tt != null && !tt.equals("-1")) {
            String[] reservations = tt.split("/");
            LocalDate weekStartDate = LocalDate.parse(date1);
            for (String reservation : reservations) {
                try {
                    reservation = reservation.trim();
                    String[] parts = reservation.split(",");
                    String post = parts[0].trim();
                    String dateHeure = parts[1].trim();

                    String[] dateTimeParts = dateHeure.split(" ");
                    LocalDate resDate = LocalDate.parse(dateTimeParts[0]);

                    String timeFromDB = dateTimeParts[1].substring(0, 5);

                    int t = -1;
                    for (int i = 0; i < timeSlots.length; i++) {
                        if (timeSlots[i].startsWith(timeFromDB)) {
                            t = i;
                            break;
                        }
                    }

                    int k = (int) java.time.temporal.ChronoUnit.DAYS.between(weekStartDate, resDate);

                    if (t != -1 && k >= 0 && k < 7) {
                        int place = (t * 7) + k;
                        if (place >= 0 && place < gridPanel.getComponentCount()) {
                            JPanel aa = (JPanel) gridPanel.getComponent(place);
                            aa.add(new eventLabel(post));
                            aa.revalidate();
                            aa.repaint();
                        }
                        System.out.println("Processing: " + post);
                        System.out.println("  -> Date Found: " + resDate + " | Week Start: " + weekStartDate);
                        System.out.println("  -> Days Diff (k): " + k);
                        System.out.println("  -> Time Found: [" + timeFromDB + "]");
                        System.out.println("  -> Row Index (t): " + t);
                    }
                } catch (Exception e) {
                    System.err.println("Error processing reservation: " + reservation);
                }
            }
        }
    }

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
        week = new javax.swing.JLabel();
        next = new javax.swing.JButton();
        back = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        week.setText("jLabel1");

        next.setText("next");
        next.addActionListener(this::nextActionPerformed);

        back.setText("back");
        back.addActionListener(this::backActionPerformed);

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
                .addComponent(week, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(377, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(161, 161, 161)
                .addComponent(next, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(238, 238, 238))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(week, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(res, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(next, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextActionPerformed

        Dat = Dat.plusDays(7);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate weekEnd = Dat.plusDays(6);

        week.setText(Dat.format(formatter) + " -> " + weekEnd.format(formatter));
        reservs(Dat.format(formatter));

        System.out.println("Nex wek" + Dat);
    }//GEN-LAST:event_nextActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        Dat = Dat.minusDays(7);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate weekEnd = Dat.plusDays(6);
        week.setText(Dat.format(formatter) + " -> " + weekEnd.format(formatter));
        reservs(Dat.format(formatter));

        System.out.println("prec week " + Dat);
    }//GEN-LAST:event_backActionPerformed

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
    private javax.swing.JButton back;
    private javax.swing.JButton next;
    private javax.swing.JScrollPane res;
    private javax.swing.JLabel week;
    // End of variables declaration//GEN-END:variables
}
