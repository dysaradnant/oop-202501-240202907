package com.upb.agripos.payment;

public class CashPayment implements PaymentMethod {
    @Override
    public boolean pay(double amount) {
        System.out.println("Memproses pembayaran tunai sebesar: " + amount);
        return true; // Tunai selalu dianggap berhasil di sisi sistem
    }
}
