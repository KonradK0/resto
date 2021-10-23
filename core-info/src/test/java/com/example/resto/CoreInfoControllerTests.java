package com.example.resto;


import com.example.resto.model.RestaurantCoreInfo;
import com.example.resto.service.CoreInfoService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class CoreInfoControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CoreInfoService service;


    private final ObjectMapper objectMapper = new ObjectMapper();


    @Test
    public void testFindByName() throws Exception {
        RestaurantCoreInfo japaneseRestaurant = new RestaurantCoreInfo("Japanese Restaurant", 2, 50, 12);
        List<RestaurantCoreInfo> restaurants = List.of(japaneseRestaurant);
        when(service.findByName(any())).thenReturn(restaurants);
        MvcResult mvcResult = mockMvc.perform(get("/api/public/coreinfo/Restaurant_1"))
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
        when(service.findByCuisineName(any())).thenReturn(restaurants);
        MvcResult mvcResult = mockMvc.perform(get("/api/public/coreinfo/cuisine/italian"))
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
