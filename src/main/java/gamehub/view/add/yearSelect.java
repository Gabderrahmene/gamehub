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
public class yearSelect extends JComboBox {

    public yearSelect() {
        this.setBackground(new java.awt.Color(51, 0, 102));
        this.setFont(new java.awt.Font("Segoe UI", 3, 12));
        this.setForeground(new java.awt.Color(255, 255, 255));
        this.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"2025","2026","2027"}));
    }
    
    public String getSelection(){
        
        return (String) this.getSelectedItem();
        
    }
}
