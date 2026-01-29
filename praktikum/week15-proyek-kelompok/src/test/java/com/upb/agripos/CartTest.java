package com.upb.agripos;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import com.upb.agripos.product.Product;
import com.upb.agripos.transaction.Cart;

public class CartTest {
    private Cart cart;
    private Product p1;
    private Product p2;

    @Before
    public void setUp() {
        cart = new Cart();
        // Menggunakan constructor Product yang sudah kita buat di package product
        p1 = new Product("P001", "Pupuk Urea", "Pupuk", 50000.0, 10);
        p2 = new Product("P002", "Benih Jagung", "Benih", 20000.0, 5);
    }

    @Test
    public void testAddItem() {
        cart.addItem(p1, 2);
        assertEquals(1, cart.getItems().size());
        assertEquals(100000.0, cart.getTotal(), 0.01); // 50rb x 2
    }

    @Test
    public void testTotalCalculation() {
        cart.addItem(p1, 1); // 50rb
        cart.addItem(p2, 2); // 40rb
        assertEquals(90000.0, cart.getTotal(), 0.01); // Total harus 90rb
    }

    @Test
    public void testClearCart() {
        cart.addItem(p1, 1);
        cart.clear();
        assertEquals(0, cart.getItems().size());
        assertEquals(0.0, cart.getTotal(), 0.01);
    }
}
