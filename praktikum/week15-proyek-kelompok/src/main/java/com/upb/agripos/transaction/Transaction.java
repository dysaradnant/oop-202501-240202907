package com.upb.agripos.transaction;

import java.time.LocalDateTime;
import java.util.List;

public class Transaction {
    private String id;
    private List<CartItem> items;
    private double total;
    private LocalDateTime tanggal;

    public Transaction(String id, List<CartItem> items, double total) {
        this.id = id;
        this.items = items;
        this.total = total;
        this.tanggal = LocalDateTime.now();
    }

    // Getter
    public String getId() { return id; }
    public List<CartItem> getItems() { return items; }
    public double getTotal() { return total; }
    public LocalDateTime getTanggal() { return tanggal; }
}