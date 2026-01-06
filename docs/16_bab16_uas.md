# Bab 16 – UAS (Presentasi & Demo Proyek Akhir)

## Tujuan
Pada UAS, mahasiswa mampu:
1. Mempresentasikan sistem yang telah dibangun secara jelas dan sistematis.
2. Mendemonstrasikan aplikasi berjalan sesuai spesifikasi.
3. Menjawab pertanyaan penguji terkait desain, implementasi, dan pengujian.
4. Menyusun dokumentasi akhir yang rapi dan dapat dipakai untuk evaluasi.

---

## Bentuk UAS
UAS berbentuk **presentasi dan demo aplikasi Agri-POS (hasil Bab 15)**.

Output UAS menilai:
- Kesiapan aplikasi (running end-to-end)
- Konsistensi desain (UML) dengan implementasi
- Kualitas arsitektur (SOLID/DIP, layering)
- Testing & dokumentasi
- Kemampuan menjelaskan dan menjawab pertanyaan

---

## Aturan
1. Aplikasi yang diuji adalah hasil proyek Bab 15 (proyek kelompok).
2. Tidak boleh mengganti tema/proyek di minggu UAS.
3. Setiap anggota harus bisa menjelaskan:
	- arsitektur layer (View/Controller/Service/DAO)
	- desain database
	- minimal 1 sequence diagram + implementasinya
	- test case dan hasil pengujian

---

## Materi Demo
Minimal demo menampilkan:
1. Aplikasi JavaFX terbuka dan berjalan.
2. Alur inti fitur Agri-POS (minimal 2 alur):
	- lihat daftar produk dari DB
	- tambah/hapus produk (membuktikan perubahan di DB)
	- keranjang + total
	- checkout ringkas
3. Bukti koneksi DB (data berubah setelah insert/delete).
4. Bukti testing:
	- jalankan unit test JUnit (atau tunjukkan hasil run terakhir) dan jelaskan apa yang diuji.

---

## Artefak yang Dikumpulkan
Pengumpulan UAS berfokus pada **dokumentasi penuh** (karena implementasi/kode sudah dikumpulkan pada Bab 14 dan Bab 15).

Gunakan struktur yang sudah tersedia berikut:

```
praktikum/week16-uas/
 ├─ screenshots/
 │   ├─ demo_flow_1.png
 │   ├─ demo_flow_2.png
 │   └─ junit_result.png
 └─ laporan.md
```

Catatan:
- **Tidak perlu mengumpulkan ulang source code** di week16.
- Wajib mencantumkan **link repo** dan **tag/commit rilis** (versi yang didemo), yang merujuk ke hasil Bab 15.

Commit message (bila ada perubahan dokumentasi UAS):
```
week16-uas: docs [deskripsi singkat]
```

---

## Format Laporan UAS (`laporan.md`)
`laporan.md` berperan sebagai **paket dokumentasi akhir**. Minimal berisi:
1. Identitas (kelompok, anggota, peran).
2. Link repo + tag/commit rilis yang didemo.
3. Ringkasan fitur + scope akhir.
4. Ringkasan arsitektur (diagram singkat atau poin layer).
5. Daftar artefak desain (UML) + perubahan terakhir (jika ada).
6. Ringkasan testing:
	- daftar test case manual utama (referensi TC di Bab 15)
	- bukti unit test (screenshot)
	- ringkasan test report (manual + unit) / link dokumen test report Bab 15
7. Ringkasan kendala terakhir + solusi.
8. Pembagian kontribusi final (ringkas).

Tambahan yang disarankan (untuk dokumentasi penuh):
- Link/rekap dokumen Bab 15: SRS, arsitektur, database, test plan, test report, user guide, runbook
- Release notes singkat (perubahan terakhir sebelum demo)
- Link slide presentasi dan/atau link video demo (jika diminta)

---

## Checklist Release
- [ ] Proyek dapat dijalankan dan didemo tanpa error.
- [ ] Koneksi database jelas (konfigurasi tidak hardcode password di kode publik).
- [ ] Layering rapi: View tidak akses DAO langsung.
- [ ] Minimal 1 unit test berjalan.
- [ ] Screenshot demo + unit test tersedia.
- [ ] Laporan UAS rapi dan bisa dipakai menilai.
- [ ] Link tag/commit rilis untuk demo tercantum.

---

## Kriteria Penilaian
- Presentasi jelas & sistematis
- Demo sesuai spesifikasi
- Jawaban pertanyaan penguji tepat
- Dokumentasi dan laporan rapi

