package com.example.resto.repository;

import com.example.resto.model.Cuisine;
import com.example.resto.model.RestaurantCoreInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CuisineRepository extends CrudRepository<Cuisine, Long> {
    List<Cuisine> findByName(String name);
}
