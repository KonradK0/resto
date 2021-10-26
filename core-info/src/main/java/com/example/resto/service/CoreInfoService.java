package com.example.resto.service;

import com.example.resto.model.Cuisine;
import com.example.resto.model.RestaurantCoreInfo;
import com.example.resto.repository.RestaurantCoreInfoRepository;
import com.example.resto.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
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

    public Optional<List<RestaurantCoreInfo>> findByName(String name, Optional<String[]> current_ids) {
        if (current_ids.isPresent()) {
            Predicate<RestaurantCoreInfo> nameEqualsPredicate = rci -> rci.getName().equals(name);
            return getRestaurantCoreInfos(current_ids.get(), nameEqualsPredicate);
        }
        return this.coreInfoRepository.findByName(name);
    }

    public Optional<List<RestaurantCoreInfo>> findByCuisineName(String cuisineName, Optional<String[]> current_ids) {
        if (current_ids.isPresent()) {
            Predicate<RestaurantCoreInfo> cuisineNameInRestCuisinesPredicate = rci -> rci
                    .getCuisines()
                    .stream()
                    .map(Cuisine::getName)
                    .anyMatch(name -> name.equals(cuisineName));
            return getRestaurantCoreInfos(current_ids.get(), cuisineNameInRestCuisinesPredicate);

        }
        return this.coreInfoRepository.findByCuisines_Name(cuisineName);
    }

    public Optional<List<RestaurantCoreInfo>> findPricingRangeBetween(int low, int high, Optional<String[]> current_ids) {
        if (current_ids.isPresent()) {
            Predicate<RestaurantCoreInfo> pricingRangeBetweenPredicate = rci -> (Utils.isBetween(rci.getPricingRange(), low, high));
            return getRestaurantCoreInfos(current_ids.get(), pricingRangeBetweenPredicate);
        }
        return this.coreInfoRepository.findPricingRangeBetween(low, high);
    }

    public Optional<List<RestaurantCoreInfo>> findRatingBetween(double low, double high, Optional<String[]> current_ids){
        if (current_ids.isPresent()) {
            Predicate<RestaurantCoreInfo> findRatingBetweenPredicate = rci -> (Utils.isBetween(rci.getRating(), low, high));
            return getRestaurantCoreInfos(current_ids.get(), findRatingBetweenPredicate);
        }
        return this.coreInfoRepository.findRatingBetween(low, high);
    }

    private Optional<List<RestaurantCoreInfo>> getRestaurantCoreInfos(String[] current_ids, Predicate<RestaurantCoreInfo> filterPredicate) {
        return Optional.of(Arrays
                .stream(current_ids)
                .parallel()
                .map(Long::valueOf)
                .map(id -> this.coreInfoRepository.findById(id).orElseThrow())
                .filter(filterPredicate)
                .collect(Collectors.toList()));
    }
}
