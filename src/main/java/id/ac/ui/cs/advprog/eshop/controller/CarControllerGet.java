package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.itemServiceGet;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/car")
public class CarControllerGet extends AbstractControllerGet<Car> {

    public CarControllerGet(itemServiceGet<Car> carService) {
        super(carService,
                "createCar",
                "carList",
                "editCar",
                "car",
                "cars");
    }

    @GetMapping("/createCar")
    public String createCarPage(Model model) {
        return createPage(model);
    }

    @GetMapping("/listCar")
    public String carListPage(Model model) {
        return listPage(model);
    }

    @GetMapping("/editCar/{id}")
    public String editCarPage(@PathVariable String id, Model model) {
        return editPage(id, model);
    }

    @Override
    protected Car createNewInstance() {
        return new Car();
    }
}
