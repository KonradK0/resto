package com.example.resto.repository;

import com.example.resto.model.RestaurantCoreInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantCoreInfoRepository extends CrudRepository<RestaurantCoreInfo, Long> {
    Optional<List<RestaurantCoreInfo>> findByName(String name);

//    RestaurantCoreInfo findById(long id);

    Optional<List<RestaurantCoreInfo>> findByCuisines_Name(String CuisineName);

    Optional<List<RestaurantCoreInfo>> pricingRangeBetween(int low, int high);

    Optional<List<RestaurantCoreInfo>> ratingBetween(double low, double high);
}
