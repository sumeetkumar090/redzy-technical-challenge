package com.rezdy.lunch.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "recipe")
public class Recipe {

    @Id
    @Column(name = "recipe_title")
    private String title;

    // Many to Many done on the basis of id rather than table
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "recipe_ingredient",
            joinColumns = @JoinColumn(name = "recipe_title"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_title"))
    private List<Ingredient> ingredients = new ArrayList<>();;

    public String getTitle() {
        return title;
    }

    public Recipe setTitle(String title) {
        this.title = title;
        return this;
    }

    public List<Ingredient> getIngredientsSet() {
        return ingredients;
    }

    public Recipe setIngredientsSet(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
        return this;
    }
}
