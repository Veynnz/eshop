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
        product1.setProductId("1");
        product1.setProductName("Test Product 1");
        product1.setProductQuantity(10);

        product2 = new Product();
        product2.setProductId("2");
        product2.setProductName("Test Product 2");
        product2.setProductQuantity(20);
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

        Iterator<Product> iterator = productList.iterator();
        when(productRepository.findAll()).thenReturn(iterator);

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
        when(productRepository.edit("1", product1)).thenReturn(product1);

        Product editedProduct = productService.edit("1", product1);

        assertEquals(product1, editedProduct);
        verify(productRepository, times(1)).edit("1", product1);
    }

    @Test
    void testDelete() {
        when(productRepository.findById("1")).thenReturn(product1);

        Product deletedProduct = productService.delete("1");

        assertEquals(product1, deletedProduct);
        verify(productRepository, times(1)).findById("1");
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
        when(productRepository.edit("nonExistentId", product1)).thenReturn(null);

        Product editedProduct = productService.edit("nonExistentId", product1);

        assertNull(editedProduct);
        verify(productRepository, times(1)).edit("nonExistentId", product1);
    }

    @Test
    void testDeleteNotFound() {
        when(productRepository.findById("nonExistentId")).thenReturn(null);

        Product deletedProduct = productService.delete("nonExistentId");

        assertNull(deletedProduct);
        verify(productRepository, times(1)).findById("nonExistentId");
    }
}