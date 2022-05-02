package com.course.practicaljava.config;

import com.course.practicaljava.entity.Car;
import com.course.practicaljava.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import com.course.practicaljava.repository.CarElasticRepository;

import java.util.ArrayList;

@Component
public class CarElasticDatasource {

    private static final Logger LOG = LoggerFactory.getLogger(CarElasticDatasource.class);
    private final CarElasticRepository carElasticRepository;
    private final CarService carService;
    private final WebClient webClient;

    @Autowired
    public CarElasticDatasource(
        CarElasticRepository carElasticRepository,
        @Qualifier("randomCarService") CarService carService,
        @Qualifier("webClientElasticsearch") WebClient webClient
    ) {
        this.carElasticRepository = carElasticRepository;
        this.carService = carService;
        this.webClient = webClient;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void populateData() {
        var response = webClient.delete().uri("practical-java").retrieve().bodyToMono(String.class).block();
        LOG.info("End delete with response {}", response);

        var cars = new ArrayList<Car>();
        for (int i = 0; i < 10000; i++) {
            cars.add(carService.generateCar());
        }

        carElasticRepository.saveAll(cars);

        LOG.info("Saved {} cars", carElasticRepository.count());
    }
}
