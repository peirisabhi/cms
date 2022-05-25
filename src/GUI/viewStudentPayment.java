/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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
public class viewStudentPayment extends javax.swing.JFrame {

    /**
     * Creates new form viewStudentPayment
     */
    String stId;
    String classId;

    HashMap<String, String> institute = new HashMap<String, String>();

    public viewStudentPayment(String stId, String theme) {
        this.stId = stId;
        initComponents();

        tableDesign();
        themeLoad(theme);

        try {
            ResultSet rs1 = search("SELECT * FROM student WHERE id = '" + stId + "'");
            if (rs1.next()) {
                jLabel11.setText(rs1.getString("name"));
                jLabel12.setText(rs1.getString("id"));
                jLabel9.setText(rs1.getString("nic"));
                jLabel10.setText(rs1.getString("gender"));
                jLabel8.setText(rs1.getString("contact1"));
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                jLabel7.setText(sdf.format(rs1.getDate("date_reg")));

                ImageIcon imageIcon = new ImageIcon(new ImageIcon(rs1.getString("path")).getImage().getScaledInstance(121, 131, Image.SCALE_DEFAULT));
                jLabel4.setIcon(imageIcon);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            warning(ex);
        }

        try {
            System.out.println("2");
            String q2 = "SELECT \n"
                    + "	* \n"
                    + "\n"
                    + "FROM \n"
                    + "	student_class\n"
                    + "	INNER JOIN class\n"
                    + "	INNER JOIN student_class_card_type\n"
                    + "	INNER JOIN teacher\n"
                    + "	INNER JOIN subject \n"
                    + "	INNER JOIN grade\n"
                    + "	\n"
                    + "WHERE \n"
                    + "	student_class.class_id = class.id\n"
                    + "	&& student_class.student_class_card_type_id = student_class_card_type.id\n"
                    + "	&& class.teacher_id = teacher.id\n"
                    + "	&& class.subject_id = subject.id\n"
                    + "	&& subject.grade_id = grade.id\n"
                    + "	\n"
                    + "	&& student_class.student_id = '" + stId + "'\n"
                    + "	&& student_class.`status` = '1';";

            ResultSet rs2 = search(q2);
            TableModel model = jTable1.getModel();
            DefaultTableModel dtm = (DefaultTableModel) model;
            dtm.setRowCount(0);
            int i = 0;
            while (rs2.next()) {
                i++;
                Vector<String> v = new Vector<>();

                v.add(rs2.getString("class.id"));
                v.add(rs2.getString("subject") + " " + rs2.getString("grade") + " " + rs2.getString("teacher.name") + " " + rs2.getString("class.desc"));
                v.add(rs2.getString("student_class.date").substring(0, 19));

                if (rs2.getString("type").equals("Full")) {
                    v.add(rs2.getString("fee"));
                }
                if (rs2.getString("type").equals("Half")) {
                    v.add(String.valueOf(Double.parseDouble(rs2.getString("fee")) / 2));
                }
                if (rs2.getString("type").equals("Free")) {
                    v.add("0.00");
                }

                v.add(rs2.getString("student_class.note"));

                dtm.addRow(v);
            }

            jLabel28.setText(String.valueOf(i));

        } catch (SQLException ex) {
            ex.printStackTrace();
            warning(ex);
        }

        try {
            System.out.println("3");
            String q3 = "SELECT\n"
                    + "	subject.subject,\n"
                    + "	grade.grade,\n"
                    + "	teacher.name,\n"
                    + " student_class_fees.year,\n"
                    + "	month.month,\n"
                    + "	fees.date,\n"
                    + "	class.desc,\n"
                    + "	staff.fname\n"
                    + "\n"
                    + "FROM\n"
                    + "	fees\n"
                    + "	INNER JOIN student_class_fees\n"
                    + "	INNER JOIN staff\n"
                    + "	INNER JOIN month\n"
                    + "	INNER JOIN student_class\n"
                    + "	INNER JOIN class\n"
                    + "	INNER JOIN teacher\n"
                    + "	INNER JOIN subject\n"
                    + "	INNER JOIN grade\n"
                    + "\n"
                    + "WHERE\n"
                    + "	fees.staff_id = staff.id\n"
                    + "	&& student_class_fees.fees_id = fees.id\n"
                    + "	&& student_class_fees.month_id = month.id\n"
                    + "	&& student_class_fees.student_class_id = student_class.id\n"
                    + "	&& student_class.class_id = class.id\n"
                    + "	&& class.subject_id = subject.id\n"
                    + "	&& subject.grade_id = grade.id\n"
                    + "	&& class.teacher_id = teacher.id\n"
                    + "\n"
                    + "	&& fees.student_id = '" + stId + "'";

            ResultSet rs3 = search(q3);
            TableModel model = jTable2.getModel();
            DefaultTableModel dtm = (DefaultTableModel) model;
            dtm.setRowCount(0);

            int i = 0;

            while (rs3.next()) {
                i++;
                Vector<String> v = new Vector<>();

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                String d = sdf.format(rs3.getDate("year"));

                v.add(rs3.getString("subject") + " " + rs3.getString("grade") + " " + rs3.getString("teacher.name") + " " + rs3.getString("class.desc"));
                v.add(d + " " + rs3.getString("month").substring(0, 3));
                v.add(rs3.getString("fees.date").substring(0, 19));
                v.add(rs3.getString("staff.fname"));

                dtm.addRow(v);
            }

            jLabel26.setText(String.valueOf(i));

        } catch (SQLException ex) {
            ex.printStackTrace();
            warning(ex);
        }

        try {
            System.out.println("4");
            String q4 = "SELECT \n"
                    + "	* \n"
                    + "\n"
                    + "FROM \n"
                    + "	class_attendance\n"
                    + "	INNER JOIN student_class \n"
                    + "	INNER JOIN class\n"
                    + "	INNER JOIN teacher\n"
                    + "	INNER JOIN subject\n"
                    + "	INNER JOIN grade\n"
                    + "	\n"
                    + "WHERE \n"
                    + "	class_attendance.student_class_id = student_class.id\n"
                    + "	&& student_class.class_id = class.id\n"
                    + "	&& class.subject_id = subject.id\n"
                    + "	&& class.teacher_id = teacher.id\n"
                    + "	&& subject.grade_id = grade.id\n"
                    + "	\n"
                    + "	&& class_attendance.student_id = '" + stId + "'";

            ResultSet rs4 = search(q4);
            TableModel model = jTable3.getModel();
            DefaultTableModel dtm = (DefaultTableModel) model;
            dtm.setRowCount(0);

            int i = 0;

            while (rs4.next()) {
                i++;
                Vector<String> v = new Vector<>();

                v.add(rs4.getString("subject") + " " + rs4.getString("grade") + " " + rs4.getString("teacher.name") + " " + rs4.getString("class.desc"));
                v.add(rs4.getString("class_attendance.date"));
                v.add(rs4.getString("class_attendance.time"));

                dtm.addRow(v);
            }

            jLabel27.setText(String.valueOf(i));

        } catch (SQLException ex) {
            ex.printStackTrace();
            warning(ex);
        }

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

    public viewStudentPayment() {
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

        buttonGroup1 = new javax.swing.ButtonGroup();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel26 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();

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
                "CLASS ID", "CLASS", "REG_DATE", "FEE", "NOTE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(10);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(50);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(10);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(200);
        }

        jTable2.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CLASS", "PAYING", "DATE", "ADJUSTED BY"
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
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setPreferredWidth(300);
            jTable2.getColumnModel().getColumn(1).setPreferredWidth(50);
            jTable2.getColumnModel().getColumn(2).setPreferredWidth(80);
            jTable2.getColumnModel().getColumn(3).setPreferredWidth(80);
        }

        jTable3.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable3.setOpaque(false);
        jTable3.setShowVerticalLines(false);
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);
        if (jTable3.getColumnModel().getColumnCount() > 0) {
            jTable3.getColumnModel().getColumn(0).setPreferredWidth(350);
        }

        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel26.setText("0");

