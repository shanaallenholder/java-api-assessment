package com.cbfacademy.apiassessment;

import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

/**
 * This recipe repository interface defines the methods for managing the recipes in the system
 * It provides the methods for retrieving, saving, updating, adding, searching and deleting recipes. 
 *  */ 
@Repository
public interface RecipeRepository  {

// Method to retrieve all recipes. 
List<Recipe> findAll();

// Method to retrieve a recipe by its unique identifier (id).
Optional<Recipe> findById(UUID id);

// Method to search for recipes by name
List<Recipe> searchRecipeByName(String name);

// Method to search for recipe by allergen
List<Recipe> searchRecipeByAllergen(String name);

// Method to add a new recipe.
Recipe addRecipe(Recipe recipe);

// Method to update an exisitng recipe by its unique identifier (id).
Recipe updateRecipe(UUID id, Recipe recipe);

// Method to delete a recipe by its unique identifier (id).
void deleteRecipe(UUID id);





}