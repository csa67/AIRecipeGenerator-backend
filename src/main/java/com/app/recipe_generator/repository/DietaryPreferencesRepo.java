package com.app.recipe_generator.repository;

import com.app.recipe_generator.entity.DietaryPreferences;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DietaryPreferencesRepo extends
        JpaRepository<DietaryPreferences, Integer> {

    Optional<DietaryPreferences> findByUserId(UUID userId);

    void deleteByUserId(UUID userId);
}
