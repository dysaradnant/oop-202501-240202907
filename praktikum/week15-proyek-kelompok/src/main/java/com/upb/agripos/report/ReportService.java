package com.upb.agripos.report;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.upb.agripos.util.DatabaseConnection;

public class ReportService {
    public double getDailyTurnover() {
        String sql = "SELECT SUM(total_amount) FROM transactions";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) return rs.getDouble(1);
        } catch (SQLException e) { e.printStackTrace(); }
        return 0;
    }
}
