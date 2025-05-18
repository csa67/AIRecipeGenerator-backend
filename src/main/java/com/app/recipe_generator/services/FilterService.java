package com.app.recipe_generator.services;

import com.app.recipe_generator.entity.Filter;
import com.app.recipe_generator.entity.User;
import com.app.recipe_generator.repository.FilterRepository;
import com.app.recipe_generator.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FilterService {

    @Autowired
    FilterRepository filterRepository;

    @Autowired
    UserRepo userRepo;

    public Filter createOrUpdateFilter(User user, Filter filterData) {
        return filterRepository.findByUser(user)
                .map(existing -> {
                    existing.setAllergens(filterData.getAllergens());
                    existing.setDietType(filterData.getDietType());
                    return filterRepository.save(existing);
                }).orElseGet(() -> {
                    filterData.setUser(user);
                    return filterRepository.save(filterData);
                });
    }

    public Optional<Filter> getFilterForUser(User user) {
        return filterRepository.findByUser(user);
    }

}
