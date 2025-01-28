package com.app.job_tracker.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "recipes")
@Getter
@Setter
@NoArgsConstructor
public class SavedRecipes {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @ElementCollection
    @CollectionTable(name = "recipe_ingredients", joinColumns = @JoinColumn(name = "recipe_id"))
    private List<RecipeIngredient> ingredients;

    @Column(nullable = false)
    private String instructions;

    private int calories;

    @Column(nullable = false)
    private int servings;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
