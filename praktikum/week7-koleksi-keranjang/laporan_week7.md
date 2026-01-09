# Laporan Praktikum Minggu 7
Topik: Collections dan Implementasi Keranjang Belanja

## Identitas
- Nama  : Dysar Adnant Ilham Nur Asnawi
- NIM   : 240202907
- Kelas : 3IKKA

---

## Tujuan
Mahasiswa mampu:

1. Menjelaskan konsep collection dalam Java (List, Map, Set).
2. Menggunakan ArrayList untuk menyimpan dan mengelola objek.
3. Mengimplementasikan Map atau Set sesuai kebutuhan pengelolaan data.
4. Melakukan operasi dasar pada collection: tambah, hapus, dan hitung total.
5. Menganalisis efisiensi penggunaan collection dalam konteks sistem Agri-POS.

---

## Dasar Teori

### 1. Collections Framework
 Java Collections Framework menyediakan struktur data untuk mengelola objek secara dinamis dan efisien.

 Struktur utama:
 - List (implementasi: ArrayList) — Terurut, dapat menyimpan elemen duplikat.
- Map (implementasi: HashMap) — Menyimpan pasangan key–value, akses cepat berdasarkan key.
- Set (implementasi: HashSet) — Tidak menerima duplikat dan tidak mempertahankan urutan.
  
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
1. Membuat class `Product.java` untuk representasi produk.  
2. Membuat class `ShoppingCart.java` untuk keranjang dengan ArrayList.  
3. Membuat class `ShoppingCartMap.java` untuk keranjang dengan Map dan quantity.  
4. Membuat class `MainCart.java` untuk menjalankan demo penambahan, penghapusan, dan menampilkan keranjang.  
---

## Kode Program  

### Product.java
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

### ShoppingCart.java
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
````

### ShoppingCartMap.java
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

### MainCart.java
```java
package com.upb.agripos;

public class MainCart {
    public static void main(String[] args) {
        System.out.println("Hello, I am Dysar Adnant Ilham Nur Asnawi-240202907 (Week7)");

        Product Benih = new Product("Benih", "Padi", 25000);
        Product Pupuk = new Product("Pupuk", "Pupuk Urea", 350000);
        Product AlatPertanian = new Product("Alat Pertanian", "Cangkul Baja", 90000);
        Product ObatHama = new Product("Obat Hama", "Racun Serangga", 120000);

        // Demo ShoppingCart (ArrayList)
        ShoppingCart cartlist = new ShoppingCart();
        cartlist.addProduct(Benih);
        cartlist.addProduct(Pupuk);
        cartlist.addProduct(AlatPertanian);
        cartlist.addProduct(ObatHama);
        cartlist.printCart();

        cartlist.removeProduct(Pupuk);
        cartlist.printCart();

        // Demo ShoppingCartMap (Map, quantity)
        ShoppingCart cartmap = new ShoppingCart();
        cartmap.addProduct(Benih);
        cartmap.addProduct(Pupuk);
        cartmap.addProduct(AlatPertanian);
        cartmap.addProduct(ObatHama);
        cartmap.printCart();

        cartmap.removeProduct(Pupuk);
        cartmap.printCart();
    }
}

```

---

## Hasil Eksekusi

### MainCart
<img width="1919" height="1079" alt="MainCart" src="https://github.com/user-attachments/assets/4587ccc5-c837-4a11-b46f-de3b14ef0576" />

---

## Analisis

- Program menggunakan class Product untuk menyimpan nama dan harga produk.
- ShoppingCart menggunakan ArrayList untuk daftar produk, sedangkan ShoppingCartMap menggunakan HashMap untuk menghitung jumlah (quantity).
- MainCart menambahkan produk, menampilkan keranjang, menghapus produk, dan menghitung total belanja.
- Perbedaan dengan minggu sebelumnya: sekarang menggunakan Collections, sehingga lebih mudah menambah, hapus, dan hitung total dibanding menggunakan array biasa.
- Kendala: menampilkan harga dan quantity di output.

---

## Kesimpulan
- Dengan menggunakan class dan object, program menjadi lebih terstruktur dan modular.
- Collections Framework (ArrayList dan HashMap) memudahkan pengelolaan data dinamis seperti keranjang belanja, termasuk menambah, menghapus, dan menghitung total harga.
- Program sekarang lebih fleksibel dan mudah dikembangkan, misalnya menambahkan produk baru atau fitur quantity tanpa mengubah banyak kode.

---

## Quiz
1. [Jelaskan perbedaan mendasar antara List, Map, dan Set.]  
   **Jawaban:** List, Map, dan Set merupakan bagian dari Collections Framework yang memiliki fungsi berbeda. List digunakan untuk menyimpan data secara berurutan dan memperbolehkan adanya data yang sama (duplikasi). Set digunakan untuk menyimpan data yang unik sehingga tidak memperbolehkan adanya data yang sama. Sedangkan Map digunakan untuk menyimpan data dalam bentuk pasangan key–value, di mana setiap key harus unik dan digunakan untuk mengakses value dengan cepat.

2. [Mengapa ArrayList cocok digunakan untuk keranjang belanja sederhana?]  
   **Jawaban:** Arraylist cocok digunakan karena mudah untuk menyimpan daftar produk, mendukung penambahan dan penghapusan data fleksibel, cocok untuk keranjang belanja sederhana yang tidak memerlukan pengelolaan jumlah, dan data dpat ditampilkan sesuai penambahan. 

3. [Bagaimana struktur Set mencegah duplikasi data?]  
   **Jawaban:** Set menggunakan mekanisme pengecekan elemen yang sama berdasarkan metode `equals()` dan `hashCode()`.
Jika data yang dimasukkan sudah ada, maka data tersebut tidak akan ditambahkan lagi, sehingga tidak terjadi duplikasi.

4. [Kapan sebaiknya menggunakan Map dibandingkan List? Jelaskan dengan contoh.]  
   **Jawaban:**  Map digunakan ketika data memiliki pasangan key dan value, misalnya produk dan jumlahnya.
   Contoh:
   Dalam keranjang belanja, jika satu produk bisa dibeli lebih dari satu, maka Map lebih tepat digunakan:
   - key → produk
   - value → jumlah produk
   Dengan Map, kita bisa dengan mudah menambah, mengurangi, dan menghitung total berdasarkan quantity.
