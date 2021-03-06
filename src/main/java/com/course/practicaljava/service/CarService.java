package com.course.practicaljava.service;

import com.course.practicaljava.entity.Car;

import java.util.List;

public interface CarService {

    List<String> BRANDS = List.of("Toyota", "Honda", "Ford", "BMW");

    List<String> COLORS = List.of("Red", "Black", "White", "Blue");

    List<String> TYPES = List.of("Sedan", "SUV", "MPV", "Hatchback");

    List<String> ADDITIONAL_FEATURES = List.of("GPS", "Alarm", "Media player");

    List<String> FUELS = List.of("Petrol", "Electric", "Hybrid");

    List<String> TIRE_MANUFACTURERS = List.of("Goodyear", "Bridgestorne");

    Car generateCar();

}
