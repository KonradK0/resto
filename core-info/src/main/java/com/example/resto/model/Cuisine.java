package com.example.resto.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity(name = "CUISINE")
@Getter
@Setter
@NoArgsConstructor
public class Cuisine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "cuisines", fetch = FetchType.EAGER)
    Set<RestaurantCoreInfo> restaurants;

    public Cuisine(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Cuisine{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cuisine cuisine = (Cuisine) o;
        return name.equals(cuisine.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
