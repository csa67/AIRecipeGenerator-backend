package com.app.recipe_generator.repository;

import com.app.recipe_generator.entity.SavedRecipes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SavedRecipesRepo extends JpaRepository<SavedRecipes, UUID> {
}
