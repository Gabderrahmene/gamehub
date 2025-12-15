/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gamehub.view.mensuel;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

/**
 *
 * @author abdou
 */
public class JourCellule extends JScrollPane {

    public JourCellule() {
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.setColumnHeaderView(new JLabel("Column Header"));       
        this.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        this.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 0));
        this.setBorder(null);
        this.setViewportBorder(null);
        
    }

}
