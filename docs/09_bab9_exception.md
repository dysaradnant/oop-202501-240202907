# Bab 9 – Exception Handling, Custom Exception, dan Penerapan Design Pattern

## Tujuan Pembelajaran

Mahasiswa mampu:

1. Menjelaskan perbedaan antara error dan exception.
2. Mengimplementasikan try–catch–finally dengan tepat.
3. Membuat custom exception sesuai kebutuhan program.
4. Mengintegrasikan exception handling ke dalam aplikasi sederhana (kasus keranjang belanja).
5. (Opsional) Menerapkan design pattern sederhana (Singleton/MVC) dan unit testing dasar.

---

## Ringkasan Teori

### 1. Error vs Exception

- Error → kondisi fatal, tidak dapat ditangani (contoh: OutOfMemoryError).
- Exception → kondisi tidak normal yang dapat ditangani oleh program.

### 2. Struktur try–catch–finally

```java
try {
    // kode yang berpotensi menimbulkan kesalahan
} catch (Exception e) {
    // penanganan
} finally {
    // blok yang selalu dijalankan
}
```

### 3. Membuat Custom Exception

```java
package com.upb.agripos;

public class InvalidQuantityException extends Exception {
    public InvalidQuantityException(String message) {
        super(message);
    }
}
```

---

## Studi Kasus Agri-POS: Keranjang Belanja

Keranjang belanja harus memvalidasi:

- Jumlah pembelian > 0
- Produk ada dalam keranjang
- Stok mencukupi

Kesalahan–kesalahan tersebut ditangani menggunakan custom exception.

---

## Langkah Praktikum

### 1. Membuat Custom Exception

```java
package com.upb.agripos;

public class InvalidQuantityException extends Exception {
    public InvalidQuantityException(String msg) { super(msg); }
}
```

```java
package com.upb.agripos;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String msg) { super(msg); }
}
```

```java
package com.upb.agripos;

public class InsufficientStockException extends Exception {
    public InsufficientStockException(String msg) { super(msg); }
}
```

### 2. Model Product dengan Stok

```java
package com.upb.agripos;

public class Product {
    private final String code;
    private final String name;
    private final double price;
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
    public void reduceStock(int qty) { this.stock -= qty; }
}
```

### 3. Implementasi ShoppingCart dengan Exception Handling

```java
package com.upb.agripos;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private final Map<Product, Integer> items = new HashMap<>();

    public void addProduct(Product p, int qty) throws InvalidQuantityException {
        if (qty <= 0) {
            throw new InvalidQuantityException("Quantity harus lebih dari 0.");
        }
        items.put(p, items.getOrDefault(p, 0) + qty);
    }

    public void removeProduct(Product p) throws ProductNotFoundException {
        if (!items.containsKey(p)) {
            throw new ProductNotFoundException("Produk tidak ada dalam keranjang.");
        }
        items.remove(p);
    }

    public void checkout() throws InsufficientStockException {
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int qty = entry.getValue();
            if (product.getStock() < qty) {
                throw new InsufficientStockException(
                    "Stok tidak cukup untuk: " + product.getName()
                );
            }
        }
        // contoh pengurangan stok bila semua cukup
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            entry.getKey().reduceStock(entry.getValue());
        }
    }
}
```

### 4. Main Program untuk Menguji Exception Handling

```java
package com.upb.agripos;

public class MainExceptionDemo {
    public static void main(String[] args) {
        System.out.println("Hello, I am [Nama]-[NIM] (Week9)");

        ShoppingCart cart = new ShoppingCart();
        Product p1 = new Product("P01", "Pupuk Organik", 25000, 3);

        try {
            cart.addProduct(p1, -1);
        } catch (InvalidQuantityException e) {
            System.out.println("Kesalahan: " + e.getMessage());
        }

        try {
            cart.removeProduct(p1);
        } catch (ProductNotFoundException e) {
            System.out.println("Kesalahan: " + e.getMessage());
        }

        try {
            cart.addProduct(p1, 5);
            cart.checkout();
        } catch (Exception e) {
            System.out.println("Kesalahan: " + e.getMessage());
        }
    }
}
```

---

## Design Pattern Sederhana

### 1. Singleton Pattern

Digunakan untuk layanan yang hanya boleh ada satu instance, misalnya ProductService.

```java
package com.upb.agripos;

public class ProductService {
    private static ProductService instance;
    private ProductService() {}

    public static ProductService getInstance() {
        if (instance == null) { instance = new ProductService(); }
        return instance;
    }
}
```

### 2. Konsep MVC

- Model → Product, ShoppingCart
- View → tampilkan output terminal
- Controller → MainExceptionDemo

---

## Tugas Individu

CustomException untuk validasi keranjang belanja.

Tugas:

1. Buat minimal 2 custom exception.
2. Terapkan pada metode: tambahProduk, hapusProduk, checkout.
3. Berikan pesan kesalahan yang informatif.
4. Sertakan: kode program, screenshot hasil eksekusi, laporan week9, commit & push.

Commit message:

```
week9-exception: [fitur] [deskripsi singkat]
```

---

## Quiz Minggu 9

1. Jelaskan perbedaan error dan exception.
2. Apa fungsi finally dalam blok try–catch–finally?
3. Mengapa custom exception diperlukan?
4. Berikan contoh kasus bisnis dalam POS yang membutuhkan custom exception.

---

## Checklist Keberhasilan

- [ ] Mahasiswa mampu menjelaskan error vs exception
- [ ] Program menggunakan try–catch–finally
- [ ] Minimal dua custom exception berhasil dibuat
- [ ] Exception berhasil terintegrasi dalam keranjang belanja
- [ ] Laporan lengkap dan commit benar

---

## Struktur Direktori Praktikum

```
praktikum/week9-exception-handling/
 ├─ src/main/java/com/upb/agripos/
 │   ├─ Product.java
 │   ├─ InvalidQuantityException.java
 │   ├─ ProductNotFoundException.java
 │   ├─ InsufficientStockException.java
 │   ├─ ShoppingCart.java
 │   └─ MainExceptionDemo.java
 ├─ screenshots/
 │   └─ hasil.png                 
 └─ laporan_week9.md          
```
