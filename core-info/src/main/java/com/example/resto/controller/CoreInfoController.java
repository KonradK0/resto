package com.example.resto.controller;

import com.example.resto.model.RestaurantCoreInfo;
import com.example.resto.model.User;
import com.example.resto.service.CoreInfoService;
import com.example.resto.util.Utils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


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
    public List<RestaurantCoreInfo> findByName(@SessionAttribute User user,
                                               @PathVariable String scope,
                                               @PathVariable String name,
                                               @RequestParam(required = false) String[] current_ids,
                                               HttpServletResponse res) {
        if (user != null) {
            return this.coreInfoService.findByName(name, current_ids);
        } else {
            return Utils.unauthorizedAccess(res);
        }
    }

    @GetMapping(value = "/cuisine/{cuisineName}")
    List<RestaurantCoreInfo> findByCuisines_Name(@SessionAttribute User user,
                                                 @PathVariable String scope,
                                                 @PathVariable String cuisineName,
                                                 @RequestParam(required = false) String[] current_ids,
                                                 HttpServletResponse res) {
        if (user != null) {
            return this.coreInfoService.findByCuisineName(cuisineName, current_ids);
        } else {
            return Utils.unauthorizedAccess(res);
        }
    }

    @GetMapping(value = "/pricingrange")
    List<RestaurantCoreInfo> findPricingRangeBetween(@SessionAttribute User user,
                                                     @PathVariable String scope,
                                                     @RequestParam int low,
                                                     @RequestParam int high,
                                                     @RequestParam(required = false) String[] current_ids,
                                                     HttpServletResponse res) {
        if (user != null) {
            return this.coreInfoService.findPricingRangeBetween(low, high, current_ids);
        } else {
            return Utils.unauthorizedAccess(res);
        }
    }

    @GetMapping(value = "/rating")
    List<RestaurantCoreInfo> findRatingRangeBetween(@SessionAttribute User user,
                                                    @PathVariable String scope,
                                                    @RequestParam int low,
                                                    @RequestParam int high,
                                                    @RequestParam(required = false) String[] current_ids,
                                                    HttpServletResponse res) {
        if (user != null) {
            return this.coreInfoService.findRatingBetween(low, high, current_ids);
        } else {
            return Utils.unauthorizedAccess(res);
        }
    }

}
