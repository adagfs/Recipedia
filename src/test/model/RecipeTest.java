package model;

import exceptions.InvalidRating;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

public class RecipeTest {
    Recipe r1;
    List<Ingredient> ingredients;
    List<String> steps;
    List<String> categories;
    int time;

    @BeforeEach

    public void setup() {
        time = 15;
        ingredients = new ArrayList<>();
        steps = new ArrayList<>();
        try {
            r1 = new Recipe("Pasta", ingredients, steps, categories, time, 0);
        } catch (Exception e) {

        }
    }

    @Test
    public void testChangeRating() {

        try {
            r1.rateRecipe(4);
        } catch (InvalidRating invalidRating) {
            fail("Error not expected");
        }

        assertEquals(4, r1.getRating());
    }

    @Test
    public void testChangeRatingFail() {

        try {
            r1.rateRecipe(6);
            fail("Should not have passed");
        } catch (InvalidRating invalidRating) {
            System.out.println("Worked!");
        }

    }

    @Test
    public void testChangeRatingFailLow() {

        try {
            r1.rateRecipe(-2);
            fail("Should not have passed");
        } catch (InvalidRating invalidRating) {
            System.out.println("Worked!");
        }
    }



    @Test
    public void testUntestedGetters() {
        assertEquals(15, r1.getTime());
        assertEquals(new ArrayList<>(), r1.getIngredients());
        assertEquals(new ArrayList<>(),r1.getSteps());
        assertNull(r1.getCategories());
    }




}
