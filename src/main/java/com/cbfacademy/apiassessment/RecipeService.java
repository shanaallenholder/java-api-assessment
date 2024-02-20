package com.cbfacademy.apiassessment;


import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.cbfacademy.apiassessment.entities.Recipe;

// The Recipe Service class holds the logic for the 
@Service
public interface RecipeService {

    
// Retrieves all recipes 
public List<Recipe> findAllRecipes() throws IOException{
 // METHOD NEEDS TO BE IMPLEMENTED
        return null;
    }
    // Retrieves a recipe by its ID
    public Recipe getRecipeById(UUID id) throws IOException {
        List<Recipe> recipes = loadRecipesFromJson();
        return recipes.stream()
               .filter(recipe -> recipe.getId().equals(id))
               .findFirst()
               .orElseThrow(()-> new NoSuchElementException("Recipe not found with id: " + id));
        
    }
   // Searches for a recipe by its name
public List <Recipe> searchRecipeByName(String name) throws IOException;


   //Search for recipes by their specific allergens 
public List<Recipe> searchRecipesByAllergen(Boolean isGlutenFree, Boolean isNutFree, Boolean isVegan) throws IOException;
      List<Recipe> filteredRecipes = new ArrayList<>();
      List<Recipe> allRecipes = recipeRpository.findAll();

      for (Recipe recipe : allRecipes) {
        if ((isGlutenFree == null || recipe.isGlutenFree() == isGlutenFree) &&
        (isNutFree == null || recipe.isNutFree() == isNutFree) &&
        (isVegan == null || recipe.isVegan() == isVegan)) {
          filteredRecipes.add(recipe);
        }
        
      }
        

    // adds a new recipe to JSON file
public Recipe addRecipe(Recipe recipe) throws IOException {
     List<Recipe> recipes = loadRecipesFromJson();
     recipes.add(recipe);

      // Write the updated list back to the JSON file (not implemented here)
    // You'll need to serialize the list of recipes and write it to the file

     return recipe
    }
   // updates an exisiting recipe 
 public Recipe updateRecipe(UUID id, Recipe recipe) throws IOException {
            if (recipe.getName().equals(name)) {

                recipe.setName(updateRecipe.getName());
                recipe.setVegan(updateRecipe.isVegan());
                recipe.setGlutenFree(updateRecipe.isGlutenFree());
                recipe.setNutFree(updatedecipe.isNutFree());

                return recipe;
            }
           throw new NoSuchElementException("Recipe does not exist:" + id)
            }


 public void deleteRecipe(UUID id) throws IOException {
      List<Recipe> recipes = loadRecipesFromJson();

      int indexToDelete = -1;
      for (int i = 0; i < recipes.size(); i++) {
        if (recipes.get(i).getName().equals(name)) {
            indexToDelete = i;
            break;
        }
      }
  private void writeRecipesToJson(List<Recipe> recipes) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(recipes);
        FileWriter writer = new FileWriter("data.json");
        writer.write(json);
        writer.close();
    }
      if (indexToDelete != -1) {
        recipes.remove(indexToDelete);
        writeRecipeToJson(recipes); 
      } else {
        //if the recipe at this ID is not found, throw an exception
        throw new NoSuchElementException("Recipe Not Found with name: " + name);
      }

    }
}