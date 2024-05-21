package com.estu.recipeapp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Recipe {
    private String name;
    private List<String> ingredients;
    private String instructions;
    private List<Category> categories;
    private List<String> tags;
    private double averageRating;
    private int ratingCount;
    private int totalRatingSum;
    private int servingSize;
    private List<Integer> ratings = new ArrayList<>();

    public Recipe(String name, List<String> ingredients, String instructions, int servingSize) {
        this.name = name;
        this.ingredients = new ArrayList<>(ingredients);
        this.instructions = instructions;
        this.servingSize = servingSize; // Correct assignment to the field
        this.categories = new ArrayList<>();
        this.tags = new ArrayList<>();
        this.averageRating = 0.0;
        this.ratingCount = 0;
        this.totalRatingSum = 0;
    }
    
    

    public void addCategory(Category category) {
        if (!categories.contains(category)) {
            categories.add(category);
        }
    }

    public void addTag(String tag) {
        tags.add(tag);
    }

    public void addRating(int rating) {
        if (rating >= 1 && rating <= 5) {
            ratings.add(rating); // Store each rating
            this.ratingCount++;
            this.totalRatingSum += rating;
            this.averageRating = (double) this.totalRatingSum / this.ratingCount;
        } else {
            System.out.println("Invalid rating. Please enter a value between 1 and 5.");
        }
    }

    public void updateRating(double rating) {
        averageRating = (averageRating * ratingCount + rating) / (++ratingCount);
    }

    public String getName() {
        return name;
    }

    public List<String> getIngredients() {
        return new ArrayList<>(ingredients);
    }

    public String getInstructions() {
        return instructions;
    }

    public List<Category> getCategories() {
        return new ArrayList<>(categories);
    }

    public List<String> getTags() {
        return new ArrayList<>(tags);
    }

    public int getServingSize() {
        return servingSize;
    }


    @Override
    public String toString() {
        return "Recipe{" +
               "name='" + name + '\'' +
               ", ingredients=" + ingredients +
               ", instructions='" + instructions + '\'' +
               ", servingSize='" + servingSize + " servings" + '\'' +
               ", categories=" + categories.stream().map(Category::getName).collect(Collectors.joining(", ")) +
               ", tags=" + String.join(", ", tags) +
               ", averageRating=" + averageRating +
               '}';
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }


    public int getTotalRating() {
        return ratings.stream().mapToInt(Integer::intValue).sum();
    }

    public int getNumberOfRatings() {
        return ratings.size();
    }

    public int getTotalRatingSum() {
        return totalRatingSum;
    }
    public double getAverageRating() {
        return averageRating;
    }

    public int getRatingCount() {
        return ratingCount;
    }



    public void showRecipeDetails() {
        System.out.println("Recipe Name: " + this.getName());
        System.out.println("Ingredients: " + String.join(", ", this.ingredients));
        System.out.println("Instructions: " + this.instructions);
        System.out.println("Categories: " + this.getCategories().stream().map(Category::getName).collect(Collectors.joining(", ")));
        System.out.println("Tags: " + String.join(", ", this.tags));
        System.out.println("Total Ratings Sum: " + getTotalRatingSum());
        System.out.println("Average Rating: " + getAverageRating());
    }
    
   
}
