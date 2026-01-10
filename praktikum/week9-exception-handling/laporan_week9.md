# Laporan Praktikum Minggu 9
Topik: Exception Handling, Custom Exception, dan Penerapan Design Pattern

## Identitas
- Nama  : Dysar Adnant Ilham Nur Asnawi
- NIM   : 240202907
- Kelas : 3IKKA

---

## Tujuan
1. Menjelaskan perbedaan antara error dan exception.
2. Mengimplementasikan try–catch–finally dengan tepat.
3. Membuat custom exception sesuai kebutuhan program.
4. Mengintegrasikan exception handling ke dalam aplikasi sederhana (kasus keranjang belanja).
5. (Opsional) Menerapkan design pattern sederhana (Singleton/MVC) dan unit testing dasar.

---

## Dasar Teori
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

## Langkah Praktikum
1. Membuat Custom Exception  
2. Model Product dengan Stok 
3. Implementasi ShoppingCart dengan Exception Handling
4. Main Program untuk Menguji Exception Handling
5. Commit message: week9-exception: [fitur] [deskripsi singkat]

---

## Kode Program
MainExceptionDemo.java  

```java
package com.upb.agripos;

public class MainExceptionDemo {
    public static void main(String[] args) {

        System.out.println("Hello, I am Dysar Adnant Ilham Nur Asnawi-240202907 (Week9)");

        ShoppingCart cart = new ShoppingCart();
        Product pupuk = new Product("P01", "Pupuk Organik", 25000, 3);

        // Uji quantity salah
        try {
            cart.addProduct(pupuk, -1);
        } catch (InvalidQuantityException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Uji hapus produk yang tidak ada
        try {
            cart.removeProduct(pupuk);
        } catch (ProductNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Uji stok tidak cukup
        try {
            cart.addProduct(pupuk, 5);
            cart.checkout();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
```

---

## Hasil Eksekusi

### MainExceptionDemo
<img width="1919" height="1079" alt="MainExceptionDemo" src="https://github.com/user-attachments/assets/d96a1edf-4deb-4753-98da-799b555ba2c5" />

---

## Analisis
1. Cara Kerja Kode

   Program ini mengimplementasikan sistem exception handling yang komprehensif untuk keranjang belanja Agri-POS dengan lima custom exception yang spesifik. Custom exception `NegativePriceException` dan `EmptyCartException` ditambahkan untuk memperkuat validasi bisnis, melengkapi tiga exception sebelumnya yaitu `InvalidQuantityException`, `ProductNotFoundException`, dan `InsufficientStockException`.

   Class `Product` dilengkapi dengan validasi harga di constructor yang akan melempar `NegativePriceException` jika harga yang diinputkan bernilai negatif. Validasi ini memastikan integritas data produk sejak awal pembuatan objek, mencegah produk dengan harga invalid masuk ke dalam sistem.

   Class `ShoppingCart` mengimplementasikan tiga method utama dengan exception handling yang ketat. Method `addProduct()` memvalidasi quantity dan melempar `InvalidQuantityException` untuk nilai yang tidak valid. Method `removeProduct()` memeriksa apakah keranjang kosong terlebih dahulu sebelum melakukan penghapusan, melempar `EmptyCartException` jika keranjang kosong atau `ProductNotFoundException` jika produk tidak ditemukan. Method `checkout()` melakukan validasi ganda dengan memeriksa keranjang kosong dan ketersediaan stok sebelum memproses transaksi.

   Program `MainTugasIndividu` mendemonstrasikan pengujian yang mencakup semua kemungkinan exception. Setiap test case dirancang untuk memicu exception tertentu dan menampilkan pesan kesalahan yang informatif. Pengujian dilakukan secara sistematis mulai dari validasi pembuatan produk, operasi keranjang kosong, penambahan dan penghapusan produk, hingga proses checkout dengan berbagai kondisi.

2. Perbedaan dengan Praktikum Sebelumnya

   Praktikum sebelumnya fokus pada penggunaan Collections untuk struktur data keranjang belanja, sedangkan praktikum ini menambahkan layer validasi bisnis yang komprehensif menggunakan custom exception. Penambahan `NegativePriceException` untuk validasi harga produk dan `EmptyCartException` untuk validasi operasi pada keranjang kosong membuat sistem lebih akurat dan sesuai dengan kebutuhan aplikasi Agri-POS.

