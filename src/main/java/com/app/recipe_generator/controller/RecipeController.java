package com.app.recipe_generator.controller;

import com.app.recipe_generator.entity.SavedRecipes;
import com.app.recipe_generator.model.RecipeResponse;
import com.app.recipe_generator.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    // Save Recipe with Ingredients
    @PostMapping("/save")
    public ResponseEntity<RecipeResponse> saveRecipe(
            @RequestBody SavedRecipes recipe) {

        RecipeResponse response = recipeService.saveRecipe(recipe);
        return ResponseEntity.ok(response);
    }
}
