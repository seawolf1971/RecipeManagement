package com.estu.recipeapp;

import java.util.List;

public interface RecipeManager {
    void createRecipe(Recipe recipe);
    boolean modifyRecipe(String recipeName, Recipe newRecipe);
    Recipe searchRecipeByName(String name);
    List<Recipe> searchRecipesByCategory(String category);
    List<Recipe> searchRecipesByTag(String tag);
    void rateRecipe(String recipeName, double rating);
    
}
