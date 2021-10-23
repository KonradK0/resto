package com.example.resto.controller;

import com.example.resto.model.RestaurantCoreInfo;
import com.example.resto.service.CoreInfoService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/{scope}/coreinfo", produces = MediaType.APPLICATION_JSON_VALUE)
public class CoreInfoController {

    private final CoreInfoService coreInfoService;

    public CoreInfoController(CoreInfoService coreInfoService) {
        this.coreInfoService = coreInfoService;
    }

    @GetMapping(value = "/{name}")
    public List<RestaurantCoreInfo> findByName(@PathVariable String scope, @PathVariable String name) {
        return this.coreInfoService.findByName(name);
    }

    @GetMapping(value = "/cuisine/{cuisineName}")
    List<RestaurantCoreInfo> findByCuisines_Name(@PathVariable String scope, @PathVariable String cuisineName) {
        return this.coreInfoService.findByCuisineName(cuisineName);
    }

}
