package com.rezdy.lunch.service;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Ingredient {

    @Id
    @Column(name = "ingredient_title")
    private String title;

    private LocalDate bestBefore;

    private LocalDate useBy;

    // Missing Annotation Added
    @ManyToMany(mappedBy = "ingredients")
    private Set<Recipe> recipes = new HashSet<>();;

    public String getTitle() {
        return title;
    }

    public Ingredient setTitle(String title) {
        this.title = title;
        return this;
    }

    public LocalDate getBestBefore() {
        return bestBefore;
    }

    public Ingredient setBestBefore(LocalDate bestBefore) {
        this.bestBefore = bestBefore;
        return this;
    }

    public LocalDate getUseBy() {
        return useBy;
    }

    public Ingredient setUseBy(LocalDate useBy) {
        this.useBy = useBy;
        return this;
    }
}
