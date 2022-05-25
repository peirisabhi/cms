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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
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
public class UpdateStudent extends javax.swing.JFrame {

    File file;
    String filePath;

    /**
     * Creates new form UpdateStudent
     */
    String stId;
    Student s;
    double tot;
    int count;
    int classes = 0;
    String oldFilePath;
    String userId;
    String user;
    String theme;

    HashMap<String, String> institute = new HashMap<String, String>();

    public UpdateStudent(Student s, String stId, String theme, String userId, String user) {
        this.stId = stId;
        this.s = s;
        this.theme = theme;
        this.userId = userId;
        this.user = user;
        initComponents();
        tableDesign();
        cityLoad();
        gradeLoad();
        schoolLoad();
        themeLoad(theme);

        jLabel2.setVisible(false);
        jLabel9.setVisible(false);

        ResultSet rs1 = search("SELECT * FROM institute");

        try {
            if (rs1.next()) {
                institute.put("name", rs1.getString("name"));
                institute.put("address", rs1.getString("address"));
                institute.put("phone1", rs1.getString("phone1"));
                institute.put("phone2", rs1.getString("phone2"));
                institute.put("website", rs1.getString("website"));
                institute.put("fax", rs1.getString("fax"));
                institute.put("thank_msg", rs1.getString("thank_msg"));
                institute.put("addmision", rs1.getString("addmision"));
                institute.put("to_institute", rs1.getString("to_institute"));
                institute.put("path", rs1.getString("path"));
                institute.put("noti_pay", rs1.getString("noti_pay"));
                institute.put("print_after_pay", rs1.getString("print_after_pay"));
                institute.put("noti_leave", rs1.getString("noti_leave"));
                institute.put("logo_invoice", rs1.getString("logo_invoice"));
                institute.put("noti_exam_results", rs1.getString("noti_exam_results"));
                institute.put("print_2_invoice", rs1.getString("print_2_invoice"));
                institute.put("noti_register", rs1.getString("noti_register"));
                institute.put("noti_class_cancel", rs1.getString("noti_class_cancel"));
                institute.put("noti_user_login", rs1.getString("noti_user_login"));
                institute.put("print_barcode", rs1.getString("print_barcode"));
            }
        } catch (SQLException ex) {
            warning(ex);
            ex.printStackTrace();
        }

        try {

            ResultSet rs = search("SELECT * FROM student INNER JOIN grade INNER JOIN city INNER JOIN school INNER JOIN student_class INNER JOIN student_class_card_type INNER JOIN class INNER JOIN subject INNER JOIN teacher INNER JOIN day WHERE student.grade_id = grade.id && student.school_id = school.id && student.city_id = city.id && student_class.student_id = student.id && student_class.student_class_card_type_id = student_class_card_type.id && student_class.class_id = class.id && class.subject_id = subject.id && class.teacher_id = teacher.id && class.day_id = day.id && student_class.status = '1' && student.id = '" + stId + "';");
            jTextField1.setText(stId);

            TableModel model = jTable2.getModel();
            DefaultTableModel dtm = (DefaultTableModel) model;
            dtm.setRowCount(0);

            while (rs.next()) {
                Vector<String> v = new Vector<>();

                v.add(rs.getString("class.id"));
                v.add(rs.getString("subject"));
                v.add(rs.getString("teacher.name"));
                v.add(rs.getString("day"));
                v.add(rs.getString("start_time") + "-" + rs.getString("end_time"));
                v.add(rs.getString("fee") + "(" + rs.getString("student_class_card_type.type") + ")");

                dtm.addRow(v);

                classes++;
                jLabel27.setText(String.valueOf(classes));

                jTextField2.setText(rs.getString("student.name"));
                jDateChooser1.setDate(rs.getDate("student.dob"));
                jTextField10.setText(rs.getString("student.nic"));
                jTextField11.setText(rs.getString("student.contact1"));
                jTextField12.setText(rs.getString("student.contact2"));
                jComboBox1.setSelectedItem(rs.getString("city"));
                jComboBox3.setSelectedItem(rs.getString("school"));
                jComboBox2.setSelectedItem(rs.getString("grade"));
                jTextField14.setText(rs.getString("stream"));
                jTextField13.setText(rs.getString("student.email"));
                jTextArea1.setText(rs.getString("student.address"));

                if (rs.getString("gender").equals("F")) {
                    jRadioButton2.setSelected(true);
                } else {
                    jRadioButton1.setSelected(true);
                }

                if (rs.getString("sms").equals("1")) {
                    jCheckBox1.setSelected(true);
                }

                ImageIcon imageIcon = new ImageIcon(new ImageIcon(rs.getString("student.path")).getImage().getScaledInstance(166, 187, Image.SCALE_DEFAULT));
                jLabel4.setIcon(imageIcon);
                oldFilePath = rs.getString("student.path").toString();

            }

        } catch (Exception e) {
            System.out.println(e);
            warning(e);
        }

    }

