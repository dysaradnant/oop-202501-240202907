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