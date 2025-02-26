package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setName("Sampo Cap Bambang");
        product.setQuantity(100);
        Product createdProduct = productRepository.create(product);

        List<Product> productList = productRepository.findAll();
        assertFalse(productList.isEmpty());
        Product savedProduct = productList.get(0);
        assertEquals(product.getId(), savedProduct.getId());
        assertEquals(product.getName(), savedProduct.getName());
        assertEquals(product.getQuantity(), savedProduct.getQuantity());
    }

    @Test
    void testFindByIdFirst() {
        Product product = new Product();
        product.setName("Sampo Cap Bambang");
        product.setQuantity(100);
        Product createdProduct1 = productRepository.create(product);
        Product foundProduct = productRepository.findById(createdProduct1.getId());
        assertNotNull(foundProduct);
        assertEquals(createdProduct1.getId(), foundProduct.getId());
    }

    @Test
    void testFindByIdMiddle() {
        Product product1 = new Product();
        product1.setName("Sampo Cap Bambang");
        product1.setQuantity(100);

        Product product2 = new Product();
        product2.setName("Sampo Cap Bango");
        product2.setQuantity(99);

        Product product3 = new Product();
        product3.setName("Sampo Cap Bangka");
        product3.setQuantity(98);

        productRepository.create(product1);
        Product createdProduct2 = productRepository.create(product2);
        productRepository.create(product3);

        Product foundProduct = productRepository.findById(createdProduct2.getId());
        assertNotNull(foundProduct);
        assertEquals(createdProduct2.getId(), foundProduct.getId());
    }

    @Test
    void testFindByIdLast() {
        Product product1 = new Product();
        product1.setName("Sampo Cap Bambang");
        product1.setQuantity(100);

        Product product2 = new Product();
        product2.setName("Sampo Cap Bango");
        product2.setQuantity(99);

        Product product3 = new Product();
        product3.setName("Sampo Cap Bangka");
        product3.setQuantity(98);

        productRepository.create(product1);
        productRepository.create(product2);
        Product createdProduct3 = productRepository.create(product3);

        Product foundProduct = productRepository.findById(createdProduct3.getId());
        assertNotNull(foundProduct);
        assertEquals(createdProduct3.getId(), foundProduct.getId());
    }

    @Test
    void testFindByIdNotFound(){
        Product foundProduct = productRepository.findById("nonExistentID");
        assertNull(foundProduct);
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setName("Sampo Cap Bambang");
        product1.setQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setName("Sampo Cap Useb");
        product2.setQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getId(), savedProduct.getId());
        savedProduct = productIterator.next();
        assertEquals(product2.getId(), savedProduct.getId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testEditProduct() {
        Product product = new Product();
        product.setName("Sampo Cap Bambang");
        product.setQuantity(100);
        Product createdProduct = productRepository.create(product);

        String productId = createdProduct.getId();

        Product updatedProduct = new Product();
        updatedProduct.setName("Sampo Cap Bango");
        updatedProduct.setQuantity(200);

        Product result = productRepository.update(productId, updatedProduct);

        assertNotNull(result);
        assertEquals(updatedProduct.getName(), result.getName());
        assertEquals(updatedProduct.getQuantity(), result.getQuantity());
    }

    @Test
    void testDeleteProduct() {
        Product product = new Product();
        product.setName("Sampo Cap Bambang");
        product.setQuantity(100);
        Product createdProduct = productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());

        Product deletedProduct = productRepository.delete(createdProduct.getId());
        assertEquals(createdProduct.getId(), deletedProduct.getId());

        productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }
}
