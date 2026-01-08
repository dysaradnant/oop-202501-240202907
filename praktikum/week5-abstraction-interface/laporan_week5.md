# Laporan Praktikum Minggu 5
Topik: Abstraction (Abstract Class & Interface)

## Identitas
- Nama  : Dysar Adnant Ilham Nur Asnawi
- NIM   : 240202907
- Kelas : 3IKKA

---

## Tujuan
- Mahasiswa mampu **menjelaskan perbedaan abstract class dan interface**.
- Mahasiswa mampu **mendesain abstract class dengan method abstrak** sesuai kebutuhan kasus.
- Mahasiswa mampu **membuat interface dan mengimplementasikannya pada class**.
- Mahasiswa mampu **menerapkan multiple inheritance melalui interface** pada rancangan kelas.
- Mahasiswa mampu **mendokumentasikan kode** (komentar kelas/method, README singkat pada folder minggu).

---

## Dasar Teori
**Abstraksi** adalah proses menyederhanakan kompleksitas dengan menampilkan elemen penting dan menyembunyikan detail implementasi.
- **Abstract class**: tidak dapat diinstansiasi, dapat memiliki method abstrak (tanpa badan) dan non-abstrak. Dapat menyimpan state (field).
- **Interface**: kumpulan kontrak (method tanpa implementasi konkret). Sejak Java 8 mendukung default method. Mendukung **multiple inheritance** (class dapat mengimplementasikan banyak interface).
- Gunakan **abstract class** bila ada _shared state_ dan perilaku dasar; gunakan **interface** untuk mendefinisikan kemampuan/kontrak lintas hierarki.

Dalam konteks Agri-POS, **Pembayaran** dapat dimodelkan sebagai abstract class dengan method abstrak `prosesPembayaran()` dan `biaya()`. Implementasi konkritnya: `Cash` dan `EWallet`. Kemudian, interface seperti `Validatable` (mis. verifikasi OTP) dan `Receiptable` (mencetak bukti) dapat diimplementasikan oleh jenis pembayaran yang relevan.

---

## Langkah Praktikum
1. 1. **Abstract Class – Pembayaran**
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

## Kode Program

### Pambayaran.java
```java
package com.upb.agripos.model.pembayaran;

public abstract class Pembayaran {
    protected String invoiceNo;
    protected double total;

    public Pembayaran(String invoiceNo, double total) {
        this.invoiceNo = invoiceNo;
        this.total = total;
    }

    public abstract double biaya();               // biaya tambahan
    public abstract boolean prosesPembayaran();   // proses spesifik tiap metode

    public double totalBayar() {
        return total + biaya();
    }

    public String getInvoiceNo() { return invoiceNo; }
    public double getTotal() { return total; }
}
```

### Cash.java
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
        return tunai >= totalBayar();
    }

    public double kembalian() {
        return Math.max(0, tunai - totalBayar());
    }

    @Override
    public String cetakStruk() {
        return String.format(
            "\n=== PEMBAYARAN CASH ===\n" +
            "Invoice : %s\n" +
            "Total   : %.1f\n" +
            "Biaya   : %.1f\n" +
            "Bayar   : %.1f\n" +
            "Kembali : %.1f\n" +
            "Status  : %s\n",
            invoiceNo, total, biaya(), tunai, kembalian(),
            prosesPembayaran() ? "BERHASIL" : "GAGAL"
        );
    }
}
```

### EWallet.java
```java
package com.upb.agripos.model.pembayaran;

import com.upb.agripos.model.kontrak.Validatable;
import com.upb.agripos.model.kontrak.Receiptable;

public class EWallet extends Pembayaran implements Validatable, Receiptable {
    private String akun;
    private String otp;

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
        return otp != null && otp.length() == 6;
    }

    @Override
    public boolean prosesPembayaran() {
        return validasi();
    }

    @Override
    public String cetakStruk() {
        return String.format(
            "\n=== PEMBAYARAN E-WALLET ===\n" +
            "Invoice : %s\n" +
            "Akun    : %s\n" +
            "Total   : %.1f\n" +
            "Biaya   : %.1f\n" +
            "Status  : %s\n",
            invoiceNo, akun, total, biaya(),
            prosesPembayaran() ? "BERHASIL" : "GAGAL"
        );
    }
}
```

### TransferBank.java
```java
package com.upb.agripos.model.pembayaran;

import com.upb.agripos.model.kontrak.Validatable;
import com.upb.agripos.model.kontrak.Receiptable;

public class TransferBank extends Pembayaran implements Validatable, Receiptable {
    private String rekeningTujuan;
    private String kodeValidasi;

    public TransferBank(String invoiceNo, double total, String rekeningTujuan, String kodeValidasi) {
        super(invoiceNo, total);
        this.rekeningTujuan = rekeningTujuan;
        this.kodeValidasi = kodeValidasi;
    }

    @Override
    public double biaya() {
        return 3500.0; // biaya tetap
    }

    @Override
    public boolean validasi() {
        return kodeValidasi != null && kodeValidasi.length() >= 4;
    }

    @Override
    public boolean prosesPembayaran() {
        return validasi();
    }

