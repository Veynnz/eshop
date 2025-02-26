package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        product1 = new Product();
        product1.setId("1");
        product1.setName("Test Product 1");
        product1.setQuantity(10);

        product2 = new Product();
        product2.setId("2");
        product2.setName("Test Product 2");
        product2.setQuantity(20);
    }

    @Test
    void testCreate() {
        when(productRepository.create(product1)).thenReturn(product1);

        Product createdProduct = productService.create(product1);

        assertEquals(product1, createdProduct);
        verify(productRepository, times(1)).create(product1);
    }

    @Test
    void testFindAll() {
        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);

        when(productRepository.findAll()).thenReturn(productList);

        List<Product> foundProducts = productService.findAll();

        assertEquals(productList.size(), foundProducts.size());
        assertEquals(productList, foundProducts);
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(productRepository.findById("1")).thenReturn(product1);

        Product foundProduct = productService.findById("1");

        assertEquals(product1, foundProduct);
        verify(productRepository, times(1)).findById("1");
    }

    @Test
    void testEdit() {
        when(productRepository.update("1", product1)).thenReturn(product1);

        Product editedProduct = productService.edit("1", product1);

        assertEquals(product1, editedProduct);
        verify(productRepository, times(1)).update("1", product1);
    }

    @Test
    void testDelete() {
        when(productRepository.delete("1")).thenReturn(product1); // Mock delete()

        Product deletedProduct = productService.delete("1");

        assertEquals(product1, deletedProduct); // Assert the correct product
        verify(productRepository, times(1)).delete("1"); // Verify delete() call
    }

    @Test
    void testFindByIdNotFound() {
        when(productRepository.findById("nonExistentId")).thenReturn(null);

        Product foundProduct = productService.findById("nonExistentId");

        assertNull(foundProduct);
        verify(productRepository, times(1)).findById("nonExistentId");
    }

    @Test
    void testEditNotFound() {
        when(productRepository.update("nonExistentId", product1)).thenReturn(null);

        Product editedProduct = productService.edit("nonExistentId", product1);

        assertNull(editedProduct);
        verify(productRepository, times(1)).update("nonExistentId", product1);
    }

    @Test
    void testDeleteNotFound() {
        when(productRepository.delete("nonExistentId")).thenReturn(null); // Mock delete()

        Product deletedProduct = productService.delete("nonExistentId");

        assertNull(deletedProduct);
        verify(productRepository, times(1)).delete("nonExistentId"); // Verify delete() call
    }
}