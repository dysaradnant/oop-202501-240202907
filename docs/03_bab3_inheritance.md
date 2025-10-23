# Bab 3 – Inheritance (Kategori Produk)

## Tujuan Pembelajaran
- Mahasiswa mampu **menjelaskan konsep inheritance (pewarisan class)** dalam OOP.  
- Mahasiswa mampu **membuat superclass dan subclass** untuk produk pertanian.  
- Mahasiswa mampu **mendemonstrasikan hierarki class** melalui contoh kode.  
- Mahasiswa mampu **menggunakan `super` untuk memanggil konstruktor dan method parent class**.  
- Mahasiswa mampu **membuat laporan praktikum** yang menjelaskan perbedaan penggunaan inheritance dibanding class tunggal.  

---

## Ringkasan Teori
Inheritance adalah mekanisme dalam OOP yang memungkinkan suatu class mewarisi atribut dan method dari class lain.  
- **Superclass**: class induk yang mendefinisikan atribut umum.  
- **Subclass**: class turunan yang mewarisi atribut/method superclass, dan dapat menambahkan atribut/method baru.  
- `super` digunakan untuk memanggil konstruktor atau method superclass.  

Dalam konteks Agri-POS, kita dapat membuat class `Produk` sebagai superclass, kemudian `Benih`, `Pupuk`, dan `AlatPertanian` sebagai subclass. Hal ini membuat kode lebih reusable dan terstruktur.

---

## Langkah Praktikum
1. **Membuat Superclass Produk**  
   - Gunakan class `Produk` dari Bab 2 sebagai superclass.  

2. **Membuat Subclass**  
   - `Benih.java` → atribut tambahan: varietas.  
   - `Pupuk.java` → atribut tambahan: jenis pupuk (Urea, NPK, dll).  
   - `AlatPertanian.java` → atribut tambahan: material (baja, kayu, plastik).  

3. **Membuat Main Class**  
   - Instansiasi minimal satu objek dari tiap subclass.  
   - Tampilkan data produk dengan memanfaatkan inheritance.  

4. **Menambahkan CreditBy**  
   - Panggil class `CreditBy` untuk menampilkan identitas mahasiswa.  

5. **Commit dan Push**  
   - Commit dengan pesan: `week3-inheritance`.  

---

## Struktur Repositori
```
oop-20251-<nim>/
 └─ praktikum/week3-inheritance/
     ├─ src/main/java/com/upb/agripos/model/
     │   ├─ Produk.java
     │   ├─ Benih.java
     │   ├─ Pupuk.java
     │   └─ AlatPertanian.java
     ├─ src/main/java/com/upb/agripos/util/
     │   └─ CreditBy.java
     ├─ src/main/java/com/upb/agripos/
     │   └─ MainInheritance.java
     ├─ screenshots/
     │   └─ hasil.png
     └─ laporan_week3.md
```

---

## Contoh Implementasi Program

### Benih.java
```java
package com.upb.agripos.model;

public class Benih extends Produk {
    private String varietas;

    public Benih(String kode, String nama, double harga, int stok, String varietas) {
        super(kode, nama, harga, stok);
        this.varietas = varietas;
    }

    public String getVarietas() { return varietas; }
    public void setVarietas(String varietas) { this.varietas = varietas; }
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

### MainInheritance.java
```java
package com.upb.agripos;

import com.upb.agripos.model.*;
import com.upb.agripos.util.CreditBy;

public class MainInheritance {
    public static void main(String[] args) {
        Benih b = new Benih("BNH-001", "Benih Padi IR64", 25000, 100, "IR64");
        Pupuk p = new Pupuk("PPK-101", "Pupuk Urea", 350000, 40, "Urea");
        AlatPertanian a = new AlatPertanian("ALT-501", "Cangkul Baja", 90000, 15, "Baja");

        System.out.println("Benih: " + b.getNama() + " Varietas: " + b.getVarietas());
        System.out.println("Pupuk: " + p.getNama() + " Jenis: " + p.getJenis());
        System.out.println("Alat Pertanian: " + a.getNama() + " Material: " + a.getMaterial());

        CreditBy.print("<NIM>", "<Nama Mahasiswa>");
    }
}
```

---

## Tugas dan Latihan
- **Tugas 1**: Buat subclass `Benih`, `Pupuk`, dan `AlatPertanian` yang mewarisi class `Produk`.  
- **Tugas 2**: Tambahkan atribut khusus di masing-masing subclass.  
- **Tugas 3**: Instansiasi minimal satu objek dari setiap subclass dan tampilkan datanya.  
- **Latihan Mandiri**: Buat method tambahan di subclass (contoh: `deskripsi()` menampilkan informasi lengkap produk).  

**Ketentuan Pengumpulan**:  
- Sertakan kode program.  
- Sertakan screenshot hasil eksekusi.  
- Commit log sesuai instruksi.  
- Laporan singkat (`laporan_week3.md`).  

---

## Checklist Keberhasilan
- [ ] Superclass `Produk` digunakan kembali tanpa duplikasi kode.  
- [ ] Subclass `Benih`, `Pupuk`, dan `AlatPertanian` berhasil dibuat dengan atribut tambahan.  
- [ ] Program berjalan menampilkan objek dari setiap subclass.  
- [ ] CreditBy ditampilkan dengan benar.  
- [ ] Commit sesuai instruksi.  
- [ ] Laporan singkat lengkap dengan analisis.  

---

## Quiz
1. Apa keuntungan menggunakan inheritance dibanding membuat class terpisah tanpa hubungan?  
   **Jawaban:** …  

2. Bagaimana cara subclass memanggil konstruktor superclass?  
   **Jawaban:** …  

3. Berikan contoh kasus di POS pertanian selain Benih, Pupuk, dan Alat Pertanian yang bisa dijadikan subclass.  
   **Jawaban:** …  

---

## Referensi
- Liang, Y. D. *Introduction to Java Programming* (Bab 11).  
- Horstmann, C. S. *Core Java Volume I – Fundamentals*.  
- Oracle Docs: [Inheritance](https://docs.oracle.com/javase/tutorial/java/IandI/subclasses.html).  
