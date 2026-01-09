package com.upb.agripos;

public class MainCart {
    public static void main(String[] args) {
        System.out.println("Hello, I am Dysar Adnant Ilham Nur Asnawi-240202907 (Week7)");

        Product Benih = new Product("Benih", "Padi", 25000);
        Product Pupuk = new Product("Pupuk", "Pupuk Urea", 350000);
        Product AlatPertanian = new Product("Alat Pertanian", "Cangkul Baja", 90000);
        Product ObatHama = new Product("Obat Hama", "Racun Serangga", 120000);

        // Demo ShoppingCart (ArrayList)
        ShoppingCart cartlist = new ShoppingCart();
        cartlist.addProduct(Benih);
        cartlist.addProduct(Pupuk);
        cartlist.addProduct(AlatPertanian);
        cartlist.addProduct(ObatHama);
        cartlist.printCart();

        cartlist.removeProduct(Pupuk);
        cartlist.printCart();

        // Demo ShoppingCartMap (Map, quantity)
        ShoppingCart cartmap = new ShoppingCart();
        cartmap.addProduct(Benih);
        cartmap.addProduct(Pupuk);
        cartmap.addProduct(AlatPertanian);
        cartmap.addProduct(ObatHama);
        cartmap.printCart();

        cartmap.removeProduct(Pupuk);
        cartmap.printCart();
    }
}