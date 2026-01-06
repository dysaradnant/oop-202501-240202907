# Bab 12 – GUI Dasar JavaFX (Event-Driven Programming)

## Tujuan Pembelajaran
Setelah mengikuti praktikum ini, mahasiswa mampu:
1. Menjelaskan konsep event-driven programming.
2. Membangun antarmuka grafis sederhana menggunakan JavaFX.
3. Membuat form input data produk.
4. Menampilkan daftar produk pada GUI.
5. Mengintegrasikan GUI dengan modul backend yang telah dibuat (DAO & Service).

---

## Keterkaitan dengan Pertemuan Sebelumnya
Praktikum ini WAJIB menggunakan hasil dari:
- Pertemuan 7: Collections
- Pertemuan 10: MVC & Design Pattern
- Pertemuan 11: DAO + JDBC (CRUD PostgreSQL)

Mahasiswa TIDAK diperbolehkan membuat ulang logika CRUD di layer GUI. Gunakan `ProductService` yang memanggil `ProductDAO` di backend.

---

## Keterkaitan dengan Bab 6 (UML + SOLID)
Implementasi GUI pada Bab 12 harus merealisasikan artefak desain Bab 6:

1. **Use Case**
	- Minimal mencakup alur "Kelola Produk" (Tambah Produk dan Lihat Daftar Produk).
	- Nama use case/aktor pada laporan Bab 6 harus konsisten dengan fitur GUI.

2. **Activity Diagram**
	- Alur Tambah Produk di GUI mengikuti activity diagram (validasi input → panggil service → update tampilan).

3. **Sequence Diagram**
	- Untuk aksi tombol "Tambah Produk", urutan interaksi mengikuti sequence diagram: `View` → `Controller` → `Service` → `DAO` → `DB`, lalu kembali untuk memperbarui UI.

4. **Class Diagram & SOLID**
	- Struktur kelas mengikuti class diagram Bab 6 (entitas/model, controller, service, DAO).
	- Terapkan **DIP**: `View` tidak boleh memanggil `DAO` langsung; akses data harus melalui `Service` (dan/atau interface DAO bila sudah dirancang di Bab 6).
 
Jika Bab 6 Anda menggunakan nama kelas/metode berbeda, Bab 12 harus mengikuti nama tersebut (bukan membuat versi baru).

---

## Deskripsi Tugas

### Judul Tugas
GUI Dasar Daftar Produk Agri-POS Menggunakan JavaFX

Mahasiswa diminta membangun aplikasi JavaFX yang:
- Bersifat event-driven
- Memiliki form input produk
- Memiliki tombol interaksi
- Terhubung dengan `ProductService` / `ProductDAO`

---

## Spesifikasi Fungsional

GUI minimal harus memiliki:
1. TextField:
	- Kode Produk
	- Nama Produk
	- Harga
	- Stok
2. Button:
	- Tambah Produk
3. Area tampilan data:
	- ListView atau TextArea (TableView digunakan pada Pertemuan 13)

---

## Arsitektur yang Digunakan
Disarankan menggunakan pendekatan MVC:

- Model   : `Product`
- View    : JavaFX Form
- Controller : `ProductController`
- Data Access : `ProductDAO` / `ProductService`

---

## Contoh Event Handling JavaFX
```java
btnAdd.setOnAction(event -> {
	 Product p = new Product(
		  txtCode.getText(),
		  txtName.getText(),
		  Double.parseDouble(txtPrice.getText()),
		  Integer.parseInt(txtStock.getText())
	 );
	 productService.insert(p); // productService mengarah ke DAO
	 listView.getItems().add(p.getCode() + " - " + p.getName());
});
```

---

## Struktur Direktori Praktikum

```
praktikum/week12-gui-dasar/
 ├─ src/main/java/com/upb/agripos/
 │   ├─ model/Product.java
 │   ├─ dao/ProductDAO.java
 │   ├─ service/ProductService.java
 │   ├─ view/ProductFormView.java
 │   ├─ controller/ProductController.java
 │   └─ AppJavaFX.java
 ├─ screenshots/
 │   └─ gui_form_produk.png
 └─ laporan_week12.md
```

Commit message:
```
week12-gui-dasar: [fitur] [deskripsi singkat]
```

---

## Tugas Individu Pertemuan 12

Mahasiswa wajib:

1. Membuat GUI JavaFX dasar dengan form input produk.
2. Menggunakan event handler JavaFX.
3. Mengintegrasikan GUI dengan DAO melalui `ProductService`.
4. Menampilkan data produk hasil input.
5. Menambahkan traceability Bab 6 → GUI (lihat ketentuan pengumpulan).

---

## Ketentuan Pengumpulan

Setiap pengumpulan wajib menyertakan:

- Kode program
- Screenshot GUI berjalan (`screenshots/gui_form_produk.png`)
- Commit log sesuai instruksi
- Laporan `laporan_week12.md`

Isi minimal `laporan_week12.md` WAJIB memuat tabel traceability berikut (boleh ditambah baris sesuai desain Bab 6 Anda):

| Artefak Bab 6 | Referensi | Handler GUI | Controller/Service | DAO | Dampak UI/DB |
|---|---|---|---|---|---|
| Use Case | UC-01 Tambah Produk | Tombol Tambah | `ProductController.add()` → `ProductService.insert()` | `ProductDAO.insert()` | UI list bertambah + DB insert |
| Activity | AD-01 Tambah Produk | Tombol Tambah | (metode sesuai Anda) | (metode sesuai Anda) | validasi → simpan → tampil |
| Sequence | SD-01 Tambah Produk | Tombol Tambah | View→Controller→Service | DAO→DB | urutan panggilan sesuai SD |

---

## Catatan Penting

GUI pada pertemuan ini masih sederhana.
Data akan ditampilkan lebih terstruktur menggunakan TableView pada Pertemuan 13.

---

## Checklist Kepatuhan (Bab 6)
- Nama use case/kelas/metode konsisten dengan Bab 6.
- `View` hanya mengurus UI (event + binding), bukan query/CRUD.
- Alur tombol "Tambah" mengikuti Activity + Sequence Diagram.
- Laporan memuat tabel traceability Bab 6 → GUI.

---

