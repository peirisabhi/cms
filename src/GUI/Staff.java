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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
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
public class Staff extends javax.swing.JInternalFrame {

    /**
     * Creates new form DashBoard
     */
    
    HashMap<String, String> institute = new HashMap<String, String>();
    String theme;
    
    public Staff(String theme) {
        initComponents();
        this.theme = theme;
        BasicInternalFrameTitlePane titlePane = (BasicInternalFrameTitlePane) ((BasicInternalFrameUI) this.getUI()).getNorthPane();
        this.remove(titlePane);
        this.setLocation(0, -25);
        
        tableDesign();
        tableLoad();
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        setBorder(null);
        setPreferredSize(new java.awt.Dimension(1010, 570));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NAME", "NIC", "ADDRESS", "EMAIL", "CONTACT", "POSITION"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setOpaque(false);
        jTable1.setShowVerticalLines(false);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(10);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(120);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(50);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(50);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(30);
        }

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/images/basic/edit_32px.png"))); // NOI18N
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/images/basic/add_32px.png"))); // NOI18N
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/images/basic/delete_bin_32px.png"))); // NOI18N
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/images/basic/print_32px.png"))); // NOI18N
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/images/basic/send_32px.png"))); // NOI18N
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Search Staff");

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("0");

        jLabel12.setText("records found  ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 394, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(31, 31, 31))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1002, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField1))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 511, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel12)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(52, 52, 52)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(23, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setBounds(0, 0, 1010, 595);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked

        int row = jTable1.getSelectedRow();

        if (row != -1) {

            ManageStaff mt = new ManageStaff(this, jTable1.getValueAt(row, 0).toString(), theme);
            mt.jButton1.setEnabled(false);
            mt.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(this, "Please Select user", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked

        ResultSet rs = search("SELECT MAX(id) AS id FROM staff;");

        String id = null;
        try {
            if (rs.next()) {
                if (rs.getString("id") != null) {
                    String[] x = rs.getString("id").split("-");
                    id = x[0] + "-" + String.valueOf(Integer.valueOf(x[1]) + 1);
                } else {
                    id = "EM-1";
                }

            }
        } catch (SQLException ex) {
            System.out.println(ex);
            warning(ex);
        }

        ManageStaff ms = new ManageStaff(this,theme);
        ms.jTextField1.setText(id);
        ms.jButton2.setEnabled(false);
        ms.setVisible(true);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked

        int row = jTable1.getSelectedRow();

        if (row != -1) {
            int r = JOptionPane.showConfirmDialog(this, "Are you sure do you want to delete this user", "Conform", JOptionPane.INFORMATION_MESSAGE);
            if (r == 0) {
                if (1 == iud("UPDATE staff SET `status`='0' WHERE  `id`='" + jTable1.getValueAt(row, 0).toString() + "';")) {
                    tableLoad();
                    JOptionPane.showMessageDialog(this, "Deleted", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            }

        } else {
            JOptionPane.showMessageDialog(this, "Please Select user", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        int row = jTable1.getSelectedRow();

        if (row != -1) {
            int r = JOptionPane.showConfirmDialog(this, "Do you want to send email to " + jTable1.getValueAt(row, 1) + " user", "Conform", JOptionPane.INFORMATION_MESSAGE);

            if (r == 0) {
                new StaffSendMail(jTable1.getValueAt(row, 4).toString(), theme).setVisible(true);
            }

        } else {
            int r = JOptionPane.showConfirmDialog(this, "Do you want to send email to all registerd user's", "Conform", JOptionPane.INFORMATION_MESSAGE);

            if (r == 0) {
                new StaffSendMail("All Users", theme).setVisible(true);
            }

        }
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        try {
            String path = "src//reports//staffs.jasper";

            HashMap<String, Object> map = new HashMap<>();
            map.put("Parameter1", institute.get("name").toUpperCase());
            map.put("Parameter2", institute.get("address"));
            map.put("Parameter3", institute.get("phone1"));
            map.put("Parameter4", institute.get("phone2"));
            map.put("Parameter5", institute.get("fax"));
            map.put("Parameter6", "ACTIVE EMPLOYEES");
            map.put("Parameter7", institute.get("thank_msg"));

            TableModel model = jTable1.getModel();

            JRTableModelDataSource tabelDataSource = new JRTableModelDataSource(model);

            JasperPrint print = JasperFillManager.fillReport(path, map, tabelDataSource);

            JasperViewer.viewReport(print, false);

        } catch (JRException ex) {
            ex.printStackTrace();
            warning(ex);
        }
    }//GEN-LAST:event_jLabel5MouseClicked

    void tableLoad() {

        TableModel model = jTable1.getModel();
        DefaultTableModel dtm = (DefaultTableModel) model;
        dtm.setRowCount(0);

        try {
            ResultSet rs = search("SELECT * FROM staff INNER JOIN position  WHERE status = '1' && staff.position_id = position.id");
            int i = 1;
            while (rs.next()) {
                Vector<String> v = new Vector<>();

                v.add(rs.getString("staff.id"));
                v.add(rs.getString("staff.fname") +" " +rs.getString("staff.lname"));
                v.add(rs.getString("staff.nic"));
                v.add(rs.getString("staff.address"));
                v.add(rs.getString("staff.email"));
                v.add(rs.getString("contact"));
                v.add(rs.getString("position"));

                dtm.addRow(v);

                jLabel18.setText(String.valueOf(i));
                i++;
            }

        } catch (Exception e) {
            System.out.println(e);
            warning(e);
        }

    }
    
    
    void tableDesign() {
        JTableHeader th = jTable1.getTableHeader();
        Font font = new Font("Century Gothic", Font.PLAIN, 12);
        th.setFont(font);
        th.setOpaque(false);
        Color cb = new Color(45, 52, 54);
        Color cf = new Color(241, 242, 246);
        th.setForeground(cf);
        th.setBackground(cb);

    }
    
    
    
      void themeLoad(String theme){
    
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/images/basic/"+theme+"/add_32px.png")));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/images/basic/"+theme+"/edit_32px.png")));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/images/basic/"+theme+"/delete_bin_32px.png")));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/images/basic/"+theme+"/send_32px.png")));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/images/basic/"+theme+"/print_32px.png")));
        
    }
    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}