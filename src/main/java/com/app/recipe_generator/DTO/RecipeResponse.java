package com.app.recipe_generator.DTO;

import lombok.Data;
import java.util.UUID;

@Data
public class RecipeResponse {
    private UUID id;
    private String message;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
