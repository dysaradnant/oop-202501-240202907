package com.upb.agripos.payment;

public class EWalletPayment implements PaymentMethod {
    private PaymentGateway gateway;

    public EWalletPayment() {
        this.gateway = new PaymentGateway();
    }

    @Override
    public boolean pay(double amount) {
        System.out.println("Menghubungi Gateway untuk E-Wallet...");
        return gateway.verify(amount); // Verifikasi saldo sesuai Sequence Diagram
    }
}