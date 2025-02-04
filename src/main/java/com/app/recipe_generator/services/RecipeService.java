package com.app.recipe_generator.services;

import com.app.recipe_generator.entity.Ingredient;
import com.app.recipe_generator.entity.RecipeIngredient;
import com.app.recipe_generator.entity.SavedRecipes;
import com.app.recipe_generator.entity.User;
import com.app.recipe_generator.model.RecipeResponse;
import com.app.recipe_generator.repository.IngredientRepo;
import com.app.recipe_generator.repository.RecipeIngredientRepo;
import com.app.recipe_generator.repository.SavedRecipesRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    @Autowired
    private SavedRecipesRepo recipesRepo;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private RecipeIngredientRepo recipeIngredientRepo;

    @Autowired
    private MyUserDetailService userDetailService;

    @Transactional
    public RecipeResponse saveRecipe(SavedRecipes recipe) {
        try {
            Optional<SavedRecipes> existingRecipe = recipesRepo.findByName(recipe.getName());

            if (existingRecipe.isPresent()) {
                return new RecipeResponse("Recipe already saved!", false);
            }

            User user = userDetailService.getUserDetails();
            recipe.setUser(user);

            List<RecipeIngredient> ingredients = new ArrayList<>();
            SavedRecipes savedRecipe = recipesRepo.save(recipe);

            for (RecipeIngredient ri : recipe.getIngredients()) {
                Ingredient ingredient = ingredientService.saveOrFindIngredient(ri.getIngredient().getName());
                ri.setRecipe(savedRecipe);
                ri.setIngredient(ingredient);
                ingredients.add(ri);
            }

            recipeIngredientRepo.saveAll(ingredients);
            savedRecipe.setIngredients(ingredients);
            recipesRepo.save(savedRecipe);

            return new RecipeResponse("Recipe saved successfully!", true);

        } catch (Exception e) {
            // ❌ Log the error and roll back transaction
            System.err.println("Error while saving recipe: " + e.getMessage());
            throw new RuntimeException("Failed to save recipe. Please try again.");
        }
    }



    // ✅ Fetch all saved recipes
    public List<SavedRecipes> getAllSavedRecipes() {
        return recipesRepo.findAll();
    }

    public RecipeResponse deleteRecipe(UUID recipeId) {
        Optional<SavedRecipes> existingRecipe = recipesRepo.findById(recipeId);

        if (existingRecipe.isPresent()) {
            recipesRepo.deleteById(recipeId);
            return new RecipeResponse("Recipe deleted successfully!", true);
        } else {
            return new RecipeResponse("Recipe not found!", false);
        }
    }
}
