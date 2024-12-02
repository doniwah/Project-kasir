/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package form_utama;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.AbstractBorder;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.DocumentEvent;

public class Minuman extends javax.swing.JPanel {

    JPanel gridPanel = new JPanel(new GridLayout(0, 2, 10, 10));
    private Blob gambar;

    public Blob getGambar() {
        return gambar;
    }

    public void setGambar(Blob gambar) {
        this.gambar = gambar;
    }

    public Minuman() {
        initComponents();
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> {
            text_cari.setVisible(true);
            //btn_cari.setVisible(true);
            btn_mkn.setVisible(true);
            btn_mnm.setVisible(true);
            btn_tambah.setVisible(true);

            // Panel utama dengan GridLayout
            // 2 kolom, baris dinamis
            gridPanel.setBackground(new Color(245, 245, 245));

            // Koneksi ke database dan ambil data
            List<Minuman.MenuItem> menuList = fetchMenuData();

            // Looping untuk menambahkan panel menu ke gridPanel
            for (Minuman.MenuItem menu : menuList) {
                // Panel utama untuk satu menu
                JPanel menuPanel = new JPanel();
                menuPanel.setLayout(new BorderLayout(10, 0)); // Spasi horizontal antara komponen
                menuPanel.setBackground(new Color(255, 255, 255, 200));
                menuPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding

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

                // Panel informasi di sebelah kanan gambar
                JPanel infoPanel = new JPanel();
                infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS)); // Tumpuk secara vertikal
                infoPanel.setBackground(new Color(245, 245, 245));

                // Informasi menu
                JLabel nameLabel = new JLabel("<html><b>Nama menu: " + menu.getName() + "</b></html>");
                JLabel stockLabel = new JLabel("Stok: " + menu.getStock());
                JLabel codeLabel = new JLabel("KM: " + menu.getCode());
                JLabel priceLabel = new JLabel("HJ: Rp. " + menu.getPrice());

                // Tambahkan label ke infoPanel
                infoPanel.add(nameLabel);
                infoPanel.add(Box.createVerticalStrut(5)); // Spasi antar elemen
                infoPanel.add(stockLabel);
                infoPanel.add(Box.createVerticalStrut(5));
                infoPanel.add(codeLabel);
                infoPanel.add(Box.createVerticalStrut(5));
                infoPanel.add(priceLabel);

                // Tambahkan komponen ke menuPanel
                menuPanel.add(imgLabel, BorderLayout.WEST); // Gambar di sebelah kiri
                menuPanel.add(infoPanel, BorderLayout.CENTER); // Informasi di sebelah kanan

                // Tambahkan menuPanel ke gridPanel
                gridPanel.add(menuPanel);
            }

