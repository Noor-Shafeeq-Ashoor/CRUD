package com.ga.food.controller;

import com.ga.food.model.Recipe;
import com.ga.food.service.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class RecipeController {

    private RecipeService recipeService;

    @PostMapping("/categories/{categoryId}/recipes")
    public Recipe createRecipe(@PathVariable(value = "categoryId") Long categoryId, @RequestBody Recipe recipeObject){

        System.out.println("calling recipe ===========>" );
        return recipeService.createRecipe(categoryId,recipeObject);
    }

    // Get all recipes
    @GetMapping("/recipes")
    public List<Recipe> getRecipes() {
        return recipeService.getRecipe();
    }

    // Get recipe by ID
    @GetMapping("/recipes/{id}")
    public Recipe getRecipe(@PathVariable Long id) {
        return recipeService.getRecipe(id);
    }
    @PutMapping("/categories/{categoryId}/recipes/{recipeId}")
    public Recipe updateRecipe(
            @PathVariable Long categoryId,
            @PathVariable Long recipeId,
            @RequestBody Recipe recipeObject) {

        return recipeService.updateRecipe(categoryId, recipeId, recipeObject);
    }

}