package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    private Order order;
    private Map<String, String> paymentData;

    @BeforeEach
    void setUp(){
        paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
    }

    @Test
    void testCreatePaymentInvalidMethod() {
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("a1b2c3d4-e5f6-7890-1234-567890abcdef", "RAWR",
                    paymentData);
        });
    }

    @Test
    void testCreatePaymentInvalidPaymentData() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("a1b2c3d4-e5f6-7890-1234-567890abcdef", "VOUCHER", null);
        });
    }

    @Test
    void testSetStatusToRejected() {
        Payment payment = new Payment("a1b2c3d4-e5f6-7890-1234-567890abcdef", "VOUCHER", paymentData);
        payment.setStatus("REJECTED");

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testSetStatusToInvalid() {
        Payment payment = new Payment("a1b2c3d4-e5f6-7890-1234-567890abcdef", "VOUCHER", paymentData);

        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus("RAWR RAWR");
        });
    }
}