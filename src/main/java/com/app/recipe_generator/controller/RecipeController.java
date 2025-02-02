package com.app.recipe_generator.controller;

import com.app.recipe_generator.entity.SavedRecipes;
import com.app.recipe_generator.model.RecipeResponse;
import com.app.recipe_generator.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    // Save Recipe with Ingredients
    @PostMapping("/saveRecipe")
    public ResponseEntity<RecipeResponse> saveRecipe(
            @RequestBody SavedRecipes recipe) {

        RecipeResponse response = recipeService.saveRecipe(recipe);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/savedRecipes")
    public ResponseEntity<List<SavedRecipes>> getAllSavedRecipes() {
        List<SavedRecipes> recipes = recipeService.getAllSavedRecipes();
        return ResponseEntity.ok(recipes);
    }

    @DeleteMapping("/recipe/{id}")
    public ResponseEntity<RecipeResponse> deleteRecipe(@PathVariable UUID id) {
        RecipeResponse response = recipeService.deleteRecipe(id);
        return ResponseEntity.ok(response);
    }
}
