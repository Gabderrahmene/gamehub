/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gamehub.view.admin;

import gamehub.view.main.*;
import gamehub.control.ClientHandle;
import gamehub.models.User;
import gamehub.models.client;
import gamehub.view.add.AddGlobal;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import raven.toast.Notifications;

/**
 *
 * @author abdel
 */
public class reserv_admin extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(reserv_admin.class.getName());
    private reservs_admin tableViewComponent;

    /**
     * Creates new form reserv
     */
    public reserv_admin() {
        initComponents();
        tableViewComponent = new reservs_admin();


        res.setViewportView(tableViewComponent);

        this.revalidate();
        this.repaint();
    }

    private void openAgendaWindow() {
        JFrame agendaFrame = new JFrame("Agenda CollaborativeT - List of Events");
        agendaFrame.setContentPane(new reservs_admin()); 

        agendaFrame.pack();  

        agendaFrame.setSize(800, 600);

        agendaFrame.setLocationRelativeTo(null);  
        agendaFrame.setVisible(true);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        res = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton2.setBackground(new java.awt.Color(102, 0, 102));
        jButton2.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("DELETE");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel1.setText("RESERVATIONS :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(res, javax.swing.GroupLayout.PREFERRED_SIZE, 843, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(325, 325, 325)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(res, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String[] selectedValues = tableViewComponent.getSelectedRowValues();

        if (selectedValues != null) {
            String post = selectedValues[0];
            String date = selectedValues[1];
            client user = tableViewComponent.getSelectedRowValues1();

            System.out.println("Selected user: " + user);
            System.out.println("Selected dat: " + date);
            System.out.println("Selected psot: " + post);

            int confirm = JOptionPane.showConfirmDialog(this,
                    "Delete this reservation?\n\n"
                    + "POST: " + post + "\n"
                    + "DATE: " + date,
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                String tt = new ClientHandle(User.bf, User.pw).del_reserv(user.getId(), date, post);
                if (tt == null) {

                } else if (tt.equals("-1")) {
                    Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "erreur pendant la suppression");
                } else {
                    Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, "suppression reussis");
                    tableViewComponent.supPostRow();
                }
            }
        } else {
            JOptionPane.showMessageDialog(this,
                    "Please select a reservation first.",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane res;
    // End of variables declaration//GEN-END:variables
}
