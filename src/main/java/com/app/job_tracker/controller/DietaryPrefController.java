package com.app.job_tracker.controller;

import com.app.job_tracker.entity.DietaryPreferences;
import com.app.job_tracker.entity.User;
import com.app.job_tracker.services.DietaryPreferencesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/dietary-preferences")
public class DietaryPrefController {

    @Autowired
    private DietaryPreferencesService dietaryPreferencesService;

    @PostMapping
    public ResponseEntity<?> addDietaryPreferences(
            @RequestBody DietaryPreferences preferences
            ){
       try{
           DietaryPreferences savedPreferences = dietaryPreferencesService.addDietaryPreferences(preferences);
           return ResponseEntity.ok(savedPreferences);
       } catch(IllegalArgumentException e){
           return ResponseEntity.badRequest().body(e.getMessage());
       }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<DietaryPreferences> getDietaryPreferences(@PathVariable UUID userId) {
        Optional<DietaryPreferences> preferences = dietaryPreferencesService.getDietaryPreferencesByUserId(userId);
        return preferences.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDietaryPreferences(@PathVariable Integer id, @RequestBody DietaryPreferences preferences) {
        try {
            preferences.setId(id);
            DietaryPreferences updatedPreferences = dietaryPreferencesService.updateDietaryPreferences(preferences);
            return ResponseEntity.ok(updatedPreferences);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteDietaryPreferences(@PathVariable UUID userId) {
        dietaryPreferencesService.deleteDietaryPreferences(userId);
        return ResponseEntity.noContent().build();
    }
}
