package gamehub.view.admin;

import gamehub.view.main.*;
import gamehub.control.ClientHandle;
import gamehub.models.User;
import gamehub.view.add.modify;
import gamehub.view.mensuel.ReservLabel;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import raven.toast.Notifications;

public class hebdo_admin extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(hebdo_admin.class.getName());
    private final String[] days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
    public final String[] timeSlots = {"10:00-12:00", "12:00-14:00", "14:00-16:00", "16:00-18:00", "18:00-20:00", "20:00-22:00"};
    private JPanel gridPanel;
    private ReservLabel currentlyDragging = null;
    private JPanel sourceCell = null;
    Color purple_dark = new Color(51,0,102);
    LocalDate Dat = LocalDate.now();

    public hebdo_admin() {
        initComponents();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate weekEnd = Dat.plusDays(6);
        week.setText(Dat.format(formatter) + " -> " + weekEnd.format(formatter));

        JPanel headerPanel = createHeaderPanel();

        JPanel timeLabelPanel = createTimeLabelPanel();

        JPanel scheduleGrid = createScheduleGrid();

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(timeLabelPanel, BorderLayout.WEST);
        contentPanel.add(scheduleGrid, BorderLayout.CENTER);

        JPanel topHeaderContainer = new JPanel(new BorderLayout());
        JLabel cornerSpace = new JLabel(" ");
        cornerSpace.setPreferredSize(new Dimension(80, 30));
        topHeaderContainer.add(cornerSpace, BorderLayout.WEST);
        topHeaderContainer.add(headerPanel, BorderLayout.CENTER);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(topHeaderContainer, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(createNavigationPanel(), BorderLayout.SOUTH);

        res.setViewportView(mainPanel);

        reservs(Dat.format(formatter));
    }

    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel(new GridLayout(1, days.length));
        panel.setPreferredSize(new Dimension(800, 30));

        for (String day : days) {
            JLabel dayLabel = new JLabel(day, SwingConstants.CENTER);
            dayLabel.setBackground(purple_dark);
            dayLabel.setOpaque(true);  
            dayLabel.setFont(dayLabel.getFont().deriveFont(Font.BOLD, 12f));
            dayLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            panel.add(dayLabel);
        }
        return panel;
    }

    private JPanel createTimeLabelPanel() {
        JPanel panel = new JPanel(new GridLayout(timeSlots.length, 1));

        for (String time : timeSlots) {
            JLabel timeLabel = new JLabel("  " + time, SwingConstants.LEFT);
            timeLabel.setPreferredSize(new Dimension(80, 50));
            timeLabel.setBackground(purple_dark);
            timeLabel.setOpaque(true);  
            timeLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            panel.add(timeLabel);
        }
        return panel;
    }

    private JPanel createScheduleGrid() {
        gridPanel = new JPanel(new GridLayout(timeSlots.length, days.length));

        int rows = timeSlots.length;
        int cols = days.length;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                final JPanel cellPanel = new JPanel();
                JScrollPane scrollPane = new JScrollPane();
                cellPanel.setLayout(new BoxLayout(cellPanel, BoxLayout.Y_AXIS));
                cellPanel.setBackground(Color.BLACK);
                cellPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));

                // Add drop listener to each cell
                cellPanel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        handleDrop(cellPanel);
                    }
                });

                scrollPane.setPreferredSize(new Dimension(100, 50));
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
                scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 0));
                scrollPane.setViewportView(cellPanel);
                gridPanel.add(scrollPane);
            }
        }

        gridPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (currentlyDragging != null) {
                    currentlyDragging.setBackground(new Color(97, 49, 237));
                    currentlyDragging.repaint();
                    currentlyDragging = null;
                    sourceCell = null;
                }
            }
        });

        return gridPanel;
    }

    private JPanel createNavigationPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        return panel;
    }

    private void setupLabelDrag(ReservLabel label) {
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                currentlyDragging = label;
                sourceCell = (JPanel) label.getParent();

                label.setBackground(new Color(97, 49, 237, 150));
                label.repaint();
            }
        });
    }

    private void handleDrop(JPanel targetCell) {
        if (currentlyDragging == null || targetCell == null) {
            return;
        }
        if (targetCell == sourceCell) {
            currentlyDragging.setBackground(new Color(97, 49, 237));
            currentlyDragging.repaint();
            currentlyDragging = null;
            sourceCell = null;
            return;
        }
        int OldI = -1;
        int OldY = -1;
        int NewI = -1;
        int NewY = -1;
        for (int i = 0; i < gridPanel.getComponentCount(); i++) {
            JPanel cell = (JPanel) ((JScrollPane) gridPanel.getComponent(i)).getViewport().getView();
            if (cell == sourceCell) {
                OldI = i%7;
                OldY = i/7;
            }
            if (cell == targetCell) {
                NewI = i%7;
                NewY = i/7;
            }

        }
        if (OldI == -1 || OldY == -1 || NewI == -1 || NewY == -1) {
            currentlyDragging = null;
            sourceCell = null;
            return;
        }
        String parts = ((String) currentlyDragging.getEvent());
        String lastPart = "1";
        Pattern pattern = Pattern.compile("\\d+$"); // Matches digits at end
        Matcher matcher = pattern.matcher(parts);
        
        if (matcher.find()) {
            lastPart= matcher.group();
        }
        ReservLabel newLabel = new ReservLabel(currentlyDragging.getEvent());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        setupLabelDrag(newLabel);
        System.out.println( (String) Dat.plusDays(OldI).format(formatter) + " " + timeSlots[OldY].split("-")[0]+":00");
        String bres = new ClientHandle(User.bf, User.pw).modify_reserv(User.username, (String) Dat.plusDays(OldI).format(formatter) + " " + timeSlots[OldY].split("-")[0]+":00", parts, (String) Dat.plusDays(NewI).format(formatter) + " " + timeSlots[NewY].split("-")[0]+":00",lastPart);
        if (bres.equals("-1")) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "erreur pendant la modification");
            currentlyDragging = null;
            sourceCell = null;
            return;
        } else {
            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, "modification reussis");

        }

        targetCell.add(newLabel);
        targetCell.revalidate();
        targetCell.repaint();

        if (sourceCell != null) {
            sourceCell.remove(currentlyDragging);
            sourceCell.revalidate();
            sourceCell.repaint();
        }

        currentlyDragging = null;
        sourceCell = null;
    }

    private void reservs(String date1) {
        // Clear the grid
        for (int i = 0; i < gridPanel.getComponentCount(); i++) {
            JPanel cell = (JPanel) ((JScrollPane) gridPanel.getComponent(i)).getViewport().getView();
            cell.removeAll();
            cell.revalidate();
            cell.repaint();
        }

        String tt = new ClientHandle(User.bf, User.pw).get_reserv_wek(User.username, date1);

        if (tt != null && !tt.equals("-1")) {
            String[] reservations = tt.split("/");
            LocalDate weekStartDate = LocalDate.parse(date1);
            for (String reservation : reservations) {
                try {
                    reservation = reservation.trim();
                    String[] parts = reservation.split(",");
                    String post = parts[0].trim();
                    String dateHeure = parts[1].trim();

                    String[] dateTimeParts = dateHeure.split(" ");
                    LocalDate resDate = LocalDate.parse(dateTimeParts[0]);

                    String timeFromDB = dateTimeParts[1].substring(0, 5);

                    int t = -1;
                    for (int i = 0; i < timeSlots.length; i++) {
                        if (timeSlots[i].startsWith(timeFromDB)) {
                            t = i;
                            break;
                        }
                    }

                    int k = (int) java.time.temporal.ChronoUnit.DAYS.between(weekStartDate, resDate);

                    if (t != -1 && k >= 0 && k < 7) {
                        int place = (t * 7) + k;
                        if (place >= 0 && place < gridPanel.getComponentCount()) {
                            JPanel aa = (JPanel) ((JScrollPane) gridPanel.getComponent(place)).getViewport().getView();
                            ReservLabel label = new ReservLabel(post);
                            setupLabelDrag(label);
                            aa.add(label);
                            aa.revalidate();
                            aa.repaint();
                        }
                    }
                } catch (Exception e) {
                    System.err.println("Error processing reservation: " + reservation);
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        res = new javax.swing.JScrollPane();
        week = new javax.swing.JLabel();
        next = new javax.swing.JButton();
        back = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        week.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        week.setText("jLabel1");

        next.setBackground(new java.awt.Color(0, 0, 102));
        next.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        next.setForeground(new java.awt.Color(255, 255, 255));
        next.setText("next");
        next.addActionListener(this::nextActionPerformed);

        back.setBackground(new java.awt.Color(51, 0, 102));
        back.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        back.setForeground(new java.awt.Color(255, 255, 255));
        back.setText("back");
        back.addActionListener(this::backActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(res)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(315, 315, 315)
                .addComponent(week, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(377, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(161, 161, 161)
                .addComponent(next, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(238, 238, 238))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(week, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(res, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(next, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextActionPerformed

        Dat = Dat.plusDays(7);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate weekEnd = Dat.plusDays(6);

        week.setText(Dat.format(formatter) + " -> " + weekEnd.format(formatter));
        reservs(Dat.format(formatter));

        System.out.println("Nex wek" + Dat);
    }//GEN-LAST:event_nextActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        Dat = Dat.minusDays(7);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate weekEnd = Dat.plusDays(6);
        week.setText(Dat.format(formatter) + " -> " + weekEnd.format(formatter));
        reservs(Dat.format(formatter));

        System.out.println("prec week " + Dat);
    }//GEN-LAST:event_backActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JButton next;
    private javax.swing.JScrollPane res;
    private javax.swing.JLabel week;
    // End of variables declaration//GEN-END:variables
}
