package com.estu.recipeapp;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class ConsoleInterface {
    private RecipeManager manager;
    private Scanner scanner;

    public ConsoleInterface(RecipeManager manager) {
        this.manager = manager;
        this.scanner = new Scanner(System.in);
    }

    List<String> categories = Arrays.asList("Appetizers", "Main Dishes", "Desserts");
    List<String> tags = Arrays.asList("Vegetarian", "Gluten-Free", "Spicy");

    public void displayMenu() {
        System.out.println("Please select an option from the menu:");
        System.out.println("1. Create Recipe");
        System.out.println("2. Modify Recipe");
        System.out.println("3. Search Recipe");
        System.out.println("4. Rate Recipe");
        System.out.println("5. Exit");
    }

    public void handleUserInput() {
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                createRecipe();
                break;
            case 2:
                modifyRecipe();
                break;
            case 3:
                searchRecipe();
                break;
            case 4:
                rateRecipe();
                break;
            case 5:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please select again.");
        }
    }

    private void createRecipe() {
        System.out.println("**********Recipe Creation**********");
        System.out.print("Enter recipe name: ");
        String name = scanner.nextLine();
        System.out.print("Enter ingredients (comma-separated): ");
        String ingredientsInput = scanner.nextLine();
        List<String> ingredients = Arrays.asList(ingredientsInput.split(","));
        System.out.print("Enter instructions: ");
        String instructions = scanner.nextLine();
        System.out.print("Enter serving size: ");
        int servingSize = scanner.nextInt();
        scanner.nextLine();  // Consuming the leftover newline character
        
        System.out.println("Select category:");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i));
        }
        System.out.print("Enter category choice: ");
        int categoryChoice = scanner.nextInt();
        scanner.nextLine();  // Consuming the newline after number input
        String category = categories.get(categoryChoice - 1);

        System.out.println("Select tag:");
        for (int i = 0; i < tags.size(); i++) {
            System.out.println((i + 1) + ". " + tags.get(i));
        }
        System.out.print("Enter tag choice: ");
        int tagChoice = scanner.nextInt();
        scanner.nextLine();  // Consuming the newline after number input
        String tag = tags.get(tagChoice - 1);

        Recipe recipe = new Recipe(name, ingredients, instructions, servingSize);
        recipe.addCategory(new Category(category));  // Ensure Category class exists and is used
        recipe.addTag(tag);
        System.out.print("Save recipe? (y/n): ");
        String saveChoice = scanner.nextLine();
        if (saveChoice.equalsIgnoreCase("y")) {
            manager.createRecipe(recipe);
            System.out.println("Recipe " + recipe.getName() + " created successfully.");
        } else {
            System.out.println("Recipe not saved.");
        }
        System.out.println("*************************************");
    }
    

    private void modifyRecipe() {
        System.out.print("Enter recipe name to modify: ");
        String name = scanner.nextLine();
        Recipe existingRecipe = manager.searchRecipeByName(name);
        if (existingRecipe == null) {
            System.out.println("Recipe not found.");
            return;
        }
        System.out.print("Enter new ingredients (comma-separated): ");
        String newIngredientsInput = scanner.nextLine();
        List<String> newIngredients = Arrays.asList(newIngredientsInput.split(","));
        System.out.print("Enter new instructions: ");
        String newInstructions = scanner.nextLine();
        System.out.print("Enter new serving size: ");
        int newServingSize = scanner.nextInt();
        scanner.nextLine();  // Consuming the leftover newline character
    
        // Assuming you want to retain categories and tags
        Recipe newRecipe = new Recipe(name, newIngredients, newInstructions, newServingSize);
        boolean success = manager.modifyRecipe(name, newRecipe);
        if (success) {
            System.out.println("Recipe modified successfully.");
        } else {
            System.out.println("Recipe modification failed.");
        }
    }
    
    
    
    

    private void searchRecipe() {
        System.out.println("**********Recipe Searching**********");
        System.out.println("1. Search by name");
        System.out.println("2. Search by category");
        System.out.println("3. Search by tag");
        System.out.print("Enter choice: ");
        int searchChoice = scanner.nextInt();
        scanner.nextLine();
        switch (searchChoice) {
            case 1:
                System.out.print("Enter recipe name to search: ");
                String name = scanner.nextLine();
                Recipe recipeByName = manager.searchRecipeByName(name);
                if (recipeByName != null) {
                    System.out.println("Found recipe by name: " + recipeByName);
                    recipeByName.showRecipeDetails();
                    System.out.println("*************************************");
                } else {
                    System.out.println("Recipe not found by name.");
                }
                break;
            case 2:
                System.out.print("Enter category to search: ");
                String category = scanner.nextLine();
                List<Recipe> recipesByCategory = manager.searchRecipesByCategory(category);
                if (!recipesByCategory.isEmpty()) {
                    System.out.println("Found recipes by category:");
                    for (Recipe recipe : recipesByCategory) {
                        System.out.println(recipe);
                    }
                    System.out.println("*************************************");
                } else {
                    System.out.println("Recipes not found by category.");
                }
                break;
            case 3:
                System.out.print("Enter tag to search: ");
                String tag = scanner.nextLine();
                List<Recipe> recipesByTag = manager.searchRecipesByTag(tag);
                if (!recipesByTag.isEmpty()) {
                    System.out.println("Found recipes by tag:");
                    for (Recipe recipe : recipesByTag) {
                        System.out.println(recipe);
                    }
                    System.out.println("*************************************");
                } else {
                    System.out.println("Recipes not found by tag.");
                }
                break;
            default:
                System.out.println("Invalid choice. Please select again.");
        }
    }

    private void rateRecipe() {
        System.out.println("**********Recipe Rating**********");
        System.out.print("Enter recipe name to rate: ");
        String name = scanner.nextLine();
        Recipe recipeToRate = manager.searchRecipeByName(name);
        if (recipeToRate != null) {
            System.out.print("Enter rating (1-5): ");
            int rating = scanner.nextInt();  // Ensure this is an int and matches the expected rating input
            scanner.nextLine();  // Consume the newline left-over
            recipeToRate.addRating(rating);
            System.out.println("Recipe rated successfully.");
            System.out.println("*************************************");
        } else {
            System.out.println("Recipe with name " + name + " does not exist. Please rate an existing recipe.");
            System.out.println("*************************************");
        }
    }
    

    public static void main(String[] args) {
        RecipeManager manager = new InMemoryRecipeManager();
        ConsoleInterface console = new ConsoleInterface(manager);
        while (true) {
            console.displayMenu();
            console.handleUserInput();
        }
    }
}
