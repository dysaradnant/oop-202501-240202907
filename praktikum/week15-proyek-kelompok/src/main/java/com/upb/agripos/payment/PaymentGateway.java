package com.upb.agripos.payment;

public class PaymentGateway {
    public boolean verify(double amount) {
        // Simulasi: Anggap saja saldo selalu cukup untuk demo ini
        // Sesuai alur 'Verifikasi Saldo' di Activity Diagram
        return amount < 10000000;
    }
}