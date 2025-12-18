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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 *
 * @author abdel
 */
public class reservs extends JLabel implements ActionListener {

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
        agendaTable.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mousePressed(java.awt.event.MouseEvent evt) {
            reservsMousePressed(evt);
        }
    });
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
    private void reservsMousePressed(java.awt.event.MouseEvent evt) {
       if (SwingUtilities.isRightMouseButton(evt)) {
        int row = agendaTable.rowAtPoint(evt.getPoint());
        
        if (row >= 0) {
            agendaTable.setRowSelectionInterval(row, row);
            JPopupMenu pop = new JPopupMenu();
            JMenuItem item1 = new JMenuItem("modify");
            item1.addActionListener((ActionListener) this);
            item1.setActionCommand("modify");
            pop.add(item1);
            JMenuItem item2 = new JMenuItem("delete");
            item2.addActionListener((ActionListener) this);
            item2.setActionCommand("delete");
            pop.add(item2);
            pop.show(agendaTable,  evt.getX(), evt.getY());
        }
    }}

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
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch(command){
            case "delete":
                String inf[] = getSelectedRowValues();
                int confirm = JOptionPane.showConfirmDialog(this,
                    "Delete this reservation?\n\n"
                    + "POST: " + inf[0] + "\n"
                    + "DATE: " + inf[1],
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        String tt = new ClientHandle(User.bf, User.pw).del_reserv(User.username, inf[1], inf[0]);
                        if (!tt.equals("-1")) {
                            JOptionPane.showMessageDialog(this,
                                "Reservation deleted successfully!",
                                "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(this,
                                "Failed to delete reservation.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                break;
                case "modify":
        }
            
    }}
      
    


