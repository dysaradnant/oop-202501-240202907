# Laporan Praktikum Minggu 10
Topik: Design Pattern (Singleton, MVC) dan Unit Testing menggunakan JUnit

## Identitas
- Nama  : Dysar Adnant Ilham Nur Asnawi
- NIM   : 240202907
- Kelas : 3IKKA

---

## Tujuan
1. Menjelaskan konsep dasar design pattern dalam rekayasa perangkat lunak.
2. Mengimplementasikan Singleton Pattern dengan benar.
3. Menjelaskan dan menerapkan Model–View–Controller (MVC) pada aplikasi sederhana.
4. Membuat dan menjalankan unit test menggunakan JUnit.
5. Menganalisis manfaat penerapan design pattern dan unit testing terhadap kualitas perangkat lunak.


---

## Dasar Teori
### 1. Design Pattern

Design pattern adalah solusi desain yang telah teruji untuk menyelesaikan masalah umum dalam pengembangan perangkat lunak. Fokus minggu ini:
- Singleton Pattern
- MVC (Model–View–Controller)

### 2. Singleton Pattern

Tujuan: Menjamin suatu class hanya memiliki satu instance dan menyediakan titik akses global.

Karakteristik:
- Constructor `private`
- Atribut `static instance`
- Method `static getInstance()`
Penerapan pada Agri-POS: koneksi database atau service global yang tidak boleh lebih dari satu instance.

### 3. MVC (Model–View–Controller)

Memisahkan tanggung jawab aplikasi:

| Komponen | Tanggung Jawab |
|---------|------------------|
| Model   | Data dan logika bisnis |
| View    | Tampilan/output |
| Controller | Penghubung Model dan View |

Contoh Struktur MVC Sederhana:
- Model → `Product`
- View → `ConsoleView`
- Controller → `ProductController`

---

## Kode Program
### 1. Model
```java
package com.upb.agripos.model;

public class Product {
    private final String code;
    private final String name;

    public Product(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() { return code; }
    public String getName() { return name; }
}
```

### 2. View
```java
package com.upb.agripos.view;

public class ConsoleView {
    public void showMessage(String message) {
        System.out.println(message);
    }
}

```

### 3. Controller
```java
package com.upb.agripos.controller;

import com.upb.agripos.model.Product;
import com.upb.agripos.view.ConsoleView;

public class ProductController {
    private final Product model;
    private final ConsoleView view;

    public ProductController(Product model, ConsoleView view) {
        this.model = model;
        this.view = view;
    }

    public void showProduct() {
        view.showMessage("Produk: " + model.getCode() + " - " + model.getName());
    }
}
```
### 4. Main Program (Integrasi MVC)
```java
package com.upb.agripos;

import com.upb.agripos.model.Product;
import com.upb.agripos.view.ConsoleView;
import com.upb.agripos.controller.ProductController;

public class AppMVC {
    public static void main(String[] args) {
        System.out.println("Hello, I am Dysar Adnant Ilham Nur Asnawi-240202907 (Week10)");

        Product product = new Product("P01", "Pupuk Organik");
        ConsoleView view = new ConsoleView();
        ProductController controller = new ProductController(product, view);

        controller.showProduct();
    }
}

```

---

## Hasil Eksekusi 
### AppMVC
<img width="1919" height="1079" alt="AppMVC" src="https://github.com/user-attachments/assets/bc15cde7-34f2-421a-96ca-f87b62d0ca0a" />
### junit_result
<img width="1919" height="1079" alt="junit_result" src="https://github.com/user-attachments/assets/a53e9c22-105a-462a-8366-fc78f44b5c89" />

---

## Analisis
- Jelaskan bagaimana kode berjalan.   
   **Jawaban:** Program dijalankan melalui class AppMVC sebagai titik awal aplikasi. Pada saat program dijalankan, method main() akan dieksekusi. Pertama, sistem menampilkan identitas mahasiswa pada console. Selanjutnya, objek Product dibuat sebagai Model yang berfungsi menyimpan data produk berupa kode dan nama produk. Kemudian, objek ConsoleView dibuat sebagai View yang bertugas menampilkan informasi ke layar. Setelah itu, objek ProductController dibuat dengan menghubungkan Model dan View. Controller berperan mengambil data dari Model dan meneruskannya ke View melalui method showProduct(). Ketika method tersebut dipanggil, data produk ditampilkan ke console sesuai dengan konsep Model–View–Controller (MVC). Selain itu, penerapan Singleton Pattern pada class DatabaseConnection memastikan bahwa hanya terdapat satu instance koneksi yang digunakan selama aplikasi berjalan. Hal ini mencegah pembuatan objek koneksi secara berulang dan meningkatkan efisiensi sistem.

