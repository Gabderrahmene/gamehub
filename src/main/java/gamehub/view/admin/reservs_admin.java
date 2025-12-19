/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gamehub.view.admin;

import gamehub.view.main.*;
import gamehub.control.ClientHandle;
import gamehub.models.User;
import gamehub.view.add.modify;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import raven.toast.Notifications;

/**
 *
 * @author abdel
 */
public class reservs_admin extends JLabel implements ActionListener {
    private JTable agendaTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;
    private TableRowSorter<DefaultTableModel> rowSorter;
    Color purple_dark = new Color(51,0,102);
    Color dark_blue= new Color(0,0,51);
    public reservs_admin() {
       setLayout(new BorderLayout());

    String[] columnNames = {"POST", "DATE", "USER"};
    tableModel = new DefaultTableModel(columnNames, 0) {
        @Override
        public boolean isCellEditable(int row, int column) { return false; }
    };

    agendaTable = new JTable(tableModel);
    
    agendaTable.getTableHeader().setBackground(purple_dark);
    agendaTable.getTableHeader().setForeground(Color.WHITE); // White text on purple background
    agendaTable.getTableHeader().setFont(agendaTable.getTableHeader().getFont().deriveFont(Font.BOLD));
    
    // Customize table rows
    agendaTable.setBackground(dark_blue);
    agendaTable.setForeground(Color.WHITE); // White text on blue background
    agendaTable.setGridColor(Color.GRAY); // Grid lines color
    agendaTable.setSelectionBackground(purple_dark); // Selection color
    agendaTable.setSelectionForeground(Color.WHITE);
    
    agendaTable.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mousePressed(java.awt.event.MouseEvent evt) {
            reservsMousePressed(evt);
        }
    });
    rowSorter = new TableRowSorter<>(tableModel);
    agendaTable.setRowSorter(rowSorter);

    agendaTable.setFillsViewportHeight(true);
    agendaTable.setRowHeight(25);

    add(createSearchPanel(), BorderLayout.NORTH);
    
    JScrollPane scrollPane = new JScrollPane(agendaTable);
    add(scrollPane, BorderLayout.CENTER);
        add(createSearchPanel(), BorderLayout.NORTH);

        String tt = new ClientHandle(User.bf, User.pw).get_reserv_admin();
        String[] reservations = tt.split("/");

        for (String reservation : reservations) {
            if (!reservation.isBlank()) {
                reservation = reservation.trim();
                String[] parts = reservation.split(",");
                String post = parts[0].trim();
                String dateHeure = parts[1].trim();
                String username=parts[2].trim();
                String[] dateTimeParts = dateHeure.split(" ");
                String date = dateTimeParts[0];
                String heure = dateTimeParts[1];
                
                addPostRow(post, dateHeure, username);
            }

        }
    }
    
    public void addPostRow(String titre, String date, String responsable) {
        Object[] rowData = {titre, date, responsable};
        tableModel.addRow(rowData);
    }
public void supPostRow() {
    int viewRow = agendaTable.getSelectedRow();
    if (viewRow != -1) {
        int modelRow = agendaTable.convertRowIndexToModel(viewRow);
        tableModel.removeRow(modelRow);
    }
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
                pop.show(agendaTable, evt.getX(), evt.getY());
            }
        }
    }
private JPanel createSearchPanel() {
    JPanel panel = new JPanel(new BorderLayout(10, 10));
    panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    
    searchField = new JTextField();
    panel.add(new JLabel("Search: "), BorderLayout.WEST);
    panel.add(searchField, BorderLayout.CENTER);
    Color dark_blue = new Color(51, 0, 153);
    panel.setBackground(dark_blue);
    searchField.setBackground(dark_blue);
    searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
        public void insertUpdate(javax.swing.event.DocumentEvent e) { filter(); }
        public void removeUpdate(javax.swing.event.DocumentEvent e) { filter(); }
        public void changedUpdate(javax.swing.event.DocumentEvent e) { filter(); }

        private void filter() {
            String text = searchField.getText().trim();
            if (text.isEmpty()) {
                rowSorter.setRowFilter(null);
            } else {
                rowSorter.setRowFilter(RowFilter.regexFilter("^(?i)" + java.util.regex.Pattern.quote(text)));
            }
        }
    });
    return panel;
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

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        String inf[] = getSelectedRowValues();
        switch (command) {
            case "delete":
                int confirm = JOptionPane.showConfirmDialog(this,
                        "Delete this reservation?\n\n"
                        + "POST: " + inf[0] + "\n"
                        + "DATE: " + inf[1],
                        "Confirm Deletion",
                        JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    String tt = new ClientHandle(User.bf, User.pw).del_reserv(User.username, inf[1], inf[0]);
                    if (tt == null) {

                    } else if (tt.equals("-1")) {
                        Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "erreur pendant la suppression");
                    } else {
                        Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, "suppression reussis");
                        supPostRow();
                    }
                }
                break;
            case "modify":

                modify addReserv = new modify(null, true, inf);
                addReserv.setVisible(true);
                String res = addReserv.getRes();
                addReserv.dispose();
                if (res == null) {

                } else if (res.equals("-1")) {
                    Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "erreur pendant la modification");
                } else {
                    Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, "modification reussis");
                    supPostRow();
                    String[] nr= res.split(",");
                    addPostRow(nr[1],nr[0],"vous");
                    
                }

        }

    }
}
