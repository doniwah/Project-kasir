/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package form_pendukung;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Image;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.io.File;
import form_pendukung.Config;
import form_utama.warning;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Files;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author acer
 */
public class tambah_makanan extends javax.swing.JFrame {
    // TODO add your handling code here:

    private String a;
    private String b;
    private String kd_menu;
    private JFileChooser fileChooser;
    private JFileChooser fileChooser1;
    form_pendukung.Config koneksi = new Config();
    private boolean fungsi = false;
    private boolean fungsi1 = false;
    private File selectedFile;

    private File selectedFile1;
    private Timer timer;
    private int targetY;
    private int currentY;
    private int currentX;
    private int targetX;
    private int speed = 10;

    public tambah_makanan() {
        initComponents();
        setBackground(new Color(0, 0, 0, 0));
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> {
            text_nama.setVisible(true);
            text_kd1.setVisible(true);
            text_harga.setVisible(true);
            t_path.setVisible(true);
            b_paht.setVisible(true);
            b_clear.setVisible(true);
            b_simpan.setVisible(true);
            b_keluar.setVisible(true);
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

    public void setAnimationSpeed(int speed) {
        this.speed = speed; // Makin kecil makin cepat
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
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        b_paht = new javax.swing.JButton();
        text_harga = new javax.swing.JTextField();
        text_kd1 = new javax.swing.JTextField();
        t_path = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        b_keluar = new javax.swing.JButton();
        text_nama = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        b_clear = new javax.swing.JButton();
        b_simpan = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelcustom1.setBackground(new java.awt.Color(164, 192, 239));
        panelcustom1.setRoundBottomLeft(20);
        panelcustom1.setRoundBottomRight(20);
        panelcustom1.setRoundTopLeft(20);
        panelcustom1.setRoundTopRight(20);

        jLabel4.setBackground(new java.awt.Color(164, 192, 239));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("KD_Makanan");

        jLabel3.setBackground(new java.awt.Color(164, 192, 239));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tambah Menu");

        b_paht.setText("................");
        b_paht.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_pahtActionPerformed(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(164, 192, 239));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Harga Makanan");

        b_keluar.setText("KEMBALI");
        b_keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_keluarActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(164, 192, 239));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nama Makanan");

        jLabel6.setBackground(new java.awt.Color(164, 192, 239));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Pilih gambar makanan");

        b_clear.setText("CLEAR");

        b_simpan.setText("SIMPAN");
        b_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_simpanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelcustom1Layout = new javax.swing.GroupLayout(panelcustom1);
        panelcustom1.setLayout(panelcustom1Layout);
        panelcustom1Layout.setHorizontalGroup(
            panelcustom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelcustom1Layout.createSequentialGroup()
                .addGroup(panelcustom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelcustom1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel3))
                    .addGroup(panelcustom1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(panelcustom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(text_kd1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(text_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(text_harga, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addGroup(panelcustom1Layout.createSequentialGroup()
                                .addGroup(panelcustom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(t_path, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(b_keluar))
                                .addGap(30, 30, 30)
                                .addGroup(panelcustom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelcustom1Layout.createSequentialGroup()
                                        .addComponent(b_clear)
                                        .addGap(31, 31, 31)
                                        .addComponent(b_simpan))
                                    .addComponent(b_paht, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel4))))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        panelcustom1Layout.setVerticalGroup(
            panelcustom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelcustom1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel3)
                .addGap(26, 26, 26)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(text_kd1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel2)
                .addGap(3, 3, 3)
                .addComponent(text_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel5)
                .addGap(3, 3, 3)
                .addComponent(text_harga, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel6)
                .addGap(3, 3, 3)
                .addGroup(panelcustom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t_path, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_paht, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(panelcustom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_keluar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        getContentPane().add(panelcustom1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 560, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void b_pahtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_pahtActionPerformed
        fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter imageFilter = new FileNameExtensionFilter(
                "Image files", "jpg", "jpeg", "png");
        fileChooser.addChoosableFileFilter(imageFilter);
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            t_path.setText(path);
        }

    }//GEN-LAST:event_b_pahtActionPerformed

    public void simpankedatabase() {
        kd_menu = text_kd1.getText();
        String nama_menu = text_nama.getText();
        int harga_menu = Integer.parseInt(text_harga.getText());
        String status_menu = "Habis";
        String kategori = "Makanan";
        try {

            String queryInsert = "INSERT INTO menu "
                    + "(kd_menu, nama_menu, stok_menu, harga_menu, status_menu, gbr_menu, kategori) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement stSimpan = koneksi.ConfigDB().prepareStatement(queryInsert);

            stSimpan.setString(1, kd_menu);
            stSimpan.setString(2, nama_menu);        // kd_menu
            stSimpan.setInt(3, 0);   // nama_menu
            stSimpan.setInt(4, harga_menu);        // kd_user
            stSimpan.setString(5, status_menu);  // nama_kasir
            stSimpan.setString(6, null);
            stSimpan.setString(7, kategori); // tanggal   // jumlah_beli   // kembalian

            stSimpan.executeUpdate();
            //JOptionPane.showMessageDialog(null, "Menu Berhasil ditambahkan");
            prosesGambar(selectedFile);
            //refresTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal menyimpan: " + e.getMessage());
            e.printStackTrace();
        }

    }

    private void prosesGambar(File file) {
        try {
            // Baca file sebagai byte array
            byte[] imageBytes = Files.readAllBytes(file.toPath());

            // Buat ImageIcon untuk preview
            ImageIcon imageIcon = new ImageIcon(imageBytes);

            // Resize gambar jika terlalu besar
            if (imageIcon.getIconWidth() > 300) {
                Image image = imageIcon.getImage();
                Image newimg = image.getScaledInstance(300, -1, java.awt.Image.SCALE_SMOOTH);
                imageIcon = new ImageIcon(newimg);
            }

            // Tampilkan preview (opsional)
//            tampilkanPreview(imageIcon);
            // Simpan ke database
            simpanKeDatabase(imageBytes, file.getName());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error memproses gambar: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void simpanKeDatabase(byte[] imageBytes, String fileName) {
        // Gunakan try-with-resources untuk koneksi database
        try (
                Connection conn = (Connection) Config.ConfigDB(); PreparedStatement pstmt = conn.prepareStatement("UPDATE menu SET gbr_menu = ? WHERE kd_menu = '" + kd_menu + "'")) {

            pstmt.setBytes(1, imageBytes);
// Pastikan currentId sudah dideklarasikan

            int result = pstmt.executeUpdate();
            if (result > 0) {
                
                try {
                    this.setVisible(false);
                    this.dispose();
                    NotifTambahMenu a = new NotifTambahMenu();
                    a.setVisible(true);
                    a.setAnimationSpeed(8);
                    a.showSlideUp();
                } catch (Exception e) {
                }

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Error menyimpan ke database: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void b_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_simpanActionPerformed
        simpankedatabase();
    }//GEN-LAST:event_b_simpanActionPerformed

    private void b_keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_keluarActionPerformed

        this.setVisible(false);
        form_utama.FormMenu menu = new form_utama.FormMenu(a, b);
        menu.setVisible(false);
        menu.setVisible(true);
    }//GEN-LAST:event_b_keluarActionPerformed

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
            java.util.logging.Logger.getLogger(tambah_makanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(tambah_makanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(tambah_makanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(tambah_makanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new tambah_makanan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_clear;
    private javax.swing.JButton b_keluar;
    private javax.swing.JButton b_paht;
    private javax.swing.JButton b_simpan;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private form_pendukung.panelcustom panelcustom1;
    private javax.swing.JTextField t_path;
    private javax.swing.JTextField text_harga;
    private javax.swing.JTextField text_kd1;
    private javax.swing.JTextField text_nama;
    // End of variables declaration//GEN-END:variables
}
