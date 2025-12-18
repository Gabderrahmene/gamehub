/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gamehub.view.main;

import gamehub.control.ClientHandle;
import gamehub.models.User;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 *
 * @author abdel
 */
public class reservs extends JLabel {

    private JTable agendaTable;
    private DefaultTableModel tableModel;

    public reservs() {
        // Use BorderLayout for the panel to contain the JScrollPane
        setLayout(new BorderLayout());

        // 1. Define the Column Names
        String[] columnNames = {"POST", "DATE", "USER"};

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

        String tt = new ClientHandle(User.bf, User.pw).get_reserv(User.username);
        String[] reservations = tt.split("/");

        for (String reservation : reservations) {
            if (!reservation.isBlank()) {
                reservation = reservation.trim();
                String[] parts = reservation.split(",");
                String post = parts[0].trim();
                String dateHeure = parts[1].trim();
                String[] dateTimeParts = dateHeure.split(" ");
                String date = dateTimeParts[0];
                String heure = dateTimeParts[1];
                addPostRow(post, dateHeure, "vous");
            }

        }
    }

    public void addPostRow(String titre, String date, String responsable) {
        Object[] rowData = {titre, date, responsable};
        tableModel.addRow(rowData);
    }

    public int getSelectedRowIndex() {
        return agendaTable.getSelectedRow();
    }

    public String[] getSelectedRowValues() {
        int selectedRow = agendaTable.getSelectedRow();
        if (selectedRow != -1) {
            String post = (String) tableModel.getValueAt(selectedRow, 0);
            String date = (String) tableModel.getValueAt(selectedRow, 1);
            String user = (String) tableModel.getValueAt(selectedRow, 2);
            return new String[]{post, date, user};
        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Agenda Collaboratif Table Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            reservs tableView = new reservs();
            frame.add(tableView);
            frame.setPreferredSize(new Dimension(500, 300));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

}
