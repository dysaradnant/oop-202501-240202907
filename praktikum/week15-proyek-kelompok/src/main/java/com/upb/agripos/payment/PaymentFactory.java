package com.upb.agripos.payment;

public class PaymentFactory {
    public static PaymentMethod getPaymentMethod(String type) {
        if (type == null) {
            return null;
        }

        if (type.equalsIgnoreCase("CASH")) {
            // Gunakan 'new CashPayment()' agar lulus uji 'instanceof'
            return new CashPayment();
        }
        else if (type.equalsIgnoreCase("EWALLET")) {
            // Tambahkan logika ini agar tidak mengembalikan null
            return new EWalletPayment();
        }

        return null;
    }
}