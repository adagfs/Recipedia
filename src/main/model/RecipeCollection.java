package model;

import exceptions.InvalidRating;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
RecipeCollection is the Collection of Recipes that a user has accumulated over time. There is a single field
called collection, which represents this list of Recipes.
This class is simple in the fields it has, but is packed with many methods that are described below.
 */
public class RecipeCollection implements Serializable,Iterable<Recipe> {
    List<Recipe> collection;


    public RecipeCollection() {
        collection = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds a Recipe to your collection
    public void addRecipeToCollection(Recipe recipe) {
        collection.add(recipe);

    }

    //EFFECTS: if a recipe has the same name as the recipeName string, then that recipe will be returned
    //Otherwise, null is returned instead
    public Recipe findRecipeByName(String recipeName) {
        Recipe correctRecipe = null;
        for (Recipe recipe : collection) {
            if (recipe.getName().equals(recipeName)) {
                correctRecipe = recipe;
            }
        }
        if (correctRecipe == null) {
            System.out.println("Recipe does not exist. Making sure you didn't make any typos!");
            return null;
        } else {
            return correctRecipe;
        }
    }
    //TODO: If it modifies a recipe inside of a list of recipes, is it also modifying recipe?

    //MODIFIES: this
    //EFFECTS: If number between 0 and 5, changes/sets the rating of a recipe in your collection and returns true.
    //Else, catch invalidRating and print out statement stating that it is an invalid input
    public boolean changeRecipeRating(String recipeName, int rating) {
        Recipe correctRecipe = findRecipeByName(recipeName);
        if (correctRecipe == null) {
            return false;
        } else {
            try {
                correctRecipe.rateRecipe(rating);
            } catch (InvalidRating invalidRating) {
                System.out.println("Invalid Rating. Please try again");
            }
            return true;
        }
    }

    //EFFECTS: checks what recipes can be made with the items in virtualKitchen. If all ingredients in
    // a recipe are in the kitchen, the isPossibleRecipe state is turned to true. If 1 or 2 ingredients are muissing,
    // the isAlmostPossibleRecipe state is turned to true.
    public void checkPossibleRecipes(VirtualKitchen virtualKitchen) {

        for (Recipe recipe : collection) {
            int missing = 0;
            for (Ingredient ingredient : recipe.getIngredients()) {
                boolean found = false;
                for (FoodGroup foodGroup : virtualKitchen.getFoodGroups()) {
                    if (foodGroup.getFoodList().contains(ingredient.getItem().toLowerCase())) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    missing++;
                }
            }
            if (missing == 0) {
                recipe.setIsPossibleRecipe(true);
            } else if (missing <= 2) {
                recipe.setIsAlmostPossibleRecipe(true);
            }
        }
    }

    //EFFECTS: determine number of recipes in recipe collection
    public int getRecipeCollectionSize() {
        return collection.size();
    }

    //getter
    public List<Recipe> getRecipeCollection() {
        return collection;
    }

    @Override
    public Iterator<Recipe> iterator() {
        return collection.iterator();
    }
}
