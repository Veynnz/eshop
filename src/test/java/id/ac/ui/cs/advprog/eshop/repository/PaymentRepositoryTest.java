package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {

    PaymentRepository paymentRepository;

    List<Payment> payments;

    Order order;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();
        payments = new ArrayList<>();

        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setId("f583dcd4-1407-4acf-b109-dbf1191f4b19");
        product1.setName("Sampo Cap Bambang");
        product1.setQuantity(2);
        products.add(product1);

        order = new Order("abb00ecc-5453-4162-b4c5-53ac13364aff",
                products, 1708560000L, "Me");

        Map<String, String > paymentData1 = new HashMap<>();
        paymentData1.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment1 = new Payment("bca1d2e3-f456-78ab-9cde-0123456789ff", "VOUCHER", paymentData1);
        payments.add(payment1);
        Map<String, String > paymentData2 = new HashMap<>();
        paymentData2.put("voucherCode", "ESHOP1234ABC5679");
        Payment payment2 = new Payment("fedcba98-7654-3210-ffed-cba987654321", "VOUCHER", paymentData2);
        payments.add(payment2);

        order = new Order("11223344-5566-7788-99aa-bbccddeeff00",
                null, 1709000000L, "John Doe");
    }

    @Test
    void testSaveCreate() {
        Payment payment = payments.get(0);
        Payment result = paymentRepository.save(order, payment);

        Payment findResult = paymentRepository.findById(payments.get(0).getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getStatus(), findResult.getStatus());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertSame(payment.getPaymentData(), findResult.getPaymentData());
    }

    @Test
    void testUpdateStatus() {
        Payment payment = payments.get(0);
        Payment result = paymentRepository.save(order, payment);
        assertEquals("SUCCESS", result.getStatus());
        assertEquals("SUCCESS", order.getStatus());

        paymentRepository.update(payment, "REJECTED");
        Payment findResult = paymentRepository.findById(payments.get(0).getId());
        Order findOrder = paymentRepository.getOrder(findResult.getId());
        assertEquals("REJECTED", findResult.getStatus());
        assertEquals("FAILED", findOrder.getStatus());
    }

    @Test
    void testUpdateInvalidStatus() {
        Payment payment = payments.get(0);
        paymentRepository.save(order, payment);

        assertThrows(IllegalArgumentException.class, () -> {
            paymentRepository.update(payment, "INVALID_STATUS");
        });
    }

    @Test
    void testFindByIdIfFound() {
        for (Payment payment : payments) {
            paymentRepository.save(order, payment);
        }

        Payment findPayment = paymentRepository.findById(payments.get(0).getId());
        assertEquals(payments.get(0).getId(), findPayment.getId());
        assertEquals(payments.get(0).getMethod(), findPayment.getMethod());
        assertEquals(payments.get(0).getStatus(), findPayment.getStatus());
        assertSame(payments.get(0).getPaymentData(), findPayment.getPaymentData());
    }

    @Test
    void testFindByIdIfNotFound() {
        for (Payment payment : payments) {
            paymentRepository.save(order, payment);
        }

        Payment findPayment = paymentRepository.findById("non_existent_id");
        assertNull(findPayment);
    }

    @Test
    void testFindAll() {
        for (Payment payment : payments) {
            paymentRepository.save(order, payment);
        }

        List<Payment> findPayments = paymentRepository.findAll();
        assertEquals(2, findPayments.size());
    }

    @Test
    void testFindOrderIfFound() {
        Payment payment = payments.get(0);
        paymentRepository.save(order, payment);

        Payment findResult = paymentRepository.findById(payments.get(0).getId());
        Order findOrder = paymentRepository.getOrder(findResult.getId());
        assertEquals(order.getId(), findOrder.getId());
        assertEquals(order.getStatus(), findOrder.getStatus());
        assertEquals(order.getAuthor(), findOrder.getAuthor());
        assertEquals(order.getOrderTime(), findOrder.getOrderTime());
        assertEquals(order.getProducts(), findOrder.getProducts());
    }

    @Test
    void testFindOrderIfNotFound() {
        Payment payment = payments.get(0);
        paymentRepository.save(order, payment);

        Payment findResult = paymentRepository.findById(payments.get(0).getId());
        Order findOrder = paymentRepository.getOrder("non_existent_order_id");
        assertNull(findOrder);
    }
}
