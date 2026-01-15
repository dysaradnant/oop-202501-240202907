# Laporan Praktikum Minggu 11
Topik: Data Access Object (DAO) dan CRUD Database dengan JDBC

## Identitas
- Nama  : Dysar Adnant Ilham Nur Asnawi
- NIM   : 240202907
- Kelas : 3IKKA

---

## Tujuan
1. Menjelaskan konsep Data Access Object (DAO) dalam pengembangan aplikasi OOP.
2. Menghubungkan aplikasi Java dengan basis data menggunakan JDBC.
3. Mengimplementasikan operasi CRUD (Create, Read, Update, Delete) secara lengkap.
4. Mengintegrasikan DAO dengan class aplikasi OOP sesuai prinsip desain yang baik.

---

## Dasar Teori
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

## Kode Program  
### 1. Basis Data
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

        System.out.println(
                "Dysar Adnant Ilham Nur Asnawi - 240202907"
        );
        conn.close();

    }
}
```

---

## Hasil Eksekusi
### crud_result
<img width="1919" height="1079" alt="crud_result" src="https://github.com/user-attachments/assets/81b1b8a5-6f99-4661-a88e-5b8eb374fa58" />

---

## Analisis
- Jelaskan bagaimana kode berjalan.   
   **Jawaban:**  Program dijalankan melalui kelas MainDAOTest yang berfungsi sebagai titik awal eksekusi. Pertama, program memuat driver JDBC PostgreSQL, kemudian melakukan koneksi ke database menggunakan DriverManager. Setelah koneksi berhasil, objek ProductDAOImpl dibuat dan digunakan untuk menjalankan operasi CRUD (Create, Read, Update, Delete) terhadap tabel products. Setiap operasi database diakses melalui method DAO sehingga logika akses data terpisah dari logika aplikasi utama.
- Apa perbedaan pendekatan minggu ini dibanding minggu sebelumnya.     
   **Jawaban:**   Pada minggu sebelumnya, pengolahan data masih dilakukan secara langsung di dalam program tanpa melibatkan database dan tanpa lapisan khusus. Pada minggu ini, digunakan pendekatan DAO (Data Access Object) yang memisahkan logika akses data ke dalam class tersendiri serta memanfaatkan database PostgreSQL sebagai penyimpanan data. Pendekatan ini membuat kode lebih terstruktur, modular, dan mudah dikembangkan.
- Kendala yang dihadapi dan cara mengatasinya.     
   **Jawaban:**  Kendala utama yang dihadapi adalah error koneksi database seperti “No suitable driver found” dan kesalahan konfigurasi pom.xml. Masalah ini diatasi dengan menambahkan dependency JDBC PostgreSQL ke dalam pom.xml, memperbaiki struktur Maven, serta menjalankan program menggunakan Maven agar seluruh dependency dapat dimuat dengan benar. Selain itu, proses reload Maven juga dilakukan agar perubahan konfigurasi dapat diterapkan.
---

## Kesimpulan
Pada praktikum ini telah berhasil diterapkan konsep DAO (Data Access Object) untuk mengelola akses data menggunakan database PostgreSQL. Penerapan DAO membuat kode program menjadi lebih terstruktur karena memisahkan logika akses data dari logika aplikasi utama. Selain itu, penggunaan Maven membantu dalam pengelolaan dependency, khususnya JDBC driver, sehingga koneksi ke database dapat berjalan dengan baik. Dengan pendekatan ini, aplikasi menjadi lebih mudah dipelihara, dikembangkan, dan diuji, serta mencerminkan penerapan praktik pemrograman berorientasi objek yang baik.

---
