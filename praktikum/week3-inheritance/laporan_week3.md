# Laporan Praktikum Minggu 3
Topik: Inheritance (Kategori Produk)

## Identitas
- Nama  : Dysar Adnant Ilham Nur Asnawi
- NIM   : 240202907
- Kelas : 3IKKA

---

## Tujuan
- Mahasiswa mampu **menjelaskan konsep inheritance (pewarisan class)** dalam OOP.  
- Mahasiswa mampu **membuat superclass dan subclass** untuk produk pertanian.  
- Mahasiswa mampu **mendemonstrasikan hierarki class** melalui contoh kode.  
- Mahasiswa mampu **menggunakan `super` untuk memanggil konstruktor dan method parent class**.  
- Mahasiswa mampu **membuat laporan praktikum** yang menjelaskan perbedaan penggunaan inheritance dibanding class tunggal.  

---

## Dasar Teori
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

## Kode Program

### Produk.java
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

        CreditBy.print("240202907", "Dysar Adnant Ilham Nur Asnawi");
    }
}
```

---

## Hasil Eksekusi

### MainInheritance
<img width="1919" height="1079" alt="MainInheritance" src="https://github.com/user-attachments/assets/b02124ee-e87a-4f4e-a34e-780eb92eba09" />

## Analisis

Dengan inheritance, atribut umum seperti `kode`, `nama`, `harga`, dan `stok` cukup didefinisikan sekali di class `Produk`.
Subclass hanya menambahkan atribut khusus sesuai kebutuhan.
Hal ini membuat kode:
  - Lebih singkat dan mudah diperluas.
  - Lebih mudah dikelola ketika ada perubahan pada atribut dasar produk.
Tanpa inheritance, setiap class harus menulis ulang atribut yang sama, sehingga rawan inkonsistensi.

---

## Kesimpulan
- Penerapan inheritance dalam aplikasi POS pertanian membantu menjaga struktur kode agar lebih efisien, konsisten, dan mudah dikembangkan untuk kategori produk baru.

## Quiz
1. Apa keuntungan menggunakan inheritance dibanding membuat class terpisah tanpa hubungan?
   **Jawaban:** Inheritance membuat kode lebih efisien dan mudah dipelihara karena atribut dan method umum cukup didefinisikan sekali di superclass. Subclass dapat mewarisi serta menambah fungsionalitas baru tanpa menulis ulang kode yang sama.

2. Bagaimana cara subclass memanggil konstruktor superclass?
   **Jawaban:** Subclass menggunakan keyword `super()` di dalam konstruktor untuk memanggil konstruktor milik superclass dan menginisialisasi atribut yang diwarisi.
   
3. Berikan contoh kasus di POS pertanian selain Benih, Pupuk, dan Alat Pertanian yang bisa dijadikan subclass. 
   **Jawaban:** Contohnya `Pestisida` sebagai subclass dari `Produk`, dengan atribut tambahan seperti `bahanAktif` dan `dosisAnjuran`.
