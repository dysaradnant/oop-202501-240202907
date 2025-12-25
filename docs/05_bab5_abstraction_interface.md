# Bab 5 – Abstraction (Abstract Class & Interface)

## Tujuan Pembelajaran
- Mahasiswa mampu **menjelaskan perbedaan abstract class dan interface**.
- Mahasiswa mampu **mendesain abstract class dengan method abstrak** sesuai kebutuhan kasus.
- Mahasiswa mampu **membuat interface dan mengimplementasikannya pada class**.
- Mahasiswa mampu **menerapkan multiple inheritance melalui interface** pada rancangan kelas.
- Mahasiswa mampu **mendokumentasikan kode** (komentar kelas/method, README singkat pada folder minggu).

---

## Ringkasan Teori
**Abstraksi** adalah proses menyederhanakan kompleksitas dengan menampilkan elemen penting dan menyembunyikan detail implementasi.
- **Abstract class**: tidak dapat diinstansiasi, dapat memiliki method abstrak (tanpa badan) dan non-abstrak. Dapat menyimpan state (field).
- **Interface**: kumpulan kontrak (method tanpa implementasi konkret). Sejak Java 8 mendukung default method. Mendukung **multiple inheritance** (class dapat mengimplementasikan banyak interface).
- Gunakan **abstract class** bila ada _shared state_ dan perilaku dasar; gunakan **interface** untuk mendefinisikan kemampuan/kontrak lintas hierarki.

Dalam konteks Agri-POS, **Pembayaran** dapat dimodelkan sebagai abstract class dengan method abstrak `prosesPembayaran()` dan `biaya()`. Implementasi konkritnya: `Cash` dan `EWallet`. Kemudian, interface seperti `Validatable` (mis. verifikasi OTP) dan `Receiptable` (mencetak bukti) dapat diimplementasikan oleh jenis pembayaran yang relevan.

---

## Langkah Praktikum
1. **Abstract Class – Pembayaran**
   - Buat `Pembayaran` (abstract) dengan field `invoiceNo`, `total` dan method:
     - `double biaya()` (abstrak) → biaya tambahan (fee).
     - `boolean prosesPembayaran()` (abstrak) → mengembalikan status berhasil/gagal.
     - `double totalBayar()` (konkrit) → `return total + biaya();`.

2. **Subclass Konkret**
   - `Cash` → biaya = 0, proses = selalu berhasil jika `tunai >= totalBayar()`.
   - `EWallet` → biaya = 1.5% dari `total`; proses = membutuhkan validasi.

3. **Interface**
   - `Validatable` → `boolean validasi();` (contoh: OTP).
   - `Receiptable` → `String cetakStruk();`

4. **Multiple Inheritance via Interface**
   - `EWallet` mengimplementasikan **dua interface**: `Validatable`, `Receiptable`.
   - `Cash` setidaknya mengimplementasikan `Receiptable`.

5. **Main Class**
    - Buat `MainAbstraction.java` untuk mendemonstrasikan pemakaian `Pembayaran` (polimorfik).
    - Tampilkan hasil proses dan struk. Di akhir, panggil `CreditBy.print("[NIM]", "[Nama]")`.

6. **Commit dan Push**
   - Commit dengan pesan: `week5-abstraction-interface`.

---

## Struktur Repositori
```
oop-20251-[nim]/
 └─ praktikum/week5-abstraction-interface/
     ├─ src/main/java/com/upb/agripos/model/
     │   ├─ pembayaran/Pembayaran.java
     │   ├─ pembayaran/Cash.java
     │   └─ pembayaran/EWallet.java
     │
     │   └─ kontrak/
     │       ├─ Validatable.java
     │       └─ Receiptable.java
     ├─ src/main/java/com/upb/agripos/util/
     │   └─ CreditBy.java
     ├─ src/main/java/com/upb/agripos/
     │   └─ MainAbstraction.java
     ├─ screenshots/
     │   └─ hasil.png
     └─ laporan_week5.md
```

---

## Contoh Implementasi Program

### Pembayaran.java (abstract)
```java
package com.upb.agripos.model.pembayaran;

public abstract class Pembayaran {
    protected String invoiceNo;
    protected double total;

    public Pembayaran(String invoiceNo, double total) {
        this.invoiceNo = invoiceNo;
        this.total = total;
    }

    public abstract double biaya();               // fee/biaya tambahan
    public abstract boolean prosesPembayaran();   // proses spesifik tiap metode

    public double totalBayar() {
        return total + biaya();
    }

    public String getInvoiceNo() { return invoiceNo; }
    public double getTotal() { return total; }
}
```

### Interface: Validatable & Receiptable
```java
package com.upb.agripos.model.kontrak;

public interface Validatable {
    boolean validasi(); // misal validasi OTP/ PIN
}
```
```java
package com.upb.agripos.model.kontrak;

public interface Receiptable {
    String cetakStruk();
}
```

