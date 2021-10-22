package com.example.resto;

import com.example.resto.model.Cuisine;
import com.example.resto.model.RestaurantCoreInfo;
import com.example.resto.repository.CuisineRepository;
import com.example.resto.repository.RestaurantCoreInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Set;

@SpringBootApplication
public class RestoApplication {

    private static final Logger log = LoggerFactory.getLogger(RestoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RestoApplication.class);
    }

//    @Bean
//    public CommandLineRunner demo(CuisineRepository cuisineRepository, RestaurantCoreInfoRepository repository) {
//        return (args) -> {
////             save a few customers
//            Cuisine japanese = cuisineRepository.save(new Cuisine("Japanese"));
//            Cuisine sushi = cuisineRepository.save(new Cuisine("Sushi"));
//            Cuisine italian = cuisineRepository.save(new Cuisine("Italian"));
//            Cuisine american = cuisineRepository.save(new Cuisine("American"));
////            Cuisine american2 = cuisineRepository.save(new Cuisine("American"));
//            Cuisine burgers = cuisineRepository.save(new Cuisine("Burgers"));
//            RestaurantCoreInfo japaneseRestaurant = new RestaurantCoreInfo("Japanese Restaurant", 2, 50, 12);
//            japaneseRestaurant.getCuisines().add(japanese);
//            japaneseRestaurant.getCuisines().add(sushi);
//            repository.save(japaneseRestaurant);
//            RestaurantCoreInfo japaneseRestaurant1 = new RestaurantCoreInfo("Japanese Restaurant", 1, 123, 33);
//            japaneseRestaurant1.getCuisines().add(japanese);
//            repository.save(japaneseRestaurant1);
//            RestaurantCoreInfo italianRestaurant = new RestaurantCoreInfo("Italian Restaurant", 1, 120, 33);
//            italianRestaurant.getCuisines().add(italian);
//            repository.save(italianRestaurant);
//            RestaurantCoreInfo americanRestaurant = new RestaurantCoreInfo("American Restaurant", 2, 46, 8);
//            americanRestaurant.getCuisines().add(american);
//            repository.save(americanRestaurant);
//            RestaurantCoreInfo americanBurgerRestaurant = new RestaurantCoreInfo("American Burger Restaurant", 2, 78, 14);
//            americanBurgerRestaurant.getCuisines().add(american);
//            americanBurgerRestaurant.getCuisines().add(burgers);
//            repository.save(americanBurgerRestaurant);
//
//            // fetch all customers
//            log.info("Customers found with findAll():");
//            log.info("-------------------------------");
//            for (RestaurantCoreInfo restaurantCoreInfo : repository.findAll()) {
//                log.info(restaurantCoreInfo.toString());
//            }
//            log.info("");
//
//            // fetch an individual customer by ID
////            RestaurantCoreInfo restaurantCoreInfo = repository.findById(1L);
////            log.info("Customer found with findById(1L):");
////            log.info("--------------------------------");
////            log.info(restaurantCoreInfo.toString());
////            log.info("");
//
//            // fetch customers by last name
//            log.info("Customer found with findByName('Japanese Restaurant'):");
//            log.info("--------------------------------------------");
//            repository.findByName("Japanese Restaurant").forEach(japan -> {
//                log.info(japan.toString());
//            });
//            log.info("");
//        };
//    }
}
