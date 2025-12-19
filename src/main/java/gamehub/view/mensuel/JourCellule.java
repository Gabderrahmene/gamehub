/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gamehub.view.mensuel;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Date;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

/**
 *
 * @author abdou
 */
public class JourCellule extends JScrollPane {

    private Date date;
    private final JLabel label;
    private final JPanel panel;
    
    public JourCellule() {
        Color blue= new Color (0,0,102);
        this.label = new JLabel("", SwingConstants.CENTER);
        this.panel = new JPanel();
        this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        this.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 0));
        this.setBorder(null);
        this.setViewportBorder(null);
        this.setColumnHeaderView(label);
        panel.setBackground(Color.BLACK);
        
        label.setBackground(blue);
        label.setOpaque(true);
        setViewportView(panel);

    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setLabel(String tex) {
        this.label.setText(tex);
    }

    public void addEvent(String tex) {
        panel.add(Box.createVerticalStrut(5));
        panel.add(new ReservLabel(tex));

        panel.revalidate();
        panel.repaint();
    }

    public void resetEvent() {
        panel.removeAll();
        panel.revalidate();
        panel.repaint();
    }

    public void currentMonth(boolean act) {
        if (act) {
            label.setForeground(new Color(165, 165, 165));
        } else {
            label.setForeground(new Color(65, 65, 63));
        }
    }

}
