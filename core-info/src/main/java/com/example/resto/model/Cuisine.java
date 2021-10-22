package com.example.resto.model;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "CUISINE")
public class Cuisine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "cuisines", fetch = FetchType.EAGER)
    Set<RestaurantCoreInfo> restaurants;

    public Cuisine() {

    }

    public Cuisine(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Cuisine{" +
                "name='" + name + '\'' +
                '}';
    }

    public Set<RestaurantCoreInfo> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(Set<RestaurantCoreInfo> restaurants) {
        this.restaurants = restaurants;
    }
}
