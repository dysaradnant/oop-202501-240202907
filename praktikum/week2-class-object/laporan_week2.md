# Laporan Praktikum Minggu 2
Topik: Class dan Object (Produk Pertanian)

## Identitas
- Nama  : Dysar Adnant Ilham Nur Asnawi
- NIM   : 240202907
- Kelas : 3IKKA

---

## Tujuan
- Mahasiswa mampu **menjelaskan konsep class, object, atribut, dan method** dalam OOP.  
- Mahasiswa mampu **menerapkan access modifier dan enkapsulasi** dalam pembuatan class.  
- Mahasiswa mampu **mengimplementasikan class Produk pertanian** dengan atribut dan method yang sesuai.  
- Mahasiswa mampu **mendemonstrasikan instansiasi object** serta menampilkan data produk pertanian di console.  
- Mahasiswa mampu **menyusun laporan praktikum** dengan bukti kode, hasil eksekusi, dan analisis sederhana.  
---

## Dasar Teori
Class adalah blueprint atau cetak biru dari sebuah objek. Objek merupakan instansiasi dari class yang berisi atribut (data) dan method (perilaku). Dalam OOP, enkapsulasi dilakukan dengan menyembunyikan data menggunakan access modifier (public, private, protected) serta menyediakan akses melalui getter dan setter.  

Dalam konteks Agri-POS, produk pertanian seperti benih, pupuk, dan alat pertanian dapat direpresentasikan sebagai objek yang memiliki atribut nama, harga, dan stok. Dengan menggunakan class, setiap produk dapat dibuat, dikelola, dan dimanipulasi secara lebih terstruktur.  

---

## Langkah Praktikum
1. **Membuat Class Produk**
   - Buat file `Produk.java` pada package `model`.
   - Tambahkan atribut: `kode`, `nama`, `harga`, dan `stok`.
   - Gunakan enkapsulasi dengan menjadikan atribut bersifat private dan membuat getter serta setter untuk masing-masing atribut.  

2. **Membuat Class CreditBy**
   - Buat file `CreditBy.java` pada package `util`.
   - Isi class dengan method statis untuk menampilkan identitas mahasiswa di akhir output: `credit by: <NIM> - <Nama>`.

3. **Membuat Objek Produk dan Menampilkan Credit**
   - Buat file `MainProduk.java`.
   - Instansiasi minimal tiga objek produk, misalnya "Benih Padi", "Pupuk Urea", dan satu produk alat pertanian.
   - Tampilkan informasi produk melalui method getter.  
   - Panggil `CreditBy.print("<NIM>", "<Nama>")` di akhir `main` untuk menampilkan identitas.

4. **Commit dan Push**
   - Commit dengan pesan: `week2-class-object`.  

---

## Kode Program

### 1. Produk.java
```java
package com.upb.agripos.model;

public class Produk {
    private String kode;
    private String nama;
    private double harga;
    private int stok;

    public Produk(String kode, String nama, double harga, int stok) {
        this.kode = kode;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

    public String getKode() { return kode; }
    public void setKode(String kode) { this.kode = kode; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public double getHarga() { return harga; }
    public void setHarga(double harga) { this.harga = harga; }

    public int getStok() { return stok; }
    public void setStok(int stok) { this.stok = stok; }

    public void tambahStok(int jumlah) {
        this.stok += jumlah;
    }

    public void kurangiStok(int jumlah) {
        if (this.stok >= jumlah) {
            this.stok -= jumlah;
        } else {
            System.out.println("Stok tidak mencukupi!");
        }
    }
}
```

### 2. CreditBy.java
```java
package com.upb.agripos.util;

public class CreditBy {
    public static void print(String nim, String nama) {
        System.out.println("\ncredit by: " + nim + " - " + nama);
    }
}
```

### 3. MainProduk.java
```java
package com.upb.agripos;

import com.upb.agripos.model.Produk;
import com.upb.agripos.util.CreditBy;

public class MainProduk {
    public static void main(String[] args) {
        Produk p1 = new Produk("BNH-001", "Benih Padi IR64", 25000, 100);
        Produk p2 = new Produk("PPK-101", "Pupuk Urea 50kg", 350000, 40);
        Produk p3 = new Produk("ALT-501", "Cangkul Baja", 90000, 15);

        System.out.println("Kode: " + p1.getKode() + ", Nama: " + p1.getNama() + ", Harga: " + p1.getHarga() + ", Stok: " + p1.getStok());
        System.out.println("Kode: " + p2.getKode() + ", Nama: " + p2.getNama() + ", Harga: " + p2.getHarga() + ", Stok: " + p2.getStok());
        System.out.println("Kode: " + p3.getKode() + ", Nama: " + p3.getNama() + ", Harga: " + p3.getHarga() + ", Stok: " + p3.getStok());

        // Tampilkan identitas mahasiswa
        CreditBy.print("240202907", "Dysar Adnant Ilham Nur Asnawi");
    }
}
```

---

## Hasil Eksekusi

### 1. MainProduk
<img width="1918" height="1079" alt="MainProduk" src="https://github.com/user-attachments/assets/1c019c92-82fe-42a1-8c27-b56fc9a0fa0f" />

## Analisis
- Cara kerja program
Program ini terdiri dari tiga class:
1. Produk.java → menyimpan data produk (kode, nama, harga, stok) dan memiliki fungsi untuk menambah atau mengurangi stok.
2. CreditBy.java → menampilkan identitas pembuat program.
3. MainProduk.java → menjalankan program utama, membuat tiga objek produk, menampilkan data tiap produk, lalu mencetak identitas mahasiswa.
Alurnya:
Program dijalankan → objek produk dibuat → data produk ditampilkan → identitas mahasiswa muncul di akhir.

- Perbedaan dengan minggu sebelumnya
1. Minggu ini sudah menggunakan struktur package (model, util, main) sehingga program lebih rapi.
2. Sudah menerapkan enkapsulasi (atribut private dan getter/setter).
3. Kode lebih terorganisir dan menyerupai proyek Java sebenarnya, bukan hanya satu file seperti minggu lalu.

- Kendala dan cara mengatasinya
  - Kendala	Cara Mengatasi
  - Error “package does not exist”	Pastikan folder sesuai dengan nama package (com/upb/agripos/...).
  - Class tidak dikenali	Tambahkan perintah import yang benar di file MainProduk.java.
  - Program tidak bisa dijalankan	Jalankan file MainProduk.java karena hanya itu yang punya main().
---

## Kesimpulan
- Program ini menunjukkan cara membuat dan mengelola objek dengan OOP, memakai package agar kode lebih rapi dan mudah dikembangkan.

## Quiz
1. Mengapa atribut sebaiknya dideklarasikan sebagai private dalam class?  
   **Jawaban:** Agar data di dalam class terlindungi dan tidak bisa diubah langsung dari luar class. Ini menjaga keamanan dan konsistensi data (prinsip enkapsulasi).

2. Apa fungsi getter dan setter dalam enkapsulasi?   
   **Jawaban:** Getter digunakan untuk mengambil nilai atribut, sedangkan setter digunakan untuk mengubah nilai atribut dengan cara yang terkontrol sesuai aturan yang kita tentukan.

3. Bagaimana cara class `Produk` mendukung pengembangan aplikasi POS yang lebih kompleks?  
   **Jawaban:** Class Produk menjadi dasar pengelolaan data barang (kode, nama, harga, stok). Dengan struktur ini, program bisa dikembangkan lebih mudah, misalnya menambah fitur penjualan, laporan stok, atau integrasi ke database.
