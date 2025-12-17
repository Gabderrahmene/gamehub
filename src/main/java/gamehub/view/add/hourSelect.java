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
public class hourSelect extends JComboBox {

    public hourSelect() {
        this.setBackground(new java.awt.Color(0, 0, 102));
        this.setFont(new java.awt.Font("Segoe UI", 3, 12));
        this.setForeground(new java.awt.Color(255, 255, 255));
        this.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"10:00-12:00", "12:00-14:00", "14:00-16:00", "16:00-18:00", "18:00-20:00", "20:00-22:00"}));
    }
    
    public String getSelection(){
        
        return (String) this.getSelectedItem();
        
    }
}
