package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
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
    private ProductService servicePost;
    @Mock
    private ReadOnlyProductService serviceGet;

    @Mock
    private Model model;

    @InjectMocks
    private ProductControllerPost controllerPost;
    @InjectMocks
    private ProductControllerGet controllerGet;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProductPage() {
        String viewName = controllerGet.createProductPage(model);
        assertEquals("CreateProduct", viewName);
        verify(model).addAttribute(eq("product"), any(Product.class));
    }

    @Test
    void testCreateProductPost() {
        Product product = new Product();
        String viewName = controllerPost.createProductPost(product, model);
        assertEquals("redirect:list", viewName);
        verify(servicePost).create(product);
    }

    @Test
    void testProductListPage() {
        List<Product> productList = Arrays.asList(new Product(), new Product());
        when(serviceGet.findAll()).thenReturn(productList);

        String viewName = controllerGet.productListPage(model);
        assertEquals("ProductList", viewName);
        verify(model).addAttribute("products", productList);
    }

    @Test
    void testEditProductPage() {
        String productId = "123";
        Product product = new Product();
        when(serviceGet.findById(productId)).thenReturn(product);

        String viewName = controllerGet.editProductPage(productId, model);
        assertEquals("EditProduct", viewName);
        verify(model).addAttribute("product", product);
    }

    @Test
    void testEditProductPageNotFound() {
        String productId = "123";
        when(serviceGet.findById(productId)).thenReturn(null);

        String viewName = controllerGet.editProductPage(productId, model);
        assertEquals("redirect:/product/list", viewName);
    }

    @Test
    void testEditProductPost() {
        String productId = "123";
        Product product = new Product();
        String viewName = controllerPost.editProductPost(productId, product);
        assertEquals("redirect:/product/list", viewName);
        verify(servicePost).edit(productId, product);
    }

    @Test
    void testDeleteProduct() {
        String productId = "123";
        String viewName = controllerPost.delete(productId);
        assertEquals("redirect:/product/list", viewName);
        verify(servicePost).deleteById(productId);
    }
}