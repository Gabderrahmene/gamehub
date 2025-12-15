/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gamehub.view;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
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
    /**
     * Creates new form hebdo_
     */
    public hebdo_() {
         super("Daily/Weekly Schedule");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Use a BorderLayout for the main JFrame
        setLayout(new BorderLayout());

        // 1. Create the Header Panel (Days of the week)
        JPanel headerPanel = createHeaderPanel();
        
        // 2. Create the Time Label Panel (Left column)
        JPanel timeLabelPanel = createTimeLabelPanel();

        // 3. Create the Schedule Grid Panel (The main black rectangle area)
        JPanel scheduleGrid = createScheduleGrid();
        
        // 4. Combine Time Labels and Schedule Grid into a Content Panel
        // Use BorderLayout to stick the time labels to the WEST and the grid to the CENTER
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(timeLabelPanel, BorderLayout.WEST);
        contentPanel.add(scheduleGrid, BorderLayout.CENTER);
        
        // 5. Wrap the Content Panel in a JScrollPane
        // This allows the user to scroll if the grid is too large
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // 6. Assemble the main view
        // The header needs to be aligned with the days in the grid
        // We'll use a main container panel with a GroupLayout or a custom approach
        
        // A simple approach: 
        // Create a dedicated panel for the top part (Header and the empty corner)
        JPanel topHeaderContainer = new JPanel(new BorderLayout());
        // Add an empty space in the NW corner (where the day header meets the time labels)
        topHeaderContainer.add(new JLabel(" "), BorderLayout.WEST); // Empty corner for alignment
        topHeaderContainer.add(headerPanel, BorderLayout.CENTER);

        // Add components to the JFrame
        add(topHeaderContainer, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        
        // Add the Forward/Next buttons at the bottom
        add(createNavigationPanel(), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        initComponents();
    }


    /**
     * Creates the JPanel for the day labels (Sun, Mon, Tue, etc.)
     */
    private JPanel createHeaderPanel() {
        // +1 for the empty corner space above the time labels
        JPanel panel = new JPanel(new GridLayout(1, days.length)); 
        panel.setPreferredSize(new Dimension(800, 30)); // Set a preferred width for layout
        
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
            timeLabel.setPreferredSize(new Dimension(80, 50)); // Set fixed width and height to align with grid
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
        // Grid: Rows = time slots, Columns = days
        JPanel gridPanel = new JPanel(new GridLayout(timeSlots.length, days.length));
        scheduleCells = new ArrayList<>();
        
        int rows = timeSlots.length;
        int cols = days.length;
        
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // The black rectangles are JPanels in the screenshot.
                JPanel cellPanel = new JPanel(new BorderLayout());
                
                // Set the visual style from the screenshot (black background, light border)
                cellPanel.setBackground(Color.BLACK);
                cellPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                cellPanel.setPreferredSize(new Dimension(100, 50)); // Set cell size
                
                // Optional: Add a placeholder label for text if you want to see content
                // JLabel placeholder = new JLabel("R" + r + "C" + c, SwingConstants.CENTER);
                // placeholder.setForeground(Color.WHITE);
                // cellPanel.add(placeholder, BorderLayout.CENTER);
                
                // Store the cell panel for later use
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
        panel.add(new JButton("FORWARD"));
        panel.add(new JButton("NEXT"));
        return panel;
    }

    /**
     * *** This is the part that fulfills your looping requirement ***
     * Inserts data into the schedule cells.
     */
    public void populateScheduleData(List<String> appointments) {
        // Ensure the appointments list size does not exceed the number of cells
        int maxIndex = Math.min(appointments.size(), scheduleCells.size());
        
        // Loop through the grouped cells to insert values
        for (int i = 0; i < maxIndex; i++) {
            JPanel cell = scheduleCells.get(i);
            String appointmentText = appointments.get(i);
            
            // Clear any existing content
            cell.removeAll();
            
            // If the cell has data, update it.
            if (appointmentText != null && !appointmentText.trim().isEmpty()) {
                JLabel contentLabel = new JLabel(appointmentText, SwingConstants.CENTER);
                contentLabel.setForeground(Color.CYAN); // Make the text visible
                contentLabel.setToolTipText(appointmentText); // Useful for long text
                cell.add(contentLabel, BorderLayout.CENTER);
                cell.setBackground(new Color(50, 50, 150)); // Change color to indicate content
            } else {
                // Reset to the original 'empty' state
                cell.setBackground(Color.BLACK);
            }
            cell.revalidate(); // Re-calculate layout
            cell.repaint();    // Redraw the cell
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
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
    // End of variables declaration//GEN-END:variables
}
