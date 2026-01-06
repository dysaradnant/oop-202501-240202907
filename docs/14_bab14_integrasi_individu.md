# Bab 14 – Integrasi Individu (OOP + Database + GUI)

## Tujuan Pembelajaran
Setelah mengikuti praktikum ini, mahasiswa mampu:
1. Mengintegrasikan konsep OOP (Bab 1–5) ke dalam satu aplikasi yang utuh.
2. Mengimplementasikan rancangan UML + SOLID (Bab 6) menjadi kode nyata.
3. Mengintegrasikan Collections + Keranjang (Bab 7) ke alur aplikasi.
4. Menerapkan exception handling (Bab 9) untuk validasi dan error flow.
5. Menerapkan pattern + unit testing (Bab 10) pada bagian yang relevan.
6. Menghubungkan aplikasi dengan database via DAO + JDBC (Bab 11).
7. Menyajikan aplikasi berbasis JavaFX (Bab 12–13) yang terhubung ke backend.

---

## Deskripsi Tugas
Pada Bab 14, Anda membangun **1 aplikasi individu** bernama *Agri-POS* (kasir sederhana) yang menggabungkan hasil kerja Bab 1–13.

Target akhir:
- Aplikasi JavaFX berjalan.
- Data Produk tersimpan di PostgreSQL (CRUD via DAO).
- Keranjang belanja berjalan (Collections) dan dipakai dalam alur aplikasi.
- Struktur kelas mengikuti UML Bab 6 dan prinsip SOLID.
- Ada minimal 1 unit test JUnit yang relevan.

---

## Aturan Integrasi
1. **Tidak membuat ulang dari nol.** Lanjutkan dan gabungkan kode yang sudah Anda buat di Bab 1–13.
2. **Konsistensi Bab 6 (UML + SOLID) adalah kunci.** Jika di Bab 6 Anda menamai kelas/metode tertentu, Bab 14 harus mengikuti nama itu.
3. **DIP (Dependency Inversion) wajib diterapkan:**
	- `View` (JavaFX) tidak boleh mengakses `DAO` langsung.
	- Akses data harus melewati `Controller` → `Service` → `DAO`.
4. **Tidak ada logika SQL/CRUD di layer GUI.**
5. **Validasi & error flow wajib memakai exception (Bab 9)**, bukan hanya `if` tanpa penanganan.

---

## Cakupan Fitur Minimal

### A. Produk (Bab 2, 5, 11, 12, 13)
- Tampilkan daftar produk dari database (TableView).
- Tambah produk (insert ke database).
- Hapus produk (delete ke database).
- Validasi input (kode kosong, harga/stok tidak valid) dengan exception.

### B. Keranjang (Bab 7, 9)
- Keranjang menyimpan daftar item yang dipilih.
- Keranjang menggunakan **Collections** (mis. `List<CartItem>` dan/atau `Map<code, qty>`).
- Minimal ada 1 alur yang benar-benar memakai keranjang, contoh:
	- "Tambah ke Keranjang" (memakai produk terpilih) dan menampilkan ringkasan keranjang.
	- Hitung total belanja dari isi keranjang.

Catatan: Bab 14 tidak mewajibkan penyimpanan transaksi ke database, tetapi keranjang harus **dipakai** dan **teruji**.

### C. Pattern + Testing (Bab 10)
- Terapkan minimal **1 design pattern** yang relevan (contoh: Singleton untuk koneksi DB, Strategy untuk perhitungan diskon/pembayaran).
- Buat minimal **1 unit test JUnit** yang menguji logika non-UI (mis. perhitungan total keranjang, validasi, atau service).

### D. Identitas Praktikum (Bab 1)
- Saat aplikasi dijalankan, tampilkan identitas di **console log**:
	- `Hello World, I am [Nama]-[NIM]`

---

## Keterkaitan dengan Bab 6 (UML + SOLID)
Bab 14 mewajibkan Anda **menggunakan ulang** (atau memperbarui) artefak desain dari Bab 6:

1. **Use Case**
	- Minimal mencakup: Kelola Produk (lihat/tambah/hapus) dan Kelola Keranjang.
2. **Activity Diagram**
	- Minimal untuk alur Tambah Produk dan Tambah ke Keranjang.
