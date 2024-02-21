package com.cbfacademy.apiassessment.controller;


import org.springframework.web.bind.annotation.*;

import com.cbfacademy.apiassessment.RecipeService;
import com.cbfacademy.apiassessment.entities.Recipe;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;
import java.io.IOException;


// Recipe Controller class to implement recipes API endpoints (CRUD/Http)
@RestController
@RequestMapping("/api/recipes")

public class RecipeController {

    private final RecipeService recipeService;
    public RecipeController(RecipeService recipeService) {
    this.recipeService = recipeService;

    }

    @GetMapping()
    public ResponseEntity<List<Recipe>> getAllRecipes() throws IOException {
        List<Recipe> recipes = recipeService.getAllRecipes();
       return new ResponseEntity<>(recipes, HttpStatus.OK);

    }

    // Retrieve a specific recipe by ID
    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipeById (@PathVariable UUID id) throws IOException {
        Recipe recipe = recipeService.getRecipeById(id);
        if (recipe != null) {
            return new ResponseEntity<>(recipe, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<Recipe>> searchRecipeByName(@RequestParam String name) throws IOException {
        List<Recipe> recipes = recipeService.searchRecipeByName(name);
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    @GetMapping("/{allergen}")
    public ResponseEntity<List<Recipe>> searchRecipeByAllergen(
          @RequestParam(required = false) Boolean isGlutenFree,
          @RequestParam(required = false) Boolean isNutFree,
          @RequestParam(required = false) Boolean isVegan) throws IOException  {
      List<Recipe> recipes = recipeService.searchRecipesByAllergen(isGlutenFree, isNutFree, isVegan);
      return new ResponseEntity<>(recipes, HttpStatus.OK);

          }
            

    @PostMapping ()
    public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe) {
        try {
            Recipe newRecipe = recipeService.addRecipe(recipe);
            return new ResponseEntity<>(newRecipe, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable UUID id, @RequestBody Recipe recipe) throws IOException {
            Recipe updateRecipe = recipeService.updateRecipe(id, recipe);
            return new ResponseEntity<>(updateRecipe, HttpStatus.OK);
      
        }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable UUID id) throws IOException {
        recipeService.deleteRecipe(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
