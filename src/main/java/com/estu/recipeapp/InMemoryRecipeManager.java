package com.estu.recipeapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryRecipeManager implements RecipeManager {
    private Map<String, Recipe> recipesByName = new HashMap<>();

    @Override
    public void createRecipe(Recipe recipe) {
        recipesByName.put(recipe.getName(), recipe);
    }

    @Override
    public boolean modifyRecipe(String recipeName, Recipe newRecipe) {
        Recipe existingRecipe = recipesByName.get(recipeName);
        if (existingRecipe != null) {
            newRecipe.setCategories(existingRecipe.getCategories());
            newRecipe.setTags(existingRecipe.getTags());
            recipesByName.put(recipeName, newRecipe);
            System.out.println("Recipe " + recipeName + " modified successfully.");
            System.out.println("*************************************");
            return true;
        } else {
            System.out.println("Recipe with name " + recipeName + " does not exist.");
            return false;
        }
    }

    @Override
    public Recipe searchRecipeByName(String name) {
        return recipesByName.get(name);
    }

    @Override
    public List<Recipe> searchRecipesByCategory(String category) {
        List<Recipe> foundRecipes = new ArrayList<>();
        for (Recipe recipe : recipesByName.values()) {
            for (Category cat : recipe.getCategories()) {
                if (cat.getName().equalsIgnoreCase(category)) {
                    foundRecipes.add(recipe);
                    break;
                }
            }
        }
        return foundRecipes;
    }

    @Override
    public List<Recipe> searchRecipesByTag(String tag) {
        List<Recipe> foundRecipes = new ArrayList<>();
        for (Recipe recipe : recipesByName.values()) {
            if (recipe.getTags().contains(tag)) {
                foundRecipes.add(recipe);
            }
        }
        return foundRecipes;
    }

    @Override
    public void rateRecipe(String recipeName, double rating) {
        Recipe recipe = recipesByName.get(recipeName);
        if (recipe != null) {
            recipe.updateRating(rating);
            System.out.println("Recipe " + recipeName + " rated successfully.");
        } else {
            System.out.println("Recipe with name " + recipeName + " does not exist. Please rate an existing recipe.");
        }
    }
}
