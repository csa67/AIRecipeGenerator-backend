package com.app.recipe_generator.utils;

import com.app.recipe_generator.DTO.RecipeGenRequestDTO;
import com.app.recipe_generator.entity.Filter;
import org.springframework.stereotype.Component;

@Component
public class PromptBuilder {

    public String buildPrompt(RecipeGenRequestDTO req, Filter filter) {
        StringBuilder sb = new StringBuilder();
        sb.append("Generate 3-5 recipes using: ")
                .append(String.join(", ", req.getIngredients())).append(".\n")
                .append("Category: ").append(req.getCategory()).append("\n");

        if (req.getCuisine() != null) sb.append("Cuisine: ").append(req.getCuisine()).append("\n");
        if (req.getCalories() != null) sb.append("Calories under: ").append(req.getCalories()).append("\n");
        if (req.getTime() != null) sb.append("Ready in under: ").append(req.getTime()).append(" minutes\n");
        if (req.getAdditionalInstructions() != null)
            sb.append("Extra instructions: ").append(req.getAdditionalInstructions()).append("\n");

        sb.append("Dietary preferences: ")
                .append(String.join(", ", filter.getDietType())).append("\n")
                .append("Avoid allergens: ")
                .append(String.join(", ", filter.getAllergens())).append("\n")
                .append("Respond in JSON with: title, description, servings, calories, time, ingredients (name, quantity, unit), instructions (list).\n");

        return sb.toString();
    }
}