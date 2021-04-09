package model;

import exceptions.InvalidRating;

import java.io.Serializable;
import java.util.List;

/*
This class represents a recipe. There is the name for the recipe (nm), A list of ingredients needed
for the recipe (ingredients), a list of steps taken to make the recipe (steps), a list of categories that
the recipe has been tagged with (Categories), the estimated time that will be taken to make the recipe
(timeInMin), and a rating of the recipe (rating).
2 other fields are isPossibleRecipe and isAlmostPossibleRecipe which are boolean values used to establish
if a given recipe can be made or if a recipe can almost be made in relation to the contents found in a
Virtual Kitchen.
 */
public class Recipe implements Serializable {
    String nm;
    List<Ingredient> ingredients;
    List<String> steps;
    List<String> categories;
    int timeInMin;
    int rating;
    boolean isPossibleRecipe;
    boolean isAlmostPossibleRecipe;


    // delete or rename this class!

    //constructor
    //EFFECTS: creates a new Recipe with a name, list of ingredients, and list of steps, and estimated time of cooking.
    //The rating is set to -1 which isPossibleRecipe and isAlmostPossibleRecipe are set to false.
    public Recipe(String nm, List<Ingredient> ing, List<String> steps, List<String> categories, int time, int rating)
            throws InvalidRating {
        this.nm = nm;
        this.ingredients = ing;
        this.steps = steps;
        this.categories = categories;
        this.timeInMin = time;
        rateRecipe(rating);
        isPossibleRecipe = false;
        isAlmostPossibleRecipe = false;
    }

    //MODIFIES: this
    //EFFECTS: Change/Set the rating of the Recipe from between 0 and 5 stars
    //If num not in this range, throw InvalidRating exception
    public void rateRecipe(int rating) throws InvalidRating {
        if (rating < 0 || rating > 5) {
            throw new InvalidRating();
        } else {
            this.rating = rating;
        }
    }



    //getter
    public String getName() {
        return this.nm;
    }

    //getter
    public List<Ingredient> getIngredients() {
        return this.ingredients;
    }

    //getter
    public List<String> getSteps() {
        return this.steps;
    }

   //getter
    public List<String> getCategories() {
        return this.categories;
    }

    //getter
    public int getTime() {
        return this.timeInMin;
    }

    //getter
    public int getRating() {
        return this.rating;
    }

    //getter
    public boolean getIsPossibleRecipe() {
        return isPossibleRecipe;
    }

    //getter
    public boolean getIsAlmostPossibleRecipe() {
        return  isAlmostPossibleRecipe;
    }

    //setter
    public void setIsPossibleRecipe(boolean state) {
        isPossibleRecipe = state;
    }

    //setter
    public void setIsAlmostPossibleRecipe(boolean state) {
        isAlmostPossibleRecipe = state;
    }



}
