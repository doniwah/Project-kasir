package form_utama;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import com.raven.chart.ModelChart;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
//import com.raven.chart.ModelChart;
//import java.awt.Color;
/**
 *
 * @author acer
 */
public class FormLaporan extends javax.swing.JPanel {

    /**
     * Creates new form FormLaporan
     */
    public FormLaporan() throws FontFormatException, IOException {
        initComponents();

        setOpaque(false);
        File fontFile = new File("src/fonts/Kanit-SemiBold.ttf"); // Sesuaikan path
        Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(20f); // Ukuran 24
        jLabel3.setFont(customFont);
        jLabel4.setFont(customFont);
        jLabel5.setFont(customFont);
        panel_utama.setBackground(new Color(255, 255, 255, 0));
        scroll.setBackground(new Color(255, 255, 255, 0));
        //getContentPane().setBackground(new Color(250, 250, 250));
        chart.addLegend("Pengeluaran", new Color(245, 189, 135));
        chart.addLegend("Pendapatan Kotor", new Color(135, 189, 245));
        chart.addLegend("Pendapatan Bersih", new Color(189, 135, 245));

        try {
            String sql = "SELECT transaksi_jual.hari, sum(transaksi_jual.total_transaksijual) as penghasilan_kotor, sum(transaksi_beli.total_transaksibeli) as pengeluaran"
                    + " FROM transaksi_jual, transaksi_beli group by hari ORDER BY FIELD(hari, 'Senin', 'Selasa', 'Rabu', 'Kamis', 'Jumat', 'Sabtu', 'Minggu')";
            Connection konek = new Config().ConfigDB();
            java.sql.PreparedStatement pst = konek.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery(sql);

            while (rs.next()) {
                String hari = rs.getString("hari");
                double pengeluaran = rs.getDouble("pengeluaran");
                double pendapatanKotor = rs.getDouble("penghasilan_kotor");
                double pendapatanBersih = rs.getDouble("penghasilan_kotor");

                chart.addData(new ModelChart(hari, new double[]{pengeluaran, pendapatanKotor, pendapatanBersih}));
            }
        } catch (Exception e) {
        }

//        chart.addData(new ModelChart("Senin", new double[]{500, 200, 80}));
//        chart.addData(new ModelChart("Selasa", new double[]{600, 750, 90}));
//        chart.addData(new ModelChart("Rabu", new double[]{200, 350, 460}));
//        chart.addData(new ModelChart("Kamis", new double[]{480, 150, 750}));
//        chart.addData(new ModelChart("Jumat", new double[]{350, 540, 300}));
//        chart.addData(new ModelChart("Sabtu", new double[]{190, 280, 81}));
//        init();
    }

//    private void buatGrafikPenghasilan() {
//     // Buat dataset kosong
//    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//
//    // Buat grafik tipe bar
//    JFreeChart barChart = ChartFactory.createBarChart(
//            "Grafik Penghasilan", // Judul grafik
//            "Hari", // Label sumbu X
//            "Penghasilan (Rp)", // Label sumbu Y
//            dataset // Data untuk grafik
//    );
//
//    CategoryPlot plot = barChart.getCategoryPlot(); // Dapatkan plot dari grafik
//    BarRenderer renderer = (BarRenderer) plot.getRenderer();
//    renderer.setSeriesPaint(0, new Color(255, 255, 255, 150)); // Warna batang
//    renderer.setMaximumBarWidth(0.1); // Atur lebar batang
//
//    // Transparansi untuk panel
//    barChart.setBackgroundPaint(null); // Transparan
//    barChart.getPlot().setBackgroundPaint(null); // Latar belakang plot transparan
//    barChart.getPlot().setOutlinePaint(null); // Hilangkan garis batas plot
//
//    // Masukkan grafik ke dalam JPanel
//    ChartPanel chartPanel = new ChartPanel(barChart);
//    chartPanel.setBackground(null); // Latar belakang panel transparan
//    chartPanel.setOpaque(false); // Hilangkan opacity panel
//    chartPanel.setPreferredSize(panel_grafik.getSize()); // Sesuaikan ukuran
//    panel_grafik.setLayout(new java.awt.BorderLayout());
//    panel_grafik.add(chartPanel, java.awt.BorderLayout.CENTER);
//    panel_grafik.validate();
//
//    // Jalankan animasi untuk mengisi dataset
//    jalankanAnimasiTinggiBatang(dataset, chartPanel);
//
//    }
//
//   private void jalankanAnimasiTinggiBatang(DefaultCategoryDataset dataset, ChartPanel chartPanel) {
//    try {
//        String sql = "SELECT hari, sum(total_transaksijual) as penghasilan FROM transaksi_jual group by hari ORDER BY FIELD(hari, 'Senin', 'Selasa', 'Rabu', 'Kamis', 'Jumat', 'Sabtu', 'Minggu')";
//        Connection konek = new Config().ConfigDB();
//        java.sql.PreparedStatement pst = konek.prepareStatement(sql);
//        java.sql.ResultSet rs = pst.executeQuery(sql);
//
//        List<String> hariList = new ArrayList<>();
//        List<Double> targetList = new ArrayList<>();
//        List<Double> currentList = new ArrayList<>();
//
//        while (rs.next()) {
//            hariList.add(rs.getString("hari"));
//            double targetValue = rs.getDouble("penghasilan");
//            targetList.add(targetValue);
//            currentList.add(0.0);
//        }
//        konek.close();
//
//        // Gunakan AnimationTimer untuk animasi yang lebih halus
//        final long startTime = System.currentTimeMillis();
//        final long animationDuration = 2000; // Durasi animasi 2 detik
//
//        Timer timer = new Timer(50, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                long elapsed = System.currentTimeMillis() - startTime;
//                double progress = Math.min(1.0, (double) elapsed / animationDuration);
//
//                // Interpolasi easing untuk animasi lebih mulus
//                double easedProgress = 1 - Math.pow(1 - progress, 4);
//
//                boolean allDone = true;
//                for (int i = 0; i < hariList.size(); i++) {
//                    double targetValue = targetList.get(i);
//                    double currentValue = targetValue * easedProgress;
//                    dataset.setValue(currentValue, "Pendapatan Setiap Harinya", hariList.get(i));
//                    
//                    if (progress < 1.0) {
//                        allDone = false;
//                    }
//                }
//
//                chartPanel.repaint();
//
//                if (allDone) {
//                    ((Timer) e.getSource()).stop();
//                }
//            }
//        });
//        timer.start();
//
//    } catch (Exception e) {
//        e.printStackTrace();
//    }
//}
//private void init() {
//        chart.addLegend("Pengeluaran", new Color(12, 84, 175), new Color(0, 108, 247));
//        chart.addData(new ModelChart("Senin", new double[]{500, 200, 80, 89}));
//        chart.addData(new ModelChart("Selasa", new double[]{600, 750, 90, 150}));
//        chart.addData(new ModelChart("Rabu", new double[]{200, 350, 460, 900}));
//        chart.addData(new ModelChart("Kamis", new double[]{480, 150, 750, 700}));
//        chart.addData(new ModelChart("Jumat", new double[]{350, 540, 300, 150}));
//        chart.addData(new ModelChart("Sabtu", new double[]{190, 280, 81, 200}));
//        chart.start();
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        scroll = new javax.swing.JScrollPane();
        panel_utama = new form_utama.panelcustom();
        panelcustom2 = new form_utama.panelcustom();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        panel_pengeluaran = new form_utama.panelcustom();
        jLabel3 = new javax.swing.JLabel();
        panel_grapik = new form_utama.panelcustom();
        chart = new com.raven.chart.Chart();
        panel_bersih = new form_utama.panelcustom();
        jLabel5 = new javax.swing.JLabel();
        panel_kotor = new form_utama.panelcustom();
        jLabel4 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        bg = new javax.swing.JLabel();

