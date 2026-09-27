package com.ga.food.service;

import com.ga.food.Exception.InfoNotFoundException;
import com.ga.food.model.categories;
import com.ga.food.model.Recipe;
import com.ga.food.repository.CategoryRepository;
import com.ga.food.repository.RecipeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RecipeService {

    private RecipeRepository recipeRepository;
    private CategoryRepository categoryRepository;

    public Recipe createRecipe(Long categoryId, Recipe recipe){
        System.out.println("Service calling createRecipe ==>");
        categories category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new InfoNotFoundException(
                                "Category with id " + categoryId + " not found"
                        ));
        recipe.setCategory(category);
        return recipeRepository.save(recipe);
    }

    // GET

    // Get all recipes
    public List<Recipe> getRecipe() {
        System.out.println("service calling recipes");
        return recipeRepository.findAll();
    }

    // Get recipe by ID
    public Recipe getRecipe(Long id) {
        return recipeRepository.findById(id)
                .orElseThrow(() ->
                        new InfoNotFoundException(
                                "Recipe with id " + id + " not found"
                        ));
    }

    // Update

    public Recipe updateRecipe(Long id, Long categoryId, Recipe recipeObject) {

        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() ->
                        new InfoNotFoundException(
                                "Recipe with id " + id + " not found"
                        ));

        categories category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new InfoNotFoundException(
                                "Category with id " + categoryId + " not found"
                        ));

        recipe.setName(recipeObject.getName());
        recipe.setTime(recipeObject.getTime());
        recipe.setPortions(recipeObject.getPortions());
        recipe.setIngredients(recipeObject.getIngredients());
        recipe.setSteps(recipeObject.getSteps());
        recipe.setPublic(recipeObject.isPublic());

        // Change category
        recipe.setCategory(category);

        return recipeRepository.save(recipe);
    }

    //Delete

    public void deleteRecipe(Long id) {

        Recipe recipe = recipeRepository.findById(id).orElseThrow(() ->
                        new InfoNotFoundException("Recipe with id " + id + " not found"));

        recipeRepository.delete(recipe);
    }
}