package com.app.recipe_generator.repository;

import com.app.recipe_generator.entity.SavedRecipes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SavedRecipesRepo extends JpaRepository<SavedRecipes, UUID> {
    Optional<SavedRecipes> findByTitle(String title);
}

