package com.course.practicaljava.api.server;

import com.course.practicaljava.dto.CarFilterDto;
import com.course.practicaljava.entity.Car;
import com.course.practicaljava.exception.IllegalParamException;
import com.course.practicaljava.repository.CarElasticRepository;
import com.course.practicaljava.response.ErrorResponse;
import com.course.practicaljava.service.CarService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping(value = "/api/car/v1")
public class CarController {

    private static final Logger LOG = LoggerFactory.getLogger(CarController.class);
    private final CarService carService;
    private final CarElasticRepository carElasticRepository;

    @Autowired
    public CarController(
        CarService carService,
        CarElasticRepository carElasticRepository
    ) {
        this.carService = carService;
        this.carElasticRepository = carElasticRepository;
    }

    @GetMapping(value = "/random3", produces = MediaType.APPLICATION_JSON_VALUE)
    public Car getRandomCar() {
        return carService.generateCar();
    }

    @PostMapping(value = "/echo2", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String echo(@RequestBody Car car) {
        LOG.info("CAR is {}", car);
        return car.toString();
    }

    @GetMapping(value = "random-cars2", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Car> randomCars() {
        var result = new ArrayList<Car>();

        for (int i = 0; i < ThreadLocalRandom.current().nextInt(1, 10); i++) {
            result.add(carService.generateCar());
        }

        return result;
    }

    @GetMapping(value = "/count2")
    public String countCar() {
        return "Number of cars " + carElasticRepository.count();
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String saveCar(@RequestBody Car car) {
        Car savedCar = carElasticRepository.save(car);
        return "Saved with id: " + savedCar.getId();
    }

    @GetMapping(value = "/{id}")
    public Car getCar(@PathVariable String id) {
        return carElasticRepository.findById(id).orElse(null);
    }

    @PutMapping(value = "/{id}")
    public String updateCar(@PathVariable String id, @RequestBody Car updateCar) {
        updateCar.setId(id);
        Car updatedCar = carElasticRepository.save(updateCar);
        return "Updated with id: " + updatedCar.getId();
    }

    @GetMapping(value = "")
    public ResponseEntity<Page<Car>> getCars(CarFilterDto filter, Pageable pageable) {
        if (StringUtils.isNumeric(filter.getColor())) {
            throw new IllegalArgumentException("Invalid color: " + filter.getColor());
        }

        if (StringUtils.isNumeric(filter.getBrand())) {
            throw new IllegalParamException("Invalid brand: " + filter.getBrand());
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Custom-Header", "Custom value");
        return ResponseEntity
            .status(HttpStatus.ACCEPTED)
            .headers(httpHeaders)
            .body(
                carElasticRepository.findAllByBrandAndColor(filter.getBrand(), filter.getColor(), pageable)
            );
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    private ResponseEntity<ErrorResponse> handleInvalidColorException(IllegalArgumentException e) {
        var message = "Exception" + e.getMessage();
        LOG.warn(message);

        var errorResponse = new ErrorResponse(message, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

}
