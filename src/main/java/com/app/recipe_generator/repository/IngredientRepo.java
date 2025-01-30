package com.app.recipe_generator.repository;

import com.app.recipe_generator.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IngredientRepo extends JpaRepository<Ingredient,Integer> {
    Optional<Ingredient> findByNameIgnoreCase(String name);
}
