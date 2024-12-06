package form_utama;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.ui.FlatBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        jLabel4 = new javax.swing.JLabel();

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

        text_nama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_namaActionPerformed(evt);
            }
        });

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

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Lupa Password?");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelcustom1Layout = new javax.swing.GroupLayout(panelcustom1);
        panelcustom1.setLayout(panelcustom1Layout);
        panelcustom1Layout.setHorizontalGroup(
            panelcustom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelcustom1Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(panelcustom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelcustom1Layout.createSequentialGroup()
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelcustom1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(124, 124, 124))))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(posisi, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addGap(8, 8, 8))
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
                .addContainerGap(40, Short.MAX_VALUE)
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
                    + "'AND password ='" + text_password.getText() + "'";

            Connection konek = new Config().ConfigDB();

            java.sql.PreparedStatement pst = konek.prepareStatement(sql);

            java.sql.ResultSet rs = pst.executeQuery(sql);
            String selectedRole = (String) posisi.getSelectedItem();
            if (rs.next()) {
                System.out.println("database berhasil");

                PreparedStatement checkStmt = konek.prepareStatement(
                        "SELECT posisi FROM user WHERE username ='" + text_nama.getText() + "'"
                );

                //java.sql.PreparedStatement pst1 = konek.prepareStatement(sql1);
                ResultSet rs1 = checkStmt.executeQuery();
                if (rs1.next()) {
                    String posisii = rs.getString("posisi");
                    if (selectedRole.equals(posisii)) {

                        Untukposisi(a, b);
                        FormMenu menuForm = new FormMenu(a, b);
                        //FormMenu menubaru = new FormMenu();
                        menuForm.setVisible(true);
                        this.setVisible(false);
                    } else {
                        form_pendukung.posisisalah b = new form_pendukung.posisisalah();
                        b.setVisible(true);
                        b.setAnimationSpeed(8);
                        b.showSlideUp();
                    }
                }

            } else {
                form_pendukung.loginsalah a = new form_pendukung.loginsalah();
                a.setVisible(true);
                a.setAnimationSpeed(8);
                a.showSlideUp();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    public class GantiPasswordFrame extends JFrame {

        JTextField txtUsername = new JTextField();
        private Timer timer;
        private int targetY;
        private int currentY;
        private int currentX;
        private int targetX;
        private int speed = 10;

        public GantiPasswordFrame() {
            // Pengaturan JFrame
            setUndecorated(true);
            setBackground(new Color(0, 0, 0, 0));
            setTitle("Ganti Password");
            setSize(400, 246);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);

            // Panel utama dengan sudut melengkung
            RoundedPanel mainPanel = new RoundedPanel(20); // Radius sudut 20
            mainPanel.setBackground(new Color(164, 192, 239)); // Warna biru muda
            mainPanel.setLayout(null); // Menggunakan layout absolute
            mainPanel.setOpaque(false);
            // Label judul
            JLabel lblTitle = new JLabel("Ganti Password");
            lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
            lblTitle.setBounds(120, 20, 200, 30); // Posisi judul
            mainPanel.add(lblTitle);

            // TextField Username
            txtUsername.setBounds(50, 70, 300, 40);
            txtUsername.setPreferredSize(new Dimension(77, 26));
            txtUsername.setText("Username");
            txtUsername.addFocusListener(new FocusAdapter() {
                public void focusGained(FocusEvent e) {
                    // Kosongkan text saat field mendapat fokus
                    if (txtUsername.getText().equals("Username")) {
                        txtUsername.setText("");
                        txtUsername.setForeground(Color.BLACK); // Mengubah warna teks menjadi hitam
                    }
                }

                public void focusLost(FocusEvent e) {
                    // Isi ulang teks jika tidak ada input
                    if (txtUsername.getText().isEmpty()) {
                        txtUsername.setText("Username");
                        txtUsername.setForeground(new Color(204, 204, 204)); // Warna abu-abu untuk teks placeholder
                    }
                }
            });
            mainPanel.add(txtUsername);

            // TextField Password Baru
            JTextField txtPasswordBaru = new JTextField();
            txtPasswordBaru.setBounds(50, 140, 300, 40);
            txtPasswordBaru.setPreferredSize(new Dimension(77, 26));
            txtPasswordBaru.setForeground(new Color(204, 204, 204));
            txtPasswordBaru.setText("Password Baru");
            txtPasswordBaru.addFocusListener(new FocusAdapter() {
                public void focusGained(FocusEvent e) {
                    // Kosongkan text saat field mendapat fokus
                    if (txtPasswordBaru.getText().equals("Password Baru")) {
                        txtPasswordBaru.setText("");
                        txtPasswordBaru.setForeground(Color.BLACK); // Mengubah warna teks menjadi hitam
                    }
                }

                public void focusLost(FocusEvent e) {
                    // Isi ulang teks jika tidak ada input
                    if (txtPasswordBaru.getText().isEmpty()) {
                        txtPasswordBaru.setText("Password Baru");
                        txtPasswordBaru.setForeground(new Color(204, 204, 204)); // Warna abu-abu untuk teks placeholder
                    }
                }
            });
            mainPanel.add(txtPasswordBaru);

            // Tombol Simpan
            JButton btnSave = new JButton("Simpan");
            btnSave.setBounds(150, 200, 100, 30);
            btnSave.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Kode aksi yang akan dijalankan
                    updatepw();
                    System.out.println("Tombol diklik!");
                }
            });
            mainPanel.add(btnSave);

            JButton batal = new JButton("Batal");
            batal.setBounds(150, 150, 100, 30);
            mainPanel.add(batal);

            // Tambahkan panel ke JFrame
            add(mainPanel);

        }

        public void updatepw() {
            try {
                String sql = "SELECT password FROM user WHERE username ='" + text_nama.getText();

                Connection konek = new Config().ConfigDB();

                java.sql.PreparedStatement pst = konek.prepareStatement(sql);

                java.sql.ResultSet rs = pst.executeQuery(sql);
                if (rs.next()) {
                    String username = rs.getString("username");

                    if (username.equals(txtUsername)) {
                        System.out.println("berhasil");
                    } else {
                        System.out.println("username tidak ada");
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }

        // Subclass JPanel untuk membuat rounded panel
        static class RoundedPanel extends JPanel {

            private int cornerRadius;

            public RoundedPanel(int cornerRadius) {
                this.cornerRadius = cornerRadius;
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Dimension arcs = new Dimension(cornerRadius, cornerRadius); // Radius sudut
                int width = getWidth();
                int height = getHeight();

                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Warna isi panel
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, width, height, arcs.width, arcs.height);

                // Warna border (opsional)
                g2.setColor(Color.GRAY);
                g2.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
            }
        }

        public void showSlideUp() {
            setupAnimation("UP");
            setVisible(true);
            timer.start();
        }
        
        
        

        private void setupAnimation(String direction) {
            // Dapatkan ukuran layar
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

            // Posisi horizontal di tengah
            currentX = (screenSize.width - getWidth()) / 2;
            targetX = currentX;

            switch (direction) {
                case "UP":
                    // Mulai dari bawah layar
                    currentY = screenSize.height;
                    // Target ke tengah layar
                    targetY = (screenSize.height - getHeight()) / 2;
                    break;
                case "DOWN":
                    // Mulai dari atas layar
                    currentY = -getHeight();
                    // Target ke tengah layar
                    targetY = (screenSize.height - getHeight()) / 2;
                    break;
            }

            // Set posisi awal form
            setLocation(currentX, currentY);

            // Buat timer untuk animasi
            timer = new Timer(1, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (currentY != targetY) {
                        // Hitung langkah perpindahan
                        int step = Math.abs(currentY - targetY) / speed + 1;

                        // Update posisi
                        if (currentY < targetY) {
                            currentY = Math.min(targetY, currentY + step);
                        } else {
                            currentY = Math.max(targetY, currentY - step);
                        }

                        // Set lokasi baru
                        setLocation(currentX, currentY);

                        // Hentikan timer jika sudah sampai target
                        if (currentY == targetY) {
                            timer.stop();
                        }
                    }
                }
            });
        }

        ;
      
      

         public void setAnimationSpeed(int speed) {
            this.speed = speed; // Makin kecil makin cepat
        }

    }


    private void check_boxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_boxActionPerformed
        if (check_box.isSelected()) {
            text_password.setEchoChar((char) 0);
        } else {
            text_password.setEchoChar('*');
        }
    }//GEN-LAST:event_check_boxActionPerformed

    private void text_namaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_namaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_namaActionPerformed

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        GantiPasswordFrame b = new GantiPasswordFrame();
        b.setVisible(true);
        b.setAnimationSpeed(8);
        b.showSlideUp();
    }//GEN-LAST:event_jLabel4MouseClicked

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
            java.util.logging.Logger.getLogger(FormLogin.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormLogin.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormLogin.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormLogin.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel kiri;
    private javax.swing.JPanel kiri1;
    private form_utama.panelcustom panelcustom1;
    private javax.swing.JComboBox<String> posisi;
    private javax.swing.JTextField text_nama;
    private javax.swing.JPasswordField text_password;
    // End of variables declaration//GEN-END:variables

}
