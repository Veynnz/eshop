package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        Product createdProduct = productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(createdProduct.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindByIdFirst() {
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        Product createdProduct1 = productRepository.create(product);
        Product foundProduct = productRepository.findById(createdProduct1.getProductId());
        assertNotNull(foundProduct);
        assertEquals(createdProduct1.getProductId(), foundProduct.getProductId());
    }

    @Test
    void testFindByIdMiddle() {
        Product product1 = new Product();
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);

        Product product2 = new Product();
        product2.setProductName("Sampo Cap Bango");
        product2.setProductQuantity(99);

        Product product3 = new Product();
        product3.setProductName("Sampo Cap Bangka");
        product3.setProductQuantity(98);

        productRepository.create(product1);
        Product createdProduct2 = productRepository.create(product2);
        productRepository.create(product3);

        Product foundProduct = productRepository.findById(createdProduct2.getProductId());
        assertNotNull(foundProduct);
        assertEquals(createdProduct2.getProductId(), foundProduct.getProductId());
    }

    @Test
    void testFindByIdLast() {
        Product product1 = new Product();
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);

        Product product2 = new Product();
        product2.setProductName("Sampo Cap Bango");
        product2.setProductQuantity(99);

        Product product3 = new Product();
        product3.setProductName("Sampo Cap Bangka");
        product3.setProductQuantity(98);

        productRepository.create(product1);
        productRepository.create(product2);
        Product createdProduct3 = productRepository.create(product3);

        Product foundProduct = productRepository.findById(createdProduct3.getProductId());
        assertNotNull(foundProduct);
        assertEquals(createdProduct3.getProductId(), foundProduct.getProductId());
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
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductName("Sampo Cap Useb");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testEditProduct() {
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        Product createdProduct = productRepository.create(product);

        String productId = createdProduct.getProductId();

        Product updatedProduct = new Product();
        updatedProduct.setProductName("Sampo Cap Bango");
        updatedProduct.setProductQuantity(200);

        Product result = productRepository.edit(productId, updatedProduct);

        assertNotNull(result);
        assertEquals(updatedProduct.getProductName(), result.getProductName());
        assertEquals(updatedProduct.getProductQuantity(), result.getProductQuantity());

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(updatedProduct.getProductName(), savedProduct.getProductName());
        assertEquals(updatedProduct.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testEditUpdatedProductEmpty() {
        // Assuming productRepository starts with an empty list
        Product updatedProduct = new Product();
        Product result = productRepository.edit("non-existent-id", updatedProduct);
        assertNull(result); // Should return null when list is empty
    }

    @Test
    void testEditProductNotFound_IdNotInList() {
        // Create a product to ensure the list is not empty
        Product product1 = new Product();
        product1.setProductName("Product 1");
        productRepository.create(product1);

        // Attempt to edit a product with a non-existent ID
        Product updatedProduct = new Product();
        Product result = productRepository.edit("another-non-existent-id", updatedProduct);

        // Verify that the method returns null
        assertNull(result);
    }

    @Test
    void testDeleteProduct() {
        Product product = new Product();
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        Product createdProduct = productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());

        Product deletedProduct = productRepository.delete(createdProduct.getProductId());
        assertEquals(createdProduct.getProductId(), deletedProduct.getProductId());

        productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testDeleteProduct_EmptyList() {
        // Assuming productRepository starts with an empty list
        Product deletedProduct = productRepository.delete("non-existent-id");
        assertNull(deletedProduct); // Should return null when list is empty
    }

    @Test
    void testDeleteProductNotFound_IdNotInList() {
        // Create a product to ensure the list is not empty
        Product product1 = new Product();
        product1.setProductName("Product 1");
        productRepository.create(product1);

        // Attempt to delete a product with a non-existent ID
        Product deletedProduct = productRepository.delete("another-non-existent-id");

        // Verify that the method returns null
        assertNull(deletedProduct);
    }
}