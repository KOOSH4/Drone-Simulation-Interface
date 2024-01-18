package de.frauas.dronesimulation.app.ui.form;

import de.frauas.dronesimulation.app.ui.swing.ScrollBar;
import de.frauas.dronesimulation.app.dronelist.DroneList;
import de.frauas.dronesimulation.app.ui.model.Model_Card;
import de.frauas.dronesimulation.app.ui.model.StatusType;

import java.awt.Color;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Form_Home extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;

    private de.frauas.dronesimulation.app.ui.component.Card card1;
    private de.frauas.dronesimulation.app.ui.component.Card card2;
    private de.frauas.dronesimulation.app.ui.component.Card card3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane panel;
    private de.frauas.dronesimulation.app.ui.swing.PanelBorder panelBorder1;
    private javax.swing.JScrollPane spTable;
    private de.frauas.dronesimulation.app.ui.swing.Table table;

    public Form_Home(List<DroneList> droneInstanceList) {
        initComponents();
        int total = droneInstanceList.size();
        String totalString = String.valueOf(total);
        card1.setData(new Model_Card(
                new ImageIcon(getClass().getResource("/de/frauas/dronesimulation/app/ui/icon/stock.png")),
                "Total Number", totalString, " "));
        card2.setData(new Model_Card(
                new ImageIcon(getClass().getResource("/de/frauas/dronesimulation/app/ui/icon/profit.png")),
                "Manufacturer", "10+ different brands ", " "));
        card3.setData(
                new Model_Card(new ImageIcon(getClass().getResource("/de/frauas/dronesimulation/app/ui/icon/flag.png")),
                        "Demo ", "demo ", "xyz "));
        // add row table
        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        // table.addRow(new Object[]{"71", "Holy Stone", "HS100", "HoHS-2024-F26CA5",
        // StatusType.ON});
        // table.addRow(new Object[]{"72", "Snaptain", "S5C", "SnS5-2030-360F05",
        // StatusType.OFF});
        // table.addRow(new Object[]{"73", "Hubsan", "X4 H107D", "HuX4-2028-208EA8",
        // StatusType.OFF});

        for (DroneList droneinstance : droneInstanceList) {
            // each drone in list.
            // droneinstance.printStatus(); // print drone instance status
            // droneinstance.getDroneType().printStatus(); // print drone type status

            // System.out.println("--------------------------");
            table.addRow(new Object[] { droneinstance.getId(), droneinstance.getDroneType().getManufacturer(),
                    droneinstance.getDroneType().getTypeName(), droneinstance.getSerialnumber(), StatusType.ON });
        }

    }

    // @SuppressWarnings("unchecked")
    private void initComponents() {

        panel = new javax.swing.JLayeredPane();
        card1 = new de.frauas.dronesimulation.app.ui.component.Card();
        card2 = new de.frauas.dronesimulation.app.ui.component.Card();
        card3 = new de.frauas.dronesimulation.app.ui.component.Card();
        panelBorder1 = new de.frauas.dronesimulation.app.ui.swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        spTable = new javax.swing.JScrollPane();
        table = new de.frauas.dronesimulation.app.ui.swing.Table();

        setBackground(new java.awt.Color(242, 242, 242));

        panel.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        card1.setColor1(new java.awt.Color(142, 142, 250));
        card1.setColor2(new java.awt.Color(123, 123, 245));
        panel.add(card1);

        card2.setColor1(new java.awt.Color(186, 123, 247));
        card2.setColor2(new java.awt.Color(167, 94, 236));
        panel.add(card2);

        card3.setColor1(new java.awt.Color(241, 208, 62));
        card3.setColor2(new java.awt.Color(211, 184, 61));
        panel.add(card3);

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
        jLabel1.setText("Drone Overview Table");

        spTable.setBorder(null);

        table.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {

                },
                new String[] {
                        "ID", "Manufacturer", "Typename", "Serialnumber", "Status"
                }) {
            private static final long serialVersionUID = 1L;
            boolean[] canEdit = new boolean[] {
                    false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        spTable.setViewportView(table);

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
                panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(panelBorder1Layout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelBorder1Layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(spTable))
                                .addContainerGap()));
        panelBorder1Layout.setVerticalGroup(
                panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                                .addGap(20, 20, 20)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 875,
                                                Short.MAX_VALUE))
                                .addGap(20, 20, 20)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(20, 20, 20)));
    }
}
