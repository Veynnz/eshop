package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    @Mock
    private ProductService service;

    @Mock
    private Model model;

    @InjectMocks
    private ProductController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProductPage() {
        String viewName = controller.createProductPage(model);
        assertEquals("CreateProduct", viewName);
        verify(model).addAttribute(eq("product"), any(Product.class));
    }

    @Test
    void testCreateProductPost() {
        Product product = new Product();
        String viewName = controller.createProductPost(product, model);
        assertEquals("redirect:list", viewName);
        verify(service).create(product);
    }

    @Test
    void testProductListPage() {
        List<Product> productList = Arrays.asList(new Product(), new Product());
        when(service.findAll()).thenReturn(productList);

        String viewName = controller.productListPage(model);
        assertEquals("ProductList", viewName);
        verify(model).addAttribute("products", productList);
    }

    @Test
    void testEditProductPage() {
        String productId = "123";
        Product product = new Product();
        when(service.findById(productId)).thenReturn(product);

        String viewName = controller.editProductPage(productId, model);
        assertEquals("EditProduct", viewName);
        verify(model).addAttribute("product", product);
    }

    @Test
    void testEditProductPageNotFound() {
        String productId = "123";
        when(service.findById(productId)).thenReturn(null);

        String viewName = controller.editProductPage(productId, model);
        assertEquals("redirect:/product/list", viewName);
    }

    @Test
    void testEditProductPost() {
        String productId = "123";
        Product product = new Product();
        String viewName = controller.editProductPost(productId, product);
        assertEquals("redirect:/product/list", viewName);
        verify(service).edit(productId, product);
    }

    @Test
    void testDeleteProduct() {
        String productId = "123";
        String viewName = controller.deleteProduct(productId);
        assertEquals("redirect:/product/list", viewName);
        verify(service).delete(productId);
    }
}