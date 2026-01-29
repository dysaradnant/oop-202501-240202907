package com.upb.agripos.payment;

public class PaymentService {
    /**
     * Memproses pembayaran sesuai dengan alur di Sequence Diagram
     */
    public boolean processPayment(double total, String type) {
        // 1. Dapatkan metode pembayaran dari Factory
        PaymentMethod method = PaymentFactory.getPaymentMethod(type);

        if (method == null) {
            System.err.println("Metode pembayaran tidak valid");
            return false;
        }

        // 2. Eksekusi pembayaran (Polymorphism)
        return method.pay(total);
    }
}