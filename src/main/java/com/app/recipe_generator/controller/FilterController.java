package com.app.recipe_generator.controller;

import com.app.recipe_generator.entity.Filter;
import com.app.recipe_generator.entity.User;
import com.app.recipe_generator.repository.UserRepo;
import com.app.recipe_generator.services.FilterService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/filters")
public class FilterController {

    @Autowired
    FilterService filterService;

    @Autowired
    UserRepo userRepo;

    private User getLoggedInUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @GetMapping("/me")
    public ResponseEntity<?> getMyFilter() {
        User user = getLoggedInUser();
        return filterService.getFilterForUser(user)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/me")
    public ResponseEntity<?> createOrUpdateMyFilter(@RequestBody Filter filterData) {
        User user = getLoggedInUser();
        Filter saved = filterService.createOrUpdateFilter(user, filterData);
        return ResponseEntity.ok(saved);
    }
}