    @Override
    public String cetakStruk() {
        return String.format(
            "\n=== PEMBAYARAN TRANSFER BANK ===\n" +
            "Invoice : %s\n" +
            "Rek     : %s\n" +
            "Total   : %.1f\n" +
            "Biaya   : %.1f\n" +
            "Status  : %s\n",
            invoiceNo, rekeningTujuan, total, biaya(),
            prosesPembayaran() ? "BERHASIL" : "GAGAL"
        );
    }
}
```

### Validatable.java
```java
package com.upb.agripos.model.kontrak;

public interface Validatable {
    boolean validasi(); // contoh: OTP, kode, PIN, dll.
}
```

### Receiptable.java
```java
package com.upb.agripos.model.kontrak;

public interface Receiptable {
    String cetakStruk(); // mencetak hasil pembayaran
}
```

### CreditBy.java
```java
package com.upb.agripos.util;

/**
 * Utilitas untuk menampilkan identitas pembuat program.
 */
public class CreditBy {
    public static void print(String nim, String nama) {
        System.out.println("\n---");
        System.out.println("Credit by: " + nim + " - " + nama);
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
        Pembayaran ewallet = new EWallet("INV-002", 150000, "dv@ewallet", "123456");
        Pembayaran transfer = new TransferBank("INV-003", 200000, "9876543210", "9999");

        System.out.println(((Receiptable) cash).cetakStruk());
        System.out.println(((Receiptable) ewallet).cetakStruk());
        System.out.println(((Receiptable) transfer).cetakStruk());

        CreditBy.print("240202907", "Dysar Adnant Ilham Nur Asnawi");
    }
}
```
---

## Hasil Eksekusi
<img width="1919" height="1079" alt="MainAbstraction" src="https://github.com/user-attachments/assets/44294094-07af-43af-927e-0c766282940c" />

---

## Analisis
- Program ini menggunakan abstraksi supaya tidak perlu menulis ulang detail cara kerja setiap jenis pembayaran.
Semua jenis pembayaran (seperti `Cash`, `EWallet`, dan `TransferBank`) dibuat berdasarkan satu kerangka utama, yaitu class Pembayaran.
- Setiap kelas turunan punya aturan sendiri untuk menghitung biaya dan cara memproses pembayaran.
Lalu, ada dua interface (`Validatable` dan `Receiptable`) yang digunakan agar kelas bisa punya beberapa kemampuan sekaligus
- Untuk kesulitannya, sempat bingung memastikan semua kelas turunan sudah melengkapi method dari class abstrak dan interface.
Tapi bisa diatasi dengan fitur bantuan IDE (seperti auto generate method) supaya tidak ada yang ketinggalan.
---

## Kesimpulan
Dengan memakai abstract class dan interface, program jadi lebih mudah diatur dan dikembangkan. KKita bisa menambah jenis pembayaran baru tanpa harus mengubah bagian lain dari program.
Selain itu, konsep multiple inheritance lewat interface membuat program lebih fleksibel dan tetap aman.
Secara keseluruhan, penerapan abstraksi ini membuat kode lebih terstruktur dan efisien.

---

## Quiz
1. Jelaskan perbedaan konsep dan penggunaan abstract class dan interface.  
   **Jawaban:** 
   - Abstract class adalah kelas dasar yang bisa punya atribut (data) dan juga method dengan isi (implementasi) atau tanpa isi (abstrak). Biasanya digunakan kalau beberapa kelas punya kesamaan sifat atau data, tapi cara kerjanya bisa berbeda.

   - Interface berisi kumpulan method tanpa isi (kontrak). Digunakan kalau kita ingin beberapa kelas punya kemampuan yang sama, walaupun tidak saling berhubungan.

2. Mengapa multiple inheritance lebih aman dilakukan dengan interface pada Java?  
   **Jawaban:** Karena interface tidak menyimpan data atau variabel instance, jadi tidak ada konflik saat satu kelas mengimplementasikan banyak interface.
Berbeda kalau pewarisan dilakukan dari beberapa class sekaligus, bisa terjadi benturan (misalnya dua class punya atribut atau method dengan nama sama).
Jadi, Java membolehkan multiple inheritance lewat interface karena lebih aman dan terhindar dari kebingungan pewarisan data.  

3. Pada contoh Agri-POS, bagian mana yang paling tepat menjadi abstract class dan mana yang menjadi interface? Jelaskan alasannya.
   **Jawaban:** 
- Abstract class: `Pembayaran`
Karena semua jenis pembayaran (Cash, EWallet, TransferBank) punya data dan logika dasar yang sama, seperti `invoiceNo`, `total`, dan `totalBayar()`.

- Interface: `Validatable` dan `Receiptable` 
Karena keduanya hanya berisi kemampuan tambahan yang bisa dimiliki oleh kelas tertentu, seperti melakukan validasi OTP (`Validatable`) atau mencetak struk (`Receiptable`).
Jadi `Pembayaran` dijadikan abstract class karena punya struktur umum dan data bersama, sedangkan `Validatable` dan `Receiptable` dijadikan interface karena hanya berfungsi sebagai kontrak perilaku tambahan.
