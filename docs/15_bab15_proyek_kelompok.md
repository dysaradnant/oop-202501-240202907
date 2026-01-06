# Bab 15 – Proyek Kelompok (Desain Sistem + Implementasi Terintegrasi + Testing + Dokumentasi)

## Tujuan Pembelajaran
Setelah mengikuti proyek ini, mahasiswa mampu:
1. Berkolaborasi dalam tim untuk membangun aplikasi terintegrasi.
2. Mendesain sistem secara lengkap menggunakan UML (Use Case, Class, Sequence).
3. Mengimplementasikan sistem sesuai desain dengan arsitektur yang rapi (SOLID + DIP).
4. Menyusun dokumentasi teknis dan laporan proyek yang lengkap.
5. Menyusun test plan dan test case, serta menjalankan unit/integration test dasar.

---

## Deskripsi Proyek
Tugas Bab 15 adalah **proyek akhir kelompok** berbasis Java yang melanjutkan aplikasi *Agri-POS* dari Bab 14 (integrasi individu), lalu mengembangkannya menjadi sistem yang lebih rapi dan siap demo.

Fokus Bab 15:
- **Dokumentasi lengkap**: desain sistem, arsitektur, database, dan test plan.
- **Implementasi terintegrasi**: GUI (JavaFX) + Service + DAO (JDBC) + PostgreSQL.
- **Testing**: minimal unit test untuk logika non-UI dan bukti eksekusi test.

---

## Aturan Proyek
1. **Kelompok 3–5 orang**
2. **Base code wajib dari Bab 14**
3. **Konsisten dengan Bab 6 (UML + SOLID)**:
	- `View` → `Controller` → `Service` → `DAO` → `DB`.
	- Tidak ada SQL/CRUD di layer GUI.
	- Gunakan exception untuk validasi/error flow.
4. **Kerja kolaboratif wajib terbukti di Git**:
	- Setiap anggota minimal memiliki kontribusi commit yang bermakna.
	- Gunakan pull request (atau minimal review pair) bila memungkinkan.
5. **Tidak menambah “halaman/fitur UI” di luar yang dibutuhkan**; fokus ke integrasi, kualitas desain, dan kelengkapan dokumentasi.

---

## Ruang Lingkup Fitur
Fokus proyek adalah **Agri-POS**.

Ruang lingkup WAJIB mengikuti **Functional Requirements (FR) Bab 6** berikut (gunakan penamaan yang konsisten di UML, kode, dan laporan):

### FR-1 Manajemen Produk
- CRUD Produk: tambah, ubah, hapus, tampil daftar.
- Atribut minimal produk: `kode`, `nama`, `kategori`, `harga`, `stok`.
- Data tersimpan di PostgreSQL via DAO (JDBC) dan tampil di JavaFX.

### FR-2 Transaksi Penjualan
- Buat transaksi baru.
- Tambah produk ke keranjang, ubah qty, hapus item keranjang.
- Hitung total belanja dari isi keranjang.

### FR-3 Metode Pembayaran
- Mendukung pembayaran **Tunai** dan **E-Wallet**.
- Wajib dirancang extensible (OCP): penambahan metode baru tidak mengubah kode inti (gunakan interface/strategy).

### FR-4 Struk dan Laporan
- Setelah pembayaran berhasil, sistem menampilkan struk (cukup *preview* di UI/console; tidak wajib printer fisik).
- Admin dapat melihat laporan penjualan harian atau periodik (tampilan sederhana di UI).

### FR-5 Login dan Hak Akses
- Ada login sederhana (atau pilihan role dengan kredensial sederhana) untuk minimal 2 peran: **Kasir** dan **Admin**.
- Hak akses minimal:
	- Kasir: transaksi (keranjang + checkout).
	- Admin: manajemen produk + laporan.

Catatan UI: boleh menambah komponen seperlunya untuk memenuhi FR-4/FR-5, tetapi tetap jaga agar UI sederhana (mis. Tab/Section di 1 window utama, bukan banyak window).

