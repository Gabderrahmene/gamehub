/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gamehub.view.mensuel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JLabel;
import javax.swing.plaf.basic.BasicTabbedPaneUI.MouseHandler;

/**
 *
 * @author abdou
 */
public class ReservLabel extends JLabel {
    
    private String event;
    private MouseHandler mouseHandler;
    private int dragOffsetX, dragOffsetY;
    private boolean isBeingDragged = false;
    
    public ReservLabel(String event) {
        this.setText(event);
        this.event = event;
        setFont(new Font("Arial", Font.BOLD, 10));
        setOpaque(false);
        setForeground(Color.WHITE);
        setBackground(new Color(97, 49, 237));
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setPreferredSize(new Dimension(50, 50));
        setupDrag();
    }

    private void setupDrag() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                isBeingDragged = true;
                dragOffsetX = e.getX();
                dragOffsetY = e.getY();
                
                setBackground(new Color(97, 49, 237, 150));
                repaint();
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                // When mouse is released
                isBeingDragged = false;
                setBackground(new Color(97, 49, 237));
                repaint();
            }
        });
    }
    
    public boolean isBeingDragged() {
        return isBeingDragged;
    }
    
    public int getDragOffsetX() {
        return dragOffsetX;
    }
    
    public int getDragOffsetY() {
        return dragOffsetY;
    }

    public String getEvent() {
        return this.event;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        try {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(getBackground());
            g2.fillRect(0, 0, getWidth(), getHeight());
        } finally {
            g2.dispose();
        }
        super.paintComponent(g);
    }
}