            // Tambahkan gridPanel ke mainPanel
            mainPanel.removeAll(); // Bersihkan isi mainPanel sebelumnya
            mainPanel.setLayout(new BorderLayout());
            JScrollPane scrollPane = new JScrollPane(gridPanel);
            mainPanel.add(scrollPane, BorderLayout.CENTER);
            mainPanel.revalidate(); // Update tampilan
            mainPanel.repaint();   // Refresh tampilan
        });

        panel_utama.setOpaque(false);
        panel_utama.setBorder(new FormMenu.RoundedBorder(10));

    }

    private static List<MenuItem> fetchMenuData() {
        List<MenuItem> menuList = new ArrayList<>();
        String url = "jdbc:mysql://localhost:8111/db_kasir"; // Ganti dengan nama database Anda
        String user = "root"; // Ganti dengan username database Anda
        String password = ""; // Ganti dengan password database Anda

        String query = "SELECT * FROM menu WHERE kategori = 'minuman'";

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

    private void displayFilteredMenu(String keyword) {
        // Panel utama dengan GridLayout
        JPanel gridPanel = new JPanel(new GridLayout(0, 2, 10, 10)); // 2 kolom, baris dinamis
        gridPanel.setBackground(new Color(245, 245, 245));

        // Filter data berdasarkan keyword
        List<MenuItem> filteredMenu = fetchMenuData().stream()
                .filter(menu -> menu.getName().toLowerCase().contains(keyword.toLowerCase()))
                .toList();

        // Looping untuk menambahkan panel menu ke gridPanel
        for (MenuItem menu : filteredMenu) {
            // Panel utama untuk satu menu
            JPanel menuPanel = new JPanel();
            menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.X_AXIS)); // Horizontal layout
            menuPanel.setBackground(new Color(255, 255, 255, 200)); // Warna latar
            menuPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding luar

            // Gambar menu
            JLabel imgLabel = new JLabel();
            if (menu.getImageBytes() != null) {
                ImageIcon icon = new ImageIcon(menu.getImageBytes());
                imgLabel.setIcon(new ImageIcon(icon.getImage().getScaledInstance(144, 144, Image.SCALE_SMOOTH)));
            } else {
                imgLabel.setText("No Image");
                imgLabel.setHorizontalAlignment(SwingConstants.CENTER); // Gambar di tengah horizontal
            }
            imgLabel.setPreferredSize(new Dimension(144, 144)); // Ukuran tetap untuk gambar
            imgLabel.setAlignmentY(Component.CENTER_ALIGNMENT); // Pastikan sejajar vertikal
            menuPanel.add(imgLabel); // Tambahkan gambar ke menuPanel

            // Panel informasi di sebelah kanan
            JPanel infoPanel = new JPanel();
            infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS)); // Teks vertikal
            infoPanel.setBackground(new Color(245, 245, 245)); // Warna latar teks
            infoPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0)); // Padding di sebelah kiri

            // Informasi menu
            JLabel nameLabel = new JLabel("<html><b>Nama menu: " + menu.getName() + "</b></html>");
            JLabel stockLabel = new JLabel("Stok: " + menu.getStock());
            JLabel codeLabel = new JLabel("KM: " + menu.getCode());
            JLabel priceLabel = new JLabel("HJ: Rp. " + menu.getPrice());

            // Tambahkan label ke infoPanel
            nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            stockLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            codeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            priceLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

            infoPanel.add(nameLabel);
            infoPanel.add(Box.createVerticalStrut(5)); // Jarak antar elemen
            infoPanel.add(stockLabel);
            infoPanel.add(Box.createVerticalStrut(5));
            infoPanel.add(codeLabel);
            infoPanel.add(Box.createVerticalStrut(5));
            infoPanel.add(priceLabel);

            // Tambahkan infoPanel ke menuPanel
            menuPanel.add(infoPanel);

            // Tambahkan menuPanel ke gridPanel
            gridPanel.add(menuPanel);
        }

        // Tambahkan gridPanel ke mainPanel
        mainPanel.removeAll(); // Bersihkan isi mainPanel sebelumnya
        mainPanel.setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(gridPanel);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.revalidate(); // Update tampilan
        mainPanel.repaint();   // Refresh tampilan
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

    static class RoundedBorder extends AbstractBorder {

        private final int radius;

        public RoundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(c.getBackground());
            g2.fillRoundRect(x, y, width - 1, height - 1, radius, radius);
            g2.setColor(Color.GRAY); // Warna border
            g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(radius + 1, radius + 1, radius + 1, radius + 1);
        }

        @Override
        public boolean isBorderOpaque() {
            return false;
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

        panel = new javax.swing.JPanel();
        panel_utama = new javax.swing.JPanel();
        mainPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btn_mkn1 = new javax.swing.JButton();
        text_cari = new javax.swing.JTextField();
        btn_mkn = new javax.swing.JButton();
        btn_mnm = new javax.swing.JButton();
        btn_tambah1 = new javax.swing.JButton();
        btn_tambah = new javax.swing.JButton();
        bg = new javax.swing.JLabel();

        setLayout(new java.awt.CardLayout());

        panel.setLayout(new java.awt.CardLayout());

        panel_utama.setBackground(new java.awt.Color(255, 255, 255));
        panel_utama.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panel_utama.add(mainPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, 790, 410));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(164, 192, 239));
        jLabel2.setText("Menu Makanan");
        panel_utama.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, -1, -1));

        btn_mkn1.setBackground(new java.awt.Color(164, 192, 239));
        btn_mkn1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btn_mkn1.setForeground(new java.awt.Color(255, 255, 255));
        btn_mkn1.setText("Cari");
        btn_mkn1.setBorderPainted(false);
        btn_mkn1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_mkn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_mkn1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_mkn1MouseExited(evt);
            }
        });
        btn_mkn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_mkn1ActionPerformed(evt);
            }
        });
        panel_utama.add(btn_mkn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 80, 40));

        text_cari.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                text_cariMouseEntered(evt);
            }
        });
        text_cari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                text_cariKeyTyped(evt);
            }
        });
        panel_utama.add(text_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 120, 610, 40));

        btn_mkn.setBackground(new java.awt.Color(164, 192, 239));
        btn_mkn.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btn_mkn.setForeground(new java.awt.Color(255, 255, 255));
        btn_mkn.setText("Makanan");
        btn_mkn.setBorderPainted(false);
        btn_mkn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_mkn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_mknMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_mknMouseExited(evt);
            }
        });
        btn_mkn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_mknActionPerformed(evt);
            }
        });
        panel_utama.add(btn_mkn, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 140, 40));

        btn_mnm.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btn_mnm.setForeground(new java.awt.Color(164, 192, 239));
        btn_mnm.setText("Minuman");
        btn_mnm.setBorderPainted(false);
        btn_mnm.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_mnm.setDoubleBuffered(true);
        btn_mnm.setEnabled(false);
        btn_mnm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_mnmMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_mnmMouseExited(evt);
            }
        });
        panel_utama.add(btn_mnm, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 210, 140, 40));

        btn_tambah1.setBackground(new java.awt.Color(164, 192, 239));
        btn_tambah1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_tambah1.setForeground(new java.awt.Color(255, 255, 255));
        btn_tambah1.setText("Tambah menu");
        btn_tambah1.setBorderPainted(false);
        btn_tambah1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_tambah1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_tambah1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_tambah1MouseExited(evt);
            }
        });
        btn_tambah1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambah1ActionPerformed(evt);
            }
        });
        panel_utama.add(btn_tambah1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 210, 130, 40));

        btn_tambah.setBackground(new java.awt.Color(164, 192, 239));
        btn_tambah.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_tambah.setForeground(new java.awt.Color(255, 255, 255));
        btn_tambah.setText("Tambah stok");
        btn_tambah.setBorderPainted(false);
        btn_tambah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_tambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_tambahMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_tambahMouseExited(evt);
            }
        });
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });
        panel_utama.add(btn_tambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 210, 130, 40));

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/batik azure.png"))); // NOI18N
        panel_utama.add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 770));

        panel.add(panel_utama, "card2");

        add(panel, "card3");
    }// </editor-fold>//GEN-END:initComponents

    private void btn_mkn1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_mkn1MouseEntered
        btn_mkn1.setBackground(new Color(255, 255, 255));
        btn_mkn1.setForeground(new Color(164, 192, 239));
    }//GEN-LAST:event_btn_mkn1MouseEntered

    private void btn_mkn1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_mkn1MouseExited
        btn_mkn1.setBackground(new Color(164, 192, 239));
        btn_mkn1.setForeground(new Color(255, 255, 255));
    }//GEN-LAST:event_btn_mkn1MouseExited

    private void btn_mkn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_mkn1ActionPerformed
        String keyword = text_cari.getText().trim(); // Ambil teks dari text field
        displayFilteredMenu(keyword);
    }//GEN-LAST:event_btn_mkn1ActionPerformed

    private void text_cariMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_text_cariMouseEntered
        String keyword = text_cari.getText().trim(); // Ambil teks dari text field
        displayFilteredMenu(keyword);
    }//GEN-LAST:event_text_cariMouseEntered

    private void text_cariKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_text_cariKeyTyped

    }//GEN-LAST:event_text_cariKeyTyped

    private void btn_mknMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_mknMouseEntered
        btn_mkn.setBackground(new Color(255, 255, 255));
        btn_mkn.setForeground(new Color(164, 192, 239));
    }//GEN-LAST:event_btn_mknMouseEntered

    private void btn_mknMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_mknMouseExited
        btn_mkn.setBackground(new Color(164, 192, 239));
        btn_mkn.setForeground(new Color(255, 255, 255));
    }//GEN-LAST:event_btn_mknMouseExited

    private void btn_mnmMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_mnmMouseEntered
        // TODO add your handling code here:
        btn_mnm.setBackground(new Color(255, 255, 255));
        btn_mnm.setForeground(new Color(164, 192, 239));
    }//GEN-LAST:event_btn_mnmMouseEntered

    private void btn_mnmMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_mnmMouseExited
        btn_mnm.setBackground(new Color(164, 192, 239));
        btn_mnm.setForeground(new Color(255, 255, 255));
    }//GEN-LAST:event_btn_mnmMouseExited

    private void btn_tambah1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_tambah1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_tambah1MouseEntered

    private void btn_tambah1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_tambah1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_tambah1MouseExited
    private String a;
    private String b;
    private void btn_tambah1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambah1ActionPerformed
        // TODO add your handling code here:
        FormMenu m = new FormMenu(a, b);
        m.dispose();
        form_pendukung.tambah_minuman a = new form_pendukung.tambah_minuman();
        a.setVisible(true);
        a.setAnimationSpeed(8);
        a.showSlideUp();
    }//GEN-LAST:event_btn_tambah1ActionPerformed

    private void btn_tambahMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_tambahMouseEntered
        btn_tambah.setBackground(new Color(255, 255, 255));
        btn_tambah.setForeground(new Color(164, 192, 239));
    }//GEN-LAST:event_btn_tambahMouseEntered

    private void btn_tambahMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_tambahMouseExited
        btn_tambah.setBackground(new Color(164, 192, 239));
        btn_tambah.setForeground(new Color(255, 255, 255));
    }//GEN-LAST:event_btn_tambahMouseExited

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        // TODO add your handling code here:
        form_pendukung.stok_minuman a = new form_pendukung.stok_minuman();
        a.setVisible(true);
        a.setAnimationSpeed(8);
        a.showSlideUp();
    }//GEN-LAST:event_btn_tambahActionPerformed

    private void btn_mknActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_mknActionPerformed
        panel.removeAll();
        panel.add(new Makanan());
        panel.repaint();
        panel.revalidate();
    }//GEN-LAST:event_btn_mknActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bg;
    private javax.swing.JButton btn_mkn;
    private javax.swing.JButton btn_mkn1;
    private javax.swing.JButton btn_mnm;
    private javax.swing.JButton btn_tambah;
    private javax.swing.JButton btn_tambah1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel panel;
    private javax.swing.JPanel panel_utama;
    private javax.swing.JTextField text_cari;
    // End of variables declaration//GEN-END:variables
}
