package com.app.recipe_generator.services;

import com.app.recipe_generator.entity.Ingredient;
import com.app.recipe_generator.repository.IngredientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepo ingredientRepo;

    // Save or find ingredient
    public Ingredient saveOrFindIngredient(String ingredientName) {
        return ingredientRepo.findByNameIgnoreCase(ingredientName)
                .orElseGet(() -> {
                    Ingredient newIngredient = new Ingredient();
                    newIngredient.setName(ingredientName);
                    return ingredientRepo.save(newIngredient);
                });
    }
}
