package ui;

import exceptions.InvalidRating;
import model.Ingredient;
import model.Recipe;
import model.RecipeCollection;
import model.VirtualKitchen;
import ui.exceptions.NeedNonEmptyRecipeCollectionException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
Class which handles all console-related Recipe Collection matters
 */

public class RecipeCollectionUI implements Serializable {

    private Scanner input = new Scanner(System.in);
    private RecipeCollection recipeCollection;
    private List<String> categories;
    private KitchenManagerApp kitchenManagerApp;
    private VirtualKitchenUI virtualKitchenUI;


    //RecipeCollection recipeCollection = new RecipeCollection();

    public RecipeCollectionUI(KitchenManagerApp kitchenManagerApp) {
        this.kitchenManagerApp = kitchenManagerApp;
        recipeCollection = new RecipeCollection();
        categories = new ArrayList<>();
        categories.add("Volumetrics Diet");
        categories.add("Keto");
        categories.add("Vegan");
        categories.add("Vegetarian");
        categories.add("Flexitarian");
        categories.add("Probiotic-rich");
        virtualKitchenUI = new VirtualKitchenUI();
    }

    public RecipeCollection getRecipeCollection() {
        return recipeCollection;
    }

    public void displayRecipeCollectionMenu() {
        System.out.println("\n" + "What would you like to do?");
        System.out.println("1. Add a brand new recipe");
        System.out.println("2. View a recipe in detail");
        System.out.println("3. Rate one of your recipes");
        System.out.println("4. Filter your recipes by category");
        System.out.println("5. Check what recipes you can make with the food in your kitchen");
        System.out.println("\n" + "Input the number corresponding to your desired action or input 'quit' "
                + "to leave this menu");
    }


    //MODIFIES: this
    //EFFECTS: Display Recipe Collection and offers user new options
    //user can select specific recipes to view, change their rating,
    public void goToRecipeCollection(RecipeCollection rc, String greeting) {
        boolean keepGoingRC = true;
        String command;

        System.out.println(greeting);
        while (keepGoingRC) {
            System.out.println("\n" + "List of Recipes:");
            viewRecipeCollection(rc);

            displayRecipeCollectionMenu();

            command = KitchenManagerApp.prepCommand(input.nextLine());

            if (command.equals("quit")) {
                keepGoingRC = false;
            } else {
                try {
                    processRecipeCollectionCommands(command);
                } catch (NeedNonEmptyRecipeCollectionException e) {
                    System.out.println("Please create a recipe before accessing this command" + "\n");
                }
            }


        }

    }

    //MODIFIES: this
    //EFFECTS: Processes the input given by the user and perform the corresponding action
    //Checks all inputs except "1" to see if action is valid
    private void processRecipeCollectionCommands(String command)
            throws NeedNonEmptyRecipeCollectionException {
        if (command.equals("1")) {
            createRecipe();
        } else if (command.equals("2")) {
            checkValidityOfRecipeCollectionAction();
            viewRecipeInDetail();
        } else if (command.equals("3")) {
            checkValidityOfRecipeCollectionAction();
            rateRecipe();
        } else if (command.equals("4")) {
            checkValidityOfRecipeCollectionAction();
            viewFilteredRecipes();
        } else if (command.equals("5")) {
            checkValidityOfRecipeCollectionAction();
            viewPossibleRecipes();
        } else {
            System.out.println("Invalid input");
        }
    }

    //EFFECTS: Checks if Recipe Collection has any recipes and throws an exception if it is empty
    private void checkValidityOfRecipeCollectionAction() throws NeedNonEmptyRecipeCollectionException {
        if (recipeCollection.getRecipeCollectionSize() == 0) {
            throw new NeedNonEmptyRecipeCollectionException();
        }
    }

    //MODIFIES: this
    //EFFECTS: Create a new recipe based off of user inputs. Recipe is added to recipeCollection
    private void createRecipe() {
       // javax.swing.SwingUtilities.invokeLater(
       // public void run() {
        new RecipeCreationGUI(this);
        //};
    }

    //EFFECTS: displays potential categories for user to add to their Recipe
    private void printExistingCategories(List<String> assignedCategories) {
        int i = 1;
        for (String s : categories) {
            if (!assignedCategories.contains(s)) {
                System.out.println(i + ". " + s);
                i++;
            }
        }
    }

    //EFFECTS: prints out an introduction shown before adding items to a List.
    //The first time an item is added, one message is printed while every other time, a different message
    //is printed.
    private int printIntroduction(int iterations, String s2, String s3) {
        if (iterations == 1) {
            System.out.println(s2);
        } else {
            System.out.println(s3);
        }
        iterations++;
        return iterations;
    }

