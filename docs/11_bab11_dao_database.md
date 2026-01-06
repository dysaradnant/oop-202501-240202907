# Bab 11 – Data Access Object (DAO) dan CRUD Database dengan JDBC

## Tujuan Pembelajaran

Setelah mengikuti praktikum ini, mahasiswa mampu:

1. Menjelaskan konsep Data Access Object (DAO) dalam pengembangan aplikasi OOP.
2. Menghubungkan aplikasi Java dengan basis data menggunakan JDBC.
3. Mengimplementasikan operasi CRUD (Create, Read, Update, Delete) secara lengkap.
4. Mengintegrasikan DAO dengan class aplikasi OOP sesuai prinsip desain yang baik.

---

## Ringkasan Teori

### 1. Konsep Data Access Object (DAO)

DAO adalah pola desain yang memisahkan logika akses data dari logika bisnis aplikasi. Dengan DAO, perubahan teknologi basis data tidak memengaruhi logika utama aplikasi.

Manfaat DAO:
- Kode lebih terstruktur dan mudah dipelihara
- Mengurangi tight coupling antara aplikasi dan database
- Mendukung pengujian dan pengembangan lanjutan

---

### 2. JDBC dan Koneksi Database

JDBC (Java Database Connectivity) digunakan untuk menghubungkan aplikasi Java dengan basis data relasional, dalam praktikum ini menggunakan PostgreSQL.

Komponen utama JDBC:
- DriverManager
- Connection
- PreparedStatement
- ResultSet

---

## Spesifikasi Teknis Praktikum

### 1. Basis Data

Gunakan PostgreSQL dengan ketentuan minimal berikut:

Nama database: `agripos`

Struktur tabel produk:
```sql
CREATE TABLE products (
    code VARCHAR(10) PRIMARY KEY,
    name VARCHAR(100),
    price DOUBLE PRECISION,
    stock INT
);
```

---

### 2. Class Model – Product

```java
package com.upb.agripos.model;

public class Product {
    private String code;
    private String name;
    private double price;
    private int stock;

    public Product(String code, String name, double price, int stock) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getCode() { return code; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }

    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setStock(int stock) { this.stock = stock; }
}
```

---

### 3. Interface DAO

```java
package com.upb.agripos.dao;

import java.util.List;
import com.upb.agripos.model.Product;

public interface ProductDAO {
    void insert(Product product) throws Exception;
    Product findByCode(String code) throws Exception;
    List<Product> findAll() throws Exception;
    void update(Product product) throws Exception;
    void delete(String code) throws Exception;
}
```

---

### 4. Implementasi DAO dengan JDBC

Implementasi DAO harus menggunakan PreparedStatement.

```java
package com.upb.agripos.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.upb.agripos.model.Product;

public class ProductDAOImpl implements ProductDAO {

    private final Connection connection;

    public ProductDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Product p) throws Exception {
        String sql = "INSERT INTO products(code, name, price, stock) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, p.getCode());
            ps.setString(2, p.getName());
            ps.setDouble(3, p.getPrice());
            ps.setInt(4, p.getStock());
            ps.executeUpdate();
        }
    }

    @Override
    public Product findByCode(String code) throws Exception {
        String sql = "SELECT * FROM products WHERE code = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, code);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Product(
                        rs.getString("code"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("stock")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<Product> findAll() throws Exception {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Product(
                    rs.getString("code"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getInt("stock")
                ));
            }
        }
        return list;
    }

    @Override
    public void update(Product p) throws Exception {
        String sql = "UPDATE products SET name=?, price=?, stock=? WHERE code=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, p.getName());
            ps.setDouble(2, p.getPrice());
            ps.setInt(3, p.getStock());
            ps.setString(4, p.getCode());
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(String code) throws Exception {
        String sql = "DELETE FROM products WHERE code=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, code);
            ps.executeUpdate();
        }
    }
}
```

---

## Integrasi DAO dengan Aplikasi

DAO tidak boleh dipanggil langsung oleh UI. Integrasi dilakukan melalui class aplikasi (misalnya `MainDAOTest`) atau service.

```java
package com.upb.agripos;

import java.sql.Connection;
import java.sql.DriverManager;
import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.dao.ProductDAOImpl;
import com.upb.agripos.model.Product;

public class MainDAOTest {
    public static void main(String[] args) throws Exception {
        Connection conn = DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/agripos",
            "postgres",
            "1234"
        );

        ProductDAO dao = new ProductDAOImpl(conn);

        dao.insert(new Product("P01", "Pupuk Organik", 25000, 10));
        dao.update(new Product("P01", "Pupuk Organik Premium", 30000, 8));

        Product p = dao.findByCode("P01");
        System.out.println(p.getName());

        dao.delete("P01");
        conn.close();
    }
}
```

---

## Struktur Direktori Praktikum (Week 11)

```
praktikum/week11-dao-database/
 ├─ src/main/java/com/upb/agripos/
 │   ├─ model/Product.java
 │   ├─ dao/ProductDAO.java
 │   ├─ dao/ProductDAOImpl.java
 │   ├─ MainDAOTest.java
 ├─ sql/products.sql
 ├─ screenshots/
 │   └─ crud_result.png
 └─ laporan_week11.md  
```

Commit message:
```
week11-dao-database: [fitur] [deskripsi singkat]
```

---

## Tugas Individu

Tugas:
1. Implementasikan DAO Product menggunakan JDBC.
2. Jalankan operasi CRUD lengkap.
3. Integrasikan DAO dengan class aplikasi OOP.

Pengumpulan wajib menyertakan:
- Kode program
- Screenshot hasil eksekusi CRUD
- Commit log sesuai instruksi
- Laporan week 11

---

## Penilaian (Mengacu RPS)

| Aspek                         | Bobot |
|-------------------------------|------:|
| Implementasi DAO sesuai OOP   | 30%   |
| CRUD berjalan lengkap         | 30%   |
| Integrasi DAO dengan aplikasi | 20%   |
| Laporan & dokumentasi         | 20%   |
| Total                         | 100%  |

---

## Checklist Keberhasilan

- [ ] Interface DAO dibuat
- [ ] Implementasi DAO menggunakan JDBC
- [ ] CRUD berjalan lengkap
- [ ] Tidak ada SQL langsung di `main()`
- [ ] Screenshot hasil CRUD tersedia (`screenshots/crud_result.png`)
- [ ] Laporan lengkap (`laporan_week11.md`)
