package com.example.resto.controller;

import com.example.resto.model.RestaurantCoreInfo;
import com.example.resto.model.User;
import com.example.resto.service.CoreInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(
        value = "api/coreinfo",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class CoreInfoController {

    private static final Logger log = LoggerFactory.getLogger(CoreInfoController.class);

    private final CoreInfoService coreInfoService;

    public CoreInfoController(CoreInfoService coreInfoService) {
        this.coreInfoService = coreInfoService;
    }

    @GetMapping(value = "/public/{name}")
    public Optional<List<RestaurantCoreInfo>> findByName(@SessionAttribute Optional<User> user,
                                               @PathVariable String name,
                                               @RequestParam Optional<String[]> current_ids,
                                               HttpServletResponse res) {
        if (user.isPresent()) {
            return this.coreInfoService.findByName(name, current_ids);
        } else {
            return this.unauthorizedAccess(res);
        }
    }

    @GetMapping(value = "/public/cuisine/{cuisineName}")
    Optional<List<RestaurantCoreInfo>> findByCuisines_Name(@SessionAttribute Optional<User> user,
                                                 @PathVariable String cuisineName,
                                                 @RequestParam Optional<String[]> current_ids,
                                                 HttpServletResponse res) {
        if (user.isPresent()) {
            return this.coreInfoService.findByCuisineName(cuisineName, current_ids);
        } else {
            return this.unauthorizedAccess(res);
        }
    }

    @GetMapping(value = "/public/pricingrange")
    Optional<List<RestaurantCoreInfo>>  findPricingRangeBetween(@SessionAttribute Optional<User> user,
                                                     @RequestParam int low,
                                                     @RequestParam int high,
                                                     @RequestParam Optional<String[]> current_ids,
                                                     HttpServletResponse res) {
        if (user.isPresent()) {
            return this.coreInfoService.findPricingRangeBetween(low, high, current_ids);
        } else {
            return this.unauthorizedAccess(res);
        }
    }

    @GetMapping(value = "/public/rating")
    Optional<List<RestaurantCoreInfo>> findRatingRangeBetween(@SessionAttribute Optional<User> user,
                                                    @RequestParam int low,
                                                    @RequestParam int high,
                                                    @RequestParam Optional<String[]> current_ids,
                                                    HttpServletResponse res) {
        if (user.isPresent()) {
            return this.coreInfoService.findRatingBetween(low, high, current_ids);
        } else {
            return this.unauthorizedAccess(res);
        }
    }

    private Optional<List<RestaurantCoreInfo>> unauthorizedAccess(HttpServletResponse res) {
        try {
            res.sendError(401, "401 Unauthorized access");
        } catch (IOException e) {
            res.setStatus(500);
            log.error(String.valueOf(e));
        }
        return Optional.empty();
    }
}
