package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.itemServicePost;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product")
public class ProductControllerPost extends AbstractControllerPost<Product> {

    public ProductControllerPost(itemServicePost<Product> productService) {
        super(productService,
                "redirect:/product/list");
    }

    @PostMapping("/create")
    public String createProductPost(@ModelAttribute Product product) {
        return createPost(product);
    }

    @PostMapping("/edit/{id}")
    public String editProductPost(@PathVariable String id, @ModelAttribute Product product) {
        return editPost(id, product);
    }

    @PostMapping("/delete/{id}")
    public String deletePost(@PathVariable String id) {
        return delete(id);
    }
}
