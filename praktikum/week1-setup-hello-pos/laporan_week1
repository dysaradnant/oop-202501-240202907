# Laporan Praktikum Minggu 1
Topik: Pengenalan Paradigma dan Setup Proyek

## Identitas
- Nama  : Dysar Adnant Ilham Nur Asnawi
- NIM   : 240202907
- Kelas : 3IKKA

---

## Tujuan
- Mahasiswa mampu mendefinisikan paradigma prosedural, OOP, dan fungsional.
- Mahasiswa mampu membandingkan kelebihan dan keterbatasan tiap paradigma.
- Mahasiswa mampu memberikan contoh program sederhana untuk masing-masing paradigma.
- Mahasiswa aktif dalam diskusi kelas (bertanya, menjawab, memberi opini).
---

## Dasar Teori
Paradigma pemrograman adalah cara pandang dalam menyusun program:
- **Prosedural**: program dibangun sebagai rangkaian perintah (fungsi/prosedur).
- **OOP (Object-Oriented Programming)**: program dibangun dari objek yang memiliki data (atribut) dan perilaku (method).
- **Fungsional**: program dipandang sebagai pemetaan fungsi matematika, lebih menekankan ekspresi dan transformasi data.

Dalam konteks Agri-POS, OOP membantu memodelkan entitas nyata seperti Produk, Transaksi, dan Pembayaran sebagai objek. Dengan demikian, sistem lebih mudah dikembangkan dan dipelihara.  

---

## Langkah Praktikum
1. Setup Project
- Pastikan sudah menginstall **JDK** (Java Development Kit), IDE (misal: IntelliJ IDEA, VS Code, NetBeans), Git, PostgreSQL, dan JavaFX di komputer.
- Buat folder project `oop-pos-<nim>`.
- Inisialisasi repositori Git.
- Buat struktur awal `src/main/java/com/upb/agripos/`.
- Pastikan semua tools dapat berjalan (uji dengan membuat dan menjalankan program Java sederhana).

2. Program Sederhana dalam 3 Paradigma
- Prosedural: program untuk menghitung total harga dua produk.
- OOP: class `Produk` dengan atribut nama dan harga, buat minimal tiga objek, lalu hitung total.
- Fungsional: gunakan Stream atau lambda untuk menghitung total harga dari minimal tiga objek.

3. Commit dan Push
- Commit dengan pesan: week1-setup-hello-pos.


---

