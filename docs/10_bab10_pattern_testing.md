# Bab 10 – Design Pattern (Singleton, MVC) dan Unit Testing menggunakan JUnit

## Tujuan Pembelajaran (Sub-CPMK04)

Setelah mengikuti praktikum ini, mahasiswa mampu:

1. Menjelaskan konsep dasar design pattern dalam rekayasa perangkat lunak.
2. Mengimplementasikan Singleton Pattern dengan benar.
3. Menjelaskan dan menerapkan Model–View–Controller (MVC) pada aplikasi sederhana.
4. Membuat dan menjalankan unit test menggunakan JUnit.
5. Menganalisis manfaat penerapan design pattern dan unit testing terhadap kualitas perangkat lunak.

---

## Ringkasan Teori

### 1. Design Pattern

Design pattern adalah solusi desain yang telah teruji untuk menyelesaikan masalah umum dalam pengembangan perangkat lunak. Fokus minggu ini:
- Singleton Pattern
- MVC (Model–View–Controller)

### 2. Singleton Pattern

Tujuan: Menjamin suatu class hanya memiliki satu instance dan menyediakan titik akses global.

Karakteristik:
- Constructor `private`
- Atribut `static instance`
- Method `static getInstance()`

Contoh Implementasi:
```java
package com.upb.agripos.config;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private DatabaseConnection() {}

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
}
```

Penerapan pada Agri-POS: koneksi database atau service global yang tidak boleh lebih dari satu instance.

### 3. MVC (Model–View–Controller)

Memisahkan tanggung jawab aplikasi:

| Komponen | Tanggung Jawab |
|---------|------------------|
| Model   | Data dan logika bisnis |
| View    | Tampilan/output |
| Controller | Penghubung Model dan View |

Contoh Struktur MVC Sederhana:
- Model → `Product`
- View → `ConsoleView`
- Controller → `ProductController`

---

## Implementasi Praktikum

### 1. Model
```java
package com.upb.agripos.model;

public class Product {
    private final String code;
    private final String name;

    public Product(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() { return code; }
    public String getName() { return name; }
}
```

### 2. View
```java
package com.upb.agripos.view;

public class ConsoleView {
    public void showMessage(String message) {
        System.out.println(message);
    }
}
```

### 3. Controller (WAJIB DIISI)
```java
package com.upb.agripos.controller;

import com.upb.agripos.model.Product;
import com.upb.agripos.view.ConsoleView;

public class ProductController {
    private final Product model;
    private final ConsoleView view;

    public ProductController(Product model, ConsoleView view) {
        this.model = model;
        this.view = view;
    }

    public void showProduct() {
        view.showMessage("Produk: " + model.getCode() + " - " + model.getName());
    }
}
```

### 4. Main Program (Integrasi MVC)
```java
package com.upb.agripos;

import com.upb.agripos.model.Product;
import com.upb.agripos.view.ConsoleView;
import com.upb.agripos.controller.ProductController;

public class AppMVC {
    public static void main(String[] args) {
        System.out.println("Hello, I am [Nama]-[NIM] (Week10)");
        Product product = new Product("P01", "Pupuk Organik");
        ConsoleView view = new ConsoleView();
        ProductController controller = new ProductController(product, view);
        controller.showProduct();
    }
}
```

---

## Unit Testing Menggunakan JUnit

Tujuan unit testing:
- Memastikan fungsi berjalan sesuai harapan
- Mendeteksi kesalahan lebih awal
- Meningkatkan kepercayaan terhadap kode

### Contoh Unit Test JUnit
```java
package com.upb.agripos;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.upb.agripos.model.Product;

public class ProductTest {
    @Test
    public void testProductName() {
        Product p = new Product("P01", "Benih Jagung");
        assertEquals("Benih Jagung", p.getName());
    }
}
```

Kriteria unit test benar:
- Menggunakan anotasi `@Test`
- Menggunakan assertion
- Test dapat dijalankan tanpa error

---

## Langkah Praktikum Pertemuan 10

1. Implementasikan Singleton untuk `DatabaseConnection`.
2. Buat struktur MVC sederhana untuk fitur Product.
3. Buat minimal 1 unit test JUnit.
4. Jalankan unit test dan dokumentasikan hasilnya.

Commit message:
```
week10-pattern-testing: [fitur] [deskripsi singkat]
```

---

## Struktur Direktori Praktikum (Week 10)

```
praktikum/week10-pattern-testing/
 ├─ src/main/java/com/upb/agripos/
 │   ├─ model/Product.java
 │   ├─ view/ConsoleView.java
 │   ├─ controller/ProductController.java
 │   ├─ config/DatabaseConnection.java
 │   └─ AppMVC.java
 ├─ src/test/java/com/upb/agripos/
 │   └─ ProductTest.java
 ├─ screenshots/
 │   └─ junit_result.png
 └─ laporan_week10.md
```

---

## Tugas Individu

Tugas:
1. Implementasikan Singleton Database Connection.
2. Terapkan MVC pada satu fitur aplikasi sederhana.
3. Buat minimal 1 unit test menggunakan JUnit.

Pengumpulan wajib menyertakan:
- Kode program
- Screenshot hasil unit test
- Commit log sesuai instruksi
- Laporan Week 10

---

## Quiz

1. Mengapa constructor pada Singleton harus bersifat private?
2. Jelaskan manfaat pemisahan Model, View, dan Controller.
3. Apa peran unit testing dalam menjaga kualitas perangkat lunak?
4. Apa risiko jika Singleton tidak diimplementasikan dengan benar?

---

## Checklist Keberhasilan

- [ ] Singleton Pattern diimplementasikan dengan benar
- [ ] Struktur MVC jelas dan konsisten
- [ ] Unit test JUnit berjalan tanpa error
- [ ] Screenshot hasil test tersedia (`screenshots/junit_result.png`)
- [ ] Laporan Week 10 lengkap (`laporan_week10.md`)
- [ ] Commit dan push sesuai instruksi
