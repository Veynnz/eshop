package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.itemServiceGet;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product")
public class ProductControllerGet extends AbstractControllerGet<Product> {

    public ProductControllerGet(itemServiceGet<Product> productService) {
        super(productService,
                "CreateProduct",
                "ProductList",
                "EditProduct",
                "product",
                "products");
    }

    @GetMapping("/create")
    public String createProductPage(Model model) {
        return createPage(model);
    }

    @GetMapping("/list")
    public String productListPage(Model model) {
        return listPage(model);
    }

    @GetMapping("/edit/{id}")
    public String editProductPage(@PathVariable String id, Model model) {
        return editPage(id, model);
    }

    @Override
    protected Product createNewInstance() {
        return new Product();
    }
}
