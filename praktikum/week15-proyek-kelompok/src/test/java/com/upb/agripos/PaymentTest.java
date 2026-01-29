package com.upb.agripos;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import com.upb.agripos.payment.CashPayment;
import com.upb.agripos.payment.EWalletPayment;
import com.upb.agripos.payment.PaymentFactory;
import com.upb.agripos.payment.PaymentMethod;

public class PaymentTest {

    @Test
    public void testCashPayment() {
        // CashPayment tidak butuh argumen sesuai Class Diagram
        PaymentMethod cash = PaymentFactory.getPaymentMethod("CASH");
        assertNotNull(cash);
        assertTrue(cash instanceof CashPayment);
        assertTrue(cash.pay(100000.0)); // Cash selalu true
    }

    @Test
    public void testEWalletPaymentSuccess() {
        PaymentMethod ewallet = PaymentFactory.getPaymentMethod("EWALLET");
        assertNotNull(ewallet);
        assertTrue(ewallet instanceof EWalletPayment);
        // Gateway akan mengembalikan true jika di bawah 10jt sesuai simulasi kita
        assertTrue(ewallet.pay(50000.0));
    }

    @Test
    public void testPaymentFactoryInvalid() {
        // Test jika input metode salah
        PaymentMethod unknown = PaymentFactory.getPaymentMethod("CRYPTO");
        assertNull(unknown);
    }
}