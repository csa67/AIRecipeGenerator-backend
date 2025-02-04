package com.app.recipe_generator.controller;

import com.app.recipe_generator.entity.SavedRecipes;
import com.app.recipe_generator.model.RecipeResponse;
import com.app.recipe_generator.repository.SavedRecipesRepo;
import com.app.recipe_generator.repository.UserRepo;
import com.app.recipe_generator.services.MyUserDetailService;
import com.app.recipe_generator.services.RecipeService;
import jakarta.validation.Valid;
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

    @Autowired
    private SavedRecipesRepo recipesRepo;

    @Autowired
    private MyUserDetailService userDetailService;

    // Save Recipe with Ingredients
    @PostMapping("/saveRecipe")
    public ResponseEntity<RecipeResponse> saveRecipe(
            @Valid
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