- Apa perbedaan pendekatan minggu ini dibanding minggu sebelumnya.     
   **Jawaban:** Pada minggu sebelumnya, program dibuat secara langsung dalam satu atau beberapa class tanpa pemisahan tanggung jawab yang jelas antara data, tampilan, dan logika aplikasi. Pendekatan tersebut masih bersifat sederhana dan cenderung menggabungkan banyak fungsi dalam satu bagian program.Pada minggu ini, pendekatan yang digunakan lebih terstruktur dengan menerapkan design pattern, yaitu Singleton dan MVC. MVC memisahkan peran Model, View, dan Controller sehingga kode menjadi lebih rapi, mudah dibaca, serta mudah dikembangkan. Selain itu, pada minggu ini juga diterapkan unit testing menggunakan JUnit, yang belum digunakan pada minggu sebelumnya. Dengan adanya unit testing, setiap fungsi dapat diuji secara terpisah untuk memastikan hasilnya sesuai dengan yang diharapkan.
- Kendala yang dihadapi dan cara mengatasinya.     
   **Jawaban:** Kendala utama yang dihadapi adalah kesalahan dalam pengaturan package dan struktur folder, terutama pada pembuatan unit test JUnit, sehingga kode awalnya tidak dapat dijalankan dan ditandai dengan error. Selain itu, perintah mvn test tidak dapat dijalankan karena Apache Maven belum terkonfigurasi pada sistem. Kendala tersebut diatasi dengan memastikan struktur folder src/main/java dan src/test/java sesuai dengan package yang digunakan, serta menjalankan unit test langsung melalui fitur Testing pada Visual Studio Code tanpa menggunakan terminal Maven. Setelah konfigurasi diperbaiki, unit test berhasil dijalankan dan menghasilkan status passed tanpa error.
---

## Kesimpulan
Berdasarkan hasil praktikum yang telah dilakukan, penerapan design pattern Singleton dan Model–View–Controller (MVC) dapat meningkatkan kualitas struktur program menjadi lebih terorganisir dan mudah dipahami. Singleton memastikan bahwa hanya terdapat satu instance objek yang digunakan, sehingga penggunaan sumber daya menjadi lebih efisien. Sementara itu, penerapan MVC memisahkan tanggung jawab antara data, tampilan, dan logika aplikasi sehingga memudahkan proses pengembangan dan pemeliharaan program. Selain itu, penggunaan unit testing dengan JUnit membantu memastikan bahwa setiap fungsi pada program berjalan sesuai dengan yang diharapkan serta meminimalkan kemungkinan terjadinya kesalahan. Dengan demikian, praktikum ini memberikan pemahaman yang lebih baik mengenai pentingnya penerapan design pattern dan pengujian dalam pengembangan perangkat lunak yang terstruktur dan berkualitas.

---

## Quiz
1.  Mengapa constructor pada Singleton harus bersifat private?  
   **Jawaban:** Constructor pada Singleton harus bersifat private agar objek dari class tersebut tidak dapat dibuat secara langsung menggunakan keyword new dari luar class. Dengan demikian, pembuatan instance hanya dapat dilakukan melalui method getInstance(), sehingga jumlah instance tetap satu sesuai konsep Singleton.  

2. Jelaskan manfaat pemisahan Model, View, dan Controller.  
   **Jawaban:** Pemisahan Model, View, dan Controller (MVC) membuat kode program menjadi lebih terstruktur, mudah dipahami, dan mudah dipelihara. Model menangani data dan logika bisnis, View menangani tampilan, dan Controller mengatur alur data antara Model dan View. Dengan pemisahan ini, perubahan pada satu bagian tidak akan memengaruhi bagian lainnya secara langsung. 

3. Apa peran unit testing dalam menjaga kualitas perangkat lunak?  
   **Jawaban:** Unit testing berperan untuk memastikan setiap fungsi atau komponen berjalan sesuai dengan yang diharapkan. Dengan melakukan pengujian secara terpisah, kesalahan dapat terdeteksi lebih awal, sehingga mengurangi risiko bug pada tahap pengembangan lanjutan dan meningkatkan keandalan perangkat lunak.  

4. Apa risiko jika Singleton tidak diimplementasikan dengan benar?  
   **Jawaban:** Jika Singleton tidak diimplementasikan dengan benar, dapat terjadi pembuatan lebih dari satu instance dari class yang seharusnya hanya satu. Hal ini dapat menyebabkan pemborosan sumber daya, inkonsistensi data, serta potensi konflik dalam penggunaan objek, terutama pada aplikasi yang bersifat global seperti koneksi database.
