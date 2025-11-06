# Laporan Praktikum Minggu 3
Topik: Polymorphism (Info Produk)

## Identitas
- Nama  : Dysar Adnant Ilham Nur Asnawi
- NIM   : 240202907
- Kelas : 3IKKA

---

## Tujuan
- Mahasiswa mampu **menjelaskan konsep polymorphism** dalam OOP.  
- Mahasiswa mampu **membedakan method overloading dan overriding**.  
- Mahasiswa mampu **mengimplementasikan polymorphism (overriding, overloading, dynamic binding)** dalam program.  
- Mahasiswa mampu **menganalisis contoh kasus polymorphism** pada sistem nyata (Agri-POS).  

---

## Dasar Teori
Polymorphism berarti “banyak bentuk” dan memungkinkan objek yang berbeda merespons panggilan method yang sama dengan cara yang berbeda.  
1. **Overloading** → mendefinisikan method dengan nama sama tetapi parameter berbeda.  
2. **Overriding** → subclass mengganti implementasi method dari superclass.  
3. **Dynamic Binding** → pemanggilan method ditentukan saat runtime, bukan compile time.  

Dalam konteks Agri-POS, misalnya:  
- Method `getInfo()` pada `Produk` dioverride oleh `Benih`, `Pupuk`, `AlatPertanian` untuk menampilkan detail spesifik.  
- Method `tambahStok()` bisa dibuat overload dengan parameter berbeda (int, double).  

---

## Langkah Praktikum
1. **Overloading**  
   - Tambahkan method `tambahStok(int jumlah)` dan `tambahStok(double jumlah)` pada class `Produk`.  

2. **Overriding**  
   - Tambahkan method `getInfo()` pada superclass `Produk`.  
   - Override method `getInfo()` pada subclass `Benih`, `Pupuk`, dan `AlatPertanian`.  

3. **Dynamic Binding**  
   - Buat array `Produk[] daftarProduk` yang berisi objek `Benih`, `Pupuk`, dan `AlatPertanian`.  
   - Loop array tersebut dan panggil `getInfo()`. Perhatikan bagaimana Java memanggil method sesuai jenis objek aktual.  

4. **Main Class**  
   - Buat `MainPolymorphism.java` untuk mendemonstrasikan overloading, overriding, dan dynamic binding.  

5. **CreditBy**  
   - Tetap panggil `CreditBy.print("<NIM>", "<Nama>")`.  

6. **Commit dan Push**  
   - Commit dengan pesan: `week4-polymorphism`.  

---

## Kode Program

### Produk.java (Overloading & getInfo default)
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

    public void tambahStok(int jumlah) {
        this.stok += jumlah;
    }

    public void tambahStok(double jumlah) {
        this.stok += (int) jumlah;
    }

    public String getInfo() {
        return "Produk: " + nama + " (Kode: " + kode + ")";
    }
}
```

### Benih.java (Overriding)
```java
package com.upb.agripos.model;

public class Benih extends Produk {
    private String varietas;

    public Benih(String kode, String nama, double harga, int stok, String varietas) {
        super(kode, nama, harga, stok);
        this.varietas = varietas;
    }

    @Override
    public String getInfo() {
        return "Benih: " + super.getInfo() + ", Varietas: " + varietas;
    }
}
```

### Pupuk.java
```java
package com.upb.agripos.model;

public class Pupuk extends Produk {
    private String jenis;

    public Pupuk(String kode, String nama, double harga, int stok, String jenis) {
        super(kode, nama, harga, stok);
        this.jenis = jenis;
    }

    public String getJenis() { return jenis; }
    public void setJenis(String jenis) { this.jenis = jenis; }
}
```

### AlatPertanian.java
```java
package com.upb.agripos.model;

public class AlatPertanian extends Produk {
    private String material;

    public AlatPertanian(String kode, String nama, double harga, int stok, String material) {
        super(kode, nama, harga, stok);
        this.material = material;
    }

    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }
}
```
### ObatHama.java
```java
package com.upb.agripos.model;

public class ObatHama extends Produk {
    private String bahanAktif;
    private String jenisHama;

    public ObatHama(String kode, String nama, double harga, int stok, String bahanAktif, String jenisHama) {
        super(kode, nama, harga, stok);
        this.bahanAktif = bahanAktif;
        this.jenisHama = jenisHama;
    }

    @Override
    public String getInfo() {
        return "Obat Hama: " + super.getInfo() +
               ", Bahan Aktif: " + bahanAktif +
               ", Untuk Hama: " + jenisHama;
    }
}
```

### MainInheritance.java
```java
package com.upb.agripos;

import com.upb.agripos.model.*;
import com.upb.agripos.util.CreditBy;

