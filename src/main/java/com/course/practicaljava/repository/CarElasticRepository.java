package com.course.practicaljava.repository;


import com.course.practicaljava.entity.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarElasticRepository extends ElasticsearchRepository<Car, String> {

    Page<Car> findAllByBrandAndColor(String brand, String color, Pageable pageable);

}
