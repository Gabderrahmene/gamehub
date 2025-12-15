/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatroom.view;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
/**
 *
 * @author abdel
 */
public class reservs extends JLabel  {
  private JTable agendaTable;
    private DefaultTableModel tableModel;

    public reservs() {
        // Use BorderLayout for the panel to contain the JScrollPane
        setLayout(new BorderLayout());

        // 1. Define the Column Names
        String[] columnNames = {"USER", "DATE", "POST"};

        // 2. Create the Table Model
        // This model holds the data and column structure.
        tableModel = new DefaultTableModel(columnNames, 0) {
            // Optional: Override isCellEditable to prevent users from editing data directly
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // 3. Create the JTable using the model
        agendaTable = new JTable(tableModel);
        
        // Optional: Set table appearance to better match the screenshot
        agendaTable.setFillsViewportHeight(true); // Makes the table fill the height
        agendaTable.setRowHeight(25); // Set a pleasant row height
        
        // 4. Wrap the JTable in a JScrollPane
        // The JScrollPane ensures the table headers are visible and scrolling works.
        JScrollPane scrollPane = new JScrollPane(agendaTable);
        
        // 5. Add the scroll pane to the current panel
        add(scrollPane, BorderLayout.CENTER);
        
        // --- Example of adding initial data (Matching your screenshot) ---
        addExampleData();
    }

    /**
     * Adds a row of data to the table model.
     * @param titre The post title (Titre)
     * @param date The date
     * @param responsable The username (Responsable)
     */
    public void addPostRow(String titre, String date, String responsable) {
        // Create an array of objects for the new row
        Object[] rowData = {titre, date, responsable};
        tableModel.addRow(rowData);
    }
    
    /**
     * Fills the table with the sample data shown in your screenshot.
     */
    private void addExampleData() {
        addPostRow("abdo", "15/12", "ali");
        addPostRow("sofiane", "16/12", "ahmed");
        addPostRow("yahia", "17/12", "cr");
        addPostRow("abdo", "15/12", "ali");
        addPostRow("sofiane", "16/12", "ahmed");
    }

    // --- Example Main Method for Testing ---
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Agenda Collaboratif Table Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            reservs tableView = new reservs();
            
            // Add the table view to the frame
            frame.add(tableView);
            frame.setPreferredSize(new Dimension(500, 300));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            
            // Example of adding a new row after the frame is visible
            tableView.addPostRow("New Task", "18/12", "user_x");
        });
    }
  
}
