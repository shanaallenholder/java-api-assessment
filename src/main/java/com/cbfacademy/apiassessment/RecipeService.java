package com.cbfacademy.apiassessment;

import java.util.List;
import java.util.UUID;


/**
 * 
 * The Recipe Service class holds the logic for the managing recipes.
 * It defines the methods being used for retrieving, adding, updates and deleting recipes. 
 * As well as methods for searching recipes by name and their allergens. 
 *  */ 

public interface RecipeService {

// Retrieves all recipes. 
List<Recipe> getAllRecipes();

// Retrieves a recipe by its unique ID.
Recipe getRecipeById(UUID id);

// Search foer a recipe by its name.
List<Recipe> searchRecipeByName(String name);

// Search for a recipe by thier specific allergen.
List<Recipe> searchRecipesByAllergen(Boolean isGlutenFree, Boolean isNutFree, Boolean isVegan);

// Adds a new recipe.
Recipe addRecipe(Recipe recipe);

// Updates an exisiting recipe. 
 Recipe updateRecipe(UUID id, Recipe recipe);

 // Deletes a recipe.
 void deleteRecipe(UUID id); 

}