# Bab 4 – Polymorphism (Info Produk)

## Tujuan Pembelajaran
- Mahasiswa mampu **menjelaskan konsep polymorphism** dalam OOP.  
- Mahasiswa mampu **membedakan method overloading dan overriding**.  
- Mahasiswa mampu **mengimplementasikan polymorphism (overriding, overloading, dynamic binding)** dalam program.  
- Mahasiswa mampu **menganalisis contoh kasus polymorphism** pada sistem nyata (Agri-POS).  

---

## Ringkasan Teori
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

## Struktur Repositori
```
oop-20251-<nim>/
 └─ praktikum/week4-polymorphism/
     ├─ src/main/java/com/upb/agripos/model/
     │   ├─ Produk.java
     │   ├─ Benih.java
     │   ├─ Pupuk.java
     │   └─ AlatPertanian.java
     ├─ src/main/java/com/upb/agripos/util/
     │   └─ CreditBy.java
     ├─ src/main/java/com/upb/agripos/
     │   └─ MainPolymorphism.java
     ├─ screenshots/
     │   └─ hasil.png
     └─ laporan_week4.md
```

---

## Contoh Implementasi Program

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

### MainPolymorphism.java
```java
package com.upb.agripos;

import com.upb.agripos.model.*;
import com.upb.agripos.util.CreditBy;

public class MainPolymorphism {
    public static void main(String[] args) {
        Produk[] daftarProduk = {
            new Benih("BNH-001", "Benih Padi IR64", 25000, 100, "IR64"),
            new Pupuk("PPK-101", "Pupuk Urea", 350000, 40, "Urea"),
            new AlatPertanian("ALT-501", "Cangkul Baja", 90000, 15, "Baja")
        };

        for (Produk p : daftarProduk) {
            System.out.println(p.getInfo()); // Dynamic Binding
        }

        CreditBy.print("<NIM>", "<Nama Mahasiswa>");
    }
}
```

---

## Tugas dan Latihan
- **Tugas 1**: Implementasikan method overloading `tambahStok`.  
- **Tugas 2**: Override method `getInfo()` di masing-masing subclass.  
- **Tugas 3**: Buat array produk (`Produk[]`) yang berisi objek subclass dan tampilkan hasil `getInfo()` dengan dynamic binding.  
- **Latihan Mandiri**: Buat satu subclass tambahan (misalnya `ObatHama`) yang mengoverride `getInfo()`.  

**Ketentuan Pengumpulan**:  
- Sertakan kode program.  
- Sertakan screenshot hasil eksekusi.  
- Commit log sesuai instruksi.  
- Laporan singkat (`laporan_week4.md`).  

---

## Checklist Keberhasilan
- [ ] Overloading `tambahStok` berhasil.  
- [ ] Overriding `getInfo` pada subclass berjalan.  
- [ ] Dynamic binding berjalan melalui array produk.  
- [ ] Output menampilkan identitas mahasiswa.  
- [ ] Screenshot & laporan disertakan.  

---

## Quiz
1. Apa perbedaan overloading dan overriding?  
   **Jawaban:** …  

2. Bagaimana Java menentukan method mana yang dipanggil dalam dynamic binding?  
   **Jawaban:** …  

3. Berikan contoh kasus polymorphism dalam sistem POS selain produk pertanian.  
   **Jawaban:** …  

---

## Referensi
- Liang, Y. D. *Introduction to Java Programming* (Bab 11).  
- Schildt, H. *Java: The Complete Reference* (Bab 8).  
- Oracle Docs: [Polymorphism](https://docs.oracle.com/javase/tutorial/java/IandI/polymorphism.html).  
