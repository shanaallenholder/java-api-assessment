package com.cbfacademy.apiassessment;

import java.util.UUID;


public class RecipeNotFoundException extends RuntimeException {


    private final UUID recipeId;

    public RecipeNotFoundException(UUID recipeId) {

        super("Recipe not found with ID: " + recipeId);
        this.recipeId = recipeId;
    }

    public UUID getRecipeById() {
        return recipeId;
    }
}

    