## Kode Program
(Tuliskan kode utama yang dibuat, contoh:  

### 1. Prosedural
```java
// HelloProcedural.java
public class HelloProcedural {
   public static void main(String[] args) {
      String nim = "240202907";
      String nama = "Dysar Adnant Ilham Nur Asnawi";
      String[] produk = {"Beras", "Pupuk", "Benih"};
      int[] harga = {10000, 15000, 12000};
      int total = 0;
      System.out.println("Hello POS World");
      System.out.println("NIM: " + nim + ", Nama: " + nama);
      System.out.println("Daftar Produk:");
      for (int i = 0; i < produk.length; i++) {
         System.out.println("- " + produk[i] + ": " + harga[i]);
         total += harga[i];
      }
      System.out.println("Total harga semua produk: " + total);
   }
}
```

### 2. OOP
```java
// HelloOOP.java
class Produk {
   String nama;
   int harga;
   Produk(String nama, int harga) {
      this.nama = nama;
      this.harga = harga;
   }
}

public class HelloOOP {
   public static void main(String[] args) {
      String nim = "240202907";
      String namaMhs = "Dysar Adnant Ilham Nur Asnawi";
      Produk[] daftar = {
         new Produk("Beras", 10000),
         new Produk("Pupuk", 15000),
         new Produk("Benih", 12000)
      };
      int total = 0;
      System.out.println("Hello POS World");
      System.out.println("NIM: " + nim + ", Nama: " + namaMhs);
      System.out.println("Daftar Produk:");
      for (Produk p : daftar) {
         System.out.println("- " + p.nama + ": " + p.harga);
         total += p.harga;
      }
      System.out.println("Total harga semua produk: " + total);
   }
}
```

### 3. Fungsional
```java
// HelloFunctional.java
import java.util.*;
import java.util.stream.*;
public class HelloFunctional {
   public static void main(String[] args) {
      String nim = "240202907";
      String nama = "Dysar Adnant Ilham Nur Asnawi";
      List<String> produk = Arrays.asList("Beras", "Pupuk", "Benih");
      List<Integer> harga = Arrays.asList(10000, 15000, 12000);
      System.out.println("Hello POS World");
      System.out.println("NIM: " + nim + ", Nama: " + nama);
      System.out.println("Daftar Produk:");
      IntStream.range(0, produk.size())
         .forEach(i -> System.out.println("- " + produk.get(i) + ": " + harga.get(i)));
      int total = harga.stream().mapToInt(Integer::intValue).sum();
      System.out.println("Total harga semua produk: " + total);
   }
}
```

---

## Hasil Eksekusi
praktikum/week1-setup-hello-pos/screenshots/HelloFuctional.png
praktikum/week1-setup-hello-pos/screenshots/HelloOOP.png
praktikum/week1-setup-hello-pos/screenshots/HelloProcedural.png

## Analisis
1. HelloProcedural.java
- Pemrograman prosedural yaitu program dijalankan berdasarkan urutan instruksi dari atas ke bawah.
- Data, dan proses diletakkan dalam satu fungsi main().
- Program menggunakan array dan perulangan manual (for) untuk menampilkan produk serta menghitung total harga.
- Keterkaitan data & Data (produk, harga) tidak terikat dalam struktur khusus, hanya berupa dua array terpisah.

2. HelloOOP.java
- Program dibagi menjadi class Produk (mewakili entitas nyata) dan class utama HelloOOP sebagai pengendali logika.
- Setiap produk diwakili oleh objek Produk yang memiliki atribut nama dan harga.
- Data dan perilaku bisa digabungkan dalam satu class (misalnya bisa menambahkan metode untuk menghitung diskon atau menampilkan info produk).

3. HelloFunctional.java
- Program menggunakan pemrograman fungsional, yang menekankan penggunaan fungsi murni, stream, dan operasi deklaratif.
- Program ini memanfaatkan IntStream dan stream() untuk mengelola dan menghitung data secara lebih ringkas dan ekspresif.
- Data disimpan dalam List, lalu diproses dengan Stream API dan lambda expression.

## Kesimpulan
- Prosedural: Fokus pada bagaimana melakukan (langkah demi langkah).
- OOP: Fokus pada apa yang direpresentasikan (objek dan relasi antarobjek).
- Fungsional: Fokus pada apa yang ingin dicapai (hasil transformasi data).
---

## Quiz
1. Apakah OOP selalu lebih baik dari prosedural? 
   **Jawaban:** Tidak selalu. OOP memang lebih rapi dan mudah dikembangkan untuk program besar, tapi untuk program kecil yang sederhana, cara prosedural justru lebih cepat dan efisien digunakan.

2. Kapan functional programming lebih cocok digunakan dibanding OOP atau prosedural?  
   **Jawaban:** Paradigma fungsional lebih tepat dipakai ketika program berfokus pada pengolahan data dalam jumlah besar, seperti perhitungan statistik, analisis data, atau proses yang bisa dijalankan secara paralel. Cara ini membuat kode lebih ringkas dan mudah dikelola.

3. Bagaimana paradigma memengaruhi maintainability dan scalability aplikasi?  
   **Jawaban:** - Pada prosedural, kode biasanya sulit dikembangkan karena semua logika disatukan dalam satu alur.
                - Pada OOP, program lebih mudah diperbaiki dan diperluas karena setiap bagian dipisahkan dalam bentuk class dan objek. Sedangkan fungsional membantu menjaga kode tetap bersih dan minim kesalahan, meski perlu pemahaman lebih dalam untuk menggunakannya dengan baik.

4. Mengapa OOP lebih cocok untuk mengembangkan aplikasi POS dibanding prosedural?
   **Jawaban:** Karena dalam aplikasi POS ada banyak bagian yang berbeda, seperti produk, pelanggan, transaksi, dan kasir. Dengan OOP, setiap bagian bisa dibuat sebagai objek tersendiri sehingga program lebih teratur, mudah dikembangkan, dan tidak saling mengganggu saat ada perubahan.

5. Bagaimana paradigma fungsional dapat membantu mengurangi kode berulang (boilerplate code)? 
   **Jawaban:** Paradigma fungsional memungkinkan kita menulis kode yang lebih singkat dengan memanfaatkan fungsi-fungsi seperti map, filter, atau reduce. Dengan begitu, kita tidak perlu menulis perulangan manual yang panjang, sehingga kode jadi lebih bersih dan mudah dibaca.
