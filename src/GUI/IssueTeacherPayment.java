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
import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
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
public class IssueTeacherPayment extends javax.swing.JFrame {

    /**
     * Creates new form IssueTeacherPayment
     */
    double balance;
    String userId;
    String user;
    AddNewClassPayment anp;
    AddNewSeminarPayment ansp;

    HashMap<String, String> institute = new HashMap<String, String>();

    public IssueTeacherPayment(String tId, AddNewSeminarPayment ansp, String theme, String userId, String user) {

        this.ansp = ansp;
        this.user = user;
        this.userId = userId;

        initComponents();

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

        try {
            ResultSet rs1 = search("SELECT * FROM teacher WHERE id = '" + tId + "';");

            if (rs1.next()) {

                jLabel11.setText(rs1.getString("name"));
                jLabel12.setText(rs1.getString("id"));
                jLabel9.setText(rs1.getString("nic"));
                jLabel10.setText(rs1.getString("contact1") + "  " + rs1.getString("contact2"));
                jLabel8.setText(rs1.getString("email"));

                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                jLabel7.setText(sdf.format(rs1.getDate("date_reg")));

                ImageIcon imageIcon = new ImageIcon(new ImageIcon(rs1.getString("path")).getImage().getScaledInstance(121, 131, Image.SCALE_DEFAULT));
                jLabel4.setIcon(imageIcon);

                jLabel16.setText(rs1.getString("balance").toString());
                balance = Double.parseDouble(rs1.getString("balance").toString());

                jLabel24.setText(String.valueOf(Double.parseDouble(jLabel16.getText()) + Double.parseDouble(jLabel22.getText())));

            }

        } catch (Exception e) {
            System.out.println(e);
            warning(e);
        }

    }

    public IssueTeacherPayment(String tId, AddNewClassPayment anp, String theme, String userId, String user) {

        this.anp = anp;
        this.user = user;
        this.userId = userId;

        initComponents();

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

        try {
            ResultSet rs1 = search("SELECT * FROM teacher WHERE id = '" + tId + "';");

            if (rs1.next()) {

                jLabel11.setText(rs1.getString("name"));
                jLabel12.setText(rs1.getString("id"));
                jLabel9.setText(rs1.getString("nic"));
                jLabel10.setText(rs1.getString("contact1") + "  " + rs1.getString("contact2"));
                jLabel8.setText(rs1.getString("email"));

                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                jLabel7.setText(sdf.format(rs1.getDate("date_reg")));

                ImageIcon imageIcon = new ImageIcon(new ImageIcon(rs1.getString("path")).getImage().getScaledInstance(121, 131, Image.SCALE_DEFAULT));
                jLabel4.setIcon(imageIcon);

                jLabel16.setText(rs1.getString("balance").toString());
                balance = Double.parseDouble(rs1.getString("balance").toString());

                jLabel24.setText(String.valueOf(Double.parseDouble(jLabel16.getText()) + Double.parseDouble(jLabel22.getText())));

            }

        } catch (Exception e) {
            System.out.println(e);
            warning(e);
        }

    }

