package com.app.job_tracker.entity;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Entity
public class DietaryAllergen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "dietary_id",nullable = false)
    private DietaryPreferences dietaryPreferences;

    @Column(nullable = false)
    private String allergen;
}
