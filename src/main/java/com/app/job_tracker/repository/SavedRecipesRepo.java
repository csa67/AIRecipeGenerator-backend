package com.app.job_tracker.repository;

import com.app.job_tracker.entity.SavedRecipes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SavedRecipesRepo extends JpaRepository<SavedRecipes, UUID> {
}
