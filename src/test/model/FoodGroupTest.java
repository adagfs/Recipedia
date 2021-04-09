package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FoodGroupTest {
    FoodGroup foodGroup;

    @BeforeEach
    public void setUp() {
        foodGroup = new FoodGroup("Dairy");
    }

    @Test
    public void testAddItemSuccess() {
        //Ensure that set up is correct
        assertEquals(0, foodGroup.getSizeofFoodList());

        //Add an Item
        assertTrue(foodGroup.addItem("Milk"));

        //Confirm it has worked
        assertEquals(1, foodGroup.getSizeofFoodList());

        List<String> foodGroupComparison = new ArrayList<>();
        foodGroupComparison.add("Milk");
        assertEquals(foodGroupComparison, foodGroup.getFoodList());

    }

    @Test
    public void testAddItemFail() {
        //Ensure that set up is correct
        assertEquals(0, foodGroup.getSizeofFoodList());

        //Add an Item
        assertTrue(foodGroup.addItem("Milk"));

        //Confirm it has worked
        assertEquals(1, foodGroup.getSizeofFoodList());

        //Fail to add Item again
        assertFalse(foodGroup.addItem("Milk"));

        List<String> foodGroupComparison = new ArrayList<>();
        foodGroupComparison.add("Milk");
        assertEquals(foodGroupComparison, foodGroup.getFoodList());

    }

    @Test
    public void testRemoveItemSuccess() {
        //Ensure that set up is correct
        assertEquals(0, foodGroup.getSizeofFoodList());
        assertTrue(foodGroup.addItem("Milk"));
        assertTrue(foodGroup.addItem("Eggs"));
        assertEquals(2, foodGroup.getSizeofFoodList());

        //Remove Item
        assertTrue(foodGroup.removeItem("Milk"));

        //assert that it worked
        assertEquals(1, foodGroup.getSizeofFoodList());

        //Remove another item
        assertTrue(foodGroup.removeItem("Eggs"));
        assertEquals(0, foodGroup.getSizeofFoodList());
    }

    @Test
    public void testRemoveItemFail() {
        //Ensure that set up is correct
        assertEquals(0, foodGroup.getSizeofFoodList());
        assertTrue(foodGroup.addItem("Milk"));
        assertTrue(foodGroup.addItem("Eggs"));
        assertEquals(2, foodGroup.getSizeofFoodList());

        //Remove Item
        assertFalse(foodGroup.removeItem("Cheese"));

        //assert that it didn't work
        assertEquals(2, foodGroup.getSizeofFoodList());
    }

    @Test
    public void testGetName() {
        assertEquals("Dairy", foodGroup.getNameofFoodGroup());
    }

}
