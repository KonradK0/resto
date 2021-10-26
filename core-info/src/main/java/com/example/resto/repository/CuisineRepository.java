package com.example.resto.repository;

import com.example.resto.model.Cuisine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CuisineRepository extends CrudRepository<Cuisine, Long> {
    Optional<List<Cuisine>> findByName(String name);
}
