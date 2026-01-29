package com.upb.agripos.auth;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.upb.agripos.util.DatabaseConnection;

public class AuthService {
    public User login(String username, String password) {
        System.out.println("--- MEMULAI PROSES LOGIN ---");
        System.out.println("Mencoba login dengan User: " + username);

        String sql = "SELECT role FROM users WHERE username = ? AND password = ?";

        try (Connection conn = DatabaseConnection.getConnection()) {
            if (conn == null) {
                System.out.println("[ERROR] KONEKSI NULL! Cek DatabaseConnection.java");
                return null;
            }
            System.out.println("[INFO] Koneksi Database Berhasil Terbuka");

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, username);
                ps.setString(2, password);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        String role = rs.getString("role");
                        System.out.println("[SUKSES] User Ditemukan! Role: " + role);
                        return new User(username, role);
                    } else {
                        System.out.println("[GAGAL] User tidak ditemukan atau Password salah.");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("[CRITICAL ERROR] Terjadi kesalahan database:");
            e.printStackTrace(); // Ini akan mencetak error merah di terminal
        }
        return null;
    }
}