    public UpdateStudent() {
        initComponents();
        tableDesign();
        cityLoad();
        gradeLoad();
        schoolLoad();

        jLabel2.setVisible(false);
        jLabel9.setVisible(false);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel21 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel22 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jTextField14 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox();
        jLabel25 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel28 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel2.setBackground(new java.awt.Color(72, 52, 212));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("UPDATE STUDENT PROFILE");

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

        jTextField1.setEditable(false);
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

        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField2KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField2KeyTyped(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("Name");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("DOB");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setText("NIC");

        jTextField10.setEditable(false);
        jTextField10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField10KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField10KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField10KeyTyped(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setText("Mobile 1");

        jTextField11.setEditable(false);
        jTextField11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField11KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField11KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField11KeyTyped(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel19.setText("Mobile 2");

        jTextField12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField12KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField12KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField12KeyTyped(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel20.setText("Address");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel21.setText("City");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setText("  ");
        jLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(72, 52, 212)));

        jButton4.setBackground(new java.awt.Color(72, 52, 212));
        jButton4.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Take");
        jButton4.setContentAreaFilled(false);
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.setOpaque(true);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(72, 52, 212));
        jButton5.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Choose");
        jButton5.setContentAreaFilled(false);
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.setOpaque(true);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/images/basic/add_16px.png"))); // NOI18N
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel22.setText("Email");

        jTextField13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField13KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField13KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField13KeyTyped(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel23.setText("Geade");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTextField14.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField14KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField14KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField14KeyTyped(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel24.setText("Stream");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel25.setText("School");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/images/basic/add_16px.png"))); // NOI18N
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Search");

        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField3KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField3KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField3KeyTyped(evt);
            }
        });

        jTable2.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CLASS ID", "SUBJECT", "TEACHER", "DAY", "TIME", "FEES"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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
        jScrollPane3.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setPreferredWidth(10);
            jTable2.getColumnModel().getColumn(3).setPreferredWidth(10);
            jTable2.getColumnModel().getColumn(4).setPreferredWidth(10);
            jTable2.getColumnModel().getColumn(5).setPreferredWidth(10);
        }

        jLabel12.setText("records found  ");

        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel26.setText("0");

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel2.setText("Total : Rs.");

        jLabel9.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel9.setText("0.00");

