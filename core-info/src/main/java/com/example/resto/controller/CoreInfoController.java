package com.example.resto.controller;

import com.example.resto.model.RestaurantCoreInfo;
import com.example.resto.model.User;
import com.example.resto.service.CoreInfoService;
import com.example.resto.util.Utils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(
        value = "api/{scope}/coreinfo",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class CoreInfoController {

    private final CoreInfoService coreInfoService;

    public CoreInfoController(CoreInfoService coreInfoService) {
        this.coreInfoService = coreInfoService;
    }

    @GetMapping(value = "/{name}")
    public List<RestaurantCoreInfo> findByName(@SessionAttribute Optional<User> user,
                                               @PathVariable String scope,
                                               @PathVariable String name,
                                               @RequestParam Optional<String[]> current_ids,
                                               HttpServletResponse res) {
        if (user.isPresent()) {
            return this.coreInfoService.findByName(name, current_ids);
        } else {
            return Utils.unauthorizedAccess(res);
        }
    }

    @GetMapping(value = "/cuisine/{cuisineName}")
    List<RestaurantCoreInfo> findByCuisines_Name(@SessionAttribute Optional<User> user,
                                                 @PathVariable String scope,
                                                 @PathVariable String cuisineName,
                                                 @RequestParam Optional<String[]> current_ids,
                                                 HttpServletResponse res) {
        if (user.isPresent()) {
            return this.coreInfoService.findByCuisineName(cuisineName, current_ids);
        } else {
            return Utils.unauthorizedAccess(res);
        }
    }

    @GetMapping(value = "/pricingrange")
    List<RestaurantCoreInfo> findPricingRangeBetween(@SessionAttribute Optional<User> user,
                                                     @PathVariable String scope,
                                                     @RequestParam int low,
                                                     @RequestParam int high,
                                                     @RequestParam Optional<String[]> current_ids,
                                                     HttpServletResponse res) {
        if (user.isPresent()) {
            return this.coreInfoService.findPricingRangeBetween(low, high, current_ids);
        } else {
            return Utils.unauthorizedAccess(res);
        }
    }

    @GetMapping(value = "/rating")
    List<RestaurantCoreInfo> findRatingRangeBetween(@SessionAttribute Optional<User> user,
                                                    @PathVariable String scope,
                                                    @RequestParam int low,
                                                    @RequestParam int high,
                                                    @RequestParam Optional<String[]> current_ids,
                                                    HttpServletResponse res) {
        if (user.isPresent()) {
            return this.coreInfoService.findRatingBetween(low, high, current_ids);
        } else {
            return Utils.unauthorizedAccess(res);
        }
    }
}
