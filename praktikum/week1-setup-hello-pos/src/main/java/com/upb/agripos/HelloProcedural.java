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