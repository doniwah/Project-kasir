package form_utama;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
/**
 *
 * @author acer
 */
public class Penjualan extends javax.swing.JPanel {
    private String n;
    private DefaultTableModel model = null;
    //private Connection koneksi = (Connection) Config.ConfigDB();
    private PreparedStatement statement;
    private ResultSet resultSet;
    Config k = new Config();
    public int harga, jumlahBeli, totalBayar, uangBayar, kembalian;
    public String namaKasir, tanggal, namaMenu, kdMenu, kdUser;

 
    public Penjualan(String ia) {
        initComponents();
        n = ia;
        
        TransaksiJual transaksi = new TransaksiJual();
        transaksi.refresComboMenu();
        transaksi.refresComboKasir();
        refresTable();
    }

    public class TransaksiJual {

        // Method untuk menghitung total
        public void hitungTotal() {
            try {
                // Validasi input
                namaMenu = text_menu.getSelectedItem().toString();
                namaKasir = text_kasir.getText();

                getKdMenu();
                getKdUser();

                // Validasi tanggal
                Date date = text_tanggal.getDate();
                if (date == null) {
                    throw new Exception("Pilih tanggal terlebih dahulu");
                }

                SimpleDateFormat formatTanggal = new SimpleDateFormat("yyyy-MM-dd");
                tanggal = formatTanggal.format(date);

                // Debug untuk memeriksa input jumlah
                System.out.println("Isi text_jumlah: " + text_jumlah.getText());

                // Validasi input jumlah
                try {
                    jumlahBeli = Integer.parseInt(text_jumlah.getText()); // Ambil jumlah dari input
                    if (jumlahBeli <= 0) {
                        throw new Exception("Jumlah beli harus lebih besar dari 0");
                    }
                } catch (NumberFormatException e) {
                    throw new Exception("Masukkan angka yang valid untuk jumlah beli");
                }

                // Ambil harga dari database
                String queryHarga = "SELECT harga_menu FROM menu WHERE nama_menu = ?";
                PreparedStatement stHarga = k.ConfigDB().prepareStatement(queryHarga);
                stHarga.setString(1, namaMenu);
                ResultSet rsHarga = stHarga.executeQuery();

                if (rsHarga.next()) {
                    harga = rsHarga.getInt("harga_menu");
                } else {
                    throw new Exception("Harga menu tidak ditemukan");
                }

                // Hitung total
                totalBayar = harga * jumlahBeli; // Hitung total bayar
                if (totalBayar <= 0) {
                    throw new Exception("Total bayar tidak valid");
                }

                // Tampilkan total
                text_total.setText(String.valueOf(totalBayar));

                System.out.println("Jumlah beli sebelum dihitung: " + jumlahBeli);
                System.out.println("Total sebelum dihitung: " + totalBayar);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }

        }

