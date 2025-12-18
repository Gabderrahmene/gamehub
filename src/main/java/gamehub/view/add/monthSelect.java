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
public class monthSelect extends JComboBox {

    public monthSelect() {
        this.setBackground(new java.awt.Color(0, 0, 0));
        this.setFont(new java.awt.Font("Segoe UI", 3, 12));
        this.setForeground(new java.awt.Color(255, 255, 255));
        this.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}));
    }

    public String getSelection() {
        String monthName;

        switch ((String) this.getSelectedItem()) {
            case "January":
                return "01";
            case "February":
                return "02";
            case "March":
                return "03";
            case "April":
                return "04";
            case "May":
                return "05";
            case "June":
                return "06";
            case "July":
                return "07";
            case "August":
                return "08";
            case "September":
                return "09";
            case "October":
                return "10";
            case "November":
                return "11";
            case "December":
                return "12";
            default:
                return null; // invalid month name
        }

    }
}
