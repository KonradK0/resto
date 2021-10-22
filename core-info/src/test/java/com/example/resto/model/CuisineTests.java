package com.example.resto.model;

import com.example.resto.repository.CuisineRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CuisineTests {

    @Autowired
    private CuisineRepository cuisineRepository;

    @Test
    public void addCuisineTest() throws Exception{
        Cuisine japanese = this.cuisineRepository.save(new Cuisine("Japanese"));
        assertThat(japanese).isNotNull();
        assertThat(japanese).extracting("id").isNotNull();
        assertThat(japanese).extracting("name").isEqualTo("Japanese");
        assertThat(japanese).extracting("restaurants").isNull();
    }
}
