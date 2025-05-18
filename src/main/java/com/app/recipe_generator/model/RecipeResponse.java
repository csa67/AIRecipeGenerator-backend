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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Boolean getSaved() {
        return isSaved;
    }

    public void setSaved(Boolean saved) {
        isSaved = saved;
    }

    @Override
    public String toString() {
        return this.msg + this.isSaved;
    }
}
