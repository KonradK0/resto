package com.example.resto.service;

import com.example.resto.model.Cuisine;
import com.example.resto.model.RestaurantCoreInfo;
import com.example.resto.repository.RestaurantCoreInfoRepository;
import com.example.resto.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class CoreInfoService {

    RestaurantCoreInfoRepository coreInfoRepository;

    CuisineService cuisineService;

    @Autowired
    public CoreInfoService(RestaurantCoreInfoRepository coreInfoRepository, CuisineService cuisineService) {
        this.coreInfoRepository = coreInfoRepository;
        this.cuisineService = cuisineService;
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
        return this.coreInfoRepository.pricingRangeBetween(low, high);
    }

    public Optional<List<RestaurantCoreInfo>> findRatingBetween(double low, double high, Optional<String[]> current_ids) {
        if (current_ids.isPresent()) {
            Predicate<RestaurantCoreInfo> findRatingBetweenPredicate = rci -> (Utils.isBetween(rci.getRating(), low, high));
            return getRestaurantCoreInfos(current_ids.get(), findRatingBetweenPredicate);
        }
        return this.coreInfoRepository.ratingBetween(low, high);
    }

    public RestaurantCoreInfo save(RestaurantCoreInfo restaurantCoreInfo) {
        Set<Cuisine> cuisines = this.cuisineService.saveNewCuisines(restaurantCoreInfo);
        restaurantCoreInfo.setCuisines(cuisines);
        return this.coreInfoRepository.save(restaurantCoreInfo);
    }

    public RestaurantCoreInfo updateById(long rci_id, RestaurantCoreInfo newRci) {
        RestaurantCoreInfo old = this.coreInfoRepository.findById(rci_id)
                .orElseThrow(() -> new NoSuchElementException("No such element with id " + rci_id));
        old.setName(newRci.getName());
        old.setPricingRange(newRci.getPricingRange());
        old.setRating(newRci.getRatingSum());
        old.setNumOfRatings(newRci.getNumOfRatings());
        old.setRating(newRci.getRating());
        old.setCuisines(newRci.getCuisines());
        return this.coreInfoRepository.save(old);
    }

    public void deleteById(long id) {
        this.coreInfoRepository.deleteById(id);
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
