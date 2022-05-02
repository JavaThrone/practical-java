package com.course.practicaljava.api.server;

import com.course.practicaljava.entity.Car;
import com.course.practicaljava.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/car/v1")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping(value = "/random", produces = MediaType.APPLICATION_JSON_VALUE)
    public Car getRandomCar() {
        return carService.generateCar();
    }
}
