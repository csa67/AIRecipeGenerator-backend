package com.app.recipe_generator.repository;

import com.app.recipe_generator.entity.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeIngredientRepo extends JpaRepository<RecipeIngredient,Integer> {

}
