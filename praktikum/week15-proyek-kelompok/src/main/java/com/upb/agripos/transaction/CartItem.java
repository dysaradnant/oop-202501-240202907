package com.upb.agripos.transaction;

import com.upb.agripos.product.Product;

public class CartItem {
    private Product product;
    private int quantity;
    private double subtotal;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.subtotal = product.getHarga() * quantity;
    }

    public Product getProduct() { return product; }

    public int getQuantity() { return quantity; }

    // PERBAIKAN: Saat setQuantity, Subtotal WAJIB dihitung ulang!
    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.subtotal = this.product.getHarga() * quantity; // Ini kuncinya
    }

    public double getSubtotal() { return subtotal; }
}