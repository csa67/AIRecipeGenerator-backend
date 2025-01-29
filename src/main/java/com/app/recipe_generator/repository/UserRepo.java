package com.app.recipe_generator.repository;

import com.app.recipe_generator.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepo  extends JpaRepository<User, UUID> {

    public Optional<User> findByUsername(String username);
}