    //EFFECTS: Checks if the input given by user corresponds to a given existing category returning true or false
    private boolean refersToExistingCategory(String command) {
        for (String category : categories) {
            if (command.equals(category.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    //EFFECTS: view specific recipe from Recipe Collection
    private void viewRecipeInDetail() {
        System.out.println("\n" + "Which recipe would you like to view?");
        viewRecipeCollection(recipeCollection);
        System.out.println("\n" + "Input the name of the desired recipe or input 'quit' "
                + "to move back to your recipe collection");

        String command = KitchenManagerApp.prepCommand(input.nextLine());
        for (Recipe recipe : recipeCollection) {
            if (command.equals(recipe.getName().toLowerCase().trim())) {
                boolean viewRecipe = true;
                while (viewRecipe) {
                    viewRecipe(recipe);

                    System.out.println("Input 'leave' to move back to Recipe Collection menu");
                    String leave = input.nextLine().trim().toLowerCase();
                    if (leave.equals("leave")) {
                        viewRecipe = false;
                    }
                }
            }
        }
        System.out.println("Moving Back to Recipe Collection Menu");
//        if (command.equals("quit")) {
//            System.out.println("Incorrect input. Moving back to Recipe Collection Menu");
//        }
    }

    //EFFECTS: Displays the entire Recipe Collection by Recipe title and estimated time to cook
    //The displayed recipes will be numbered by their index + 1
    public void viewRecipeCollection(RecipeCollection rc) {
        if (rc.getRecipeCollectionSize() == 0) {
            System.out.println("You currently have no recipes in this menu. Why not make one right now?");
        } else {
            int i = 1;
            for (Recipe recipe: rc) {
                System.out.println(i + ". " + recipe.getName()
                        + "          Time to Cook: " + recipe.getTime() + " minutes");
                i++;
            }

        }
    }

    //EFFECTS: view the Recipe's name, estimated time to cook, rating, ingredients and steps
    public void viewRecipe(Recipe r) {
        String currentRating;
        currentRating = Integer.toString(r.getRating());
        System.out.println("\n" + r.getName() + "               Estimated Cooking Time: " + r.getTime() + " min        "
                + "          Rating: " + currentRating + "\n");
        System.out.println("Categories: ");
        if (r.getCategories() == null) {
            System.out.println("    No Assigned Categories");
        } else {
            for (String category : r.getCategories()) {
                System.out.println("    -" + category);
            }
        }
        System.out.println("\nIngredients:");
        for (Ingredient ing: r.getIngredients()) {
            System.out.println(ing.getItem() + ": " + ing.getQuantity());
        }
        System.out.println("\n" + "Steps:");

        for (int i = 1; i <= r.getSteps().size(); i++) {
            System.out.println(i + ". " + r.getSteps().get(i - 1));
        }
    }


    //MODIFIES: this
    //EFFECTS: rate a recipe. If recipe name is not found or rating is invalid, returns to Recipe Collection Menu
    private void rateRecipe() {
        System.out.println("Which recipe would you like to rate? Input the recipe name");
        viewRecipeCollection(recipeCollection);

        String command = KitchenManagerApp.prepCommand(input.nextLine());
        Recipe correctRecipe = null;

        for (Recipe recipe : recipeCollection) {
            if (command.equals(recipe.getName().toLowerCase())) {
                correctRecipe = recipe;
            }
        }
        if (correctRecipe != null) {
            System.out.println("What rating would you like to give this recipe out of 5?");
            String rating = input.nextLine();
            int ratingNum = Integer.parseInt(rating);

            try {
                correctRecipe.rateRecipe(ratingNum);
                System.out.println("Recipe has been rated!");
            } catch (InvalidRating invalidRating) {
                System.out.println("Invalid Rating. Returning to Recipe Collection Menu");
            }

        } else {
            System.out.println("No Recipe of that name exists. Please check spelling and capitalization!");
        }
    }

    //MODIFIES:
    //EFFECTS: view Recipes collection, but filtered by a category
    private void viewFilteredRecipes() {
        System.out.println("What category would you like to filter by?");
        printExistingCategories(categories);
        RecipeCollection filteredRecipeCollection = new RecipeCollection();
        List<String> lowerCaseCategories = new ArrayList<>();

        String command = KitchenManagerApp.prepCommand(input.nextLine());

        for (String category: categories) {
            lowerCaseCategories.add(category.toLowerCase().trim());
        }
        if (lowerCaseCategories.contains(command)) {
            int index = lowerCaseCategories.indexOf(command);
            String filteredCategory = categories.get(index);

            for (Recipe recipe : recipeCollection) {
                if (recipe.getCategories().contains(filteredCategory)) {
                    filteredRecipeCollection.getRecipeCollection().add(recipe);
                }
            }

            String greeting = "Recipe Collection filtered by " + filteredCategory + ":";
            goToRecipeCollection(filteredRecipeCollection, greeting);

        } else {
            System.out.println("Invalid category. Returning to Recipe Collection Menu");
        }

    }

    //MODIFIES: this
    //EFFECTS: Show what recipes can be made with current Kitchen items (change state of isPossibleRecipe) for
    // specific recipes) and what items can almost be made (missing one or two
    // ingredients of) (changes state of isALmostPossibleRecipe for these recipes).
    private void viewPossibleRecipes() {
        recipeCollection.checkPossibleRecipes(virtualKitchenUI.getVirtualKitchen());

        System.out.println("\n" + "Recipes you have all the ingredients for:");
        int i = 1;
        for (Recipe recipe: recipeCollection) {
            if (recipe.getIsPossibleRecipe()) {
                System.out.println(i + ". " + recipe.getName());
                i++;
            }
        }

        i = 1;
        System.out.println("\n" + "Recipes that you can make if you substitute a couple of ingredients:");
        for (Recipe recipe: recipeCollection) {
            if (recipe.getIsAlmostPossibleRecipe()) {
                System.out.println(i + ". " + recipe.getName());
                i++;
            }
        }

        System.out.println("\n" + "If you would like to make any of these recipes, choose them from the recipe list"
                + " as you are returned to the Recipe Collection Menu");

        resetPossibleRecipeStates();
    }

    //MODIFIES: this
    //EFFECTS: Resets isAlmostPossibleRecipe and isPossbleRecipe states so that the method can be called again
    // without error
    private void resetPossibleRecipeStates() {
        for (Recipe recipe: recipeCollection) {
            recipe.setIsPossibleRecipe(false);
            recipe.setIsAlmostPossibleRecipe(false);
        }
    }

    public VirtualKitchenUI getVirtualKitchenUI() {
        return virtualKitchenUI;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public void setRecipeCollection(RecipeCollection recipeCollection) {
        this.recipeCollection = recipeCollection;
    }

    public KitchenManagerApp getKitchenManagerApp() {
        return kitchenManagerApp;
    }
}
