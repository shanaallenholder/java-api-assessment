package com.cbfacademy.apiassessment;

import com.cbfacademy.apiassessment.entities.Recipe;
import com.cbfacademy.apiassessment.repositories.RecipeRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

@Repository

// This class implements the Recipe Repository interface and provides the
// implementations of the methods that allow us to interact with recipes stored
// in the JSON file.
public class JsonRepository implements RecipeRepository {


// Object mapper variable will now allow us to read and write to JSON.
// jsonfile variable created to store the recipes in json format.
private final ObjectMapper objectMapper = new ObjectMapper();
private final File jsonFile = new File("recipes.json");

    // Implements the method which will retrieve all recipes from the json file.
@Override
public List<Recipe> findAll() {
        return loadRecipesFromJson();
    }

 // Implements the method which will get a recipe from the json file by its id.
// Will iterate through the file to find the recipe with the specified id.
// Will return the recipe if found or if not will throw a IOException
 @Override
public Optional <Recipe> findById(UUID id) {
  return loadRecipesFromJson().stream()
         .filter(recipe -> recipe.getId().equals(id))
         .findFirst();
   }
    

// Search for recipes by their name
@Override
public List<Recipe> searchRecipeByName(String name) {
List<Recipe> recipes = loadRecipesFromJson(); // Load recipes from JSON file
 List<Recipe> matchingRecipes = new ArrayList<>(); // List to store matching recipes
// Iterate through recipes to find those with matching names
    for (Recipe recipe : recipes) {
        if (recipe.getName().equalsIgnoreCase(name)) {
           matchingRecipes.add(recipe); // Add matching recipe to list
    }
         }
            return matchingRecipes; // Return list of matching recipes
        }  { (IOException e) {
            // Handle IO exception
            e.printStackTrace();
            return new ArrayList<>(); // Return empty list if an error occurs
        
    }

    @Override
    public List<Recipe> searchRecipeByAllergen(String name)  {
        List<Recipe> recipes = loadRecipesFromJson();
        List<Recipe> filteredRecipes = new ArrayList<>();

        for (Recipe recipe : recipes) {
            if (recipeContainsAllergen(recipe, allergen)) {
                filteredRecipes.add(recipe);
            }
           
        }
          return filteredRecipes;
     
    }

    private boolean recipeContainsAllergen(Recipe recipe, String allergen) {
        if (allergen.equalsIgnoreCase("gluten") && recipe.isGlutenFree()) {
            return true; // Recipe is gluten-free
        } else if (allergen.equalsIgnoreCase("nut") && recipe.isNutFree()) {
            return true; // Recipe is nut-free
        } else if (allergen.equalsIgnoreCase("Vegan") && !recipe.isVegan()) {
            return true; // Recipe is not vegan
        }

        return false; //if no matches are found
    }

    @Override
    public Recipe addRecipe(Recipe recipe)  {
        List<Recipe> recipes = loadRecipesFromJson();
        recipes.add(recipe);
        saveAllRecipes(recipes);
        return recipe;
    }

    @Override
    public Recipe updateRecipe(UUID id, Recipe recipe) {
        List<Recipe> recipes = loadRecipesFromJson();

        for (int i = 0; i < recipes.size(); i++) {
           Recipe recipe = recipes.get(i);
           if (recipe.getId().equals(id)) {
            recipes.set(i, updatedRecipe);
            saveAllRecipes(recipes);
            return updatedRecipe;
           }
        }   
           
        return null;
         
    }


    @Override
    public void deleteRecipe(UUID id)  {
        List<Recipe> recipes = loadRecipesFromJson();
        recipes.removeIf(recipe -> recipe.getId().equals(id));
        saveAllRecipes(recipes);
    }

    private List<Recipe> loadRecipesFromJson() {
        try {
            if (!jsonFile.exists()) {
                jsonFile.createNewFile();
                return new ArrayList<>();
            }
            return objectMapper.readValue(jsonFile, new TypeReference<List<Recipe>>(){});
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void saveAllRecipes(List<Recipe> recipes)  {
        try {
            objectMapper.writeValue(jsonFile, recipes);
        } catch (IOException e) {
            e.printStackTrace();
        }       
    }
        }
        @Override
        public List<Recipe> searchRecipeByAllergen(String name) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'searchRecipeByAllergen'");
        }

        @Override
        public Recipe addRecipe(Recipe recipe) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'addRecipe'");
        }

        @Override
        public Recipe updateRecipe(UUID id, Recipe recipe) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'updateRecipe'");
        }

        @Override
        public void deleteRecipe(UUID id) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'deleteRecipe'");
        }
}
        


