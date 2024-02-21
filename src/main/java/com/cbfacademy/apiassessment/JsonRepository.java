package com.cbfacademy.apiassessment;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository

// This class implements the Recipe Repository interface and provides the
// implementations of the methods that allow us to interact with recipes stored
// in the JSON file.
public class JsonRepository implements RecipeRepository {

    // Object mapper variable will now allow us to read and write to JSON.
    // jsonfile variable created to store the recipes in json format.
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final File jsonFile = new File("recipes.json");

    private List<Recipe> loadRecipesFromJson() {
        try {
            if (!jsonFile.exists()) {
                jsonFile.createNewFile();
                return new ArrayList<>();
            }
            return objectMapper.readValue(jsonFile, new TypeReference<List<Recipe>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // Implements the method which will retrieve all recipes from the json file.
    @Override
    public List<Recipe> findAll() {
        return loadRecipesFromJson();
    }

    @Override
    public Optional<Recipe> findById(UUID id) {
        return loadRecipesFromJson().stream()
                .filter(recipe -> recipe.getId().equals(id))
                .findFirst();

        // // Implements the method which will get a recipe from the json file by its
        // id.
        // // Will iterate through the file to find the recipe with the specified id.
        // // Will return the recipe if found or if not will throw a IOException
        // @Override
        // public Optional <Recipe> findById(UUID id) {
        // return loadRecipesFromJson().stream()
        // .filter(recipe -> recipe.getId().equals(id))
        // .findFirst();
        // }
    }

    @Override
    public List<Recipe> searchRecipeByName(String name) {
        List<Recipe> recipes = loadRecipesFromJson();
        List<Recipe> matchingRecipes = new ArrayList<>();
        for (Recipe recipe : recipes) {
            if (recipe.getName().equalsIgnoreCase(name)) {
                matchingRecipes.add(recipe);
            }
        }
        return matchingRecipes;

        // // Search for recipes by their name
        // @Override
        // public List<Recipe> searchRecipeByName(String name) {
        // List<Recipe> recipes = loadRecipesFromJson(); // Load recipes from JSON file
        // List<Recipe> matchingRecipes = new ArrayList<>(); // List to store matching
        // recipes
        // // Iterate through recipes to find those with matching names
        // for (Recipe recipe : recipes) {
        // if (recipe.getName().equalsIgnoreCase(name)) {
        // matchingRecipes.add(recipe); // Add matching recipe to list
        // }
        // }
        // return matchingRecipes; // Return list of matching recipes
        // } { (IOException e) {
        // // Handle IO exception
        // e.printStackTrace();
        // return new ArrayList<>(); // Return empty list if an error occurs

        // }
    }

    @Override
    public List<Recipe> searchRecipeByAllergen(Boolean isGlutenFree, Boolean isNutFree, Boolean isVegan)
            throws IOException {
        // Implement search logic based on allergen flags
        // This method will be similar to the one in your service implementation

        // @Override
        // public List<Recipe> searchRecipeByAllergen(String name) {
        // List<Recipe> recipes = loadRecipesFromJson();
        // List<Recipe> filteredRecipes = new ArrayList<>();

        // for (Recipe recipe : recipes) {
        // if (recipeContainsAllergen(recipe, allergen)) {
        // filteredRecipes.add(recipe);
        // }

        // }
        // return filteredRecipes;

        // }

        // private boolean recipeContainsAllergen(Recipe recipe, String allergen) {
        // if (allergen.equalsIgnoreCase("gluten") && recipe.isGlutenFree()) {
        // return true; // Recipe is gluten-free
        // } else if (allergen.equalsIgnoreCase("nut") && recipe.isNutFree()) {
        // return true; // Recipe is nut-free
        // } else if (allergen.equalsIgnoreCase("Vegan") && !recipe.isVegan()) {
        // return true; // Recipe is not vegan
        // }

        // return false; //if no matches are found
        // }

    }

    @Override
    public Recipe addRecipe(Recipe recipe) {
        List<Recipe> recipes = loadRecipesFromJson();
        recipes.add(recipe);
        saveAllRecipes(recipes);
        return recipe;

        // @Override
        // public Recipe addRecipe(Recipe recipe) {
        // List<Recipe> recipes = loadRecipesFromJson();
        // recipes.add(recipe);
        // saveAllRecipes(recipes);
        // return recipe;
        // }
    }

    @Override
    public Recipe updateRecipe(UUID id, Recipe updatedRecipe) {
        // Implement update logic

        // @Override
        // public Recipe updateRecipe(UUID id, Recipe recipe) {
        // List<Recipe> recipes = loadRecipesFromJson();

        // for (int i = 0; i < recipes.size(); i++) {
        // Recipe recipe = recipes.get(i);
        // if (recipe.getId().equals(id)) {
        // recipes.set(i, updatedRecipe);
        // saveAllRecipes(recipes);
        // return updatedRecipe;
        // }
        // }

        // return null;

        // }
    }

    @Override
    public void deleteRecipe(UUID id) {
        // Implement delete logic

        // @Override
        // public void deleteRecipe(UUID id) {
        // List<Recipe> recipes = loadRecipesFromJson();
        // recipes.removeIf(recipe -> recipe.getId().equals(id));
        // saveAllRecipes(recipes);
        // }
    }

    private List<Recipe> loadRecipesFromJson() throws IOException {
        if (!jsonFile.exists()) {
            jsonFile.createNewFile();
            return new ArrayList<>();
        }
        return objectMapper.readValue(jsonFile, new TypeReference<List<Recipe>>() {
        });
    }

    private void saveAllRecipes(List<Recipe> recipes) throws IOException {
        objectMapper.writeValue(jsonFile, recipes);

        // private void saveAllRecipes(List<Recipe> recipes) {
        // try {
        // objectMapper.writeValue(jsonFile, recipes);
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        // }
        // }
    }

}
