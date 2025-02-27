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
                "createProduct",
                "productList",
                "editProduct",
                "product",
                "products");
    }

    @GetMapping("/create")
    public String createCarPage(Model model) {
        return createPage(model);
    }

    @GetMapping("/list")
    public String carListPage(Model model) {
        return listPage(model);
    }

    @GetMapping("/edit/{id}")
    public String editCarPage(@PathVariable String id, Model model) {
        return editPage(id, model);
    }

    @Override
    protected Product createNewInstance() {
        return new Product();
    }
}
