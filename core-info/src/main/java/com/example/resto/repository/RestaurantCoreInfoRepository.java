package com.example.resto.repository;

import com.example.resto.model.RestaurantCoreInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RestaurantCoreInfoRepository extends CrudRepository<RestaurantCoreInfo, Long> {
    List<RestaurantCoreInfo> findByName(String name);
    RestaurantCoreInfo findById(long id);
}
