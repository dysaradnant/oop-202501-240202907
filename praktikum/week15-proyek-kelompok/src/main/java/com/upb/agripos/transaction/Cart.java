package com.upb.agripos.transaction;

import java.util.ArrayList;
import java.util.List;

import com.upb.agripos.product.Product;

public class Cart {
    private List<CartItem> items = new ArrayList<>();

    public void addItem(Product product, int qty) {
        // Cek jika barang sudah ada, tinggal update qty
        for (CartItem item : items) {
            if (item.getProduct().getKode().equals(product.getKode())) {
                item.setQuantity(item.getQuantity() + qty);
                return;
            }
        }
        items.add(new CartItem(product, qty));
    }

    // --- FITUR BARU: Update Jumlah Langsung ---
    public void updateQty(String kode, int newQty) {
        for (CartItem item : items) {
            if (item.getProduct().getKode().equals(kode)) {
                if (newQty > 0) {
                    item.setQuantity(newQty);
                }
                return;
            }
        }
    }

    // --- FITUR BARU: Hapus Satu Item ---
    public void removeItem(String kode) {
        items.removeIf(item -> item.getProduct().getKode().equals(kode));
    }

    public void clear() { items.clear(); }

    public List<CartItem> getItems() { return items; }

    public double getTotal() {
        return items.stream().mapToDouble(CartItem::getSubtotal).sum();
    }
}
