package com.rezdy.lunch.controller;

import com.rezdy.lunch.service.LunchService;
import com.rezdy.lunch.service.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class LunchController {

    private LunchService lunchService;

    @Autowired
    public LunchController(LunchService lunchService) {
        this.lunchService = lunchService;
    }

    @PostMapping("/lunch")
    public List<Recipe> getRecipes(@RequestParam(value = "date") String date) {
        return lunchService.getNonExpiredRecipesOnDate(LocalDate.parse(date));
    }


    @GetMapping("/lunch")
    public List<Recipe> getRecipesByDate(@RequestParam(value = "date") String date) {
        return lunchService.getNonExpiredRecipesOnDate(LocalDate.parse(date));
    }

    @GetMapping("/lunch/{title}")
    public List<Recipe> getRecipesByTitle(@PathVariable(value = "title") String title) {
        return lunchService.getRecipesByTitle(title);
    }
    
    // Not completed
    @GetMapping("/lunch/excludeIngredients")
    public List<Recipe> excludeRecipesByTitle(@RequestParam(value = "titles") List<String> titles) {
        return lunchService.excludeRecipesByTitle(titles);
    }
}
