package com.example.resto.model;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name="RESTAURANTCOREINFO")
@Immutable
public class RestaurantCoreInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private int pricingRange;

    @Column(name = "RATING_SUM")
    private int ratingSum;

    @Column(name = "NUM_OF_RATINGS")
    private int numOfRatings;

    @Formula(value = "ROUND(RATING_SUM*1.0 / NUM_OF_RATINGS, 1)")
    private double rating;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "RESTAURANT_CUISINE",
            joinColumns = @JoinColumn(name="RESTAURANTCOREINFO_id"),
            inverseJoinColumns = @JoinColumn(name = "CUISINE_id")
    )
    Set<Cuisine> cuisines;

    public RestaurantCoreInfo() {
    }

    public RestaurantCoreInfo(String name, int pricingRange, int ratingSum, int numOfRatings) {
        this.name = name;
        this.pricingRange = pricingRange;
        this.ratingSum = ratingSum;
        this.numOfRatings = numOfRatings;
        this.cuisines = new HashSet<>();
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

    public int getPricingRange() {
        return pricingRange;
    }

    public void setPricingRange(int pricingRange) {
        this.pricingRange = pricingRange;
    }

    public int getRatingSum() {
        return ratingSum;
    }

    public void setRatingSum(int ratingSum) {
        this.ratingSum = ratingSum;
    }

    public int getNumOfRatings() {
        return numOfRatings;
    }

    public void setNumOfRatings(int numOfRatings) {
        this.numOfRatings = numOfRatings;
    }

    public Set<Cuisine> getCuisines() {
        return cuisines;
    }

    public void setCuisines(Set<Cuisine> cuisines) {
        this.cuisines = cuisines;
    }

    @Override
    public String toString() {
        return "RestaurantCoreInfo{" +
                "name='" + name + '\'' +
                ", pricingRange=" + pricingRange +
                ", rating=" + rating +
                ", cuisines=" + cuisines +
                '}';
    }

    public double getRating() {
        return rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestaurantCoreInfo that = (RestaurantCoreInfo) o;
        return pricingRange == that.pricingRange && ratingSum == that.ratingSum && numOfRatings == that.numOfRatings && Double.compare(that.rating, rating) == 0 && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, pricingRange, ratingSum, numOfRatings, rating);
    }
}
