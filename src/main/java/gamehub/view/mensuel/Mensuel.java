/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gamehub.view.mensuel;

import java.awt.Component;
import java.util.Calendar;
import javax.swing.JLabel;

/**
 *
 * @author abdou
 */
public class Mensuel extends javax.swing.JPanel {

    private int month;
    private int year;

    public Mensuel() {
        initComponents();
        this.month = 12;
        this.year = 2025;
        setDate();
    }

    private void setDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DATE, 1);
        int startDat = calendar.get(Calendar.DAY_OF_WEEK);
        calendar.add(Calendar.DATE, -startDat);
        for (Component com : getComponents()) {
            if (com.getClass().getSimpleName().equals("JourCellule")) {
                JourCellule ce = (JourCellule) com;
                ce.resetEvent();
                ce.setLabel(calendar.get(Calendar.DATE) + "");
                ce.setDate(calendar.getTime());
                ce.currentMonth(calendar.get(Calendar.MONTH) == month - 1);
                ce.addEvent("ana samaka");
                calendar.add(Calendar.DATE, 1);
            }
        }
    }

    public void changeDate(int month, int year) {
        this.month = month;
        this.year = year;
        setDate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jour1 = new gamehub.view.mensuel.JourTitre();
        jour2 = new gamehub.view.mensuel.JourTitre();
        jour3 = new gamehub.view.mensuel.JourTitre();
        jour4 = new gamehub.view.mensuel.JourTitre();
        jour5 = new gamehub.view.mensuel.JourTitre();
        jour6 = new gamehub.view.mensuel.JourTitre();
        jour7 = new gamehub.view.mensuel.JourTitre();
        jourCellule1 = new gamehub.view.mensuel.JourCellule();
        jourCellule2 = new gamehub.view.mensuel.JourCellule();
        jourCellule3 = new gamehub.view.mensuel.JourCellule();
        jourCellule4 = new gamehub.view.mensuel.JourCellule();
        jourCellule5 = new gamehub.view.mensuel.JourCellule();
        jourCellule6 = new gamehub.view.mensuel.JourCellule();
        jourCellule7 = new gamehub.view.mensuel.JourCellule();
        jourCellule8 = new gamehub.view.mensuel.JourCellule();
        jourCellule9 = new gamehub.view.mensuel.JourCellule();
        jourCellule10 = new gamehub.view.mensuel.JourCellule();
        jourCellule11 = new gamehub.view.mensuel.JourCellule();
        jourCellule12 = new gamehub.view.mensuel.JourCellule();
        jourCellule13 = new gamehub.view.mensuel.JourCellule();
        jourCellule14 = new gamehub.view.mensuel.JourCellule();
        jourCellule15 = new gamehub.view.mensuel.JourCellule();
        jourCellule16 = new gamehub.view.mensuel.JourCellule();
        jourCellule17 = new gamehub.view.mensuel.JourCellule();
        jourCellule18 = new gamehub.view.mensuel.JourCellule();
        jourCellule19 = new gamehub.view.mensuel.JourCellule();
        jourCellule20 = new gamehub.view.mensuel.JourCellule();
        jourCellule21 = new gamehub.view.mensuel.JourCellule();
        jourCellule22 = new gamehub.view.mensuel.JourCellule();
        jourCellule23 = new gamehub.view.mensuel.JourCellule();
        jourCellule24 = new gamehub.view.mensuel.JourCellule();
        jourCellule25 = new gamehub.view.mensuel.JourCellule();
        jourCellule26 = new gamehub.view.mensuel.JourCellule();
        jourCellule27 = new gamehub.view.mensuel.JourCellule();
        jourCellule28 = new gamehub.view.mensuel.JourCellule();
        jourCellule29 = new gamehub.view.mensuel.JourCellule();
        jourCellule30 = new gamehub.view.mensuel.JourCellule();
        jourCellule31 = new gamehub.view.mensuel.JourCellule();
        jourCellule32 = new gamehub.view.mensuel.JourCellule();
        jourCellule33 = new gamehub.view.mensuel.JourCellule();
        jourCellule34 = new gamehub.view.mensuel.JourCellule();
        jourCellule35 = new gamehub.view.mensuel.JourCellule();
        jourCellule36 = new gamehub.view.mensuel.JourCellule();
        jourCellule37 = new gamehub.view.mensuel.JourCellule();
        jourCellule38 = new gamehub.view.mensuel.JourCellule();
        jourCellule39 = new gamehub.view.mensuel.JourCellule();
        jourCellule40 = new gamehub.view.mensuel.JourCellule();
        jourCellule41 = new gamehub.view.mensuel.JourCellule();
        jourCellule42 = new gamehub.view.mensuel.JourCellule();

        setLayout(new java.awt.GridLayout(7, 7, 5, 1));

        jour1.setText("Dim");
        jour1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jour1ActionPerformed(evt);
            }
        });
        add(jour1);

        jour2.setText("Lun");
        jour2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jour2ActionPerformed(evt);
            }
        });
        add(jour2);

        jour3.setText("Mar");
        add(jour3);

        jour4.setText("Merc");
        add(jour4);

        jour5.setText("Jeu");
        add(jour5);

        jour6.setText("Vend");
        add(jour6);

        jour7.setText("Sam");
        jour7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jour7ActionPerformed(evt);
            }
        });
        add(jour7);

        jourCellule1.setViewportBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        add(jourCellule1);

        jourCellule2.setViewportBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        add(jourCellule2);

        jourCellule3.setViewportBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        add(jourCellule3);

        jourCellule4.setViewportBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        add(jourCellule4);

        jourCellule5.setViewportBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        add(jourCellule5);

        jourCellule6.setViewportBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        add(jourCellule6);

        jourCellule7.setViewportBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        add(jourCellule7);

        jourCellule8.setViewportBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        add(jourCellule8);

        jourCellule9.setViewportBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        add(jourCellule9);

        jourCellule10.setViewportBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        add(jourCellule10);

        jourCellule11.setViewportBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        add(jourCellule11);

        jourCellule12.setViewportBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        add(jourCellule12);

        jourCellule13.setViewportBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        add(jourCellule13);

        jourCellule14.setViewportBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        add(jourCellule14);

        jourCellule15.setViewportBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        add(jourCellule15);

        jourCellule16.setViewportBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        add(jourCellule16);

        jourCellule17.setViewportBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        add(jourCellule17);

        jourCellule18.setViewportBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        add(jourCellule18);

        jourCellule19.setViewportBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        add(jourCellule19);

        jourCellule20.setViewportBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        add(jourCellule20);

        jourCellule21.setViewportBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        add(jourCellule21);

        jourCellule22.setViewportBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        add(jourCellule22);

        jourCellule23.setViewportBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        add(jourCellule23);

        jourCellule24.setViewportBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        add(jourCellule24);

        jourCellule25.setViewportBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        add(jourCellule25);

        jourCellule26.setViewportBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        add(jourCellule26);

        jourCellule27.setViewportBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        add(jourCellule27);

        jourCellule28.setViewportBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        add(jourCellule28);

        jourCellule29.setViewportBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        add(jourCellule29);

        jourCellule30.setViewportBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        add(jourCellule30);

        jourCellule31.setViewportBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        add(jourCellule31);

        jourCellule32.setViewportBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        add(jourCellule32);

        jourCellule33.setViewportBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        add(jourCellule33);

        jourCellule34.setViewportBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        add(jourCellule34);

        jourCellule35.setViewportBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        add(jourCellule35);

        jourCellule36.setViewportBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        add(jourCellule36);

        jourCellule37.setViewportBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        add(jourCellule37);

        jourCellule38.setViewportBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        add(jourCellule38);

        jourCellule39.setViewportBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        add(jourCellule39);

        jourCellule40.setViewportBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        add(jourCellule40);

        jourCellule41.setViewportBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        add(jourCellule41);

        jourCellule42.setViewportBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        add(jourCellule42);
    }// </editor-fold>//GEN-END:initComponents

    private void jour1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jour1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jour1ActionPerformed

    private void jour7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jour7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jour7ActionPerformed

    private void jour2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jour2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jour2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gamehub.view.mensuel.JourTitre jour1;
    private gamehub.view.mensuel.JourTitre jour2;
    private gamehub.view.mensuel.JourTitre jour3;
    private gamehub.view.mensuel.JourTitre jour4;
    private gamehub.view.mensuel.JourTitre jour5;
    private gamehub.view.mensuel.JourTitre jour6;
    private gamehub.view.mensuel.JourTitre jour7;
    private gamehub.view.mensuel.JourCellule jourCellule1;
    private gamehub.view.mensuel.JourCellule jourCellule10;
    private gamehub.view.mensuel.JourCellule jourCellule11;
    private gamehub.view.mensuel.JourCellule jourCellule12;
    private gamehub.view.mensuel.JourCellule jourCellule13;
    private gamehub.view.mensuel.JourCellule jourCellule14;
    private gamehub.view.mensuel.JourCellule jourCellule15;
    private gamehub.view.mensuel.JourCellule jourCellule16;
    private gamehub.view.mensuel.JourCellule jourCellule17;
    private gamehub.view.mensuel.JourCellule jourCellule18;
    private gamehub.view.mensuel.JourCellule jourCellule19;
    private gamehub.view.mensuel.JourCellule jourCellule2;
    private gamehub.view.mensuel.JourCellule jourCellule20;
    private gamehub.view.mensuel.JourCellule jourCellule21;
    private gamehub.view.mensuel.JourCellule jourCellule22;
    private gamehub.view.mensuel.JourCellule jourCellule23;
    private gamehub.view.mensuel.JourCellule jourCellule24;
    private gamehub.view.mensuel.JourCellule jourCellule25;
    private gamehub.view.mensuel.JourCellule jourCellule26;
    private gamehub.view.mensuel.JourCellule jourCellule27;
    private gamehub.view.mensuel.JourCellule jourCellule28;
    private gamehub.view.mensuel.JourCellule jourCellule29;
    private gamehub.view.mensuel.JourCellule jourCellule3;
    private gamehub.view.mensuel.JourCellule jourCellule30;
    private gamehub.view.mensuel.JourCellule jourCellule31;
    private gamehub.view.mensuel.JourCellule jourCellule32;
    private gamehub.view.mensuel.JourCellule jourCellule33;
    private gamehub.view.mensuel.JourCellule jourCellule34;
    private gamehub.view.mensuel.JourCellule jourCellule35;
    private gamehub.view.mensuel.JourCellule jourCellule36;
    private gamehub.view.mensuel.JourCellule jourCellule37;
    private gamehub.view.mensuel.JourCellule jourCellule38;
    private gamehub.view.mensuel.JourCellule jourCellule39;
    private gamehub.view.mensuel.JourCellule jourCellule4;
    private gamehub.view.mensuel.JourCellule jourCellule40;
    private gamehub.view.mensuel.JourCellule jourCellule41;
    private gamehub.view.mensuel.JourCellule jourCellule42;
    private gamehub.view.mensuel.JourCellule jourCellule5;
    private gamehub.view.mensuel.JourCellule jourCellule6;
    private gamehub.view.mensuel.JourCellule jourCellule7;
    private gamehub.view.mensuel.JourCellule jourCellule8;
    private gamehub.view.mensuel.JourCellule jourCellule9;
    // End of variables declaration//GEN-END:variables
}