### Cash.java (extends Pembayaran, implements Receiptable)
```java
package com.upb.agripos.model.pembayaran;

import com.upb.agripos.model.kontrak.Receiptable;

public class Cash extends Pembayaran implements Receiptable {
    private double tunai;

    public Cash(String invoiceNo, double total, double tunai) {
        super(invoiceNo, total);
        this.tunai = tunai;
    }

    @Override
    public double biaya() {
        return 0.0;
    }

    @Override
    public boolean prosesPembayaran() {
        return tunai >= totalBayar(); // sederhana: cukup uang tunai
    }

    @Override
    public String cetakStruk() {
        return String.format("INVOICE %s | TOTAL: %.2f | BAYAR CASH: %.2f | KEMBALI: %.2f",
                invoiceNo, totalBayar(), tunai, Math.max(0, tunai - totalBayar()));
    }
}
```

### EWallet.java (extends Pembayaran, implements Validatable & Receiptable)
```java
package com.upb.agripos.model.pembayaran;

import com.upb.agripos.model.kontrak.Validatable;
import com.upb.agripos.model.kontrak.Receiptable;

public class EWallet extends Pembayaran implements Validatable, Receiptable {
    private String akun;
    private String otp; // sederhana untuk simulasi

    public EWallet(String invoiceNo, double total, String akun, String otp) {
        super(invoiceNo, total);
        this.akun = akun;
        this.otp = otp;
    }

    @Override
    public double biaya() {
        return total * 0.015; // 1.5% fee
    }

    @Override
    public boolean validasi() {
        return otp != null && otp.length() == 6; // contoh validasi sederhana
    }

    @Override
    public boolean prosesPembayaran() {
        return validasi(); // jika validasi lolos, anggap berhasil
    }

    @Override
    public String cetakStruk() {
        return String.format("INVOICE %s | TOTAL+FEE: %.2f | E-WALLET: %s | STATUS: %s",
                invoiceNo, totalBayar(), akun, prosesPembayaran() ? "BERHASIL" : "GAGAL");
    }
}
```

### MainAbstraction.java
```java
package com.upb.agripos;

import com.upb.agripos.model.pembayaran.*;
import com.upb.agripos.model.kontrak.*;
import com.upb.agripos.util.CreditBy;

public class MainAbstraction {
    public static void main(String[] args) {
        Pembayaran cash = new Cash("INV-001", 100000, 120000);
        Pembayaran ew = new EWallet("INV-002", 150000, "user@ewallet", "123456");

        System.out.println(((Receiptable) cash).cetakStruk());
        System.out.println(((Receiptable) ew).cetakStruk());

    CreditBy.print("[NIM]", "[Nama Mahasiswa]");
    }
}
```

---

## Tugas dan Latihan
- **Tugas 1**: Desain `Pembayaran` (abstract) sesuai kebutuhan dan implementasikan `Cash` dan `EWallet`.
- **Tugas 2**: Buat dua interface (`Validatable`, `Receiptable`) dan implementasikan pada kelas yang relevan (demonstrasi **multiple inheritance via interface**).
- **Tugas 3**: Tampilkan struk pembayaran dari masing-masing metode melalui `MainAbstraction.java`.
- **Latihan Mandiri**: Tambahkan kelas `TransferBank` yang mempunyai biaya tetap (misal Rp3.500) dan memerlukan validasi (implement `Validatable`, `Receiptable`).

**Ketentuan Pengumpulan**:
- Sertakan kode program.
- Sertakan screenshot hasil eksekusi.
- Commit log sesuai instruksi.
- Laporan singkat (`laporan_week5.md`).

---

## Checklist Keberhasilan
- [ ] Abstract class `Pembayaran` memiliki **method abstrak** dan **method konkrit** yang tepat.
- [ ] Interface diimplementasikan **dengan benar** pada kelas yang relevan.
- [ ] **Multiple inheritance via interface** berjalan (kelas mengimplementasikan ≥2 interface).
- [ ] Program menampilkan **struk** dan status proses pembayaran.
- [ ] Output menyertakan **credit by: [NIM] - [Nama]** melalui `CreditBy`.
- [ ] Screenshot & laporan telah dilampirkan.

---

## Quiz
1. Jelaskan perbedaan konsep dan penggunaan **abstract class** dan **interface**.  
   **Jawaban:** …
2. Mengapa **multiple inheritance** lebih aman dilakukan dengan interface pada Java?  
   **Jawaban:** …
3. Pada contoh Agri-POS, bagian mana yang **paling tepat** menjadi abstract class dan mana yang menjadi interface? Jelaskan alasannya.  
   **Jawaban:** …

---

## Referensi
- Liang, Y. D. *Introduction to Java Programming* (Bab 14).  
- Horstmann, C. S. *Core Java Volume I – Fundamentals* (Bab 6).  
- Oracle Docs: *Abstract Methods and Classes*, *Interfaces*.  