3. Kendala yang dihadapi dan cara mengatasinya.  

   Kendala utama adalah memastikan urutan pengecekan exception yang tepat, terutama pada method `removeProduct()` yang harus memeriksa keranjang kosong terlebih dahulu sebelum memeriksa keberadaan produk. Selain itu, diperlukan pemahaman mendalam tentang checked exception yang mengharuskan setiap method mendeklarasikan `throws` dan caller harus menangani exception menggunakan try-catch atau meneruskannya ke level atas.

---

## Kesimpulan
Program ini berhasil mengimplementasikan sistem exception handling yang komprehensif untuk keranjang belanja Agri-POS dengan lima custom exception yang terstruktur membuat kode lebih maintainable. Setiap exception memberikan pesan kesalahan yang spesifik dan informatif serta memberikan feedback yang jelas kepada pengguna. Penggunaan try-catch-finally memungkinkan program tetap berjalan tanpa gangguan yang signifikan.

Sistem ini menunjukkan pentingnya validasi data dan error handling, terutama untuk aplikasi POS yang menangani transaksi keuangan. Dengan exception handling yang baik, sistem dapat mencegah data corrupt, memberikan feedback yang jelas, dan memastikan integritas bisnis proses tetap terjaga.

---

## Quiz
1. Jelaskan perbedaan error dan exception.  
   **Jawaban:** Error merupakan kondisi fatal yang tidak dapat ditangani oleh program dan biasanya terjadi di luar kendali. Error mengindikasikan masalah serius pada sistem yang tidak dapat dipulihkan oleh aplikasi. Sedangkan exception merupakan kondisi tidak normal yang masih dapat ditangani oleh program melalui mekanisme try-catch, seperti validasi input atau kondisi bisnis yang tidak terpenuhi. Exception memungkinkan program untuk pulih dari kesalahan dan melanjutkan eksekusi.  

2. Apa fungsi finally dalam blok try–catch–finally?  
   **Jawaban:** Blok finally berfungsi untuk mengeksekusi kode yang harus dijalankan terlepas dari terjadi atau tidaknya exception dalam blok try. Blok ini selalu dijalankan baik saat exception terjadi maupun tidak, bahkan ketika ada statement return di dalam blok try atau catch. Finally biasanya digunakan untuk menutup resource yang harus dipastikan selalu ditutup untuk menghindari memory leak atau resource leak.  

3. Mengapa custom exception diperlukan?  
   **Jawaban:** Custom exception diperlukan ketika exception bawaan tidak cukup spesifik untuk menggambarkan kondisi kesalahan dalam konteks aplikasi tertentu. Dengan membuat custom exception yang sesuai dengan domain bisnis, pesan kesalahan menjadi lebih informatif dan relevan dengan logika aplikasi. Custom exception juga memudahkan penanganan error yang terstruktur, memisahkan berbagai jenis kesalahan bisnis, dan membuat kode lebih readable dan maintainable karena nama exception sudah mendeskripsikan jenis kesalahan yang terjadi.  

4. Berikan contoh kasus bisnis dalam POS yang membutuhkan custom exception.  
   **Jawaban:** Dalam sistem POS Agri-POS, beberapa kasus yang membutuhkan custom exception antara lain: `NegativePriceException` untuk mencegah produk dengan harga negatif masuk ke sistem, `EmptyCartException` untuk validasi operasi pada keranjang kosong seperti checkout atau penghapusan item, `InvalidPaymentException` untuk validasi pembayaran yang tidak sesuai dengan total belanja, `ExpiredProductException` untuk produk yang sudah kadaluarsa tidak boleh dijual, `InvalidDiscountException` untuk kode diskon yang tidak valid atau sudah expired, dan `InsufficientBalanceException` untuk pembayaran dengan saldo e-wallet yang tidak mencukupi. Setiap custom exception ini memberikan konteks kesalahan yang spesifik sesuai proses bisnis POS.   