        // Method untuk proses pembayaran
        public void prosesBayar() {
            try {
                // Debug: pastikan jumlahBeli terisi dengan benar
                System.out.println("Jumlah beli saat proses bayar: " + jumlahBeli);

                // Validasi input bayar
                if (text_bayar.getText().isEmpty()) {
                    throw new Exception("Input pembayaran tidak boleh kosong");
                }

                try {
                    uangBayar = Integer.parseInt(text_bayar.getText());
                } catch (NumberFormatException e) {
                    throw new Exception("Masukkan angka yang valid untuk pembayaran");
                }

                if (text_total.getText().isEmpty()) {
                    throw new Exception("Total pembayaran belum dihitung");
                }

                int total = Integer.parseInt(text_total.getText());
                // Hitung kembalian
                kembalian = uangBayar - total;

                // Validasi pembayaran
                if (kembalian < 0) {
                    throw new Exception("Uang bayar kurang");
                }

                // Tampilkan kembalian
                text_kembalian.setText("" + kembalian);

                System.out.println("Jumlah beli saat proses bayar: " + jumlahBeli);
                System.out.println("Total bayar saat proses bayar: " + totalBayar);

                // Simpan transaksi
                simpanTransaksi();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }

        public void refresComboMenu() {
            try {
                String query = "SELECT nama_menu FROM menu WHERE status_menu = 'Tersedia' OR status_menu = 'Hampir Habis'";
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

        public void refresComboKasir() {
            text_kasir.setText(n);
//            try {
//                // Query untuk memuat data kasir dari database
//                String query = "SELECT username FROM user WHERE posisi = 'Kasir'";
//                PreparedStatement st = k.ConfigDB().prepareStatement(query);
//                ResultSet rs = st.executeQuery();
//
//                //text_kasir.setText(""); // Hapus data sebelumnya dari combo box
//                while (rs.next()) {
//                    String username = rs.getString("username");
//                    text_kasir.se(username);
//                    System.out.println("Kasir ditambahkan ke combo box: " + username);
//                }
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, "Error memuat kasir: " + e.getMessage());
//                e.printStackTrace();
//            }
        }

        private void getKdMenu() {
            try {
                String query = "SELECT kd_menu FROM menu WHERE nama_menu = ?";
                PreparedStatement st = k.ConfigDB().prepareStatement(query);
                st.setString(1, namaMenu);
                ResultSet rs = st.executeQuery();

                if (rs.next()) {
                    kdMenu = rs.getString("kd_menu");
                    System.out.println("Kode menu ditemukan: " + kdMenu);
                } else {
                    JOptionPane.showMessageDialog(null, "Menu tidak ditemukan di database!");
                    kdMenu = null; // Pastikan null jika tidak ditemukan
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Gagal mengambil kode menu: " + e.getMessage());
                e.printStackTrace();
            }

        }

        private void getKdUser() {
            try {
                // Query untuk mendapatkan kode user berdasarkan username
                String query = "SELECT kd_user FROM user WHERE username = ?";
                PreparedStatement st = k.ConfigDB().prepareStatement(query);
                st.setString(1, namaKasir); // Ambil nama kasir dari combo box
                ResultSet rs = st.executeQuery();

                if (rs.next()) {
                    kdUser = rs.getString("kd_user");
                    System.out.println("Kode user ditemukan: " + kdUser);
                } else {
                    JOptionPane.showMessageDialog(null, "Kasir tidak ditemukan di database!");
                    kdUser = null; // Set kdUser ke null jika tidak ditemukan
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Gagal mengambil kode user: " + e.getMessage());
                e.printStackTrace();
            }

        }

        // Method untuk simpan transaksi (sama seperti sebelumnya)
        public void simpanTransaksi() {

            namaMenu = text_menu.getSelectedItem().toString(); // Dapatkan nama menu dari combo box
            getKdMenu(); // Ambil kode menu berdasarkan nama menu
            if (kdMenu == null) {
                JOptionPane.showMessageDialog(null, "Kode menu tidak ditemukan! Transaksi dibatalkan.");
                return;
            }

            namaKasir = text_kasir.getText();
            getKdUser();

            if (kdUser == null) {
                JOptionPane.showMessageDialog(null, "Kode user tidak ditemukan! Transaksi dibatalkan.");
                return; // Batalkan proses
            }

            try {
                // Ambil tanggal dari komponen input
                Date date = text_tanggal.getDate();
                if (date == null) {
                    JOptionPane.showMessageDialog(null, "Tanggal belum dipilih. Silakan pilih tanggal terlebih dahulu.");
                    return; // Batalkan proses
                }

                // Format tanggal untuk database
                SimpleDateFormat formatTanggal = new SimpleDateFormat("yyyy-MM-dd");
                tanggal = formatTanggal.format(date);
                System.out.println("Tanggal yang dipilih: " + tanggal); // Debug log

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error saat memproses tanggal: " + e.getMessage());
                e.printStackTrace();
            }
            try {

                System.out.println("Debug prosesBayar():");
                System.out.println("JUmlah " + jumlahBeli);
                System.out.println("Uang Bayar: " + uangBayar);
                System.out.println("Total: " + totalBayar);
                System.out.println("Kembalian: " + kembalian);

                String queryInsert = "INSERT INTO transaksi_jual "
                        + "(id_transaksijual, kd_menu, nama_menu, kd_user, nama_kasir, tgl, jumlah, total_transaksijual, uang_bayar, uang_kembalian) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                PreparedStatement stSimpan = k.ConfigDB().prepareStatement(queryInsert);

                stSimpan.setInt(1, 0);
                stSimpan.setString(2, kdMenu);        // kd_menu
                stSimpan.setString(3, namaMenu);   // nama_menu
                stSimpan.setString(4, kdUser);        // kd_user
                stSimpan.setString(5, namaKasir);  // nama_kasir
                stSimpan.setString(6, tanggal);    // tanggal
                stSimpan.setInt(7, jumlahBeli);    // jumlah_beli
                stSimpan.setInt(8, totalBayar);    // total_bayar
                stSimpan.setInt(9, uangBayar);     // uang_bayar
                stSimpan.setInt(10, kembalian);     // kembalian

                stSimpan.executeUpdate();
                JOptionPane.showMessageDialog(null, "Transaksi berhasil disimpan");

                refresTable();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Gagal menyimpan: " + e.getMessage());
                e.printStackTrace();
            }
        }

    }

    private void resetForm() {
        text_jumlah.setText("");
        text_bayar.setText("");
        text_total.setText("");
        text_kembalian.setText("");
        text_menu.setSelectedIndex(0);
        text_tanggal.setDate(null);
    }

    private void refresTable() {

        Object[] baris = {"ID Transaksi", "Nama Menu", "Nama Kasir", "Tanggal", "Jumlah Beli", "Total Harga", "Uang Bayar", "Total Kembalian"};
        model = new DefaultTableModel(null, baris);
        table_transaksi.setModel(model);
        String sql = "select * from transaksi_jual order by id_transaksijual asc";
        try {
            Connection konek = new Config().ConfigDB();
            Statement stat = konek.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                String id = hasil.getString("id_transaksijual");
                String nama_menu = hasil.getString("nama_menu");
                String nama_kasir = hasil.getString("nama_kasir");
                String tanggal = hasil.getString("tgl");
                String jumlah = hasil.getString("jumlah");
                String total = hasil.getString("total_transaksijual");
                String uang = hasil.getString("uang_bayar");
                String kembalian = hasil.getString("uang_kembalian");
                String[] data = {id, nama_menu, nama_kasir, tanggal, jumlah, total, uang, kembalian};
                model.addRow(data);

                text_jumlah.setText("");
                text_bayar.setText("");
                text_total.setText("");
                text_kembalian.setText("");
                text_menu.setSelectedIndex(0);
                text_tanggal.setDate(null);
            }
            konek.close();
        } catch (Exception e) {

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

        panel_utama = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        text_menu = new javax.swing.JComboBox<>();
        text_tanggal = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        text_jumlah = new javax.swing.JTextField();
        text_total = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        text_bayar = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        text_kembalian = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_transaksi = new javax.swing.JTable();
        text_kasir = new javax.swing.JTextField();

        setLayout(new java.awt.CardLayout());

        panel_utama.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Nama Menu");
        panel_utama.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, -1));

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton1.setForeground(new java.awt.Color(164, 192, 239));
        jButton1.setText("TRANSAKSI JUAL");
        jButton1.setEnabled(false);
        panel_utama.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 33, -1, 42));

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton2.setForeground(new java.awt.Color(164, 192, 239));
        jButton2.setText("TRANSAKSI BELI");
        panel_utama.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(318, 33, -1, 42));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Total");
        panel_utama.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 150, -1, -1));

        text_menu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        panel_utama.add(text_menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 227, 40));
        panel_utama.add(text_tanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 227, 40));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Tanggal");
        panel_utama.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Jumlah");
        panel_utama.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 200, -1, -1));
        panel_utama.add(text_jumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 230, 218, 40));

        text_total.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        text_total.setEnabled(false);
        panel_utama.add(text_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 180, 204, 90));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Nama Kasir");
        panel_utama.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 100, -1, -1));

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton3.setForeground(new java.awt.Color(164, 192, 239));
        jButton3.setText("Hitung");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        panel_utama.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 230, -1, 40));

        text_bayar.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        panel_utama.add(text_bayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 640, 163, 52));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Masukkan Uang");
        panel_utama.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 610, -1, -1));

        text_kembalian.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        text_kembalian.setEnabled(false);
        panel_utama.add(text_kembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 640, 163, 52));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Kembalian");
        panel_utama.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 610, -1, -1));

        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton4.setForeground(new java.awt.Color(164, 192, 239));
        jButton4.setText("Batal");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        panel_utama.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 640, 112, 52));

        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton7.setForeground(new java.awt.Color(164, 192, 239));
        jButton7.setText("Bayar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        panel_utama.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 640, 127, 52));

        table_transaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(table_transaksi);

        panel_utama.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 901, 282));

        text_kasir.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        text_kasir.setEnabled(false);
        panel_utama.add(text_kasir, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 130, 220, 40));

        add(panel_utama, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        TransaksiJual transaksi = new TransaksiJual();
        transaksi.hitungTotal();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        TransaksiJual transaksi = new TransaksiJual();
        transaksi.prosesBayar();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (text_jumlah.equals("") && text_bayar.equals("") && text_total.equals("") && text_kembalian.equals("")) {

        } else {
            text_jumlah.setText("");
            text_bayar.setText("");
            text_total.setText("");
            text_kembalian.setText("");
            text_menu.setSelectedIndex(0);
            text_tanggal.setDate(null);
        }
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panel_utama;
    private javax.swing.JTable table_transaksi;
    private javax.swing.JTextField text_bayar;
    private javax.swing.JTextField text_jumlah;
    private javax.swing.JTextField text_kasir;
    private javax.swing.JTextField text_kembalian;
    private javax.swing.JComboBox<String> text_menu;
    private com.toedter.calendar.JDateChooser text_tanggal;
    private javax.swing.JTextField text_total;
    // End of variables declaration//GEN-END:variables
}