        setLayout(new java.awt.CardLayout());

        jPanel1.setMaximumSize(new java.awt.Dimension(990, 770));
        jPanel1.setMinimumSize(new java.awt.Dimension(990, 770));
        jPanel1.setPreferredSize(new java.awt.Dimension(990, 770));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        scroll.setBorder(null);

        panel_utama.setBackground(new java.awt.Color(255, 255, 255));
        panel_utama.setRoundBottomLeft(15);
        panel_utama.setRoundBottomRight(15);
        panel_utama.setRoundTopLeft(15);
        panel_utama.setRoundTopRight(15);
        panel_utama.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelcustom2.setBackground(new java.awt.Color(164, 192, 239));
        panelcustom2.setRoundBottomLeft(15);
        panelcustom2.setRoundBottomRight(15);
        panelcustom2.setRoundTopLeft(15);
        panelcustom2.setRoundTopRight(15);
        panelcustom2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel6.setText("I.Transaksi Penjualan");
        panelcustom2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, -1));

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 34)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("DETAIL PENJUALAN DAN PENGELUARAN");
        panelcustom2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, -1, -1));

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel9.setText("Total Pendapatan Penjualan: Rp ");
        panelcustom2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 390, -1, -1));

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel8.setText("Menu Makanan dan Minuman yang Terjual");
        panelcustom2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, -1, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        panelcustom2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 560, 809, 218));

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel10.setText("II.Transaksi Pembelian Bahan Baku");
        panelcustom2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 510, -1, -1));

        jLabel11.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel11.setText("List Bahan Baku yang Dibeli");
        panelcustom2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 480, -1, -1));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable2);

        panelcustom2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 809, 218));

        jLabel12.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel12.setText("______________________________________________________________________________");
        panelcustom2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 420, -1, -1));

        jLabel13.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel13.setText("Total Pendapatan Pembelian: Rp ");
        panelcustom2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 790, -1, -1));

        panel_utama.add(panelcustom2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 460, 920, 830));

        panel_pengeluaran.setBackground(new java.awt.Color(245, 189, 135));
        panel_pengeluaran.setRoundBottomLeft(65);
        panel_pengeluaran.setRoundBottomRight(65);
        panel_pengeluaran.setRoundTopLeft(65);
        panel_pengeluaran.setRoundTopRight(65);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Pengeluran");

        javax.swing.GroupLayout panel_pengeluaranLayout = new javax.swing.GroupLayout(panel_pengeluaran);
        panel_pengeluaran.setLayout(panel_pengeluaranLayout);
        panel_pengeluaranLayout.setHorizontalGroup(
            panel_pengeluaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_pengeluaranLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        panel_pengeluaranLayout.setVerticalGroup(
            panel_pengeluaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_pengeluaranLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(94, Short.MAX_VALUE))
        );

        panel_utama.add(panel_pengeluaran, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 30, 240, 120));

        panel_grapik.setBackground(new java.awt.Color(164, 192, 239));
        panel_grapik.setRoundBottomLeft(15);
        panel_grapik.setRoundBottomRight(15);
        panel_grapik.setRoundTopLeft(15);
        panel_grapik.setRoundTopRight(15);
        panel_grapik.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panel_grapik.add(chart, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 610, 360));

        panel_utama.add(panel_grapik, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 630, 380));

        panel_bersih.setBackground(new java.awt.Color(189, 135, 245));
        panel_bersih.setPreferredSize(new java.awt.Dimension(240, 120));
        panel_bersih.setRoundBottomLeft(65);
        panel_bersih.setRoundBottomRight(65);
        panel_bersih.setRoundTopLeft(65);
        panel_bersih.setRoundTopRight(65);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Pendapatan Bersih");

        javax.swing.GroupLayout panel_bersihLayout = new javax.swing.GroupLayout(panel_bersih);
        panel_bersih.setLayout(panel_bersihLayout);
        panel_bersihLayout.setHorizontalGroup(
            panel_bersihLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_bersihLayout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        panel_bersihLayout.setVerticalGroup(
            panel_bersihLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_bersihLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(94, Short.MAX_VALUE))
        );

        panel_utama.add(panel_bersih, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 290, -1, -1));

        panel_kotor.setBackground(new java.awt.Color(135, 189, 245));
        panel_kotor.setPreferredSize(new java.awt.Dimension(240, 120));
        panel_kotor.setRoundBottomLeft(65);
        panel_kotor.setRoundBottomRight(65);
        panel_kotor.setRoundTopLeft(65);
        panel_kotor.setRoundTopRight(65);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Pendapatan Kotor");

        javax.swing.GroupLayout panel_kotorLayout = new javax.swing.GroupLayout(panel_kotor);
        panel_kotor.setLayout(panel_kotorLayout);
        panel_kotorLayout.setHorizontalGroup(
            panel_kotorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_kotorLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        panel_kotorLayout.setVerticalGroup(
            panel_kotorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_kotorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(94, Short.MAX_VALUE))
        );

        panel_utama.add(panel_kotor, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 160, -1, -1));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/batik azure.png"))); // NOI18N
        panel_utama.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 560));

        scroll.setViewportView(panel_utama);

        jPanel1.add(scroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 920, 560));
        jPanel1.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 180, 40));

        jLabel2.setText("Tanggal");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(164, 192, 239));
        jLabel1.setText("LAPORAN");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/batik azure.png"))); // NOI18N
        jPanel1.add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 990, 770));

        add(jPanel1, "card2");
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bg;
    private com.raven.chart.Chart chart;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private form_utama.panelcustom panel_bersih;
    private form_utama.panelcustom panel_grapik;
    private form_utama.panelcustom panel_kotor;
    private form_utama.panelcustom panel_pengeluaran;
    private form_utama.panelcustom panel_utama;
    private form_utama.panelcustom panelcustom2;
    private javax.swing.JScrollPane scroll;
    // End of variables declaration//GEN-END:variables
}
