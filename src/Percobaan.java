
import javax.swing.SwingUtilities;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.sql.Blob;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author acer
 */
public class Percobaan extends javax.swing.JFrame {

    private Blob gambar;

    public Blob getGambar() {
        return gambar;
    }

    public void setGambar(Blob gambar) {
        this.gambar = gambar;
    }
    public Percobaan() {
        initComponents();
        SwingUtilities.invokeLater(() -> {
            // Frame utama
            JFrame Percobaan = new JFrame("Menu Makanan dari Database");
            Percobaan.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Percobaan.setSize(800, 600);
            Percobaan.setLayout(new BorderLayout());

            // Panel utama dengan GridLayout
            JPanel gridPanel = new JPanel(new GridLayout(0, 2, 10, 10)); // 2 kolom, baris dinamis
            gridPanel.setBackground(new Color(245, 245, 245));

            // Koneksi ke database dan ambil data
            List<MenuItem> menuList = fetchMenuData();

            // Looping untuk menambahkan panel menu ke gridPanel
            for (MenuItem menu : menuList) {
                // Buat panel untuk satu menu
                JPanel menuPanel = new JPanel();
                menuPanel.setLayout(new BorderLayout());
                //menuPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
                menuPanel.setBackground(new Color(0, 0, 0, 0));

                // Gambar menu
                JLabel imgLabel = new JLabel();
                if (menu.getImageBytes() != null) {
                    // Konversi byte array menjadi ImageIcon
                    ImageIcon icon = new ImageIcon(menu.getImageBytes());
                    imgLabel.setIcon(new ImageIcon(icon.getImage().getScaledInstance(144, 144, Image.SCALE_SMOOTH)));
                } else {
                    imgLabel.setText("No Image");
                    imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
                }
                // Informasi menu
                JLabel nameLabel = new JLabel("<html><b>Nama menu: " + menu.getName() + "</b></html>");
                //nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
                JLabel stockLabel = new JLabel("Stok: " + menu.getStock());
                JLabel codeLabel = new JLabel("KM: " + menu.getCode());
                JLabel priceLabel = new JLabel("HJ: Rp. " + menu.getPrice());

                // Panel informasi
                JPanel infoPanel = new JPanel(new GridLayout(5, 1));
                infoPanel.add(nameLabel);
                infoPanel.add(stockLabel);
                infoPanel.add(codeLabel);
                infoPanel.add(priceLabel);
                infoPanel.setBackground(new Color(102, 0, 0, 25));

                // Tambahkan komponen ke panel menu
                menuPanel.add(imgLabel, BorderLayout.NORTH);
                //menuPanel.add(nameLabel, BorderLayout.CENTER);
                menuPanel.add(infoPanel, BorderLayout.SOUTH);

                // Tambahkan panel menu ke grid panel
                gridPanel.add(menuPanel);
            }

            // Tambahkan grid panel ke frame
            JScrollPane scrollPane = new JScrollPane(gridPanel);
            Percobaan.add(scrollPane, BorderLayout.CENTER);

            // Tampilkan frame
            Percobaan.setVisible(true);
        });

    }

    private static List<MenuItem> fetchMenuData() {
        List<MenuItem> menuList = new ArrayList<>();
        String url = "jdbc:mysql://localhost:8111/db_kasir"; // Ganti dengan nama database Anda
        String user = "root"; // Ganti dengan username database Anda
        String password = ""; // Ganti dengan password database Anda

        String query = "SELECT * FROM menu where kategori = 'makanan'";

        try (Connection conn = DriverManager.getConnection(url, user, password); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String name = rs.getString("nama_menu");
                int stock = rs.getInt("stok_menu");
                String code = rs.getString("kd_menu");
                int price = rs.getInt("harga_menu");

                // Ambil data gambar sebagai Blob
                Blob blob = rs.getBlob("gbr_menu");
                byte[] imageBytes = blob.getBytes(1, (int) blob.length()); // Konversi Blob ke byte array

                menuList.add(new MenuItem(name, stock, code, price, imageBytes));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching data from database: " + e.getMessage());
        }

        return menuList;
    }

    static class MenuItem {

        private final String name;
        private final int stock;
        private final String code;
        private final int price;
        private final byte[] imageBytes;

        public MenuItem(String name, int stock, String code, int price, byte[] imageBytes) {
            this.name = name;
            this.stock = stock;
            this.code = code;
            this.price = price;
            this.imageBytes = imageBytes;
        }

        public String getName() {
            return name;
        }

        public int getStock() {
            return stock;
        }

        public String getCode() {
            return code;
        }

        public int getPrice() {
            return price;
        }

        public byte[] getImageBytes() {
            return imageBytes;
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Percobaan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Percobaan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Percobaan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Percobaan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Percobaan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
