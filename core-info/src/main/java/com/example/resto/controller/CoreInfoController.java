package com.example.resto.controller;

import com.example.resto.model.RestaurantCoreInfo;
import com.example.resto.model.User;
import com.example.resto.service.CoreInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
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
            this.unauthorizedAccess(res);
            return Optional.empty();
        }
    }

    @GetMapping(value = "/public/cuisine/{cuisineName}")
    public Optional<List<RestaurantCoreInfo>> findByCuisines_Name(@SessionAttribute Optional<User> user,
                                                 @PathVariable String cuisineName,
                                                 @RequestParam Optional<String[]> current_ids,
                                                 HttpServletResponse res) {
        if (user.isPresent()) {
            return this.coreInfoService.findByCuisineName(cuisineName, current_ids);
        } else {
            this.unauthorizedAccess(res);
            return Optional.empty();
        }
    }

    @GetMapping(value = "/public/pricingrange")
    public Optional<List<RestaurantCoreInfo>>  findPricingRangeBetween(@SessionAttribute Optional<User> user,
                                                     @RequestParam int low,
                                                     @RequestParam int high,
                                                     @RequestParam Optional<String[]> current_ids,
                                                     HttpServletResponse res) {
        if (user.isPresent()) {
            return this.coreInfoService.findPricingRangeBetween(low, high, current_ids);
        } else {
            this.unauthorizedAccess(res);
            return Optional.empty();
        }
    }

    @GetMapping(value = "/public/rating")
    public Optional<List<RestaurantCoreInfo>> findRatingRangeBetween(@SessionAttribute Optional<User> user,
                                                    @RequestParam int low,
                                                    @RequestParam int high,
                                                    @RequestParam Optional<String[]> current_ids,
                                                    HttpServletResponse res) {
        if (user.isPresent()) {
            return this.coreInfoService.findRatingBetween(low, high, current_ids);
        } else {
            this.unauthorizedAccess(res);
            return Optional.empty();
        }
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(value = "hasAuthority('create:coreinfo')")
    public Optional<RestaurantCoreInfo> create(@SessionAttribute Optional<User> user,
                                               @RequestBody RestaurantCoreInfo restaurantCoreInfo,
                                               HttpServletResponse res){
        if (user.isPresent()) {
            res.setStatus(HttpServletResponse.SC_CREATED);
            return Optional.ofNullable(this.coreInfoService.save(restaurantCoreInfo));
        } else {
            this.unauthorizedAccess(res);
            return Optional.empty();
        }

    }

    @PutMapping(value = "/{rci_id}")
    @PreAuthorize(value = "hasAuthority('update:coreinfo')")
    public Optional<RestaurantCoreInfo> updateById(@SessionAttribute Optional<User> user,
                                         @PathVariable long rci_id,
                                         @RequestBody RestaurantCoreInfo restaurantCoreInfo,
                                         HttpServletResponse res){
        if(user.isPresent()) {
            return Optional.ofNullable(this.coreInfoService.updateById(rci_id, restaurantCoreInfo));
        } else {
            this.unauthorizedAccess(res);
            return Optional.empty();
        }

    }

    @DeleteMapping(value = "/{rci_id}")
    @PreAuthorize(value = "hasAuthority('delete:coreinfo')")
    public void deleteById(@SessionAttribute Optional<User> user,
                           @PathVariable long rci_id,
                           HttpServletResponse res){
        if(user.isPresent()) {
            res.setStatus(HttpServletResponse.SC_NO_CONTENT);
            this.coreInfoService.deleteById(rci_id);
        } else {
            this.unauthorizedAccess(res);
        }

    }


    private void unauthorizedAccess(HttpServletResponse res) {
        try {
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "401 Unauthorized access");
        } catch (IOException e) {
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            log.error(String.valueOf(e));
        }
    }
}
