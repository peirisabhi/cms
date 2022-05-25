/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static DB.Db.iud;
import static DB.Db.search;
import static Logger.Log.warning;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author abhi
 */
public class PayMonthlyFee extends javax.swing.JFrame {

    /**
     * Creates new form PayMonthlyFee
     */
    double tot;
    int count;
    
    String userId;
    String user;
    String position;
    String theme;

    HashMap<String, String> institute = new HashMap<String, String>();

    public PayMonthlyFee(String user, String userId, String position, String theme) {
        
        this.user = user;
        this.userId = userId;
        this.position = position;
        this.theme = theme;
        System.out.println(userId);
        initComponents();

        tableDesign();
        themeLoad(theme);

        ResultSet rs = search("SELECT * FROM institute");

        try {
            if (rs.next()) {
                institute.put("name", rs.getString("name"));
                institute.put("address", rs.getString("address"));
                institute.put("phone1", rs.getString("phone1"));
                institute.put("phone2", rs.getString("phone2"));
                institute.put("website", rs.getString("website"));
                institute.put("fax", rs.getString("fax"));
                institute.put("thank_msg", rs.getString("thank_msg"));
                institute.put("addmision", rs.getString("addmision"));
                institute.put("to_institute", rs.getString("to_institute"));
                institute.put("path", rs.getString("path"));
                institute.put("noti_pay", rs.getString("noti_pay"));
                institute.put("print_after_pay", rs.getString("print_after_pay"));
                institute.put("noti_leave", rs.getString("noti_leave"));
                institute.put("logo_invoice", rs.getString("logo_invoice"));
                institute.put("noti_exam_results", rs.getString("noti_exam_results"));
                institute.put("print_2_invoice", rs.getString("print_2_invoice"));
                institute.put("noti_register", rs.getString("noti_register"));
                institute.put("noti_class_cancel", rs.getString("noti_class_cancel"));
                institute.put("noti_user_login", rs.getString("noti_user_login"));
                institute.put("print_barcode", rs.getString("print_barcode"));
            }
        } catch (SQLException ex) {
            warning(ex);
            ex.printStackTrace();
        }

    }

    
    public PayMonthlyFee() {
        initComponents();

        tableDesign();

        ResultSet rs = search("SELECT * FROM institute");

        try {
            if (rs.next()) {
                institute.put("name", rs.getString("name"));
                institute.put("address", rs.getString("address"));
                institute.put("phone1", rs.getString("phone1"));
                institute.put("phone2", rs.getString("phone2"));
                institute.put("website", rs.getString("website"));
                institute.put("fax", rs.getString("fax"));
                institute.put("thank_msg", rs.getString("thank_msg"));
                institute.put("addmision", rs.getString("addmision"));
                institute.put("to_institute", rs.getString("to_institute"));
                institute.put("path", rs.getString("path"));
                institute.put("noti_pay", rs.getString("noti_pay"));
                institute.put("print_after_pay", rs.getString("print_after_pay"));
                institute.put("noti_leave", rs.getString("noti_leave"));
                institute.put("logo_invoice", rs.getString("logo_invoice"));
                institute.put("noti_exam_results", rs.getString("noti_exam_results"));
                institute.put("print_2_invoice", rs.getString("print_2_invoice"));
                institute.put("noti_register", rs.getString("noti_register"));
                institute.put("noti_class_cancel", rs.getString("noti_class_cancel"));
                institute.put("noti_user_login", rs.getString("noti_user_login"));
                institute.put("print_barcode", rs.getString("print_barcode"));
            }
        } catch (SQLException ex) {
            warning(ex);
            ex.printStackTrace();
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel2.setBackground(new java.awt.Color(72, 52, 212));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("CLASS FEES");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/images/basic/close_window_32px.png"))); // NOI18N
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Student ID");

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField1MouseClicked(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        jLabel13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jLabel4.setText("  ");
        jLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setText("Name");

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel30.setText("ID");

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel31.setText("Gender");

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel32.setText("NIC");

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel33.setText("Contact");

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel34.setText("Registerd");

        jLabel7.setText(" ");

        jLabel8.setText(" ");

        jLabel9.setText(" ");

        jLabel10.setText(" ");

        jLabel11.setText(" ");

        jLabel12.setText(" ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33)
                    .addComponent(jLabel34)
                    .addComponent(jLabel15)
                    .addComponent(jLabel30)
                    .addComponent(jLabel32)
                    .addComponent(jLabel31))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 16, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );

        jTable1.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CLASS", "DATE", "TIME"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setOpaque(false);
        jTable1.setShowVerticalLines(false);
        jScrollPane1.setViewportView(jTable1);

        jTable2.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CLASS ID", "CLASS", "PAYING", "LAST PAYMENT DATE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setOpaque(false);
        jTable2.setShowVerticalLines(false);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jTable3.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "CLASS ID", "CLASS", "MONTH", "PAYING"
            }
        ));
        jTable3.setOpaque(false);
        jTable3.setShowVerticalLines(false);
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);
        if (jTable3.getColumnModel().getColumnCount() > 0) {
            jTable3.getColumnModel().getColumn(0).setPreferredWidth(10);
            jTable3.getColumnModel().getColumn(1).setPreferredWidth(10);
            jTable3.getColumnModel().getColumn(2).setPreferredWidth(300);
            jTable3.getColumnModel().getColumn(3).setPreferredWidth(15);
            jTable3.getColumnModel().getColumn(4).setPreferredWidth(10);
        }

        jLabel14.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel14.setText("0.00");

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel2.setText("Total : Rs.");

        jButton6.setBackground(new java.awt.Color(72, 52, 212));
        jButton6.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("PAY NOW");
        jButton6.setContentAreaFilled(false);
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.setOpaque(true);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Attendance");

        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel26.setText("0");

        jLabel16.setText("records found  ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel13))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(269, 269, 269)
                                .addComponent(jLabel5)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 933, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jLabel16))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        this.dispose();
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jTextField1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField1MouseClicked

        if (evt.getClickCount() == 2) {
            jTextField1.setEnabled(true);
            jTextField1.setText("");
            clear();
        }
    }//GEN-LAST:event_jTextField1MouseClicked

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed

        if (evt.getKeyChar() == 10) {

            ResultSet rs1 = search("SELECT * FROM STUDENT WHERE  student.id = '" + jTextField1.getText() + "';");

            try {
                if (rs1.next()) {

                    jTextField1.setEnabled(false);

                    jLabel11.setText(rs1.getString("name"));
                    jLabel12.setText(rs1.getString("id"));
                    jLabel9.setText(rs1.getString("nic"));
                    jLabel10.setText(rs1.getString("gender"));
                    jLabel8.setText(rs1.getString("contact1"));
                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                    jLabel7.setText(sdf.format(rs1.getDate("date_reg")));

                    ImageIcon imageIcon = new ImageIcon(new ImageIcon(rs1.getString("path")).getImage().getScaledInstance(121, 131, Image.SCALE_DEFAULT));
                    jLabel4.setIcon(imageIcon);

                    ResultSet rs2 = search("SELECT * FROM student_class INNER JOIN class INNER JOIN teacher INNER JOIN subject INNER JOIN grade INNER JOIN student_class_card_type WHERE student_class.student_class_card_type_id = student_class_card_type.id && student_class.class_id = class.id && class.subject_id = subject.id && class.teacher_id = teacher.id && subject.grade_id = grade.id && student_class.`status` = '1' && student_class.student_id = '" + rs1.getString("id") + "';");

                    TableModel model = jTable2.getModel();
                    DefaultTableModel dtm = (DefaultTableModel) model;
                    dtm.setRowCount(0);

                    int i = 1;
                    while (rs2.next()) {
                        Vector<String> v = new Vector<>();

                        v.add(rs2.getString("class.id"));
                        v.add(rs2.getString("subject") + " " + rs2.getString("teacher.name") + " " + rs2.getString("class.desc") + " " + rs2.getString("grade"));

                        if (rs2.getString("type").equals("Full")) {
                            v.add(rs2.getString("fee"));
                        }
                        if (rs2.getString("type").equals("Half")) {
                            v.add(String.valueOf(Double.parseDouble(rs2.getString("fee")) / 2));
                        }
                        if (rs2.getString("type").equals("Free")) {
                            v.add("0.00");
                        }

                        String query = "SELECT MAX(fees.date) AS date FROM\n"
                                + "\n"
                                + " student_class\n"
                                + " INNER JOIN student_class_fees \n"
                                + " INNER JOIN fees\n"
                                + " \n"
                                + " WHERE \n"
                                + "   student_class.id = student_class_fees.student_class_id\n"
                                + "   && student_class_fees.fees_id = fees.id\n"
                                + "   \n"
                                + "   && student_class.student_id = '" + jTextField1.getText() + "'\n"
                                + "   && student_class.class_id = '" + rs2.getString("class.id") + "'\n"
                                + "   ";

                        ResultSet rs3 = search(query);

                        if (rs3.next()) {

                            v.add(rs3.getString("date"));
                        }

                        dtm.addRow(v);

                        jLabel26.setText(String.valueOf(i));
                        i++;
                    }

                    String quer1 = "SELECT * FROM class_attendance \n"
                            + "\n"
                            + " INNER JOIN student_class \n"
                            + " INNER JOIN class \n"
                            + " INNER JOIN teacher\n"
                            + " INNER JOIN subject\n"
                            + " INNER JOIN grade\n"
                            + " \n"
                            + " WHERE class_attendance.student_id= '" + jTextField1.getText() + "' \n"
                            + " \n"
                            + " && class_attendance.student_class_id = student_class.id\n"
                            + " && student_class.class_id = class.id\n"
                            + " && class.teacher_id = teacher.id\n"
                            + " && class.subject_id = subject.id\n"
                            + " && subject.grade_id = grade.id\n"
                            + " \n"
                            + " \n"
                            + " ORDER BY class_attendance.date DESC";

                    ResultSet rs4 = search(quer1);

                    TableModel model1 = jTable1.getModel();
                    DefaultTableModel dtm1 = (DefaultTableModel) model1;
                    dtm1.setRowCount(0);

                    while (rs4.next()) {

                        Vector<String> v = new Vector<>();

                        v.add(rs4.getString("subject") + " " + rs4.getString("teacher.name") + " " + rs4.getString("class.desc") + " " + rs4.getString("grade"));
                        v.add(rs4.getString("date"));
                        v.add(rs4.getString("time"));

                        dtm1.addRow(v);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "No records to find", "Warning", JOptionPane.WARNING_MESSAGE);

                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                warning(ex);
            }
        }

    }//GEN-LAST:event_jTextField1KeyPressed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased

    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped

        if (jTextField1.getText().length() >= 8) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked

    }//GEN-LAST:event_jLabel13MouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        if (jTable3.getRowCount() > 0) {

            new NewPayment(this,theme).setVisible(true);

        } else {
            JOptionPane.showMessageDialog(null, "Please select class", "Warning", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked

        int selectedRow = jTable2.getSelectedRow();

        if (selectedRow > -1) {

            if (evt.getClickCount() == 2) {
                new SelectPayingMonth(this, jTable2.getValueAt(selectedRow, 0).toString(), selectedRow, theme).setVisible(true);
            }
        }

    }//GEN-LAST:event_jTable2MouseClicked

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked

        if (evt.getClickCount() == 2) {
            int selectedRow = jTable3.getSelectedRow();

            if (selectedRow > -1) {

                TableModel model = jTable3.getModel();
                DefaultTableModel dtm = (DefaultTableModel) model;

                tot = tot - Double.parseDouble(jTable3.getValueAt(selectedRow, 3).toString());
                jLabel14.setText(String.valueOf(tot));
                dtm.removeRow(selectedRow);

            }
        }

    }//GEN-LAST:event_jTable3MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PayMonthlyFee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PayMonthlyFee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PayMonthlyFee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PayMonthlyFee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PayMonthlyFee().setVisible(true);
            }
        });
    }

    void clear() {
        jTextField1.setText("");
        jTextField1.setEnabled(true);
        jLabel11.setText(" ");
        jLabel12.setText(" ");
        jLabel9.setText(" ");
        jLabel10.setText(" ");
        jLabel7.setText(" ");
        jLabel8.setText(" ");

        jLabel4.setIcon(null);

        TableModel model1 = jTable1.getModel();
        DefaultTableModel dtm1 = (DefaultTableModel) model1;
        dtm1.setRowCount(0);

        TableModel model = jTable2.getModel();
        DefaultTableModel dtm = (DefaultTableModel) model;
        dtm.setRowCount(0);

        TableModel model2 = jTable3.getModel();
        DefaultTableModel dtm2 = (DefaultTableModel) model2;
        dtm2.setRowCount(0);

        jLabel26.setText("0");
        jLabel14.setText("0.00");

        jTextField1.requestFocus();

    }

    void tableDesign() {
        JTableHeader th = jTable1.getTableHeader();
        JTableHeader th1 = jTable2.getTableHeader();
        JTableHeader th2 = jTable3.getTableHeader();
        Font font = new Font("Century Gothic", Font.PLAIN, 12);
        th.setFont(font);
        th1.setFont(font);
        th2.setFont(font);
        th.setOpaque(false);
        th1.setOpaque(false);
        th2.setOpaque(false);
        Color cb = new Color(45, 52, 54);
        Color cf = new Color(241, 242, 246);
        th.setForeground(cf);
        th1.setForeground(cf);
        th2.setForeground(cf);
        th.setBackground(cb);
        th1.setBackground(cb);
        th2.setBackground(cb);

    }

