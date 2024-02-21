package com.cbfacademy.apiassessment;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.UUID;


@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Override
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll().stream().collect(Collectors.toList());
    }

    @Override
    public Recipe getRecipeById(UUID id) throws IOException {
        List<Recipe> recipes = loadRecipesFromJson();
        return recipes.stream()
               .filter(recipe -> recipe.getid().equals(id))
               .findFirst()
               .orElseThrow(() -> new NoSuchElementException("Recipe does not exist:" + id));
    }

    @Override
    public List<Recipe> searchRecipeByName(String name) throws IOException {
       List<Recipe> allRecipes = recipeRepository.findAll();

       List<Recipe> matchingRecipes = allRecipes.stream()
              .filter(recipe -> recipe.getName().equalsIgnoreCase(name))
              .collect(Collectors.toList());

              return matchingRecipes;
    }

    @Override
    public List<Recipe> searchRecipesByAllergen(Boolean isGlutenFree, Boolean isNutFree, Boolean isVegan) {
        List<Recipe> recipes = loadRecipesFromJson();
        List<Recipe> filteredRecipes = new ArrayList<>();
    
        for (Recipe recipe : recipes) {
            if ((isGlutenFree == null || recipe.isGlutenFree() == isGlutenFree) &&
                (isNutFree == null || recipe.isNutFree() == isNutFree) &&
                (isVegan == null || recipe.isVegan() == isVegan)) {
                filteredRecipes.add(recipe);
            }
        }
        return filteredRecipes;
    }

    @Override
    public Recipe addRecipe(Recipe recipe) {
        Recipe existingRecipe = recipeRepository.findById(id).orElse(null);
        if (existingRecipe != null) {
            recipe.setId(id); // ensure the id of the updated recipe matches the provided id
            return recipeRepository.save(recipe);
        }
        return null; // or throw an exception indicating recipe with the provided ID was not found
    }
    @Override
    public Recipe updateRecipe(UUID id, Recipe recipe) throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateRecipe'");
    }



    @Override
    public void deleteRecipe(UUID id) {
        recipeRepository.deleteById(id);

    }

  
    

    
  
}
