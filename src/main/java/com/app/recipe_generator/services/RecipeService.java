package com.app.recipe_generator.services;

import com.app.recipe_generator.DTO.RecipeGenRequestDTO;
import com.app.recipe_generator.DTO.RecipeResponseDTO;
import com.app.recipe_generator.entity.*;
import com.app.recipe_generator.DTO.RecipeResponse;
import com.app.recipe_generator.repository.RecipeIngredientRepo;
import com.app.recipe_generator.repository.SavedRecipesRepo;
import com.app.recipe_generator.utils.PromptBuilder;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @Autowired
    private PromptBuilder promptBuilder;

    @Autowired
    private OpenAiService openAiService;

    @Autowired
    private SavedRecipesRepo savedRecipesRepo;



    // ✅ Fetch all saved recipes
    public List<SavedRecipes> getAllSavedRecipes() {
        return recipesRepo.findAll();
    }

//    public RecipeResponse deleteRecipe(UUID recipeId) {
//        Optional<SavedRecipes> existingRecipe = recipesRepo.findById(recipeId);
//
//        if (existingRecipe.isPresent()) {
//            recipesRepo.deleteById(recipeId);
//            return new RecipeResponse("Recipe deleted successfully!", true);
//        } else {
//            return new RecipeResponse("Recipe not found!", false);
//        }
//    }

    public RecipeResponse saveRecipe(RecipeResponseDTO dto, User user) {
        SavedRecipes saved = new SavedRecipes();
        saved.setTitle(dto.getTitle());
        saved.setDescription(dto.getDescription());
        saved.setCalories(dto.getCalories());
        saved.setServings(dto.getServings());
        saved.setTime(dto.getTime());
        saved.setInstructions(String.join("\n", dto.getInstructions()));
        saved.setUser(user);

        SavedRecipes finalSaved = saved;
        List<RecipeIngredient> ingredients = dto.getIngredients().stream()
                .map(i -> {
                    RecipeIngredient ing = new RecipeIngredient();
                    ing.setName(i.getName());
                    ing.setQuantity(i.getQuantity());
                    ing.setUnit(i.getUnit());
                    ing.setRecipe(finalSaved);
                    return ing;
                }).toList();

        saved.setIngredients(ingredients);
        saved = savedRecipesRepo.save(saved);

        RecipeResponse response = new RecipeResponse();
        response.setId(saved.getId());
        response.setMessage("Recipe saved successfully");
        return response;
    }
}
