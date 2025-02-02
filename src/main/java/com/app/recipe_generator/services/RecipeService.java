package com.app.recipe_generator.services;

import com.app.recipe_generator.entity.Ingredient;
import com.app.recipe_generator.entity.RecipeIngredient;
import com.app.recipe_generator.entity.SavedRecipes;
import com.app.recipe_generator.model.RecipeResponse;
import com.app.recipe_generator.repository.IngredientRepo;
import com.app.recipe_generator.repository.RecipeIngredientRepo;
import com.app.recipe_generator.repository.SavedRecipesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public RecipeResponse saveRecipe(SavedRecipes recipe){
        Optional<SavedRecipes> existingRecipe = recipesRepo.findByName(recipe.getName());

        if (existingRecipe.isPresent()) {
            return new RecipeResponse("Recipe already saved!", false);
        }

        // Save Recipe
        SavedRecipes savedRecipe = recipesRepo.save(recipe);

        List<RecipeIngredient> savedIngredients = recipe.getIngredients().stream().map(ri -> {
            // Check if the ingredient already exists, reuse it
            Ingredient ingredient = ingredientService.saveOrFindIngredient(ri.getIngredient().getName());

            ri.setRecipe(savedRecipe);
            ri.setIngredient(ingredient);
            return ri;
        }).toList();

        recipeIngredientRepo.saveAll(savedIngredients);

        return new RecipeResponse("Recipe saved successfully!", true);
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
