package com.app.job_tracker.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class DietaryPreferences {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name="user_id",nullable = false,unique = true)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DietaryType type;

    private Integer calorieLimit;

    @OneToMany(mappedBy = "dietaryPreferences", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DietaryAllergen> allergens;
}
