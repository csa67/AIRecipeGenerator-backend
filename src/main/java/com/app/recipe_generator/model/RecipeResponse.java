package com.app.recipe_generator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecipeResponse {
    private String msg;
    private Boolean isSaved;

    public RecipeResponse(String msg, Boolean isSaved) {
        this.msg = msg;
        this.isSaved = isSaved;
    }
}
