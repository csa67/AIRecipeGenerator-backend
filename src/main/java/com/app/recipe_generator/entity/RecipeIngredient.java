package com.app.recipe_generator.entity;

import jakarta.persistence.*;

@Entity
public class RecipeIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "recipe_id",nullable = false)
    private SavedRecipes recipe;

    @ManyToOne
    @JoinColumn(name="ingredient_id",nullable = false)
    private Ingredient ingredient;

    private Double quantity;
    private String unit;

}