### FR Opsional
Jika ingin mendapatkan nilai sempurna, kelompok boleh mengambil **maksimal 3 FR opsional** berikut (pilih yang paling relevan), dengan syarat:
- FR opsional harus tercermin di **UML** (Use Case/Activity/Sequence/Class) dan **traceability**.
- Tambahkan **test case** (manual) khusus untuk FR opsional + bukti screenshot.
- Implementasi tetap mengikuti arsitektur (DIP) dan tidak menaruh SQL di GUI.

Daftar FR opsional (selaras Bab 6):
- **OFR-1 Retur/Refund**: pengembalian barang dan refund pembayaran (termasuk skenario gagal).
- **OFR-2 Diskon/Promo/Voucher**: aturan diskon fleksibel (disarankan Strategy) yang mempengaruhi total.
- **OFR-3 Pelanggan & Loyalty**: manajemen pelanggan + poin/tier/histori belanja sederhana.
- **OFR-4 Inventori Lanjutan**: reorder level/notifikasi stok minimum, stok opname, atau batch/expiry.
- **OFR-5 Pembayaran Lanjutan**: QRIS/payment gateway *mock*, split/partial payment, atau refund flow.
- **OFR-6 Audit Log & Approval**: pencatatan aksi penting (hapus/retur/void) + approval supervisor sederhana.
- **OFR-7 Mode Offline (Mock) + Sinkronisasi**: transaksi tetap berjalan saat koneksi putus (mock) + mekanisme sync sederhana.

Boleh menambah perbaikan kualitas (tanpa menambah banyak layar/fitur UI), contoh:
- Validasi yang lebih rapi (exception + pesan error yang jelas)
- Pemisahan layer dan penamaan kelas/metode makin konsisten dengan UML
- Perapihan arsitektur Service/DAO (interface + implementasi)
- Penambahan test case dan cakupan unit test (non-UI)

---

## Artefak Desain & Dokumentasi
Dokumentasi proyek dibuat di `praktikum/week15-proyek-kelompok/laporan.md` dan/atau subfolder `docs/` di dalam folder week15.

Jika diperlukan, Anda BOLEH menambahkan dokumen pendukung berikut (disarankan untuk proyek rapi):
- `docs/01_srs.md` – Software Requirements (daftar requirement + acceptance criteria)
- `docs/02_arsitektur.md` – arsitektur (layering, package map, dependency rules/DIP)
- `docs/03_database.md` – skema/ERD + catatan migrasi/seed
- `docs/04_test_plan.md` – strategi test + daftar test case
- `docs/05_test_report.md` – hasil eksekusi test (manual + unit test), bukti screenshot
- `docs/06_user_guide.md` – cara pakai aplikasi (alur singkat)
- `docs/07_runbook.md` – cara run (DB setup, config env), troubleshooting
- `docs/08_contribution.md` – pembagian tugas + ringkasan kontribusi (link PR/commit)

### 1) Dokumen Desain Sistem
Minimal memuat:
- **Ringkasan masalah dan tujuan sistem**
- **Scope & batasan**
- **Daftar requirement** (functional + non-functional sederhana)
- **Arsitektur** (layering View/Controller/Service/DAO/DB)

### 2) UML Lengkap
Minimal memuat:
- Use Case Diagram
- Class Diagram (entity + service + dao + controller)
- 2 Sequence Diagram untuk alur inti (contoh: Tambah Produk, Hapus Produk, Checkout)

### 3) Database Design
Minimal memuat:
- ERD sederhana (boleh tabel + relasi)
- Skema SQL (DDL) yang dipakai
- Contoh data (seed) secukupnya

### 4) Test Plan + Test Case
Minimal memuat:
- Daftar test (manual): precondition → langkah → expected result
- Minimal 1 bukti unit test (JUnit) berjalan

