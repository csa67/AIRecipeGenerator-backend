package com.app.recipe_generator.repository;

import com.app.recipe_generator.entity.Filter;
import com.app.recipe_generator.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FilterRepository extends JpaRepository<Filter, Long> {
    Optional<Filter> findByUser(User user);
}
