# Laporan Praktikum Minggu 6
Topik: Desain Arsitektur Sistem dengan UML dan Prinsip SOLID

## Identitas
- Nama  : Dysar Adnant Ilham Nur Asnawi
- NIM   : 240202907
- Kelas : 3IKKA

---

## Tujuan
Mahasiswa mampu:
1. Mahasiswa mampu mengidentifikasi kebutuhan sistem ke dalam diagram UML.
2. Mahasiswa mampu menggambar UML Class Diagram dengan relasi antar class yang tepat.
3. Mahasiswa mampu menjelaskan prinsip desain OOP (SOLID).
4. Mahasiswa mampu menerapkan minimal dua prinsip SOLID dalam kode program.

---

## Deskripsi Sistem
Sistem Agri-POS (Agricultural Point of Sale) adalah aplikasi Point of Sale (POS) yang dirancang untuk mengelola transaksi penjualan dan inventori produk pertanian.  

Sistem ini mendukung dua peran utama:
1. Kasir: Bertanggung jawab atas proses transaksi penjualan, mulai dari pemilihan produk hingga penyelesaian pembayaran dan pencetakan struk.
2. Admin: Bertanggung jawab atas manajemen produk (tambah, ubah, hapus, tampil) dan melihat laporan penjualan harian/periodik.
Desain arsitektur Agri-POS berfokus pada Maintainability dan Extensibility dengan menerapkan prinsip SOLID, terutama untuk mendukung penambahan metode pembayaran baru di masa depan tanpa memodifikasi modul inti.
---

## Penjelasan Diagram UML
## 1. Use Case Diagram
Diagram ini mengidentifikasi dua aktor utama: Kasir dan Admin, dan memetakan fungsionalitas sistem.
- Aktor Kasir: Melakukan Login System, Proses Checkout, Bayar, dan Cetak Struk.
- Aktor Admin: Melakukan Login System, Kelola Produk (mencakup CRUD), dan Lihat Laporan.

## 2. Sequence Diagram
Diagram ini memvisualisasikan interaksi antar objek (lifeline) selama proses transaksi hingga pembayaran selesai. Objek layanan utama adalah Sistem Transaksi, Sistem Produk, dan Sistem Pembayaran.
- Awal Transaksi: Kasir memulai dengan buatTransaksiBaru(), lalu memanggil tambahProduk() yang memicu cekStok() di Sistem Produk.
- Proses Pembayaran (Split alt):
   - TUNAI: Sistem Pembayaran mendelegasikan ke Pembayaran Tunai, yang menghitung kembalian.
   - E-WALLET: Sistem Pembayaran mendelegasikan ke Pembayaran E-Wallet, yang berinteraksi dengan Payment Gateway melalui messages authorize() dan capture().
   - Error Handling (alt E-Wallet): Terdapat fragment alt untuk skenario Cukup (auth dan capture berhasil) dan Tidak Cukup (saldoTidakCukup dikembalikan oleh Payment Gateway yang memicu error dari Sistem Pembayaran).
- Penyelesaian: Setelah pembayaran berhasil, Sistem Transaksi menerima notifikasi pembayaranSelesai(), memicu pembuatan struk.

## 3. Class Diagram
Diagram ini memvisualisasikan struktur kelas dengan fokus pada abstraksi pembayaran untuk memenuhi SOLID.
- Kelas Layanan Utama (SRP): LayananProduk, LayananTransaksi, dan LayananPembayaran.
- Pola OCP/DIP: Kelas LayananPembayaran bergantung pada Interface IMetodePembayaran. Implementasi konkret seperti PembayaranTunai dan PembayaranEWallet mengimplementasikan interface ini. Ini memisahkan business logic dari detail implementasi.

## 4. Activity Diagram
Diagram ini menggambarkan alur kerja di antara swimlane Kasir dan Sistem. Alur utama meliputi Validasi Stok, Hitung Total, dan Pemilihan Metode Pembayaran. Diagram secara eksplisit menangani dua skenario:
- Gagal Stok: Sistem akan meminta Kasir Hapus item stok habis.
- Gagal Bayar E-Wallet: Sistem mengarahkan kembali Kasir untuk Pilih metode lain.
---

## Penerapan Prinsip SOLID

