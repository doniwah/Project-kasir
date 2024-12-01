package form_pendukung;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author acer
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Config {

    private static Connection mysqlconnect;

    public static Connection ConfigDB() throws SQLException {
        try {
            String url = "jdbc:mysql://localhost:8111/db_kasir";
            String user = "root";
            String password = "";
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            mysqlconnect = DriverManager.getConnection(url, user, password);

        } catch (Exception e) {
            System.err.println("Koneksi gagal" + e.getMessage());
        }
        return mysqlconnect;
    }
}
