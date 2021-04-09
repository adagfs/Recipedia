package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RecipeCollectionTest {
    RecipeCollection rc;
    Recipe r1;
    Recipe r2;
    List<Ingredient> ingredients;
    List<Ingredient> ingredients2;
    List<String> steps;
    List<String> categories;
    int time;
    Ingredient celery;
    VirtualKitchen virtualKitchen;

    @BeforeEach
    public void setup() {
        celery = new Ingredient("celery", "1 stalk");
        ingredients = new ArrayList<>();
        ingredients.add(celery);
        ingredients.add(new Ingredient("cheese", "3"));
        ingredients.add(new Ingredient("chicken broth", "3 cups"));


        ingredients2 = new ArrayList<>();
        ingredients2.add(new Ingredient("olives", "5"));
        steps = new ArrayList<>();
        steps.add("Boil celery in pot");

        categories = new ArrayList<>();
        categories.add("Vegetarian");

        time = 15;
        try {
            r1 = new Recipe("Boiled Celery", ingredients, steps, categories, time, 1);
            r2 = new Recipe("Boiled Olives", ingredients2, steps, categories, time, 1);
        } catch (Exception e) {

        }

        rc = new RecipeCollection();

        virtualKitchen = new VirtualKitchen();



    }

    @Test
    public void testAddNewRecipe(){
        //confirm initalization is correct
        assertEquals(0, rc.getRecipeCollectionSize());

        //add a Recipe
        rc.addRecipeToCollection(r1);

        //confirm if outcome is correct
        assertEquals(1, rc.getRecipeCollectionSize());
        List<Recipe> rc2 = new ArrayList<>();
        rc2.add(r1);

        assertEquals(rc2, rc.getRecipeCollection());

        //add another recipe
        rc.addRecipeToCollection(r2);

        //confirm output again
        assertEquals(2, rc.getRecipeCollectionSize());
        rc2.add(r2);

        assertEquals(rc2, rc.getRecipeCollection());


    }
    @Test
    public void findRecipeByNameFail(){
        //confirm initalization is correct
        assertEquals(0, rc.getRecipeCollectionSize());

        //add a Recipe
        rc.addRecipeToCollection(r1);

        //confirm if outcome is correct
        assertEquals(1, rc.getRecipeCollectionSize());
        assertEquals(rc.findRecipeByName("Boiled Celery"), r1);
        assertNull(rc.findRecipeByName("cherry"));

    }

    @Test
    public void testChangeRecipeRating(){
        rc.addRecipeToCollection(r1);
        rc.changeRecipeRating("Boiled Celery", 5);

        assertEquals(5, rc.findRecipeByName("Boiled Celery").getRating());

        rc.changeRecipeRating("Boiled Celery", 3);

        assertEquals(3, rc.findRecipeByName("Boiled Celery").getRating());

    }

    @Test
    public void testChangeRecipeRatingErrorInvalidRating(){
        rc.addRecipeToCollection(r1);
        assertEquals(1, rc.findRecipeByName("Boiled Celery").getRating());
        rc.addRecipeToCollection(r1);
        rc.changeRecipeRating("Boiled Celery", 6);

        assertEquals(1, rc.findRecipeByName("Boiled Celery").getRating());

        rc.changeRecipeRating("Boiled Celery", -3);

        assertEquals(1, rc.findRecipeByName("Boiled Celery").getRating());

    }

    @Test
    public void testChangeRecipeRatingErrorInvalidRecipeName(){
        rc.addRecipeToCollection(r1);
        assertFalse(rc.changeRecipeRating("cheese", 4));

    }

    @Test
    public void testCheckPossibleRecipesWithMatchesAndNearMatches() {
        virtualKitchen.getFoodGroups().get(3).addItem("cheese");
        virtualKitchen.getFoodGroups().get(1).addItem("celery");
        virtualKitchen.getFoodGroups().get(5).addItem("chicken broth");
        rc.addRecipeToCollection(r1);
        rc.addRecipeToCollection(r2);
        assertFalse(r1.getIsAlmostPossibleRecipe());
        assertFalse(r1.getIsPossibleRecipe());
        assertFalse(r2.getIsPossibleRecipe());
        assertFalse(r2.getIsAlmostPossibleRecipe());

        rc.checkPossibleRecipes(virtualKitchen);

        assertFalse(r1.getIsAlmostPossibleRecipe());
        assertTrue(r1.getIsPossibleRecipe());
        assertFalse(r2.getIsPossibleRecipe());
        assertTrue(r2.getIsAlmostPossibleRecipe());
    }

    @Test
    public void testCheckPossibleRecipesWithNoMatches() {
        rc.addRecipeToCollection(r1);
        assertFalse(r1.getIsAlmostPossibleRecipe());
        assertFalse(r1.getIsPossibleRecipe());

        rc.checkPossibleRecipes(virtualKitchen);

        assertFalse(r1.getIsAlmostPossibleRecipe());
        assertFalse(r1.getIsPossibleRecipe());
    }


}
