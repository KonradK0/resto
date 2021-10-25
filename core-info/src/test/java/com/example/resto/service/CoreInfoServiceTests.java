package com.example.resto.service;

import com.example.resto.model.Cuisine;
import com.example.resto.model.RestaurantCoreInfo;
import com.example.resto.repository.RestaurantCoreInfoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CoreInfoServiceTests {

    private final RestaurantCoreInfoRepository repository = Mockito.mock(RestaurantCoreInfoRepository.class);

    private final CoreInfoService coreInfoService = new CoreInfoService(this.repository);

    @Test
    public void testFindByName() {
        RestaurantCoreInfo japaneseRestaurant = mock(RestaurantCoreInfo.class);
        when(japaneseRestaurant.getName()).thenReturn("Japanese Restaurant");
        RestaurantCoreInfo italianRestaurant = mock(RestaurantCoreInfo.class);
        when(italianRestaurant.getName()).thenReturn("Italian Restaurant");
        RestaurantCoreInfo italianRestaurant2 = mock(RestaurantCoreInfo.class);
        when(italianRestaurant2.getName()).thenReturn("Italian Restaurant");
        when(this.repository.findById(anyLong()))
                .thenReturn(Optional.of(japaneseRestaurant))
                .thenReturn(Optional.of(italianRestaurant))
                .thenReturn(Optional.of(italianRestaurant2));
        String[] currents = {"1", "2", "3"};

        List<RestaurantCoreInfo> actual = this.coreInfoService.findByName("Italian Restaurant", Optional.of(currents));

        List<RestaurantCoreInfo> expected = List.of(italianRestaurant, italianRestaurant2);
        assertThat(expected).hasSameElementsAs(actual);
    }

    @Test
    public void testFindByCuisineName() {
        RestaurantCoreInfo japaneseRestaurant = mock(RestaurantCoreInfo.class);
        when(japaneseRestaurant.getCuisines()).thenReturn(Set.of(new Cuisine("Japanese")));
        RestaurantCoreInfo japaneseRestaurant2 = mock(RestaurantCoreInfo.class);
        when(japaneseRestaurant2.getCuisines()).thenReturn(Set.of(new Cuisine("Japanese")));
        RestaurantCoreInfo italianRestaurant = mock(RestaurantCoreInfo.class);
        when(italianRestaurant.getCuisines()).thenReturn(Set.of(new Cuisine("Italian")));
        RestaurantCoreInfo americanRestaurant = mock(RestaurantCoreInfo.class);
        when(americanRestaurant.getCuisines()).thenReturn(Set.of(new Cuisine("American")));
        when(this.repository.findById(anyLong()))
                .thenReturn(Optional.of(japaneseRestaurant))
                .thenReturn(Optional.of(italianRestaurant))
                .thenReturn(Optional.of(americanRestaurant))
                .thenReturn(Optional.of(japaneseRestaurant2));
        String[] currents = {"1", "2", "3", "4"};

        List<RestaurantCoreInfo> actual = this.coreInfoService.findByCuisineName("Japanese", Optional.of(currents));

        List<RestaurantCoreInfo> expected = List.of(japaneseRestaurant, japaneseRestaurant2);
        assertThat(expected).hasSameElementsAs(actual);
    }

    @Test
    public void testFindPricingRangeBetween() {
        RestaurantCoreInfo japaneseRestaurant = mock(RestaurantCoreInfo.class);
        when(japaneseRestaurant.getPricingRange()).thenReturn(2);
        RestaurantCoreInfo japaneseRestaurant2 = mock(RestaurantCoreInfo.class);
        when(japaneseRestaurant2.getPricingRange()).thenReturn(3);
        RestaurantCoreInfo italianRestaurant = mock(RestaurantCoreInfo.class);
        when(italianRestaurant.getPricingRange()).thenReturn(1);
        RestaurantCoreInfo americanRestaurant = mock(RestaurantCoreInfo.class);
        when(americanRestaurant.getPricingRange()).thenReturn(1);
        when(this.repository.findById(anyLong()))
                .thenReturn(Optional.of(japaneseRestaurant))
                .thenReturn(Optional.of(italianRestaurant))
                .thenReturn(Optional.of(americanRestaurant))
                .thenReturn(Optional.of(japaneseRestaurant2));
        String[] currents = {"1", "2", "3", "4"};

        List<RestaurantCoreInfo> actual = this.coreInfoService.findPricingRangeBetween(1, 2, Optional.of(currents));

        List<RestaurantCoreInfo> expected = List.of(italianRestaurant, americanRestaurant, japaneseRestaurant);
        assertThat(expected).hasSameElementsAs(actual);

    }

    @Test
    public void testRatingBetween() {
        RestaurantCoreInfo japaneseRestaurant = mock(RestaurantCoreInfo.class);
        when(japaneseRestaurant.getRating()).thenReturn((double) 50 / 10);
        RestaurantCoreInfo japaneseRestaurant2 = mock(RestaurantCoreInfo.class);
        when(japaneseRestaurant2.getRating()).thenReturn((double) 13 / 3);
        RestaurantCoreInfo italianRestaurant = mock(RestaurantCoreInfo.class);
        when(italianRestaurant.getRating()).thenReturn((double) 120 / 33);
        RestaurantCoreInfo americanRestaurant = mock(RestaurantCoreInfo.class);
        when(americanRestaurant.getRating()).thenReturn((double) 60 / 30);
        when(this.repository.findById(anyLong()))
                .thenReturn(Optional.of(japaneseRestaurant))
                .thenReturn(Optional.of(italianRestaurant))
                .thenReturn(Optional.of(americanRestaurant))
                .thenReturn(Optional.of(japaneseRestaurant2));
        String[] currents = {"1", "2", "3", "4"};

        List<RestaurantCoreInfo> actual = this.coreInfoService.findRatingBetween(2, 4, Optional.of(currents));

        List<RestaurantCoreInfo> expected = List.of(italianRestaurant, americanRestaurant);
        assertThat(expected).hasSameElementsAs(actual);

    }
}
