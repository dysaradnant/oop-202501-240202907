# Bab 13 – GUI Lanjutan JavaFX (TableView dan Lambda Expression)

## Tujuan Pembelajaran
Setelah mengikuti praktikum ini, mahasiswa mampu:
1. Menampilkan data menggunakan TableView JavaFX.
2. Mengintegrasikan koleksi objek dengan GUI.
3. Menggunakan lambda expression untuk event handling.
4. Menghubungkan GUI dengan DAO secara penuh.
5. Membangun antarmuka GUI Agri-POS yang lebih interaktif.

---

## Keterkaitan dengan Pertemuan Sebelumnya
Praktikum ini adalah LANJUTAN LANGSUNG dari:
- Pertemuan 12 (GUI Dasar JavaFX)
- Pertemuan 11 (DAO + JDBC)
- Pertemuan 7 (Collections & Lambda)

Mahasiswa TIDAK diperbolehkan membuat project baru dari nol. Lanjutkan dari struktur week12 dan tingkatkan fiturnya.

---

## Keterkaitan dengan Bab 6 (UML + SOLID)
Implementasi TableView pada Bab 13 harus tetap konsisten dengan desain Bab 6:

1. **Use Case**
	- Minimal mencakup alur "Kelola Produk": Lihat Daftar Produk, Tambah Produk, dan Hapus Produk.

2. **Activity Diagram**
	- Alur "Lihat daftar" dan "Hapus" mengikuti activity diagram (pilih item → konfirmasi/validasi → panggil service → reload data → UI terbarui).

3. **Sequence Diagram**
	- Aksi `loadData()` dan tombol "Hapus Produk" mengikuti urutan: `View` → `Controller` → `Service` → `DAO` → `DB`.

4. **Class Diagram & SOLID**
	- TableView hanyalah **View**. Logika bisnis/CRUD tetap berada di `Service`/`DAO` sesuai class diagram.
	- Terapkan **DIP**: View tidak memegang koneksi DB atau query; akses data hanya lewat service.

Jika Bab 6 Anda menggunakan nama kelas/metode berbeda, Bab 13 harus mengikuti nama tersebut (bukan membuat versi baru).

---

## Deskripsi Tugas

### Judul Tugas
GUI Lanjutan Agri-POS dengan TableView dan Lambda Expression

Mahasiswa diminta:
- Mengganti tampilan daftar produk menjadi TableView
- Mengambil data langsung dari database
- Menambahkan tombol interaktif
- Menggunakan lambda expression pada event handler

---

## Spesifikasi Fungsional

GUI harus memiliki:
1. `TableView<Product>` dengan kolom:
	- Kode
	- Nama
	- Harga
	- Stok
2. Tombol:
	- Tambah Produk
	- Hapus Produk
3. Event handler menggunakan lambda expression.

---

## Contoh TableView dan Lambda
```java
btnDelete.setOnAction(e -> {
	 Product selected = table.getSelectionModel().getSelectedItem();
	 if (selected != null) {
		  productService.delete(selected.getCode());
		  loadData(); // reload dari DAO
	 }
});
```

Metode `loadData()` memanggil `ProductDAO.findAll()` melalui `ProductService` dan mengisi `ObservableList<Product>` pada `TableView`.

---

## Integrasi Data

- Data di TableView berasal dari: `ProductDAO.findAll()`
- Perubahan data langsung tersimpan di database PostgreSQL.

---

## Struktur Direktori Praktikum

```
praktikum/week13-gui-lanjutan/
 ├─ src/main/java/com/upb/agripos/
 │   ├─ model/Product.java
 │   ├─ dao/ProductDAO.java
 │   ├─ service/ProductService.java
 │   ├─ view/ProductTableView.java
 │   ├─ controller/ProductController.java
 │   └─ AppJavaFX.java
 ├─ screenshots/
 │   └─ tableview_produk.png     # WAJIB DIISI
 └─ laporan_week13.md            # WAJIB DIISI
```

Commit message:
```
week13-gui-lanjutan: [fitur] [deskripsi singkat]
```

---

## Tugas Individu Pertemuan 13

Mahasiswa wajib:

1. Menampilkan data produk menggunakan TableView.
2. Menggunakan lambda expression untuk event handling.
3. Mengintegrasikan GUI dengan DAO melalui `ProductService`.
4. Menyediakan operasi:
	- tambah produk
	- hapus produk
5. Menambahkan traceability Bab 6 → GUI (lihat ketentuan pengumpulan).

---

## Ketentuan Pengumpulan

Setiap pengumpulan wajib menyertakan:

- Kode program terintegrasi (week12 → week13)
- Screenshot GUI berjalan (`screenshots/tableview_produk.png`)
- Commit log sesuai instruksi
- Laporan `laporan_week13.md`

Isi minimal `laporan_week13.md` WAJIB memuat tabel traceability berikut (boleh ditambah baris sesuai desain Bab 6 Anda):

| Artefak Bab 6 | Referensi | Handler GUI | Controller/Service | DAO | Dampak UI/DB |
|---|---|---|---|---|---|
| Use Case | UC-02 Lihat Daftar Produk | `loadData()` / init view | `ProductController.load()` → `ProductService.findAll()` | `ProductDAO.findAll()` | TableView terisi dari DB |
| Use Case | UC-03 Hapus Produk | Tombol Hapus | `ProductController.delete()` → `ProductService.delete(code)` | `ProductDAO.delete(code)` | DB delete + TableView reload |
| Sequence | SD-02 Hapus Produk | Tombol Hapus | View→Controller→Service | DAO→DB | urutan panggilan sesuai SD |

---

## Catatan Integrasi Akhir

Setelah Pertemuan 13, aplikasi Agri-POS telah memiliki:

- Backend DAO + JDBC
- GUI JavaFX lengkap
- Event-driven architecture
- Siap digunakan sebagai dasar tugas integrasi akhir / UAS

---

## Checklist Kepatuhan (Bab 6)
- Nama use case/kelas/metode konsisten dengan Bab 6.
- `View` (TableView) tidak memanggil `DAO` langsung; semua lewat controller/service.
- Alur `loadData()` dan tombol "Hapus" mengikuti Activity + Sequence Diagram.
- Laporan memuat tabel traceability Bab 6 → GUI.

---

## Ringkasan Integrasi
| Pertemuan | Hasil |
|---------|------|
| 11 | DAO + JDBC |
| 12 | GUI JavaFX dasar |
| 13 | GUI lanjutan + TableView + Lambda |

