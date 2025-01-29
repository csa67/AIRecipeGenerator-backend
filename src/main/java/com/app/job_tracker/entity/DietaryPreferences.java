package com.app.job_tracker.entity;

import com.app.job_tracker.model.DietaryType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name="dietary_preferences")
@Data
public class DietaryPreferences {

    public Integer getId() {
        return id;
    }


    public User getUser() {
        return user;
    }

    public DietaryType getType() {
        return type;
    }

    public Integer getCalorieLimit() {
        return calorieLimit;
    }

    public List<DietaryAllergen> getAllergens() {
        return allergens;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
