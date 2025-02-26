package id.ac.ui.cs.advprog.eshop.service;
import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;

public interface ReadOnlyProductService {

    public List<Product> findAll();
    public Product findById(String id);
}
