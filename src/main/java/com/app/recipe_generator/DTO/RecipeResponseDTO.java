package com.app.recipe_generator.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RecipeResponseDTO {

    private String title;
    private String description;
    private int servings;
    private double calories;
    private String time;
    private List<IngredientDTO> ingredients;
    private List<String> instructions;

    // Getters and Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getServings() { return servings; }
    public void setServings(int servings) { this.servings = servings; }

    public double getCalories() { return calories; }
    public void setCalories(double calories) { this.calories = calories; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public List<IngredientDTO> getIngredients() { return ingredients; }
    public void setIngredients(List<IngredientDTO> ingredients) { this.ingredients = ingredients; }

    public List<String> getInstructions() { return instructions; }
    public void setInstructions(List<String> instructions) { this.instructions = instructions; }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class IngredientDTO {
        private String name;
        private double quantity;
        private String unit;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public double getQuantity() { return quantity; }
        public void setQuantity(double quantity) { this.quantity = quantity; }

        public String getUnit() { return unit; }
        public void setUnit(String unit) { this.unit = unit; }
    }
}