        jLabel16.setText("records found  ");

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel27.setText("0");

        jLabel17.setText("records found  ");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Payments");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/images/basic/print_32px.png"))); // NOI18N
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/images/basic/print_32px.png"))); // NOI18N
        jLabel19.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
        });

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/images/basic/print_32px.png"))); // NOI18N
        jLabel20.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
        });

        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel28.setText("0");

        jLabel21.setText("records found  ");

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel22.setText("Student");

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel23.setText("Classes");

        jRadioButton1.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Attendance");
        jRadioButton1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton1ItemStateChanged(evt);
            }
        });

        jRadioButton2.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jRadioButton2.setText("Leaving");
        jRadioButton2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton2ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel16))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel20))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 658, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 631, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel17)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jRadioButton1)
                                .addGap(10, 10, 10)
                                .addComponent(jRadioButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addGap(17, 17, 17))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addGap(727, 727, 727)
                                .addComponent(jLabel19))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel21))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 820, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel20)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(jLabel21))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jRadioButton1)
                        .addComponent(jRadioButton2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jLabel17)
                    .addComponent(jLabel26)
                    .addComponent(jLabel16))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked

        int selectedRow = jTable2.getSelectedRow();

        if (selectedRow > -1) {

            if (evt.getClickCount() == 2) {
//           /     new SelectPayingMonth(this, jTable2.getValueAt(selectedRow, 0).toString(), selectedRow, theme).setVisible(true);
            }
        }
    }//GEN-LAST:event_jTable2MouseClicked

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked


    }//GEN-LAST:event_jTable3MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked

        int r1 = JOptionPane.showConfirmDialog(this, "Are you sure do you want to print Report", "Conform", JOptionPane.INFORMATION_MESSAGE);

        if (r1 == 0) {
            String title = null;
            if (jRadioButton1.isSelected()) {
                title = "STUDENT ATTENDANCE REPORT";
            } else {
                title = "STUDENT LEAVING REPORT";
            }

            try {
                String path = "src//reports//studentAttendance.jasper";

                HashMap<String, Object> map = new HashMap<>();
                map.put("Parameter1", institute.get("name").toUpperCase());
                map.put("Parameter2", institute.get("address"));
                map.put("Parameter3", institute.get("phone1"));
                map.put("Parameter4", institute.get("phone2"));
                map.put("Parameter5", institute.get("fax"));
                map.put("Parameter6", title);
                map.put("Parameter7", institute.get("website"));
                map.put("Parameter8", jLabel12.getText());
                map.put("Parameter9", jLabel11.getText());
                map.put("Parameter10", jLabel9.getText());
                map.put("Parameter13", institute.get("thank_msg"));

                TableModel model = jTable3.getModel();

                JRTableModelDataSource tabelDataSource = new JRTableModelDataSource(model);

                JasperPrint print = JasperFillManager.fillReport(path, map, tabelDataSource);

                JasperViewer.viewReport(print, false);

            } catch (JRException ex) {
                ex.printStackTrace();
                warning(ex);
            }

        }


    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked

        int r1 = JOptionPane.showConfirmDialog(this, "Are you sure do you want to print Report", "Conform", JOptionPane.INFORMATION_MESSAGE);

        if (r1 == 0) {

            try {
                String path = "src//reports//studentCLass.jasper";

                HashMap<String, Object> map = new HashMap<>();
                map.put("Parameter1", institute.get("name").toUpperCase());
                map.put("Parameter2", institute.get("address"));
                map.put("Parameter3", institute.get("phone1"));
                map.put("Parameter4", institute.get("phone2"));
                map.put("Parameter5", institute.get("fax"));
                map.put("Parameter6", "STUDENT REGISTERD CLASSES");
                map.put("Parameter7", institute.get("website"));
                map.put("Parameter8", jLabel12.getText());
                map.put("Parameter9", jLabel11.getText());
                map.put("Parameter10", jLabel9.getText());
                map.put("Parameter13", institute.get("thank_msg"));

                TableModel model = jTable1.getModel();

                JRTableModelDataSource tabelDataSource = new JRTableModelDataSource(model);

                JasperPrint print = JasperFillManager.fillReport(path, map, tabelDataSource);

                JasperViewer.viewReport(print, false);

            } catch (JRException ex) {
                ex.printStackTrace();
                warning(ex);
            }

        }


    }//GEN-LAST:event_jLabel19MouseClicked

    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked

        int r1 = JOptionPane.showConfirmDialog(this, "Are you sure do you want to print Report", "Conform", JOptionPane.INFORMATION_MESSAGE);

        if (r1 == 0) {

            try {
                String path = "src//reports//studentPaymentHistory.jasper";

                HashMap<String, Object> map = new HashMap<>();
                map.put("Parameter1", institute.get("name").toUpperCase());
                map.put("Parameter2", institute.get("address"));
                map.put("Parameter3", institute.get("phone1"));
                map.put("Parameter4", institute.get("phone2"));
                map.put("Parameter5", institute.get("fax"));
                map.put("Parameter6", "PAYMENT HISTORY");
                map.put("Parameter7", institute.get("website"));
                map.put("Parameter8", jLabel12.getText());
                map.put("Parameter9", jLabel11.getText());
                map.put("Parameter10", jLabel9.getText());
                map.put("Parameter13", institute.get("thank_msg"));

                TableModel model = jTable2.getModel();

                JRTableModelDataSource tabelDataSource = new JRTableModelDataSource(model);

                JasperPrint print = JasperFillManager.fillReport(path, map, tabelDataSource);

                JasperViewer.viewReport(print, false);

            } catch (JRException ex) {
                ex.printStackTrace();
                warning(ex);
            }

        }

    }//GEN-LAST:event_jLabel20MouseClicked

    private void jRadioButton1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton1ItemStateChanged

        if (classId == null) {

            try {
                System.out.println("4");
                String q4 = "SELECT \n"
                        + "	* \n"
                        + "\n"
                        + "FROM \n"
                        + "	class_attendance\n"
                        + "	INNER JOIN student_class \n"
                        + "	INNER JOIN class\n"
                        + "	INNER JOIN teacher\n"
                        + "	INNER JOIN subject\n"
                        + "	INNER JOIN grade\n"
                        + "	\n"
                        + "WHERE \n"
                        + "	class_attendance.student_class_id = student_class.id\n"
                        + "	&& student_class.class_id = class.id\n"
                        + "	&& class.subject_id = subject.id\n"
                        + "	&& class.teacher_id = teacher.id\n"
                        + "	&& subject.grade_id = grade.id\n"
                        + "	\n"
                        + "	&& class_attendance.student_id = '" + stId + "'";

                ResultSet rs4 = search(q4);
                TableModel model = jTable3.getModel();
                DefaultTableModel dtm = (DefaultTableModel) model;
                dtm.setRowCount(0);

                int i = 0;

                while (rs4.next()) {
                    i++;
                    Vector<String> v = new Vector<>();

                    v.add(rs4.getString("subject") + " " + rs4.getString("grade") + " " + rs4.getString("teacher.name") + " " + rs4.getString("class.desc"));
                    v.add(rs4.getString("class_attendance.date"));
                    v.add(rs4.getString("class_attendance.time"));

                    dtm.addRow(v);
                }

                jLabel27.setText(String.valueOf(i));

            } catch (SQLException ex) {
                ex.printStackTrace();
                warning(ex);
            }

        } else {

            try {
                System.out.println("4");
                String q4 = "SELECT \n"
                        + "	* \n"
                        + "\n"
                        + "FROM \n"
                        + "	class_attendance\n"
                        + "	INNER JOIN student_class \n"
                        + "	INNER JOIN class\n"
                        + "	INNER JOIN teacher\n"
                        + "	INNER JOIN subject\n"
                        + "	INNER JOIN grade\n"
                        + "	\n"
                        + "WHERE \n"
                        + "	class_attendance.student_class_id = student_class.id\n"
                        + "	&& student_class.class_id = class.id\n"
                        + "	&& class.subject_id = subject.id\n"
                        + "	&& class.teacher_id = teacher.id\n"
                        + "	&& subject.grade_id = grade.id\n"
                        + "	\n"
                        + "	&& class_attendance.student_id = '" + stId + "'"
                        + "	&& class.id = '" + classId + "'";

                ResultSet rs4 = search(q4);
                TableModel model = jTable3.getModel();
                DefaultTableModel dtm = (DefaultTableModel) model;
                dtm.setRowCount(0);

                int i = 0;

                while (rs4.next()) {
                    i++;
                    Vector<String> v = new Vector<>();

                    v.add(rs4.getString("subject") + " " + rs4.getString("grade") + " " + rs4.getString("teacher.name") + " " + rs4.getString("class.desc"));
                    v.add(rs4.getString("class_attendance.date"));
                    v.add(rs4.getString("class_attendance.time"));

                    dtm.addRow(v);
                }

                jLabel27.setText(String.valueOf(i));

            } catch (SQLException ex) {
                ex.printStackTrace();
                warning(ex);
            }

        }

    }//GEN-LAST:event_jRadioButton1ItemStateChanged

    private void jRadioButton2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton2ItemStateChanged

        if (classId == null) {

            try {
                System.out.println("4");
                String q4 = "SELECT \n"
                        + "	* \n"
                        + "\n"
                        + "FROM \n"
                        + "	class_leaving\n"
                        + "	INNER JOIN student_class \n"
                        + "	INNER JOIN class\n"
                        + "	INNER JOIN teacher\n"
                        + "	INNER JOIN subject\n"
                        + "	INNER JOIN grade\n"
                        + "	\n"
                        + "WHERE \n"
                        + "	class_leaving.student_class_id = student_class.id\n"
                        + "	&& student_class.class_id = class.id\n"
                        + "	&& class.subject_id = subject.id\n"
                        + "	&& class.teacher_id = teacher.id\n"
                        + "	&& subject.grade_id = grade.id\n"
                        + "	\n"
                        + "	&& class_leaving.student_id = '" + stId + "'";

                ResultSet rs4 = search(q4);
                TableModel model = jTable3.getModel();
                DefaultTableModel dtm = (DefaultTableModel) model;
                dtm.setRowCount(0);

                int i = 0;

                while (rs4.next()) {
                    i++;
                    Vector<String> v = new Vector<>();

                    v.add(rs4.getString("subject") + " " + rs4.getString("grade") + " " + rs4.getString("teacher.name") + " " + rs4.getString("class.desc"));
                    v.add(rs4.getString("class_leaving.date"));
                    v.add(rs4.getString("class_leaving.time"));

                    dtm.addRow(v);
                }

                jLabel27.setText(String.valueOf(i));

            } catch (SQLException ex) {
                ex.printStackTrace();
                warning(ex);
            }

        } else {

            try {
                System.out.println("4");
                String q4 = "SELECT \n"
                        + "	* \n"
                        + "\n"
                        + "FROM \n"
                        + "	class_leaving\n"
                        + "	INNER JOIN student_class \n"
                        + "	INNER JOIN class\n"
                        + "	INNER JOIN teacher\n"
                        + "	INNER JOIN subject\n"
                        + "	INNER JOIN grade\n"
                        + "	\n"
                        + "WHERE \n"
                        + "	class_leaving.student_class_id = student_class.id\n"
                        + "	&& student_class.class_id = class.id\n"
                        + "	&& class.subject_id = subject.id\n"
                        + "	&& class.teacher_id = teacher.id\n"
                        + "	&& subject.grade_id = grade.id\n"
                        + "	\n"
                        + "	&& class_leaving.student_id = '" + stId + "'"
                        + "	&& class.id = '" + classId + "'";

                ResultSet rs4 = search(q4);
                TableModel model = jTable3.getModel();
                DefaultTableModel dtm = (DefaultTableModel) model;
                dtm.setRowCount(0);

                int i = 0;

                while (rs4.next()) {
                    i++;
                    Vector<String> v = new Vector<>();

                    v.add(rs4.getString("subject") + " " + rs4.getString("grade") + " " + rs4.getString("teacher.name") + " " + rs4.getString("class.desc"));
                    v.add(rs4.getString("class_leaving.date"));
                    v.add(rs4.getString("class_leaving.time"));

                    dtm.addRow(v);
                }

                jLabel27.setText(String.valueOf(i));

            } catch (SQLException ex) {
                ex.printStackTrace();
                warning(ex);
            }

        }
    }//GEN-LAST:event_jRadioButton2ItemStateChanged

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() == 2) {
            int selectedRow = jTable1.getSelectedRow();

            if (selectedRow > -1) {
                classId = jTable1.getValueAt(selectedRow, 0).toString();
                try {
                    System.out.println("3");
                    String q3 = "SELECT\n"
                            + "	subject.subject,\n"
                            + "	grade.grade,\n"
                            + "	teacher.name,\n"
                            + " student_class_fees.year,\n"
                            + "	month.month,\n"
                            + "	fees.date,\n"
                            + "	staff.fname\n"
                            + "\n"
                            + "FROM\n"
                            + "	fees\n"
                            + "	INNER JOIN student_class_fees\n"
                            + "	INNER JOIN staff\n"
                            + "	INNER JOIN month\n"
                            + "	INNER JOIN student_class\n"
                            + "	INNER JOIN class\n"
                            + "	INNER JOIN teacher\n"
                            + "	INNER JOIN subject\n"
                            + "	INNER JOIN grade\n"
                            + "\n"
                            + "WHERE\n"
                            + "	fees.staff_id = staff.id\n"
                            + "	&& student_class_fees.fees_id = fees.id\n"
                            + "	&& student_class_fees.month_id = month.id\n"
                            + "	&& student_class_fees.student_class_id = student_class.id\n"
                            + "	&& student_class.class_id = class.id\n"
                            + "	&& class.subject_id = subject.id\n"
                            + "	&& subject.grade_id = grade.id\n"
                            + "	&& class.teacher_id = teacher.id\n"
                            + "\n"
                            + "	&& fees.student_id = '" + stId + "'"
                            + "	&& class.id = '" + classId + "'";

                    ResultSet rs3 = search(q3);
                    TableModel model = jTable2.getModel();
                    DefaultTableModel dtm = (DefaultTableModel) model;
                    dtm.setRowCount(0);

                    int i = 0;

                    while (rs3.next()) {
                        i++;
                        Vector<String> v = new Vector<>();

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                        String d = sdf.format(rs3.getDate("year"));

                        v.add(rs3.getString("subject") + " " + rs3.getString("grade") + " " + rs3.getString("teacher.name"));
                        v.add(d + " " + rs3.getString("month"));
                        v.add(rs3.getString("fees.date"));
                        v.add(rs3.getString("staff.fname"));

                        dtm.addRow(v);
                    }

                    jLabel26.setText(String.valueOf(i));

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    warning(ex);
                }

                try {
                    System.out.println("4");
                    String q4 = "SELECT \n"
                            + "	* \n"
                            + "\n"
                            + "FROM \n"
                            + "	class_attendance\n"
                            + "	INNER JOIN student_class \n"
                            + "	INNER JOIN class\n"
                            + "	INNER JOIN teacher\n"
                            + "	INNER JOIN subject\n"
                            + "	INNER JOIN grade\n"
                            + "	\n"
                            + "WHERE \n"
                            + "	class_attendance.student_class_id = student_class.id\n"
                            + "	&& student_class.class_id = class.id\n"
                            + "	&& class.subject_id = subject.id\n"
                            + "	&& class.teacher_id = teacher.id\n"
                            + "	&& subject.grade_id = grade.id\n"
                            + "	\n"
                            + "	&& class_attendance.student_id = '" + stId + "'"
                            + "	&& class.id = '" + classId + "'";

                    ResultSet rs4 = search(q4);
                    TableModel model = jTable3.getModel();
                    DefaultTableModel dtm = (DefaultTableModel) model;
                    dtm.setRowCount(0);

                    int i = 0;

                    while (rs4.next()) {
                        i++;
                        Vector<String> v = new Vector<>();

                        v.add(rs4.getString("subject") + " " + rs4.getString("grade") + " " + rs4.getString("teacher.name") + " " + rs4.getString("class.desc"));
                        v.add(rs4.getString("class_attendance.date"));
                        v.add(rs4.getString("class_attendance.time"));

                        dtm.addRow(v);
                    }

                    jLabel27.setText(String.valueOf(i));

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    warning(ex);
                }

            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

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
            java.util.logging.Logger.getLogger(viewStudentPayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(viewStudentPayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(viewStudentPayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(viewStudentPayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new viewStudentPayment().setVisible(true);
            }
        });
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

    void themeLoad(String theme) {
        jPanel2.setBackground(new Color(Integer.parseInt(theme.split(",")[0]), Integer.parseInt(theme.split(",")[1]), Integer.parseInt(theme.split(",")[2])));
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/images/basic/" + theme + "/print_32px.png")));
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/images/basic/" + theme + "/print_32px.png")));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/images/basic/" + theme + "/print_32px.png")));
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
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
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    javax.swing.JTable jTable2;
    javax.swing.JTable jTable3;
    // End of variables declaration//GEN-END:variables
}
