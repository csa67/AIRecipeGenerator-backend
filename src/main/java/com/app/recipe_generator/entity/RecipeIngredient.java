package com.app.recipe_generator.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "recipe_ingredients")
@Getter
@Setter
@NoArgsConstructor
public class RecipeIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "recipe_id",nullable = false)
    private SavedRecipes recipe;

    @ManyToOne
    @JoinColumn(name="ingredient_id",nullable = false)
    private Ingredient ingredient;

    private Double quantity;
    private String unit;

    public SavedRecipes getRecipe() {
        return recipe;
    }

    public void setRecipe(SavedRecipes recipe) {
        this.recipe = recipe;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }
}
