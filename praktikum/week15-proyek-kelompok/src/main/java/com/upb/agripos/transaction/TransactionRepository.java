package com.upb.agripos.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.upb.agripos.util.DatabaseConnection;

public class TransactionRepository {
    public void save(String id, double total, String cashier) {
        String sql = "INSERT INTO transactions (id, total_amount, cashier_username) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.setDouble(2, total);
            ps.setString(3, cashier);
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}