---

## Implementasi (WAJIB)
### Persyaratan Implementasi
1. JavaFX sebagai GUI utama.
2. PostgreSQL sebagai database.
3. DAO + JDBC dengan `PreparedStatement`.
4. Minimal 1 custom exception domain (contoh: `ValidationException`, `OutOfStockException`).
5. Minimal 1 design pattern yang benar-benar dipakai (contoh: Singleton koneksi DB atau Strategy untuk perhitungan total/diskon).

---

## Struktur Direktori Praktikum

Gunakan struktur yang sudah tersedia berikut:

```
praktikum/week15-proyek-kelompok/
 ├─ src/main/java/com/upb/agripos/
 │   ├─ model/...
 │   ├─ dao/...
 │   ├─ service/...
 │   ├─ controller/...
 │   ├─ view/...
 │   └─ AppJavaFX.java
 ├─ src/test/java/com/upb/agripos/
 │   └─ ...Test.java
 ├─ screenshots/
 │   ├─ app_main.png 
 │   ├─ flow_feature_1.png
 │   └─ junit_result.png
 └─ laporan.md
```

Commit message:
```
week15-proyek-kelompok: [fitur] [deskripsi singkat]
```

---

## Ketentuan Laporan `laporan.md` (WAJIB)
Laporan harus jelas dan rapi, minimal berisi:

1. **Identitas kelompok** (nama anggota + NIM + peran).
2. **Ringkasan sistem** (tema, fitur utama, scope).
3. **Desain sistem** (requirements + arsitektur layer).
4. **UML lengkap** (Use Case, Class, Sequence) + penjelasan singkat.
5. **Desain DB** (DDL/ERD + catatan akses data via DAO).
6. **Test Plan & Test Case**
	- Minimal 8 test case manual untuk alur inti.
	- Minimal 1 unit test JUnit + screenshot hasil.
7. **Traceability** (WAJIB):

| Artefak | Referensi | Implementasi (kelas/metode) | Bukti |
|---|---|---|---|
| FR | FR-1 Manajemen Produk | `ProductController/ProductService/ProductDAO` | screenshot CRUD |
| FR | FR-2 Transaksi Penjualan | `CartService/TransactionService` | screenshot keranjang+total |
| FR | FR-3 Metode Pembayaran | `PaymentMethod` (Strategy) + implementasi Cash/EWallet | bukti pilihan metode |
| FR | FR-4 Struk & Laporan | `ReceiptService/ReportService` | screenshot struk + laporan |
| FR | FR-5 Login & Role | `AuthService` + guard hak akses | screenshot role kasir/admin |
| Sequence | SD-Checkout | View→Controller→Service→DAO→DB (alt gagal) | diagram + penjelasan |
| Test Case | TC-Checkout-01 | langkah uji & expected result | screenshot |

Jika mengambil **FR Opsional (OFR-*)**, tambahkan minimal 1 baris traceability + 1 test case khusus OFR, contoh:

| Artefak | Referensi | Implementasi (kelas/metode) | Bukti |
|---|---|---|---|
| FR (Opsional) | OFR-2 Diskon/Promo | `DiscountStrategy`/`PricingService` | screenshot total berubah |
| Test Case | TC-OFR-2-01 | langkah uji & expected result | screenshot |

8. **Pembagian kerja & kontribusi** (ringkas + bukti commit/PR).
9. **Kendala & solusi** (minimal 3 poin).

---

## Checklist Keberhasilan
- [ ] Proyek berjalan end-to-end (GUI → Service → DAO → DB).
- [ ] UML lengkap tersedia dan konsisten dengan kode.
- [ ] Test plan & test case tersedia (manual) + minimal 1 unit test berjalan.
- [ ] Tidak ada SQL di GUI; layering rapi (DIP).
- [ ] Screenshot bukti fitur & test tersedia.
- [ ] Laporan lengkap dan kontribusi tim jelas.

