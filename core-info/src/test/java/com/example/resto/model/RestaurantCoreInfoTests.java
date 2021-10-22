package com.example.resto.model;

import com.example.resto.repository.RestaurantCoreInfoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class RestaurantCoreInfoTests {

    @Autowired
    private RestaurantCoreInfoRepository restaurantCoreInfoRepository;

    @Test
    public void addRestaurantCoreInfoTest() throws Exception {
        Cuisine cuisine = Mockito.mock(Cuisine.class);
        RestaurantCoreInfo japaneseRestaurant = new RestaurantCoreInfo
                ("Japanese Restaurant", 2, 60, 12);
        RestaurantCoreInfo coreInfo = this.restaurantCoreInfoRepository.save(japaneseRestaurant);
        assertThat(coreInfo).isNotNull();
        assertThat(coreInfo).extracting("id").isNotNull();
        assertThat(coreInfo).extracting("name").isEqualTo("Japanese Restaurant");
        assertThat(coreInfo).extracting("pricingRange").isEqualTo(2);
        assertThat(coreInfo).extracting("ratingSum").isEqualTo(60);
        assertThat(coreInfo).extracting("numOfRatings").isEqualTo(12);
        assertThat(coreInfo).extracting("cuisines").isNotNull();
        assertThat(coreInfo.cuisines).isEmpty();
    }
}
