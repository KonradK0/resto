package com.example.resto.repository;

import com.example.resto.model.RestaurantCoreInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantCoreInfoRepository extends CrudRepository<RestaurantCoreInfo, Long> {
    List<RestaurantCoreInfo> findByName(String name);

//    RestaurantCoreInfo findById(long id);

    List<RestaurantCoreInfo> findByCuisines_Name(String CuisineName);

    List<RestaurantCoreInfo> findPricingRangeBetween(int low, int high);

    List<RestaurantCoreInfo> findRatingBetween(double low, double high);
}
