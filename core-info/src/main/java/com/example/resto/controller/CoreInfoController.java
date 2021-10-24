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
@RequestMapping(value = "api/{scope}/coreinfo", produces = MediaType.APPLICATION_JSON_VALUE)
public class CoreInfoController {

    private final CoreInfoService coreInfoService;

    public CoreInfoController(CoreInfoService coreInfoService) {
        this.coreInfoService = coreInfoService;
    }

    @GetMapping(value = "/{name}")
    public List<RestaurantCoreInfo> findByName(@SessionAttribute User user,
                                               @PathVariable String scope,
                                               @PathVariable String name,
                                               HttpServletResponse res) {
        if (user != null) {
            return this.coreInfoService.findByName(name);
        } else {
            return Utils.unauthorizedAccess(res);
        }
    }

    @GetMapping(value = "/cuisine/{cuisineName}")
    List<RestaurantCoreInfo> findByCuisines_Name(@SessionAttribute User user,
                                                 @PathVariable String scope,
                                                 @PathVariable String cuisineName,
                                                 HttpServletResponse res) {
        if (user != null) {
            return this.coreInfoService.findByCuisineName(cuisineName);
        } else {
            return Utils.unauthorizedAccess(res);
        }
    }

}
