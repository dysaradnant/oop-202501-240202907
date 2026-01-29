package com.upb.agripos.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.upb.agripos.util.DatabaseConnection;

public class JdbcProductRepository implements ProductRepository {

    @Override
    public void addProduct(Product p) {
        String sql = "INSERT INTO products (kode, nama, kategori, harga, stok) VALUES (?, ?, ?, ?, ?)";
        executeSql(sql, p.getKode(), p.getNama(), p.getKategori(), p.getHarga(), p.getStok());
    }

    @Override
    public void updateProduct(Product p) {
        String sql = "UPDATE products SET nama=?, kategori=?, harga=?, stok=? WHERE kode=?";
        executeSql(sql, p.getNama(), p.getKategori(), p.getHarga(), p.getStok(), p.getKode());
    }

    @Override
    public void deleteProduct(String kode) {
        String sql = "DELETE FROM products WHERE kode = ?";
        executeSql(sql, kode);
    }

    @Override
    public void reduceStock(String kode, int qty) {
        // Logika untuk memotong stok saat kasir melakukan checkout
        String sql = "UPDATE products SET stok = stok - ? WHERE kode = ? AND stok >= ?";
        executeSql(sql, qty, kode, qty);
    }

    @Override
    public List<Product> listProducts() {
        List<Product> list = new ArrayList<>();
        // Mengambil data untuk mengisi tabel agar tidak kosong
        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM products ORDER BY kode ASC")) {
            while (rs.next()) {
                list.add(new Product(rs.getString("kode"), rs.getString("nama"),
                        rs.getString("kategori"), rs.getDouble("harga"), rs.getInt("stok")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public Product getProductByCode(String kode) {
        String sql = "SELECT * FROM products WHERE kode = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kode);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Product(rs.getString("kode"), rs.getString("nama"),
                        rs.getString("kategori"), rs.getDouble("harga"), rs.getInt("stok"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    private void executeSql(String sql, Object... params) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) { ps.setObject(i + 1, params[i]); }
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}
