package com.app.recipe_generator.controller;

import com.app.recipe_generator.DTO.RecipeGenRequestDTO;
import com.app.recipe_generator.DTO.RecipeResponseDTO;
import com.app.recipe_generator.entity.SavedRecipes;
import com.app.recipe_generator.entity.User;
import com.app.recipe_generator.DTO.RecipeResponse;
import com.app.recipe_generator.repository.SavedRecipesRepo;
import com.app.recipe_generator.repository.UserRepo;
import com.app.recipe_generator.services.MyUserDetailService;
import com.app.recipe_generator.services.OpenAiService;
import com.app.recipe_generator.services.RecipeService;
import com.app.recipe_generator.utils.PromptBuilder;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private OpenAiService openAiService;

    @Autowired
    private PromptBuilder promptBuilder;

    // Save Recipe with Ingredients
    @PostMapping("/saveRecipe")
    public ResponseEntity<RecipeResponse> saveRecipe(
            @RequestBody RecipeResponseDTO recipeDto
    ) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        RecipeResponse response = recipeService.saveRecipe(recipeDto, user);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/savedRecipes")
    public ResponseEntity<List<SavedRecipes>> getAllSavedRecipes() {
        List<SavedRecipes> recipes = recipeService.getAllSavedRecipes();
        return ResponseEntity.ok(recipes);
    }

//    @DeleteMapping("/recipe/{id}")
//    public ResponseEntity<RecipeResponse> deleteRecipe(@PathVariable UUID id) {
//        RecipeResponse response = recipeService.deleteRecipe(id);
//        return ResponseEntity.ok(response);
//    }

    @PostMapping("/generate")
    public ResponseEntity<List<RecipeResponseDTO>> generateAiRecipes(@RequestBody RecipeGenRequestDTO request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<RecipeResponseDTO> recipes = openAiService.generateRecipes(
                promptBuilder.buildPrompt(request, user.getFilter())
        );

        return ResponseEntity.ok(recipes);
    }

    @PostMapping("/generate/img")
    public ResponseEntity<String> generateAiRecipes(@RequestBody String title) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String url = openAiService.generateRecipeImg(title);

        return ResponseEntity.ok(url);
    }
}
