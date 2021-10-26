package com.example.resto.controller;

import com.example.resto.interceptors.RestInterceptorAll;
import com.example.resto.service.CoreInfoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureMockMvc(addFilters = false)
public class CoreInfoControllerUnauthorizedTests {

    @MockBean
    RestInterceptorAll interceptor;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CoreInfoService service;

    @BeforeEach
    void initTest() throws Exception {
        when(interceptor.preHandle(any(), any(), any())).thenReturn(true);
    }

    @Test
    public void testFindByNameUnauthorized() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/coreinfo/public/Restaurant_1?current_ids=1,2"))
                .andDo(print())
                .andExpect(status().isUnauthorized())
                .andReturn();
        String actual = mvcResult.getResponse().getErrorMessage();

        String expected = "401 Unauthorized access";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testFindByCuisineNameUnauthorized() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/coreinfo/public/cuisine/italian?current_ids=1,2"))
                .andDo(print())
                .andExpect(status().isUnauthorized())
                .andReturn();
        String actual = mvcResult.getResponse().getErrorMessage();

        String expected = "401 Unauthorized access";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testFindPricingRangeBetweenUnauthorized() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/coreinfo/public/pricingrange?low=1&high=2&current_ids=1,2"))
                .andDo(print())
                .andExpect(status().isUnauthorized())
                .andReturn();
        String actual = mvcResult.getResponse().getErrorMessage();

        String expected = "401 Unauthorized access";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testFindRatingRangeBetween() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/coreinfo/public/rating?low=1&high=2&current_ids=1,2"))
                .andDo(print())
                .andExpect(status().isUnauthorized())
                .andReturn();
        String actual = mvcResult.getResponse().getErrorMessage();

        String expected = "401 Unauthorized access";
        assertThat(actual).isEqualTo(expected);
    }

}
