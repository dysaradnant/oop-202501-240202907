package com.upb.agripos;

public class MainExceptionDemo {
    public static void main(String[] args) {

        System.out.println("Hello, I am Dysar Adnant Ilham Nur Asnawi-240202907 (Week9)");

        ShoppingCart cart = new ShoppingCart();
        Product pupuk = new Product("P01", "Pupuk Organik", 25000, 3);

        // Uji quantity salah
        try {
            cart.addProduct(pupuk, -1);
        } catch (InvalidQuantityException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Uji hapus produk yang tidak ada
        try {
            cart.removeProduct(pupuk);
        } catch (ProductNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Uji stok tidak cukup
        try {
            cart.addProduct(pupuk, 5);
            cart.checkout();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
