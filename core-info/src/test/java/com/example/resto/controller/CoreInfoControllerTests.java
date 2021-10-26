package com.example.resto.controller;


//import com.example.resto.auth.AudienceValidator;
//import com.example.resto.auth.SecurityConfig;
import com.example.resto.interceptors.RestInterceptorAll;
import com.example.resto.model.RestaurantCoreInfo;
import com.example.resto.model.User;
import com.example.resto.service.CoreInfoService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureMockMvc(addFilters = false)
public class CoreInfoControllerTests {

    @MockBean
    RestInterceptorAll interceptor;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CoreInfoService service;


    private final ObjectMapper objectMapper = new ObjectMapper();

    private final Map<String, Object> sessionAttr = new HashMap<>();

    @BeforeEach
    void initTest() throws Exception {
        this.sessionAttr.clear();
        this.sessionAttr.put("user", new User());
        when(interceptor.preHandle(any(), any(), any())).thenReturn(true);
    }

    @Test
    public void testFindByName() throws Exception {
        RestaurantCoreInfo japaneseRestaurant = new RestaurantCoreInfo("Japanese Restaurant", 2, 50, 12);
        japaneseRestaurant.setId(1L);
        List<RestaurantCoreInfo> restaurants = List.of(japaneseRestaurant);
        when(service.findByName(any(), notNull())).thenReturn(Optional.of(restaurants));
        MvcResult mvcResult = mockMvc.perform(get("/api/coreinfo/public/Restaurant_1?current_ids=1,2").sessionAttrs(this.sessionAttr))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        assertNotNull(content);
        List<RestaurantCoreInfo> actual = objectMapper.readValue(content, new TypeReference<>() {
        });
        assertEquals(restaurants, actual);
    }

    @Test
    public void testFindByCuisineName() throws Exception {
        RestaurantCoreInfo italianRestaurant = new RestaurantCoreInfo("Italian Restaurant", 1, 120, 33);
        List<RestaurantCoreInfo> restaurants = List.of(italianRestaurant);
        when(service.findByCuisineName(any(), notNull())).thenReturn(Optional.of(restaurants));
        MvcResult mvcResult = mockMvc.perform(get("/api/coreinfo/public/cuisine/italian?current_ids=1,2").sessionAttrs(this.sessionAttr))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        assertNotNull(content);
        List<RestaurantCoreInfo> actual = objectMapper.readValue(content, new TypeReference<>() {
        });
        assertEquals(restaurants, actual);
    }

    @Test
    public void testFindPricingRangeBetween() throws Exception {
        RestaurantCoreInfo italianRestaurant = new RestaurantCoreInfo("Italian Restaurant", 1, 120, 33);
        List<RestaurantCoreInfo> restaurants = List.of(italianRestaurant);
        when(service.findPricingRangeBetween(anyInt(), anyInt(), any())).thenReturn(Optional.of(restaurants));
        MvcResult mvcResult = mockMvc.perform(get("/api/coreinfo/public/pricingrange?low=1&high=2&current_ids=1,2").sessionAttrs(this.sessionAttr))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        assertNotNull(content);
        List<RestaurantCoreInfo> actual = objectMapper.readValue(content, new TypeReference<>() {
        });
        assertEquals(restaurants, actual);
    }

    @Test
    public void testFindRatingRangeBetween() throws Exception {
        RestaurantCoreInfo italianRestaurant = new RestaurantCoreInfo("Italian Restaurant", 1, 120, 33);
        List<RestaurantCoreInfo> restaurants = List.of(italianRestaurant);
        when(service.findRatingBetween(anyDouble(), anyDouble(), any())).thenReturn(Optional.of(restaurants));
        MvcResult mvcResult = mockMvc.perform(get("/api/coreinfo/public/rating?low=1&high=2&current_ids=1,2").sessionAttrs(this.sessionAttr))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        assertNotNull(content);
        List<RestaurantCoreInfo> actual = objectMapper.readValue(content, new TypeReference<>() {
        });
        assertEquals(restaurants, actual);

    }

}
