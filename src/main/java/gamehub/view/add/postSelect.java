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
public class postSelect extends JComboBox {

    public postSelect() {
        this.setBackground(new java.awt.Color(0, 0, 102));
        this.setFont(new java.awt.Font("Segoe UI", 3, 12));
        this.setForeground(new java.awt.Color(255, 255, 255));
        this.setModel(new javax.swing.DefaultComboBoxModel<>());
    }

    public String getSelection() {

        return (String) this.getSelectedItem();

    }

    public void addPost(String post) {
        this.addItem(post);
    }
}
