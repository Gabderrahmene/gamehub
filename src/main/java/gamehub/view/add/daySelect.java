/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gamehub.view.add;

import javax.swing.JComboBox;

/**
 *
 * @author abdou
 */
public class daySelect extends JComboBox {

    public daySelect() {
        this.setBackground(new java.awt.Color(0, 0, 102));
        this.setFont(new java.awt.Font("Segoe UI", 3, 12));
        this.setForeground(new java.awt.Color(255, 255, 255));
        this.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28"}));
    }

    public String getSelection() {

        return (String) this.getSelectedItem();

    }
}
