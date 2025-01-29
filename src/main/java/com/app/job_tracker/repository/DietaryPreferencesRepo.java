package com.app.job_tracker.repository;

import com.app.job_tracker.entity.DietaryPreferences;
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
