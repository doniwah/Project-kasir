/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package form_pendukung;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;

/**
 *
 * @author acer
 */
public class stok_makanan extends javax.swing.JFrame {

    form_utama.Config k = new form_utama.Config();
    private Timer timer;
    private int targetY;
    private int currentY;
    private int currentX;
    private int targetX;
    private int speed = 10;

    public stok_makanan() {
        initComponents();
        refresComboMenu();
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> {
            text_menu.setVisible(true);
            text_stok.setVisible(true);
            jButton2.setVisible(true);
            jButton1.setVisible(true);
        });
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
    String a;
    String b;

    private void TambahStok() {
        String namaMenu = text_menu.getSelectedItem().toString();
        String stokStr = text_stok.getText().trim();

        if (namaMenu.isEmpty() || stokStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama menu dan jumlah stok harus diisi!");
            return;
        }

        try {
            int tambahStok = Integer.parseInt(stokStr);
            java.sql.Connection con = (Connection) form_utama.Config.ConfigDB();
            // Cek apakah menu ada di database
            PreparedStatement checkStmt = con.prepareStatement(
                    "SELECT stok_menu FROM menu WHERE nama_menu = ?"
            );
            checkStmt.setString(1, namaMenu);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                // Update stok jika menu ditemukan
                int stokLama = rs.getInt("stok_menu");
                int stokBaru = stokLama + tambahStok;

                PreparedStatement updateStmt = con.prepareStatement(
                        "UPDATE menu SET stok_menu = ? WHERE nama_menu = ?"
                );
                updateStmt.setInt(1, stokBaru);
                updateStmt.setString(2, namaMenu);
                updateStmt.executeUpdate();

                //coba.setVisible();
                //JOptionPane.showMessageDialog(this, "Stok berhasil ditambahkan!");
                //parentFrame.loadMenuData(); // Refresh tampilan menu
                form_utama.FormMenu menu = new form_utama.FormMenu(a, b);
                menu.setVisible(false);
                menu.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Menu tidak ditemukan!");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Jumlah stok harus berupa angka!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Gagal menambah stok!");
        }
    }

    public void refresComboMenu() {
        try {
            String query = "SELECT nama_menu FROM menu where kategori = 'makanan'";
            PreparedStatement st = k.ConfigDB().prepareStatement(query);
            ResultSet rs = st.executeQuery();
            text_menu.removeAllItems(); // Hapus data sebelumnya
            while (rs.next()) {
                String namaMenu = rs.getString("nama_menu");
                text_menu.addItem(namaMenu);
                System.out.println("Menu ditambahkan ke combo box: " + namaMenu);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error memuat menu: " + e.getMessage());
            e.printStackTrace();
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

        panelcustom1 = new form_pendukung.panelcustom();
        text_menu = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        text_stok = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panelcustom1.setBackground(new java.awt.Color(164, 192, 239));
        panelcustom1.setRoundBottomLeft(10);
        panelcustom1.setRoundBottomRight(10);
        panelcustom1.setRoundTopLeft(10);
        panelcustom1.setRoundTopRight(10);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Nama Menu");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tambah Stok");

        jButton1.setText("Tambah");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Batal");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelcustom1Layout = new javax.swing.GroupLayout(panelcustom1);
        panelcustom1.setLayout(panelcustom1Layout);
        panelcustom1Layout.setHorizontalGroup(
            panelcustom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelcustom1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(panelcustom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelcustom1Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelcustom1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(text_stok))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelcustom1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addComponent(text_menu, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16))
        );
        panelcustom1Layout.setVerticalGroup(
            panelcustom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelcustom1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelcustom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(text_menu, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(38, 38, 38)
                .addGroup(panelcustom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(text_stok, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addGroup(panelcustom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelcustom1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelcustom1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        TambahStok();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(stok_makanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(stok_makanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(stok_makanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(stok_makanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new stok_makanan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private form_pendukung.panelcustom panelcustom1;
    private javax.swing.JComboBox<String> text_menu;
    private javax.swing.JTextField text_stok;
    // End of variables declaration//GEN-END:variables
}
