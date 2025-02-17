package com.app.recipe_generator.entity;

import jakarta.persistence.*;

@Entity
public class DietaryAllergen {

    public Integer getId() {
        return id;
    }

    public DietaryPreferences getDietaryPreferences() {
        return dietaryPreferences;
    }

    public String getAllergenName() {
        return allergen;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "dietary_id",nullable = false)
    private DietaryPreferences dietaryPreferences;

    @Column(nullable = false)
    private String allergen;
}
