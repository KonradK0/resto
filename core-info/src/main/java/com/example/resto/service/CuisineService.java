package com.example.resto.service;

import com.example.resto.model.Cuisine;
import com.example.resto.model.RestaurantCoreInfo;
import com.example.resto.repository.CuisineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class CuisineService {

    CuisineRepository cuisineRepository;

    @Autowired
    public CuisineService(CuisineRepository cuisineRepository) {
        this.cuisineRepository = cuisineRepository;
    }

    public Optional<Cuisine> findByName(String name) {
        return this.cuisineRepository.findByName(name);
    }

    public Iterable<Cuisine> saveAll(Iterable<Cuisine> cuisines){
        return this.cuisineRepository.saveAll(cuisines);
    }

    public Set<Cuisine> getKnownCuisines(Set<Cuisine> cuisines){
        return cuisines
                .stream()
                .map(Cuisine::getName)
                .map(c-> this.cuisineRepository.findByName(c))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
    }

    public Set<Cuisine> saveNewCuisines(RestaurantCoreInfo coreInfo){
        Set<Cuisine> known_cuisines = this.getKnownCuisines(coreInfo.getCuisines());
        Set<Cuisine> cuisines = new HashSet<>(Set.copyOf(coreInfo.getCuisines()));
        cuisines.removeAll(known_cuisines);
        this.saveAll(cuisines).forEach(known_cuisines::add);
        return known_cuisines;
    }
}