public class MainPolymorphism {
    public static void main(String[] args) {
        Produk[] daftarProduk = {
                new Benih("BNH-001", "Benih Padi IR64", 25000, 100, "IR64"),
                new Pupuk("PPK-101", "Pupuk Urea", 350000, 40, "Urea"),
                new AlatPertanian("ALT-501", "Cangkul Baja", 90000, 15, "Baja"),
                new ObatHama("OBH-301", "Racun Serangga X", 120000, 25, "Deltametrin", "Wereng")
        };

        for (Produk p : daftarProduk) {
            System.out.println(p.getInfo()); // Dynamic Binding
        }

        CreditBy.print("240202907", "Dysar Adnant Ilham Nur Asnawi");
    }
}
```

---

## Hasil Eksekusi

### MainInheritance
<img width="1919" height="1079" alt="MainPolymorphism" src="https://github.com/user-attachments/assets/6ee924ef-991c-42c8-a950-93a7a2f0b6ec" />

## Analisis
Pada praktikum minggu ini, konsep polymorphism berhasil diterapkan melalui tiga mekanisme utama:
1. Overloading (Compile-time Polymorphism)
- Method `tambahStok()` dibuat dengan dua versi: satu menerima parameter bertipe `int`, satu lagi bertipe `double`.  
2. Overriding (Runtime Polymorphism)
- Method `getInfo()` pada superclass `Produk` dioverride oleh subclass `Benih`, `Pupuk`, `AlatPertanian`, `ObatHama`, dan `Pestisida`.
- Setiap subclass menampilkan informasi berbeda sesuai jenis produknya.  
- Ini menunjukkan bahwa subclass dapat mengganti perilaku bawaan dari superclass tanpa mengubah struktur dasarnya.
3. Dynamic Binding
-  Saat array Produk[] daftarProduk berisi berbagai objek subclass, method `getInfo()` yang dipanggil akan disesuaikan dengan tipe objek aktual pada runtime.
Kendala yang sempat muncul adalah memastikan setiap subclass benar-benar melakukan override terhadap method `getInfo()` dengan penulisan `@Override`, agar tidak tertukar dengan method baru.
---

## Kesimpulan
Subclass dapat menambahkan perilaku spesifik tanpa mengubah struktur utama superclass.
Selain itu, Overloading dan Overriding membuat kode lebih efisien, fleksibel, dan mudah dibaca.
Dengan menerapkan polymorphism, program menjadi lebih mudah dikembangkan karena setiap objek dapat berperilaku sesuai jenisnya masing-masing.
Konsep ini juga membantu dalam pembuatan sistem besar seperti Agri-POS, di mana berbagai produk memiliki karakteristik berbeda tetapi tetap dapat dikelola melalui satu antarmuka umum (Produk).
Secara keseluruhan, penerapan polymorphism meningkatkan modularitas, memudahkan pemeliharaan kode, dan memperkuat prinsip Object-Oriented Programming (OOP) secara menyeluruh.

## Quiz
1. Apa perbedaan overloading dan overriding?  
   **Jawaban:** Overloading terjadi ketika dua atau lebih method memiliki nama yang sama tetapi parameter berbeda (jumlah atau tipe datanya berbeda). Pemilihan method dilakukan saat compile time.Sedangkan Overriding terjadi ketika subclass menggantikan implementasi method dari superclass dengan isi yang berbeda, tetapi nama dan parameternya sama. Pemilihan method dilakukan saat runtime.

2. Bagaimana Java menentukan method mana yang dipanggil dalam dynamic binding? 
   **Jawaban:** Java menentukan method yang dipanggil berdasarkan objek aktual yang direferensikan, bukan berdasarkan tipe variabel referensinya.
Artinya, jika variabel bertipe  `Produk` menyimpan objek `Pupuk`, maka saat `getInfo()` dipanggil, method `getInfo()` milik `Pupuk` yang dijalankan, bukan milik `Produk`.

3. Berikan contoh kasus polymorphism dalam sistem POS selain produk pertanian. 
   **Jawaban:** Dalam sistem POS toko elektronik:
   - Superclass: `Produk`
   - Subclass: `Laptop`, `Smartphone`, `Aksesoris`
Masing-masing subclass dapat mengoverride method `getInfo()` untuk menampilkan informasi berbeda seperti “Prosesor”, “Kapasitas Baterai”, atau “Jenis Aksesoris”, tetapi semua dipanggil menggunakan referensi `Produk`.
Ini menunjukkan penerapan polymorphism di dunia nyata — satu tipe umum (`Produk`) yang dapat mewakili berbagai bentuk produk berbeda.
