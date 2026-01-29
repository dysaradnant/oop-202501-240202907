package com.upb.agripos.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Pastikan nama database 'agripos_db' sesuai dengan yang ada di pgAdmin Anda
    private static final String URL = "jdbc:postgresql://localhost:5432/agripos01";
    private static final String USER = "postgres";

    // PERHATIAN: Ganti ini dengan password yang Anda pakai saat buka pgAdmin!
    private static final String PASS = "1234";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println("!!! GAGAL KONEKSI DATABASE !!!");
            System.out.println("Pesan Error: " + e.getMessage());
            return null;
        }
    }
}
