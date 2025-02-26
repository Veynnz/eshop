package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService, ReadOnlyProductService {

    private final ProductRepository productRepository; // Declare as final

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) { // Constructor injection
        this.productRepository = productRepository;
    }

    @Override
    public Product create(Product product) {
        productRepository.create(product);
        return product;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(String id) {
        return productRepository.findById(id);
    }

    @Override
    public Product edit(String id, Product product) {
        return productRepository.update(id, product);
    }

    @Override
    public Product delete(String id) {
        return productRepository.delete(id);
    }
}