    public IssueTeacherPayment() {
        initComponents();
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
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();

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

        jLabel13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setText("  ");
        jLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setText("Name");

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel30.setText("ID");

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel31.setText("Contact");

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel32.setText("NIC");

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel33.setText("Email");

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

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Available Balance:");
        jLabel14.setOpaque(true);

        jLabel16.setBackground(new java.awt.Color(255, 255, 255));
        jLabel16.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("0.00");
        jLabel16.setOpaque(true);

        jLabel18.setBackground(new java.awt.Color(255, 255, 255));
        jLabel18.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("Total Pay:");
        jLabel18.setOpaque(true);

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel19.setText("0.00");
        jLabel19.setOpaque(true);

        jTextField8.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jTextField8.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField8.setBorder(null);
        jTextField8.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });
        jTextField8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField8KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField8KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField8KeyTyped(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(204, 0, 0));
        jLabel20.setText("press (Enter) to Acept");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(204, 0, 0));
        jLabel21.setText("press (Esc) to cancle");

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("Balance:");
        jLabel17.setOpaque(true);

        jLabel22.setBackground(new java.awt.Color(255, 255, 255));
        jLabel22.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel22.setText("0.00");
        jLabel22.setOpaque(true);

        jLabel23.setBackground(new java.awt.Color(255, 255, 255));
        jLabel23.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel23.setText("Total Balance:");
        jLabel23.setOpaque(true);

        jLabel24.setBackground(new java.awt.Color(255, 255, 255));
        jLabel24.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel24.setText("0.00");
        jLabel24.setOpaque(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(259, 259, 259))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addComponent(jLabel17)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(94, 94, 94)
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel21)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel18))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel16))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel22))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21))
                .addGap(19, 19, 19))
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

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked

    }//GEN-LAST:event_jLabel13MouseClicked

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jTextField8KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField8KeyReleased

        if (!jTextField8.getText().equals("")) {

            System.out.println(jTextField8.getText());

            jLabel22.setText(String.valueOf(Double.parseDouble(jTextField8.getText()) - Double.parseDouble(jLabel19.getText())));

            jLabel24.setText(String.valueOf(Double.parseDouble(jLabel16.getText()) + Double.parseDouble(jLabel22.getText())));

            if (evt.getKeyCode() == 10) {

                int r = JOptionPane.showConfirmDialog(this, "Are you sure do you want to complete this process", "Conform", JOptionPane.INFORMATION_MESSAGE);

                if (r == 0) {

                    if (anp != null) {
                        classPay();
                    } else {
                        seminarPay();
                    }

                }

            }

        }

    }//GEN-LAST:event_jTextField8KeyReleased

    private void jTextField8KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField8KeyTyped
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField8KeyTyped

    private void jTextField8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField8KeyPressed


    }//GEN-LAST:event_jTextField8KeyPressed

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
            java.util.logging.Logger.getLogger(IssueTeacherPayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IssueTeacherPayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IssueTeacherPayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IssueTeacherPayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IssueTeacherPayment().setVisible(true);
            }
        });
    }

    void themeLoad(String theme) {

        jPanel2.setBackground(new Color(Integer.parseInt(theme.split(",")[0]), Integer.parseInt(theme.split(",")[1]), Integer.parseInt(theme.split(",")[2])));

    }

    void classPay() {
        System.out.println("clasa pay");

        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy");

        int month = Integer.valueOf(sdf.format(new Date()));

        if (month == 1) {
            month = 12;
        } else {
            month--;
        }

        ResultSet rs = search("select CONCAT('CP-',MAX(CAST(SUBSTRING(id,4,8) AS UNSIGNED))) AS id from class_payment");

        String classPaymentID = null;
        try {
            if (rs.next()) {
                if (rs.getString("id") != null) {
                    String[] x = rs.getString("id").split("-");
                    classPaymentID = x[0] + "-" + String.valueOf(Integer.valueOf(x[1]) + 1);
                } else {
                    classPaymentID = "CP-1";
                }

                if (1 == iud("INSERT INTO `class_managment`.`class_payment` (`id`,`date`, `amount`, `total`, `available_balance`, `balance`, `year`, `teacher_id`, `staff_id`, `month_id`) "
                        + "VALUES ('" + classPaymentID + "', NOW(), '" + jTextField8.getText() + "', '" + this.anp.jLabel9.getText() + "', '" + jLabel16.getText() + "', '" + jLabel22.getText() + "', '" + sdf1.format(new Date()) + "', '" + jLabel12.getText() + "', '" + userId + "', '" + month + "');")) {

                    int rowCount = this.anp.jTable1.getRowCount();

                    for (int x = 0; x < rowCount; x++) {

                        String card = this.anp.jTable1.getValueAt(x, 3).toString().split(" ")[1];
                        String cardId;
                        if (card.equals("(Full)")) {
                            cardId = "1";
                        } else if (card.equals("(Half)")) {
                            cardId = "2";
                        } else {
                            cardId = "3";
                        }

                        try {
                            iud("INSERT INTO `class_managment`.`class_payment_classes` (`total`, `st_fee`, `te_fee`, `students`, `student_class_card_type_id`, `class_payment_id`, `class_id`)"
                                    + " VALUES ('" + this.anp.jTable1.getValueAt(x, 6) + "', '" + this.anp.jTable1.getValueAt(x, 4) + "', '" + this.anp.jTable1.getValueAt(x, 5) + "', '" + this.anp.jTable1.getValueAt(x, 3).toString().split(" ")[0] + "', '" + cardId + "', '" + classPaymentID + "', '" + this.anp.jTable1.getValueAt(x, 1) + "');");
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            warning(ex);
                        }
                    }

                    try {
                        if (1 == iud("UPDATE teacher SET balance = balance + " + jLabel22.getText() + " WHERE id = '" + jLabel12.getText() + "'")) {
                            JOptionPane.showMessageDialog(this, "successfully paid", "success", JOptionPane.INFORMATION_MESSAGE);

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        warning(e);
                    }

                    if (institute.get("print_after_pay").equals("1")) {

                        int r1 = JOptionPane.showConfirmDialog(this, "Are you sure do you want to print recipt", "Conform", JOptionPane.INFORMATION_MESSAGE);

                        if (r1 == 0) {
                            
                            

                            try {
                                String path = "src//reports//classPayment.jasper";

                                HashMap<String, Object> map = new HashMap<>();
                                map.put("Parameter1", institute.get("name"));
                                map.put("Parameter2", institute.get("address"));
                                map.put("Parameter3", institute.get("phone1"));
                                map.put("Parameter4", institute.get("phone2"));
                                map.put("Parameter5", institute.get("fax"));
                                map.put("Parameter6", "Teacher's Payment Report");
                                map.put("Parameter7", institute.get("website"));
                                map.put("Parameter8", classPaymentID);
                                map.put("Parameter9", jLabel11.getText());
                                map.put("Parameter10", user);
                                map.put("Parameter11", jLabel16.getText());
                                map.put("Parameter12", jLabel22.getText());
                                map.put("Parameter13", institute.get("thank_msg"));
                                map.put("Parameter14", jLabel24.getText());
                                map.put("Parameter15", jLabel19.getText());

                                TableModel model = this.anp.jTable1.getModel();

                                JRTableModelDataSource tabelDataSource = new JRTableModelDataSource(model);

                                JasperPrint print = JasperFillManager.fillReport(path, map, tabelDataSource);

                                JasperViewer.viewReport(print, false);

                            } catch (JRException ex) {
                                ex.printStackTrace();
                                warning(ex);
                            }

                        }
                    }
                    TableModel model = this.anp.jTable1.getModel();
                    DefaultTableModel dtm = (DefaultTableModel) model;
                    dtm.setRowCount(0);
                    this.anp.teacherLoad();
                    this.anp.p.dateChange();
                    this.anp.jLabel9.setText("0.00");
                    this.anp.jLabel27.setText("0");

                    this.dispose();

                } else {
                    JOptionPane.showMessageDialog(this, "Can not pay now please try again later", "Error", JOptionPane.ERROR_MESSAGE);

                }

            }
        } catch (SQLException ex) {
            System.out.println(ex);
            warning(ex);
        }

    }

    void seminarPay() {

        System.out.println("seminar pay");

        ResultSet rs = search("select CONCAT('SP-',MAX(CAST(SUBSTRING(id,4,8) AS UNSIGNED))) AS id from seminar_payment");

        String seminarPaymentID = null;
        try {
            if (rs.next()) {
                if (rs.getString("id") != null) {
                    String[] x = rs.getString("id").split("-");
                    seminarPaymentID = x[0] + "-" + String.valueOf(Integer.valueOf(x[1]) + 1);
                } else {
                    seminarPaymentID = "SP-1";
                }

                if (1 == iud("INSERT INTO `class_managment`.`seminar_payment` (`id`,`date`, `total`, `available_balance`, `balance`, `paid_amount`, `teacher_id`, `staff_id`) "
                        + "VALUES ('" + seminarPaymentID + "', NOW(), '" + this.ansp.jLabel9.getText() + "', '" + jLabel16.getText() + "', '" + jLabel22.getText() + "', '" + jTextField8.getText() + "', '" + jLabel12.getText() + "', '" + userId + "');")) {

                    int rowCount = this.ansp.jTable1.getRowCount();

                    for (int x = 0; x < rowCount; x++) {

                        String card = this.ansp.jTable1.getValueAt(x, 3).toString().split(" ")[1];
                        String cardId;
                        if (card.equals("(Full)")) {
                            cardId = "1";
                        } else if (card.equals("(Half)")) {
                            cardId = "2";
                        } else {
                            cardId = "3";
                        }

                        try {
                            iud("INSERT INTO `class_managment`.`seminar_payment_seminars` (`total`, `st_fee`, `te_fee`, `students`, `seminar_payment_id`, `type_id`, `seminar_id`)"
                                    + " VALUES ('" + this.ansp.jTable1.getValueAt(x, 6) + "', '" + this.ansp.jTable1.getValueAt(x, 4) + "', '" + this.ansp.jTable1.getValueAt(x, 5) + "', '" + this.ansp.jTable1.getValueAt(x, 3).toString().split(" ")[0] + "', '" + seminarPaymentID + "', '" + cardId + "', '" + this.ansp.jTable1.getValueAt(x, 1) + "');");
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            warning(ex);
                        }
                    }

                    try {
                        if (1 == iud("UPDATE teacher SET balance = balance + " + jLabel22.getText() + " WHERE id = '" + jLabel12.getText() + "'")) {
                            JOptionPane.showMessageDialog(this, "successfully paid", "success", JOptionPane.INFORMATION_MESSAGE);

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        warning(e);
                    }

                    if (institute.get("print_after_pay").equals("1")) {

                        int r1 = JOptionPane.showConfirmDialog(this, "Are you sure do you want to print recipt", "Conform", JOptionPane.INFORMATION_MESSAGE);

                        if (r1 == 0) {

                            try {
                                String path = "src//reports//classPayment.jasper";

                                HashMap<String, Object> map = new HashMap<>();
                                map.put("Parameter1", institute.get("name"));
                                map.put("Parameter2", institute.get("address"));
                                map.put("Parameter3", institute.get("phone1"));
                                map.put("Parameter4", institute.get("phone2"));
                                map.put("Parameter5", institute.get("fax"));
                                map.put("Parameter6", "Teacher's Payment Report");
                                map.put("Parameter7", institute.get("website"));
                                map.put("Parameter8", seminarPaymentID);
                                map.put("Parameter9", jLabel11.getText());
                                map.put("Parameter10", user);
                                map.put("Parameter11", jLabel16.getText());
                                map.put("Parameter12", jLabel22.getText());
                                map.put("Parameter13", institute.get("thank_msg"));
                                map.put("Parameter14", jLabel24.getText());
                                map.put("Parameter15", jLabel19.getText());

                                TableModel model = this.ansp.jTable1.getModel();

                                JRTableModelDataSource tabelDataSource = new JRTableModelDataSource(model);

                                JasperPrint print = JasperFillManager.fillReport(path, map, tabelDataSource);

                                JasperViewer.viewReport(print, false);

                            } catch (JRException ex) {
                                ex.printStackTrace();
                                warning(ex);
                            }

                        }
                    }
                    TableModel model = this.ansp.jTable1.getModel();
                    DefaultTableModel dtm = (DefaultTableModel) model;
                    dtm.setRowCount(0);
                    this.ansp.teacherLoad();
                    this.ansp.p.dateChange();
                    this.ansp.jLabel9.setText("0.00");
                    this.ansp.jLabel27.setText("0");

                    this.dispose();

                } else {
                    JOptionPane.showMessageDialog(this, "Can not pay now please try again later", "Error", JOptionPane.ERROR_MESSAGE);

                }

            }

        } catch (SQLException ex) {
            System.out.println(ex);
            warning(ex);
        }

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jTextField8;
    // End of variables declaration//GEN-END:variables
}
