package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.itemServicePost;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/car")
public class CarControllerPost extends AbstractControllerPost<Car> {

    public CarControllerPost(itemServicePost<Car> carService) {
        super(carService, "redirect:/car/listCar");
    }

    @PostMapping("/createCar")
    public String createCarPost(@ModelAttribute Car car) {
        return createPost(car);
    }

    @PostMapping("/editCar/{id}")
    public String editCarPost(@PathVariable String id, @ModelAttribute Car car) {
        return editPost(id, car);
    }

    @PostMapping("/deleteCar/{id}")
    public String deletePost(@PathVariable String id) {
        return delete(id);
    }
}
