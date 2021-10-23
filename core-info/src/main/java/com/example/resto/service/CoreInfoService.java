package com.example.resto.service;

import com.example.resto.model.RestaurantCoreInfo;
import com.example.resto.repository.RestaurantCoreInfoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoreInfoService {

    RestaurantCoreInfoRepository coreInfoRepository;

    public CoreInfoService(RestaurantCoreInfoRepository coreInfoRepository) {
        this.coreInfoRepository = coreInfoRepository;
    }

    public List<RestaurantCoreInfo> findByName(String name) {
        return coreInfoRepository.findByName(name);
    }

    public List<RestaurantCoreInfo> findByCuisineName(String cuisineName) {
        return coreInfoRepository.findByCuisines_Name(cuisineName);
    }
}
