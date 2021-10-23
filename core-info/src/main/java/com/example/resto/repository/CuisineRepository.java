package com.example.resto.repository;

import com.example.resto.model.Cuisine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuisineRepository extends CrudRepository<Cuisine, Long> {
    List<Cuisine> findByName(String name);
}
