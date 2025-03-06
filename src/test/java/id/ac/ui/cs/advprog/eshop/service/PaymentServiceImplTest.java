package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplTest {

    @InjectMocks
    PaymentServiceImpl paymentService;

    @Mock
    PaymentRepository paymentRepository;

    @Mock
    OrderService orderService;

    Order order;
    Map<String, String> paymentData1;
    Map<String, String> paymentData2;
    List<Payment> payments = new ArrayList<>();

    @BeforeEach
    void setUp() {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setId("a1b2c3d4-5678-90ab-cdef-1234567890ab");
        product1.setName("Some Product");
        product1.setQuantity(5);
        products.add(product1);

        order = new Order("xyz98765-4321-abcd-efgh-567890abcdef",
                products, 1710000000L, "Jane Doe");

        paymentData1 = new HashMap<>();
        paymentData1.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment1 = new Payment("123e4567-e89b-12d3-a456-426614174000", "VOUCHER", paymentData1);
        payments.add(payment1);

        paymentData2 = new HashMap<>();
        paymentData2.put("voucherCode", "ESHOP1234ABC5679");
        Payment payment2 = new Payment("987f6543-b21a-34c5-d678-567890abcdef", "VOUCHER", paymentData2);
        payments.add(payment2);
    }

    @Test
    void testProcessNewPayment() {
        when(paymentRepository.save(eq(order), any(Payment.class))).thenAnswer(invocation -> invocation.getArgument(1));

        Payment result = paymentService.addPayment(order, "VOUCHER", paymentData1);

        assertNotNull(result);
        assertEquals("VOUCHER", result.getMethod());
        assertEquals(paymentData1, result.getPaymentData());

        verify(paymentRepository).save(eq(order), any(Payment.class));
    }

    @Test
    void testUpdatePaymentStatus() {
        Payment payment = payments.get(0);

        when(paymentRepository.getOrder(payment.getId())).thenReturn(order);
        when(paymentRepository.save(any(Order.class), any(Payment.class))).thenReturn(payment);
        when(orderService.updateStatus(anyString(), anyString())).thenReturn(order);

        Payment updatedPayment = paymentService.setStatus(payment, PaymentStatus.SUCCESS.getValue());
        assertEquals(PaymentStatus.SUCCESS.getValue(), updatedPayment.getStatus());
        verify(orderService, times(1)).updateStatus(order.getId(), OrderStatus.SUCCESS.getValue());
    }

    @Test
    void testSetStatusToRejected(){
        Payment payment = payments.get(0);

        when(paymentRepository.getOrder(payment.getId())).thenReturn(order);
        when(paymentRepository.save(any(Order.class), any(Payment.class))).thenReturn(payment);
        when(orderService.updateStatus(anyString(), anyString())).thenReturn(order);

        paymentService.setStatus(payment, PaymentStatus.REJECTED.getValue());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        verify(orderService, times(1)).updateStatus(order.getId(), OrderStatus.FAILED.getValue());
    }

    @Test
    void testSetInvalidPaymentStatus() {
        Payment payment = payments.get(0);
        paymentRepository.save(order, payment);

        assertThrows(IllegalArgumentException.class, () -> paymentService.setStatus(payment, "INVALID_STATUS"));
    }

    @Test
    void testFindPaymentById() {
        Payment payment = payments.get(0);
        doReturn(payment).when(paymentRepository).findById(payment.getId());

        Payment result = paymentService.getPayment(payment.getId());
        assertNotNull(result);
        assertEquals(payment.getId(), result.getId());
    }

    @Test
    void testFindPaymentByNonExistentId() {
        doReturn(null).when(paymentRepository).findById("not_found_id");
        assertNull(paymentService.getPayment("not_found_id"));
    }

    @Test
    void testRetrieveAllPayments() {
        doReturn(payments).when(paymentRepository).findAll();

        List<Payment> results = paymentService.getAllPayments();
        assertEquals(2, results.size());
    }
}
