package com.upb.agripos;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {

    private final Map<Product, Integer> items = new HashMap<>();

    public void addProduct(Product p, int qty)
            throws InvalidQuantityException {

        if (qty <= 0) {
            throw new InvalidQuantityException("Quantity harus lebih dari 0");
        }
        items.put(p, items.getOrDefault(p, 0) + qty);
    }

    public void removeProduct(Product p)
            throws ProductNotFoundException {

        if (!items.containsKey(p)) {
            throw new ProductNotFoundException("Produk tidak ada di keranjang");
        }
        items.remove(p);
    }

    public void checkout()
            throws InsufficientStockException {

        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            if (entry.getKey().getStock() < entry.getValue()) {
                throw new InsufficientStockException(
                        "Stok tidak cukup untuk " + entry.getKey().getName()
                );
            }
        }

        // kurangi stok jika semua aman
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            entry.getKey().reduceStock(entry.getValue());
        }
    }
}
