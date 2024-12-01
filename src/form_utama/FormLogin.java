package form_utama;


import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.ui.FlatBorder;

import javax.swing.*;
import java.awt.*;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.Connection;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class FormLogin extends javax.swing.JFrame {

    private String a;
    private String b;
    private String c;

    private String n;

    public FormLogin() {
        initComponents();
        FlatLightLaf.setup();

        UIManager.put("Button.arc", 50);
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> {
            text_nama.setVisible(true);
            text_password.setVisible(true);
            check_box.setVisible(true);
            panelcustom1.setVisible(true);
        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        kiri = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        kiri1 = new javax.swing.JPanel();
        panelcustom1 = new form_utama.panelcustom();
        jLabel2 = new javax.swing.JLabel();
        text_nama = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        text_password = new javax.swing.JPasswordField();
        check_box = new javax.swing.JCheckBox();
        posisi = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        kiri.setBackground(new java.awt.Color(164, 192, 239));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/logo warung djoglo sri terbaru PUTIH.png"))); // NOI18N

        javax.swing.GroupLayout kiriLayout = new javax.swing.GroupLayout(kiri);
        kiri.setLayout(kiriLayout);
        kiriLayout.setHorizontalGroup(
            kiriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kiriLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        kiriLayout.setVerticalGroup(
            kiriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kiriLayout.createSequentialGroup()
                .addGap(148, 148, 148)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(156, Short.MAX_VALUE))
        );

        getContentPane().add(kiri, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 370, 490));

        kiri1.setBackground(new java.awt.Color(255, 255, 255));

        panelcustom1.setBackground(new java.awt.Color(164, 192, 239));
        panelcustom1.setRoundBottomLeft(20);
        panelcustom1.setRoundBottomRight(20);
        panelcustom1.setRoundTopLeft(20);
        panelcustom1.setRoundTopRight(20);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Username");

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Password");

        check_box.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        check_box.setForeground(new java.awt.Color(255, 255, 255));
        check_box.setText("show password");
        check_box.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_boxActionPerformed(evt);
            }
        });

        posisi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        posisi.setForeground(new java.awt.Color(164, 192, 239));
        posisi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Posisi", "Owner", "Kasir" }));

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(164, 192, 239));
        jButton1.setText("Login");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelcustom1Layout = new javax.swing.GroupLayout(panelcustom1);
        panelcustom1.setLayout(panelcustom1Layout);
        panelcustom1Layout.setHorizontalGroup(
            panelcustom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelcustom1Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(panelcustom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelcustom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelcustom1Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelcustom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(posisi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(102, 102, 102))
                        .addGroup(panelcustom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(check_box)
                            .addGroup(panelcustom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(text_password)
                                .addComponent(text_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelcustom1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(224, 224, 224)))
                .addContainerGap())
        );
        panelcustom1Layout.setVerticalGroup(
            panelcustom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelcustom1Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(text_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(text_password, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(check_box, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(posisi, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout kiri1Layout = new javax.swing.GroupLayout(kiri1);
        kiri1.setLayout(kiri1Layout);
        kiri1Layout.setHorizontalGroup(
            kiri1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kiri1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelcustom1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );
        kiri1Layout.setVerticalGroup(
            kiri1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kiri1Layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addComponent(panelcustom1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );

        getContentPane().add(kiri1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 0, 370, 490));

        setSize(new java.awt.Dimension(756, 496));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            String sql = "SELECT * FROM user WHERE username ='" + text_nama.getText()
            + "'AND password ='" + text_password.getText() + "'" + "AND posisi = '" + posisi.getSelectedItem() + "'";

            Connection konek = new Config().ConfigDB();
            java.sql.PreparedStatement pst = konek.prepareStatement(sql);

            java.sql.ResultSet rs = pst.executeQuery(sql);
            String selectedRole = (String) posisi.getSelectedItem();
            if (rs.next()) {
                System.out.println("database berhasil");

                //ormMenu menu = new FormMenu();
                //menu.setVisible(true);
                Untukposisi(a, b);

                FormMenu menuForm = new FormMenu(a, b);
                //FormMenu menubaru = new FormMenu();
                menuForm.setVisible(true);
                this.setVisible(false);

            } else {
                JOptionPane.showMessageDialog(null, "nama atau password salah");
                if (selectedRole.equals("Pilih Posisi")) {

                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void check_boxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_boxActionPerformed
        if (check_box.isSelected()) {
            text_password.setEchoChar((char) 0);
        } else {
            text_password.setEchoChar('*');
        }
    }//GEN-LAST:event_check_boxActionPerformed

    public void Untukposisi(String i, String n) {
        i = (String) posisi.getSelectedItem();
        a = i;
        n = text_nama.getText();
        b = n;
    }

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox check_box;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel kiri;
    private javax.swing.JPanel kiri1;
    private form_utama.panelcustom panelcustom1;
    private javax.swing.JComboBox<String> posisi;
    private javax.swing.JTextField text_nama;
    private javax.swing.JPasswordField text_password;
    // End of variables declaration//GEN-END:variables
}