//    pay monthly class fees
    void pay(String payment) {

        if (1 == iud("INSERT INTO `class_managment`.`fees` (`date`, `total`, `payment`, `staff_id`, `student_id`)"
                + " VALUES (NOW(), '" + tot + "', '" + payment + "', '" + userId + "', '" + jTextField1.getText() + "');")) {

            ResultSet rs = search("SELECT MAX(id) AS id FROM fees;"); // get inserted fees id
           
            String feesId = null;
            
            try {
                if (rs.next()) {
                    
                    feesId = rs.getString("id");
                    
                    for (int x = 0; jTable3.getRowCount() > x; x++) {

                        ResultSet rs2 = search("SELECT id FROM student_class WHERE student_class.student_id = '" + jTextField1.getText() + "' && student_class.class_id = '" + jTable3.getValueAt(x, 1) + "'"); // get student class id
                        if (rs2.next()) {

                            ResultSet rs3 = search("SELECT id FROM month WHERE month = '" + jTable3.getValueAt(x, 3).toString().split(" ")[1] + "';");
                            if (rs3.next()) {

                                try {
                                    if (1 == iud("INSERT INTO `class_managment`.`student_class_fees` (`total`, `year`, `month_id`, `student_class_id`, `fees_id`) "
                                            + "VALUES ('" + jTable3.getValueAt(x, 4) + "', NOW(), '" + rs3.getString("id") + "', '" + rs2.getString("id") + "', '" + rs.getString("id") + "');")) {

//                                      System.out.println("okk");
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    warning(e);
                                }

                            }
                        }
                    }

                    if (institute.get("print_after_pay").equals("1")) {

                        int r = JOptionPane.showConfirmDialog(this, "Are you sure do you want to print recipt", "Conform", JOptionPane.INFORMATION_MESSAGE);

                        if (r == 0) {

                            try {
                                String path = "src//reports//studentPay.jasper";

                                HashMap<String, Object> map = new HashMap<>();
                                map.put("Parameter1", institute.get("name"));
                                map.put("Parameter2", institute.get("address"));
                                map.put("Parameter3", feesId);
                                map.put("Parameter4", institute.get("phone2"));
                                map.put("Parameter5", institute.get("phone1"));
                                map.put("Parameter6", user);
                                map.put("Parameter7", jLabel12.getText()+" "+jLabel11.getText());
                                map.put("Parameter8", jLabel14.getText());
                                map.put("Parameter9", payment);
                                map.put("Parameter10", institute.get("thank_msg"));

                                TableModel model = jTable3.getModel();

                                JRTableModelDataSource tabelDataSource = new JRTableModelDataSource(model);

                                JasperPrint print = JasperFillManager.fillReport(path, map, tabelDataSource);
                                
                                JasperViewer.viewReport(print, false);

                            } catch (JRException ex) {
                                ex.printStackTrace();
                                warning(ex);
                            }

                        }
                    }
                    clear();

                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                warning(ex);
            }

        } else {
            JOptionPane.showMessageDialog(this, "Can not pay now please try again later", "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    
    
     void themeLoad(String theme) {

        jPanel2.setBackground(new Color(Integer.parseInt(theme.split(",")[0]), Integer.parseInt(theme.split(",")[1]), Integer.parseInt(theme.split(",")[2])));
        jButton6.setBackground(new Color(Integer.parseInt(theme.split(",")[0]), Integer.parseInt(theme.split(",")[1]), Integer.parseInt(theme.split(",")[2])));
   
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    javax.swing.JTable jTable2;
    javax.swing.JTable jTable3;
    javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
