package com.example.resto.service;

import com.example.resto.model.Cuisine;
import com.example.resto.model.RestaurantCoreInfo;
import com.example.resto.repository.RestaurantCoreInfoRepository;
import com.example.resto.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class CoreInfoService {

    RestaurantCoreInfoRepository coreInfoRepository;

    @Autowired
    public CoreInfoService(RestaurantCoreInfoRepository coreInfoRepository) {
        this.coreInfoRepository = coreInfoRepository;
    }

    public List<RestaurantCoreInfo> findByName(String name, String[] current_ids) {
        if (current_ids != null) {
            Predicate<RestaurantCoreInfo> nameEqualsPredicate = rci -> rci.getName().equals(name);
            return getRestaurantCoreInfos(current_ids, nameEqualsPredicate);
        }
        return this.coreInfoRepository.findByName(name);
    }

    public List<RestaurantCoreInfo> findByCuisineName(String cuisineName, String[] current_ids) {
        if (current_ids != null) {
            Predicate<RestaurantCoreInfo> cuisineNameInRestCuisinesPredicate = rci -> rci
                    .getCuisines()
                    .stream()
                    .map(Cuisine::getName)
                    .anyMatch(name -> name.equals(cuisineName));
            return getRestaurantCoreInfos(current_ids, cuisineNameInRestCuisinesPredicate);

        }
        return this.coreInfoRepository.findByCuisines_Name(cuisineName);
    }

    public List<RestaurantCoreInfo> findPricingRangeBetween(int low, int high, String[] current_ids) {
        if (current_ids != null) {
            Predicate<RestaurantCoreInfo> pricingRangeBetweenPredicate = rci -> (Utils.isBetween(rci.getPricingRange(), low, high));
            return getRestaurantCoreInfos(current_ids, pricingRangeBetweenPredicate);
        }
        return this.coreInfoRepository.findPricingRangeBetween(low, high);
    }

    public List<RestaurantCoreInfo> findRatingBetween(double low, double high, String[] current_ids){
        if (current_ids != null) {
            Predicate<RestaurantCoreInfo> findRatingBetweenPredicate = rci -> (Utils.isBetween(rci.getRating(), low, high));
            return getRestaurantCoreInfos(current_ids, findRatingBetweenPredicate);
        }
        return this.coreInfoRepository.findRatingBetween(low, high);
    }

    private List<RestaurantCoreInfo> getRestaurantCoreInfos(String[] current_ids, Predicate<RestaurantCoreInfo> filterPredicate) {
        return Arrays
                .stream(current_ids)
                .parallel()
                .map(Long::valueOf)
                .map(id -> this.coreInfoRepository.findById(id).orElseThrow())
                .filter(filterPredicate)
                .collect(Collectors.toList());
    }
}
