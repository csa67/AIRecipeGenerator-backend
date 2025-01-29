package com.app.recipe_generator.services;

import com.app.recipe_generator.entity.DietaryAllergen;
import com.app.recipe_generator.entity.DietaryPreferences;
import com.app.recipe_generator.repository.DietaryPreferencesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DietaryPreferencesService {

    @Autowired
    private DietaryPreferencesRepo dietaryPreferencesRepo;

    public DietaryPreferences addDietaryPreferences(DietaryPreferences preferences) {
        validateDietaryPreferences(preferences);
        return dietaryPreferencesRepo.save(preferences);
    }

    public Optional<DietaryPreferences> getDietaryPreferencesByUserId(UUID userId) {
        return dietaryPreferencesRepo.findByUserId(userId);
    }

    public DietaryPreferences updateDietaryPreferences(DietaryPreferences preferences) {
        validateDietaryPreferences(preferences);
        return dietaryPreferencesRepo.save(preferences);
    }

    public void deleteDietaryPreferences(UUID userId) {
        dietaryPreferencesRepo.deleteByUserId(userId);
    }


    // Validation logic
    private void validateDietaryPreferences(DietaryPreferences preferences) {
        if (preferences.getType() == null) {
            throw new IllegalArgumentException("Dietary type is required.");
        }

        if (preferences.getCalorieLimit() != null && preferences.getCalorieLimit() < 0) {
            throw new IllegalArgumentException("Calorie limit cannot be negative.");
        }

        if (preferences.getAllergens() != null) {
            for (DietaryAllergen allergen : preferences.getAllergens()) {
                if (allergen.getAllergenName() == null || allergen.getAllergenName().isEmpty()) {
                    throw new IllegalArgumentException("Allergen name cannot be empty.");
                }
            }
        }
    }
}
