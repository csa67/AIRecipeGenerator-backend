package com.app.recipe_generator.services;

import com.app.recipe_generator.entity.Ingredient;
import com.app.recipe_generator.repository.IngredientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepo ingredientRepo;
    // ✅ Find or Save Ingredient
    public Ingredient saveOrFindIngredient(String name) {
        Optional<Ingredient> existingIngredient = ingredientRepo.findByNameIgnoreCase(name);

        return existingIngredient.orElseGet(() -> {
            Ingredient newIngredient = new Ingredient();
            newIngredient.setName(name);
            return ingredientRepo.save(newIngredient);
        });
    }
    public Ingredient saveIngredient(Ingredient ingredient) {
        return ingredientRepo.save(ingredient);
    }
}