3. **Sequence Diagram**
	- Minimal untuk aksi "Tambah Produk" dan "Hapus Produk": `View` → `Controller` → `Service` → `DAO` → `DB`.
4. **Class Diagram**
	- Menunjukkan pemisahan `View/Controller/Service/DAO/Model`.
	- Menunjukkan relasi keranjang (mis. `Cart`–`CartItem`–`Product`).

Jika Bab 6 Anda sebelumnya belum mencakup keranjang, Anda boleh memperluas UML Bab 6 untuk Bab 14 (tetap sederhana).

---

## Struktur Direktori Praktikum

Gunakan struktur yang sudah tersedia berikut:

```
praktikum/week14-integrasi-individu/
 ├─ src/main/java/com/upb/agripos/
 │   ├─ model/
 │   │   ├─ Product.java
 │   │   ├─ Cart.java
 │   │   └─ CartItem.java
 │   ├─ dao/
 │   │   ├─ ProductDAO.java
 │   │   └─ JdbcProductDAO.java
 │   ├─ service/
 │   │   ├─ ProductService.java
 │   │   └─ CartService.java
 │   ├─ controller/
 │   │   └─ PosController.java
 │   ├─ view/
 │   │   └─ PosView.java
 │   └─ AppJavaFX.java
 ├─ src/test/java/com/upb/agripos/
 │   └─ CartServiceTest.java
 ├─ screenshots/
 │   ├─ app_main.png
 │   └─ junit_result.png
 └─ laporan.md
```

Catatan:
- Nama kelas/package boleh menyesuaikan UML Bab 6 Anda, tetapi struktur layer (MVC + Service + DAO) wajib dipertahankan.

Commit message:
```
week14-integrasi-individu: [fitur] [deskripsi singkat]
```

---

## Ketentuan Laporan (WAJIB)
File `praktikum/week14-integrasi-individu/laporan.md` minimal berisi:

1. **Ringkasan Aplikasi** (fitur yang didukung).
2. **Keterangan Integrasi Bab 1–13** (ringkas, poin-poin).
3. **Artefak UML** (gambar/tautan diagram yang dipakai/diupdate dari Bab 6).
4. **Tabel Traceability Bab 6 → Implementasi** (wajib):

| Artefak | Referensi | Handler/Trigger | Controller/Service | DAO | Dampak |
|---|---|---|---|---|---|
| Use Case | UC-Produk-01 Tambah | Tombol Tambah | `PosController.addProduct()` → `ProductService.insert()` | `ProductDAO.insert()` | DB insert + TableView reload |
| Sequence | SD-Produk-02 Hapus | Tombol Hapus | `PosController.deleteProduct()` → `ProductService.delete(code)` | `ProductDAO.delete(code)` | DB delete + TableView reload |
| Activity | AD-Cart-01 Tambah ke Keranjang | Tombol/aksi Add to Cart | `PosController.addToCart()` → `CartService.addItem()` | - | ringkasan keranjang + total berubah |

5. **Screenshot**
	- `screenshots/app_main.png`
	- `screenshots/junit_result.png`
6. **Kendala & Solusi** (minimal 2 poin).

---

## Checklist Keberhasilan
- [ ] Aplikasi JavaFX berjalan tanpa error.
- [ ] CRUD Produk menggunakan `DAO` (JDBC) dan terhubung PostgreSQL.
- [ ] Keranjang menggunakan Collections dan dipakai dalam alur aplikasi.
- [ ] Ada minimal 1 custom exception untuk validasi/error flow.
- [ ] Ada minimal 1 design pattern yang benar-benar dipakai.
- [ ] Ada minimal 1 unit test JUnit non-UI yang berjalan.
- [ ] Struktur `View` → `Controller` → `Service` → `DAO` konsisten (DIP).
- [ ] Laporan memuat UML + traceability.

---

## Catatan Penilaian (Ringkas)
Fokus penilaian Bab 14:
- Integrasi end-to-end (OOP + DB + GUI) berjalan.
- Konsistensi UML (Bab 6) dengan implementasi.
- Kerapian arsitektur (SOLID/DIP) dan pemisahan layer.
- Ketepatan penggunaan Collections, exception, pattern, dan testing.

