# Bab 7 – Collections dan Implementasi Keranjang Belanja

## Tujuan Pembelajaran

 Mahasiswa mampu:

1. Menjelaskan konsep collection dalam Java (List, Map, Set).
2. Menggunakan ArrayList untuk menyimpan dan mengelola objek.
3. Mengimplementasikan Map atau Set sesuai kebutuhan pengelolaan data.
4. Melakukan operasi dasar pada collection: tambah, hapus, dan hitung total.
5. Menganalisis efisiensi penggunaan collection dalam konteks sistem Agri-POS.

---

## Ringkasan Teori

### 1. Collections Framework

Java Collections Framework menyediakan struktur data untuk mengelola objek secara dinamis dan efisien.

Struktur utama:

- List (implementasi: ArrayList) — Terurut, dapat menyimpan elemen duplikat.
- Map (implementasi: HashMap) — Menyimpan pasangan key–value, akses cepat berdasarkan key.
- Set (implementasi: HashSet) — Tidak menerima duplikat dan tidak mempertahankan urutan.

---

### 2. Studi Kasus: Keranjang Belanja Agri-POS

Keranjang belanja harus dapat:

- Menambahkan produk
- Menghapus produk
- Menampilkan isi keranjang
- Menghitung total nilai transaksi
- Menangani jumlah (quantity) menggunakan Map

Kasus ini mencerminkan penggunaan struktur data dalam aplikasi nyata seperti POS.

---

## Langkah Praktikum

### 1. Membuat Class Product

```java
package com.upb.agripos;

public class Product {
    private final String code;
    private final String name;
    private final double price;

    public Product(String code, String name, double price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }

    public String getCode() { return code; }
    public String getName() { return name; }
    public double getPrice() { return price; }
}
```

## 2. Implementasi Keranjang dengan ArrayList

```java
package com.upb.agripos;

import java.util.ArrayList;

public class ShoppingCart {
    private final ArrayList<Product> items = new ArrayList<>();

    public void addProduct(Product p) { items.add(p); }
    public void removeProduct(Product p) { items.remove(p); }

    public double getTotal() {
        double sum = 0;
        for (Product p : items) {
            sum += p.getPrice();
        }
        return sum;
    }

    public void printCart() {
        System.out.println("Isi Keranjang:");
        for (Product p : items) {
            System.out.println("- " + p.getCode() + " " + p.getName() + " = " + p.getPrice());
        }
        System.out.println("Total: " + getTotal());
    }
}
```

## 3. Main Program (WAJIB DIISI)

```java
package com.upb.agripos;

public class MainCart {
    public static void main(String[] args) {
        System.out.println("Hello, I am [Nama]-[NIM] (Week7)");

        Product p1 = new Product("P01", "Beras", 50000);
        Product p2 = new Product("P02", "Pupuk", 30000);

        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(p1);
        cart.addProduct(p2);
        cart.printCart();

        cart.removeProduct(p1);
        cart.printCart();
    }
}
```

## 4. Implementasi Alternatif Menggunakan Map (Dengan Quantity)

```java
package com.upb.agripos;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCartMap {
    private final Map<Product, Integer> items = new HashMap<>();

    public void addProduct(Product p) { items.put(p, items.getOrDefault(p, 0) + 1); }

    public void removeProduct(Product p) {
        if (!items.containsKey(p)) return;
        int qty = items.get(p);
        if (qty > 1) items.put(p, qty - 1);
        else items.remove(p);
    }

    public double getTotal() {
        double total = 0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }

    public void printCart() {
        System.out.println("Isi Keranjang (Map):");
        for (Map.Entry<Product, Integer> e : items.entrySet()) {
            System.out.println("- " + e.getKey().getCode() + " " + e.getKey().getName() + " x" + e.getValue());
        }
        System.out.println("Total: " + getTotal());
    }
}
```

---

## Struktur Direktori Praktikum

```
praktikum/week7-collections/
 ├─ src/main/java/com/upb/agripos/
 │   ├─ Product.java
 │   ├─ ShoppingCart.java
 │   ├─ ShoppingCartMap.java
 │   └─ MainCart.java
 ├─ screenshots/
 │   └─ hasil.png                 # WAJIB DIISI
 └─ laporan_week7.md              # WAJIB DIISI
```

---

## Tugas Individu (Sesuai RPS)

Mahasiswa harus mengimplementasikan:

Keranjang Belanja (Shopping Cart) Agri-POS

- tambahProduk
- hapusProduk
- hitungTotal

Dengan ketentuan:

- Menyertakan kode lengkap dan paket `com.upb.agripos`
- Menyertakan screenshot hasil eksekusi (`screenshots/hasil.png`)
- Melakukan commit & push sesuai format

Commit message:

```
week7-collections: [fitur] [deskripsi singkat]
```

---

## Quiz Minggu 7

1. Jelaskan perbedaan mendasar antara List, Map, dan Set.
2. Mengapa ArrayList cocok digunakan untuk keranjang belanja sederhana?
3. Bagaimana struktur Set mencegah duplikasi data?
4. Kapan sebaiknya menggunakan Map dibandingkan List? Jelaskan dengan contoh.

---

## Checklist Keberhasilan

- [ ] Program keranjang belanja menggunakan ArrayList berjalan benar
- [ ] Program alternatif menggunakan Map berjalan benar (OPSIONAL)
- [ ] Screenshot hasil eksekusi tersedia (`screenshots/hasil.png`)
- [ ] Laporan minggu ke-7 lengkap (`laporan_week7.md`)
- [ ] Struktur paket dan folder sesuai template
- [ ] Commit & push sesuai format