        jButton6.setBackground(new java.awt.Color(72, 52, 212));
        jButton6.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("UPDATE");
        jButton6.setContentAreaFilled(false);
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.setOpaque(true);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(255, 165, 2));
        jButton7.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("ADD NEW CLASS");
        jButton7.setContentAreaFilled(false);
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton7.setOpaque(true);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel14.setText("records found  ");

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel27.setText("0");

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Male");
        jRadioButton1.setOpaque(false);

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Female");
        jRadioButton2.setOpaque(false);
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel28.setText("Send SMS");

        jCheckBox1.setText("Accept");
        jCheckBox1.setOpaque(false);

        jTable1.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CLASS ID", "CLASS", "FEES", "ADDMISION", "CARD", "TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setOpaque(false);
        jTable1.setShowVerticalLines(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(10);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(120);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(10);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(10);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(10);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel8))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 788, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jButton7))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel14)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 788, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 4, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel18))
                                .addGap(45, 45, 45)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel19)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addGap(70, 70, 70)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5))
                            .addComponent(jLabel22)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(59, 59, 59)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel25))
                                .addGap(53, 53, 53)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jComboBox3, 0, 191, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel7))
                                    .addComponent(jTextField12)
                                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jRadioButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRadioButton2))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel20)
                                        .addGap(46, 46, 46))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel28)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckBox1)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane1)
                                        .addComponent(jTextField14, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                                        .addComponent(jTextField13)))))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(11, 11, 11)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 632, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel27)
                                    .addComponent(jLabel14))
                                .addGap(4, 4, 4)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(jLabel12))))
                .addGap(43, 43, 43))
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
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 718, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        this.dispose();
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        if (evt.getKeyCode() == 10) {
            jComboBox1.requestFocus();
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

    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2KeyPressed

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jTextField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2KeyTyped

    private void jTextField10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField10KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField10KeyPressed

    private void jTextField10KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField10KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField10KeyReleased

    private void jTextField10KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField10KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField10KeyTyped

    private void jTextField11KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField11KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField11KeyPressed

    private void jTextField11KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField11KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField11KeyReleased

    private void jTextField11KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField11KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField11KeyTyped

    private void jTextField12KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField12KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField12KeyPressed

    private void jTextField12KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField12KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField12KeyReleased

    private void jTextField12KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField12KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField12KeyTyped

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        final JFileChooser fc = new JFileChooser("C:\\Users\\abhi\\Pictures");
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();

            String path = file.getAbsolutePath();

            String newPath = "";

            String[] arrSplit = path.split("\\\\");
            for (int i = 0; i < arrSplit.length; i++) {

                newPath = newPath + arrSplit[i] + "/";
            }

            filePath = newPath.substring(0, newPath.length() - 1);

            ImageIcon imageIcon = new ImageIcon(new ImageIcon(filePath).getImage().getScaledInstance(166, 166, Image.SCALE_DEFAULT));

            jLabel4.setIcon(imageIcon);

        } else {
            System.out.println("Open command cancelled by user.");
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        new AddCity(this, theme).setVisible(true);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jTextField13KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField13KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField13KeyPressed

    private void jTextField13KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField13KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField13KeyReleased

    private void jTextField13KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField13KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField13KeyTyped

    private void jTextField14KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField14KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField14KeyPressed

    private void jTextField14KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField14KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField14KeyReleased

    private void jTextField14KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField14KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField14KeyTyped

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        new AddSchool(this, theme).setVisible(true);
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jTextField3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3KeyPressed

    private void jTextField3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3KeyReleased

    private void jTextField3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3KeyTyped

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        String id = jTextField1.getText();
        String name = jTextField2.getText();
        Date date = jDateChooser1.getDate();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd");
        String dob = sdf.format(date);

        String nic = jTextField10.getText();
        String mobile1 = jTextField11.getText();
        String mobile2 = jTextField12.getText();
        String city = jComboBox1.getSelectedItem().toString();
        String school = jComboBox3.getSelectedItem().toString();
        String grade = jComboBox2.getSelectedItem().toString();
        String stream = jTextField14.getText();
        String email = jTextField13.getText();
        String address = jTextArea1.getText();

        String sms = "0";
        if (jCheckBox1.isSelected()) {
            sms = "1";
        }

        String gender = "";
        if (jRadioButton1.isSelected()) {
            gender = "M";
        } else {
            gender = "F";
        }

//            check all fileds are inserted
        if (!name.equals("") && !nic.equals("") && !mobile1.equals("") && !email.equals("") && !address.equals("") && !nic.equals("")) {

            int rowCount = jTable1.getRowCount();
            System.out.println(rowCount);
            if (rowCount != 0) {
                System.out.println("tabele");
                new NewPayment(this, theme).setVisible(true);
            } else {
                System.out.println("empty table");
                studUpdate();
            }

        } else {
            JOptionPane.showMessageDialog(this, "Required field missing", "warning", JOptionPane.WARNING_MESSAGE);
        }


    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

        new AssignClass(this, theme).setVisible(true);

    }//GEN-LAST:event_jButton7ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        if (evt.getClickCount() == 2) {
            int r = JOptionPane.showConfirmDialog(this, "Are you sure do you want to remove this class", "Conform", JOptionPane.INFORMATION_MESSAGE);
            if (r == 0) {
                int row = jTable1.getSelectedRow();
                tot = tot - Double.parseDouble(jTable1.getValueAt(row, 5).toString());
                jLabel9.setText(String.valueOf(tot));

                count--;
                jLabel26.setText(String.valueOf(count));

                TableModel model = jTable1.getModel();
                DefaultTableModel dtm = (DefaultTableModel) model;
                dtm.removeRow(row);

                if (tot == 0) {
                    jLabel9.setVisible(false);
                    jLabel2.setVisible(false);
                }

            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked

        if (evt.getClickCount() == 2) {
            int r = JOptionPane.showConfirmDialog(this, "Are you sure do you want to remove this class", "Conform", JOptionPane.INFORMATION_MESSAGE);
            if (r == 0) {
                int row = jTable2.getSelectedRow();

                if (1 == iud("UPDATE `class_managment`.`student_class` SET `status`='0' WHERE  student_class.student_id = '" + stId + "' && student_class.class_id = '" + jTable2.getValueAt(row, 0) + "';")) {

                    JOptionPane.showMessageDialog(this, "Class removed from student", "success", JOptionPane.INFORMATION_MESSAGE);

                    TableModel model = jTable2.getModel();
                    DefaultTableModel dtm = (DefaultTableModel) model;
                    dtm.removeRow(row);

                    classes--;
                    jLabel27.setText(String.valueOf(classes));

                } else {
                    JOptionPane.showMessageDialog(this, "Can not remove this class now, Please try again later", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        }

    }//GEN-LAST:event_jTable2MouseClicked

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
            java.util.logging.Logger.getLogger(UpdateStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdateStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdateStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdateStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UpdateStudent().setVisible(true);
            }
        });
    }

    public void cityLoad() {
        try {
            ResultSet rs = search("SELECT * FROM city;");
            Vector<String> v = new Vector<String>();

            while (rs.next()) {
                v.add(rs.getString("city"));
            }

            DefaultComboBoxModel dcm = new DefaultComboBoxModel(v);

            jComboBox1.setModel(dcm);

        } catch (Exception e) {
            warning(e.toString());
            System.out.println(e);
        }
    }

    void tableDesign() {
        JTableHeader th = jTable1.getTableHeader();
        JTableHeader th1 = jTable2.getTableHeader();
        Font font = new Font("Century Gothic", Font.PLAIN, 12);
        th.setFont(font);
        th1.setFont(font);
        th.setOpaque(false);
        th1.setOpaque(false);
        Color cb = new Color(45, 52, 54);
        Color cf = new Color(241, 242, 246);
        th.setForeground(cf);
        th1.setForeground(cf);
        th.setBackground(cb);
        th1.setBackground(cb);

    }

    public void gradeLoad() {
        try {
            ResultSet rs = search("SELECT * FROM grade;");
            Vector<String> v = new Vector<String>();

            while (rs.next()) {
                v.add(rs.getString("grade"));
            }

            DefaultComboBoxModel dcm = new DefaultComboBoxModel(v);

            jComboBox2.setModel(dcm);

        } catch (Exception e) {
            warning(e.toString());
            System.out.println(e);
        }
    }

    public void schoolLoad() {
        try {
            ResultSet rs = search("SELECT * FROM school;");
            Vector<String> v = new Vector<String>();

            while (rs.next()) {
                v.add(rs.getString("school"));
            }

            DefaultComboBoxModel dcm = new DefaultComboBoxModel(v);

            jComboBox3.setModel(dcm);

        } catch (Exception e) {
            warning(e.toString());
            System.out.println(e);
        }
    }

    public void adding(String payment) {
        System.out.println("addming");
        try {
            String id = jTextField1.getText();
            String name = jTextField2.getText();
            Date date = jDateChooser1.getDate();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd");
            String dob = sdf.format(date);

            String nic = jTextField10.getText();
            String mobile1 = jTextField11.getText();
            String mobile2 = jTextField12.getText();
            String city = jComboBox1.getSelectedItem().toString();
            String school = jComboBox3.getSelectedItem().toString();
            String grade = jComboBox2.getSelectedItem().toString();
            String stream = jTextField14.getText();
            String email = jTextField13.getText();
            String address = jTextArea1.getText();

            String sms = "0";
            if (jCheckBox1.isSelected()) {
                sms = "1";
            }

            String gender = "";
            if (jRadioButton1.isSelected()) {
                gender = "M";
            } else {
                gender = "F";
            }

            ResultSet rs2 = search("SELECT * FROM school WHERE school = '" + school + "'"); // get school id
            ResultSet rs3 = search("SELECT * FROM grade WHERE grade = '" + grade + "'"); // get grade id
            ResultSet rs4 = search("SELECT * FROM city WHERE city = '" + city + "'"); // get city id

            if (rs2.next()) {
                String school_id = rs2.getString("id");
                if (rs3.next()) {
                    String grade_id = rs3.getString("id");
                    if (rs4.next()) {
                        String city_id = rs4.getString("id");

                        if (filePath != null) { // check is image selected

//                        image save start
                            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                            long img = timestamp.getTime();

                            String savePath = "src/Resources/images/student/" + img + ".png";

                            InputStream is = null;
                            OutputStream os = null;

//                                    image save start
                            try {
                                is = new FileInputStream(filePath);
                                os = new FileOutputStream(savePath);
                                byte[] buffer = new byte[1024];
                                int length;
                                while ((length = is.read(buffer)) > 0) {
                                    os.write(buffer, 0, length);
                                }
                            } catch (IOException ex) {
                                warning(ex.toString());
                                System.out.println(ex);
                            } finally {
                                try {
                                    is.close();
                                } catch (IOException ex) {
                                    warning(ex.toString());
                                    System.out.println(ex);
                                }
                                try {
                                    os.close();
                                } catch (IOException ex) {
                                    warning(ex.toString());
                                    System.out.println(ex);
                                }
                            }
//                                    image save end

//                        insert student
                            if (1 == iud("UPDATE `class_managment`.`student` SET `name`='" + name + "', `dob`='" + dob + "', `contact2`='" + mobile2 + "', `address`='" + address + "', `stream`='" + stream + "', `gender`='" + gender + "', `path`='" + savePath + "', `sms`='" + sms + "', `email`='" + email + "', `grade_id`='" + grade_id + "', `school_id`='" + school_id + "', `city_id`='" + city_id + "' WHERE  `id`='" + id + "';")) {

//                            insert student fees
                                if (1 == iud("INSERT INTO `class_managment`.`fees` (`date`, `total`, `payment`, `staff_id`, `student_id`)"
                                        + " VALUES (NOW(), '" + tot + "', '" + payment + "', '" + userId + "', '" + id + "');")) {

                                    ResultSet rs5 = search("SELECT MAX(id) AS id FROM fees;"); // get inserted fees id

                                    if (rs5.next()) {
                                        System.out.println("1in");
                                        int rows = jTable1.getRowCount();

                                        for (int x = 0; x < rows; x++) {
//                                        get card type
                                            String[] card = jTable1.getValueAt(x, 4).toString().split("-");
                                            String card_id = "";
                                            if (card[0].equals("Full")) {
                                                card_id = "1";
                                            }
                                            if (card[0].equals("Half")) {
                                                card_id = "2";
                                            }
                                            if (card[0].equals("Free")) {
                                                card_id = "3";
                                            }
//                                        assign student to class
                                            iud("INSERT INTO `class_managment`.`student_class` (`note`, `date`, `status`, `student_id`, `class_id`, `student_class_card_type_id`)"
                                                    + " VALUES ('" + card[1] + "', NOW(), '1', '" + id + "', '" + jTable1.getValueAt(x, 0) + "', '" + card_id + "');");

                                            ResultSet rs6 = search("SELECT MAX(id) AS id FROM student_class;"); // get inserted student class id

//                                        get fees payment month
                                            Date d = new Date();
                                            SimpleDateFormat sdf1 = new SimpleDateFormat("M");
                                            String month = sdf1.format(date);

                                            if (rs6.next()) {
//                                            insert feees 
                                                iud("INSERT INTO `class_managment`.`student_class_fees` (`total`, `year`, `month_id`, `student_class_id`, `fees_id`) "
                                                        + "VALUES ('" + jTable1.getValueAt(x, 5) + "', YEAR(NOW()), '" + month + "', '" + rs6.getString("id") + "', '" + rs5.getString("id") + "');");
                                            }

                                        }
                                        this.s.tableLoad();
                                        JOptionPane.showMessageDialog(this, "Student Details updated", "success", JOptionPane.INFORMATION_MESSAGE);
                                        System.out.println(";;;;;");
                                        System.out.println(institute.get("print_after_pay"));
                                        if (institute.get("print_after_pay").equals("1")) {

                                            int r = JOptionPane.showConfirmDialog(this, "Are you sure do you want to print recipt", "Conform", JOptionPane.INFORMATION_MESSAGE);

                                            if (r == 0) {
                                                System.out.println("lllll");
                                                try {
                                                    String path = "src//reports//studentRegister.jasper";

                                                    HashMap<String, Object> map = new HashMap<>();
                                                    map.put("Parameter1", institute.get("name"));
                                                    map.put("Parameter2", institute.get("address"));
                                                    map.put("Parameter3", rs5.getString("id"));  // invoice id / fees id
                                                    map.put("Parameter4", institute.get("phone2"));
                                                    map.put("Parameter5", institute.get("phone1"));
                                                    map.put("Parameter6", user);
                                                    map.put("Parameter7", jTextField1.getText() + " " + jTextField2.getText());
                                                    map.put("Parameter8", jLabel9.getText());
                                                    map.put("Parameter9", payment);
                                                    map.put("Parameter10", institute.get("thank_msg"));

                                                    TableModel model = jTable1.getModel();

                                                    JRTableModelDataSource tabelDataSource = new JRTableModelDataSource(model);

                                                    JasperPrint print = JasperFillManager.fillReport(path, map, tabelDataSource);

                                                    JasperViewer.viewReport(print, false);

                                                } catch (JRException ex) {
                                                    ex.printStackTrace();
                                                    warning(ex);
                                                }

                                            }
                                        }

                                        this.dispose();

                                    }

                                } else {
                                    JOptionPane.showMessageDialog(this, "Can not update Student", "Error", JOptionPane.ERROR_MESSAGE);
                                }

                            }
                        } else {

                            //                        insert student
                            if (1 == iud("UPDATE `class_managment`.`student` SET `name`='" + name + "', `dob`='" + dob + "', `contact2`='" + mobile2 + "', `address`='" + address + "', `stream`='" + stream + "', `gender`='" + gender + "', `sms`='" + sms + "', `email`='" + email + "', `grade_id`='" + grade_id + "', `school_id`='" + school_id + "', `city_id`='" + city_id + "' WHERE  `id`='" + id + "';")) {

//                            insert student fees
                                if (1 == iud("INSERT INTO `class_managment`.`fees` (`date`, `total`, `payment`, `staff_id`, `student_id`)"
                                        + " VALUES (NOW(), '" + tot + "', '" + payment + "', '" + userId + "', '" + id + "');")) {

                                    ResultSet rs5 = search("SELECT MAX(id) AS id FROM fees;"); // get inserted fees id

                                    if (rs5.next()) {

                                        int rows = jTable1.getRowCount();

                                        for (int x = 0; x < rows; x++) {
//                                        get card type
                                            String[] card = jTable1.getValueAt(x, 4).toString().split("-");
                                            String card_id = "";
                                            if (card[0].equals("Full")) {
                                                card_id = "1";
                                            }
                                            if (card[0].equals("Half")) {
                                                card_id = "2";
                                            }
                                            if (card[0].equals("Free")) {
                                                card_id = "3";
                                            }
//                                        assign student to class
                                            iud("INSERT INTO `class_managment`.`student_class` (`note`, `date`, `status`, `student_id`, `class_id`, `student_class_card_type_id`)"
                                                    + " VALUES ('" + card[1] + "', NOW(), '1', '" + id + "', '" + jTable1.getValueAt(x, 0) + "', '" + card_id + "');");

                                            ResultSet rs6 = search("SELECT MAX(id) AS id FROM student_class;"); // get inserted student class id

//                                        get fees payment month
                                            Date d = new Date();
                                            SimpleDateFormat sdf1 = new SimpleDateFormat("M");
                                            String month = sdf1.format(date);

                                            if (rs6.next()) {
//                                            insert feees 
                                                iud("INSERT INTO `class_managment`.`student_class_fees` (`total`, `year`, `month_id`, `student_class_id`, `fees_id`) "
                                                        + "VALUES ('" + jTable1.getValueAt(x, 5) + "', YEAR(NOW()), '" + month + "', '" + rs6.getString("id") + "', '" + rs5.getString("id") + "');");
                                            }

                                        }
                                        this.s.tableLoad();
//                                        JOptionPane.showMessageDialog(this, "Student Details updated", "success", JOptionPane.INFORMATION_MESSAGE);
                                         System.out.println(";;;;;");
                                        System.out.println(institute.get("print_after_pay"));
                                        if (institute.get("print_after_pay").equals("1")) {

                                            int r = JOptionPane.showConfirmDialog(this, "Are you sure do you want to print recipt", "Conform", JOptionPane.INFORMATION_MESSAGE);

                                            if (r == 0) {
                                                System.out.println("lllll");
                                                try {
                                                    String path = "src//reports//studentRegister.jasper";

                                                    HashMap<String, Object> map = new HashMap<>();
                                                    map.put("Parameter1", institute.get("name"));
                                                    map.put("Parameter2", institute.get("address"));
                                                    map.put("Parameter3", rs5.getString("id"));  // invoice id / fees id
                                                    map.put("Parameter4", institute.get("phone2"));
                                                    map.put("Parameter5", institute.get("phone1"));
                                                    map.put("Parameter6", user);
                                                    map.put("Parameter7", jTextField1.getText() + " " + jTextField2.getText());
                                                    map.put("Parameter8", jLabel9.getText());
                                                    map.put("Parameter9", payment);
                                                    map.put("Parameter10", institute.get("thank_msg"));

                                                    TableModel model = jTable1.getModel();

                                                    JRTableModelDataSource tabelDataSource = new JRTableModelDataSource(model);

                                                    JasperPrint print = JasperFillManager.fillReport(path, map, tabelDataSource);

                                                    JasperViewer.viewReport(print, false);

                                                } catch (JRException ex) {
                                                    ex.printStackTrace();
                                                    warning(ex);
                                                }

                                            }
                                        }
                                        this.dispose();

                                    }

                                } else {
                                    JOptionPane.showMessageDialog(this, "Can not update Student", "Error", JOptionPane.ERROR_MESSAGE);
                                }

                            }

                        }
                    }

                }
            }

        } catch (SQLException ex) {
            System.out.println(ex);
            warning(ex);
        }

    }

    void studUpdate() {

        String id = jTextField1.getText();
        String name = jTextField2.getText();
        Date date = jDateChooser1.getDate();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd");
        String dob = sdf.format(date);

        String nic = jTextField10.getText();
        String mobile1 = jTextField11.getText();
        String mobile2 = jTextField12.getText();
        String city = jComboBox1.getSelectedItem().toString();
        String school = jComboBox3.getSelectedItem().toString();
        String grade = jComboBox2.getSelectedItem().toString();
        String stream = jTextField14.getText();
        String email = jTextField13.getText();
        String address = jTextArea1.getText();

        String sms = "0";
        if (jCheckBox1.isSelected()) {
            sms = "1";
        }

        String gender = "";
        if (jRadioButton1.isSelected()) {
            gender = "M";
        } else {
            gender = "F";
        }

        ResultSet rs2 = search("SELECT * FROM school WHERE school = '" + school + "'"); // get school id
        ResultSet rs3 = search("SELECT * FROM grade WHERE grade = '" + grade + "'"); // get grade id
        ResultSet rs4 = search("SELECT * FROM city WHERE city = '" + city + "'"); // get city id
        try {
            if (rs2.next()) {
                String school_id = rs2.getString("id");
                if (rs3.next()) {
                    String grade_id = rs3.getString("id");
                    if (rs4.next()) {
                        String city_id = rs4.getString("id");

                        if (filePath != null) { // check is image selected

//                        image save start
                            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                            long img = timestamp.getTime();

                            String savePath = "src/Resources/images/student/" + img + ".png";

                            InputStream is = null;
                            OutputStream os = null;

//                                    image save start
                            try {
                                is = new FileInputStream(filePath);
                                os = new FileOutputStream(savePath);
                                byte[] buffer = new byte[1024];
                                int length;
                                while ((length = is.read(buffer)) > 0) {
                                    os.write(buffer, 0, length);
                                }
                            } catch (IOException ex) {
                                warning(ex.toString());
                                System.out.println(ex);
                            } finally {
                                try {
                                    is.close();
                                } catch (IOException ex) {
                                    warning(ex.toString());
                                    System.out.println(ex);
                                }
                                try {
                                    os.close();
                                } catch (IOException ex) {
                                    warning(ex.toString());
                                    System.out.println(ex);
                                }
                            }
//                                    image save end

//                        insert student
                            if (1 == iud("UPDATE `class_managment`.`student` SET `name`='" + name + "', `dob`='" + dob + "', `contact2`='" + mobile2 + "', `address`='" + address + "', `stream`='" + stream + "', `gender`='" + gender + "', `path`='" + savePath + "', `sms`='" + sms + "', `email`='" + email + "', `grade_id`='" + grade_id + "', `school_id`='" + school_id + "', `city_id`='" + city_id + "' WHERE  `id`='" + id + "';")) {

                                this.s.tableLoad();
                                JOptionPane.showMessageDialog(this, "Student Details updated", "success", JOptionPane.INFORMATION_MESSAGE);
                                this.dispose();

                            } else {
                                JOptionPane.showMessageDialog(this, "Can not update Student", "Error", JOptionPane.ERROR_MESSAGE);
                            }

                        } else {

                            if (1 == iud("UPDATE `class_managment`.`student` SET `name`='" + name + "', `dob`='" + dob + "', `contact2`='" + mobile2 + "', `address`='" + address + "', `stream`='" + stream + "', `gender`='" + gender + "', `sms`='" + sms + "', `email`='" + email + "', `grade_id`='" + grade_id + "', `school_id`='" + school_id + "', `city_id`='" + city_id + "' WHERE  `id`='" + id + "';")) {

                                this.s.tableLoad();
                                JOptionPane.showMessageDialog(this, "Student Details uodated", "success", JOptionPane.INFORMATION_MESSAGE);
                                this.dispose();

                            } else {
                                JOptionPane.showMessageDialog(this, "Can not update Student", "Error", JOptionPane.ERROR_MESSAGE);
                            }

                        }
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            warning(ex);
        }

    }

    void themeLoad(String theme) {

        jPanel2.setBackground(new Color(Integer.parseInt(theme.split(",")[0]), Integer.parseInt(theme.split(",")[1]), Integer.parseInt(theme.split(",")[2])));
        jButton6.setBackground(new Color(Integer.parseInt(theme.split(",")[0]), Integer.parseInt(theme.split(",")[1]), Integer.parseInt(theme.split(",")[2])));
        jButton4.setBackground(new Color(Integer.parseInt(theme.split(",")[0]), Integer.parseInt(theme.split(",")[1]), Integer.parseInt(theme.split(",")[2])));
        jButton5.setBackground(new Color(Integer.parseInt(theme.split(",")[0]), Integer.parseInt(theme.split(",")[1]), Integer.parseInt(theme.split(",")[2])));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/images/basic/" + theme + "/add_16px.png")));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/images/basic/" + theme + "/add_16px.png")));

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    javax.swing.JButton jButton4;
    javax.swing.JButton jButton5;
    javax.swing.JButton jButton6;
    javax.swing.JButton jButton7;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextArea jTextArea1;
    javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField2;
    javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