| Prinsip | Penerapan dalam Desain | Contoh pada Class Diagram |
| :--- | :--- | :--- |
| **S - Single Responsibility** | Setiap *class* hanya punya satu tanggung jawab utama (Service vs. Data/Akses). | **`LayananProduk`** hanya urus logika produk, **`LayananPembayaran`** hanya urus proses pembayaran. |
| **O - Open/Closed** | Sistem **terbuka untuk ditambah** (*ekstensi*), **tertutup untuk diubah** (*modifikasi*). | *Interface* **`IMetodePembayaran`** bisa ditambah implementasi baru (**`QRISPayment`**) tanpa ubah kode `LayananPembayaran` yang sudah ada. |
| **L - Liskov Substitution** | Subclass (**`PembayaranTunai`**, **`PembayaranEWallet`**) dapat menggantikan *parent* (**`IMetodePembayaran`**) tanpa masalah. | Semua implementasi `IMetodePembayaran` mengikuti kontrak `bayar()`. |
| **I - Interface Segregation** | *Interface* kecil-kecil dan spesifik. | **`IMetodePembayaran`** hanya ada metode **`bayar()`** yang spesifik dibutuhkan klien. |
| **D - Dependency Inversion** | Bergantung pada **abstraksi**, bukan **konkret** (detail implementasi). | **`LayananPembayaran`** panggil **`IMetodePembayaran`** (*interface*). |
---


## Traceability Matrix (FR → UML → Kelas)

| FR (Functional Req.) | Use Case | Activity/Sequence | Class/Interface Realisasi |
| :--- | :--- | :--- | :--- |
| **1. Manajemen Produk** | Kelola Produk | - | `LayananProduk`, `Produk` |
| **2. Transaksi Penjualan** | Proses Checkout | Seq Transaksi, Activity Checkout | `LayananTransaksi`, `Transaksi` |
| **3. Metode Pembayaran** | Bayar | Seq Pembayaran | `LayananPembayaran`, `IMetodePembayaran`, `PembayaranTunai`, `PembayaranEWallet` |
| **4. Pencetakan Struk** | Cetak Struk | Activity Checkout | (Asumsi ada `StrukService`/modul) |
| **5. Login & Hak Akses** | Login System | - | (Asumsi ada `LayananOtentikasi`/modul) |
---


## Kesimpulan

Sistem Agri-POS berhasil dirancang sebagai solusi POS yang modular, ekstensibel, dan maintainable. Dengan mengadopsi empat diagram UML dan prinsip SOLID (terutama OCP/DIP pada modul pembayaran), sistem ini mampu memenuhi semua kebutuhan fungsional dasar, termasuk manajemen produk, transaksi penjualan, berbagai metode pembayaran, dan hak akses yang berbeda.

---

## Quiz
1. Jelaskan perbedaan aggregation dan composition serta berikan contoh penerapannya pada desain Anda.  
   **Jawaban:** Perbedaan utama antara Aggregation dan Composition terletak pada kepemilikan dan siklus hidup. Composition adalah asosiasi yang kuat, di mana objek bagian tidak dapat eksis tanpa objek keseluruhan (misalnya, `Transaksi` dan `TransactionItem`). Sebaliknya, Aggregation adalah asosiasi yang lemah, memungkinkan objek bagian ada secara independen dari objek keseluruhan (misalnya, `LayananProduk` dan `Produk`).

2. Bagaimana prinsip Open/Closed dapat memastikan sistem mudah dikembangkan? 
   **Jawaban:** Prinsip OCP memastikan sistem mudah dikembangkan dengan membuatnya terbuka untuk ekstensi tetapi tertutup untuk modifikasi. Hal ini dicapai dengan mengandalkan Interface (`IMetodePembayaran`), di mana fungsionalitas baru (misalnya, `QRISPayment`) ditambahkan sebagai kelas implementasi baru tanpa mengubah kode inti (`LayananPembayaran`). Pendekatan ini mengurangi risiko bug pada bagian sistem yang sudah stabil.  

3. Mengapa Dependency Inversion Principle (DIP) meningkatkan testability? Berikan contoh penerapannya.  
   **Jawaban:** DIP meningkatkan testability karena memutus ketergantungan antara logika bisnis tingkat tinggi dan detail implementasi tingkat rendah. Dengan bergantung pada abstraksi (`IMetodePembayaran`), pengembang dapat mengganti implementasi nyata (seperti API Payment Gateway) dengan Mock Object saat pengujian unit. Hal ini memungkinkan pengembang untuk mengontrol perilaku eksternal dan menguji logika bisnis secara cepat dan terisolasi.
