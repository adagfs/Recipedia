package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VirtualKitchenTest {
    VirtualKitchen virtualKitchen;


    @BeforeEach
    public void setup() {
        virtualKitchen = new VirtualKitchen();
    }

    @Test
    public void testGetFoodGroups() {
        List<FoodGroup> foodGroups = new ArrayList<>();
        foodGroups.add(new FoodGroup("Spices and Seasoning"));
        foodGroups.add(new FoodGroup("Produce"));
        foodGroups.add(new FoodGroup("Meat"));
        foodGroups.add(new FoodGroup("Dairy"));
        foodGroups.add(new FoodGroup("Grain"));

        assertEquals(foodGroups.get(0).getFoodList(), virtualKitchen.getFoodGroups().get(0).getFoodList());
    }
//
//    @Test
//    public void testAddSpicesAndSauces() {
//        testAddItem(virtualKitchen.getSpicesAndSauces(), "salt", "Tabasco");
//    }
//
//    @Test
//    public void testAddSpicesAndSaucesFail() {
//        testAddItemFail(virtualKitchen.getSpicesAndSauces(), "salt");
//    }
//
//    @Test
//    public void testAddProduce() {
//        testAddItem(virtualKitchen.getProduce(), "celery", "apples");
//    }
//
//    @Test
//    public void testAddProduceFail() {
//        testAddItemFail(virtualKitchen.getProduce(), "celery");
//    }
//
//    @Test
//    public void testAddMeat() {
//        testAddItem(virtualKitchen.getMeat(), "chicken thigh", "pork loin");
//    }
//
//    @Test
//    public void testAddMeatFail() {
//        testAddItemFail(virtualKitchen.getMeat(), "chicken thigh");
//    }
//
//    @Test
//    public void testAddDairy() {
//        testAddItem(virtualKitchen.getDairy(), "milk", "gouda cheese");
//    }
//
//    @Test
//    public void testAddDairyFail() {
//        testAddItemFail(virtualKitchen.getDairy(), "milk");
//    }
//    @Test
//    public void testAddGrain() {
//        testAddItem(virtualKitchen.getGrain(), "rice", "bread");
//    }
//
//    @Test
//    public void testAddGrainFail() {
//        testAddItemFail(virtualKitchen.getGrain(), "rice");
//    }
//
//    @Test
//    public void testRemoveSpicesAndSauces() {
//        testRemoveItem(virtualKitchen.getSpicesAndSauces(), "salt", "Tabasco");
//    }
//
//    @Test
//    public void testRemoveSpicesAndSaucesFail() {
//        testRemoveItemFail(virtualKitchen.getSpicesAndSauces(), "salt");
//    }
//
//    @Test
//    public void testRemoveProduce() {
//        testRemoveItem(virtualKitchen.getProduce(), "celery", "apples");
//    }
//
//    @Test
//    public void testRemoveProduceFail() {
//        testRemoveItemFail(virtualKitchen.getProduce(), "celery");
//    }
//
//    @Test
//    public void testRemoveMeat() {
//        testRemoveItem(virtualKitchen.getMeat(), "chicken thigh", "pork loin");
//    }
//
//    @Test
//    public void testRemoveMeatFail() {
//        testRemoveItemFail(virtualKitchen.getMeat(), "chicken thigh");
//    }
//
//    @Test
//    public void testRemoveDairy() {
//        testRemoveItem(virtualKitchen.getDairy(), "milk", "gouda cheese");
//    }
//
//    @Test
//    public void testRemoveDairyFail() {
//        testRemoveItemFail(virtualKitchen.getDairy(), "milk");
//    }
//    @Test
//    public void testRemoveGrain() {
//        testRemoveItem(virtualKitchen.getGrain(), "rice", "bread");
//    }
//
//    @Test
//    public void testRemoveGrainFail() {
//        testRemoveItemFail(virtualKitchen.getGrain(), "rice");
//    }
//
//    public void testAddItem(List<String> category, String item1, String item2) {
//        //confirm that initialization is correct
//        assertEquals(0, virtualKitchen.getCategorySize(category));
//
//        //add item
//        assertTrue(virtualKitchen.addItem(category, item1));
//
//        //confirm that method worked correctly
//        assertEquals(1, virtualKitchen.getCategorySize(category));
//        assertEquals(item1, category.get(0));
//
//        //add another item
//        assertTrue(virtualKitchen.addItem(category, item2));
//        assertEquals(2, virtualKitchen.getCategorySize(category));
//        assertEquals(item2, category.get(1));
//
//    }
//
//
//    public void testAddItemFail(List<String> category, String item1) {
//        //confirm that initialization is correct
//        assertEquals(0, virtualKitchen.getCategorySize(category));
//
//        //Add item
//        assertTrue(virtualKitchen.addItem(category, item1));
//
//        //confirm that method worked correctly
//        assertEquals(1, virtualKitchen.getCategorySize(category));
//        assertEquals(item1, category.get(0));
//
//        //add another item
//        assertFalse(virtualKitchen.addItem(category, item1));
//        assertEquals(1, virtualKitchen.getCategorySize(category));
//
//    }
//    public void testRemoveItem(List<String> category, String item1, String item2) {
//        //confirm that initialization is correct
//        assertTrue(virtualKitchen.addItem(category, item1));
//        assertTrue(virtualKitchen.addItem(category, item2));
//        assertEquals(2, virtualKitchen.getCategorySize(category));
//
//        //remove item
//        assertTrue(virtualKitchen.removeItem(category, item1));
//
//        //confirm that method worked correctly
//        assertEquals(1, virtualKitchen.getCategorySize(category));
//        assertEquals(item2, category.get(0));
//
//        //add another item
//        assertTrue(virtualKitchen.removeItem(category, item2));
//        assertEquals(0, virtualKitchen.getCategorySize(category));
//    }
//
//    public void testRemoveItemFail(List<String> category, String item1) {
//        //confirm that initialization is correct
//        assertTrue(virtualKitchen.addItem(category, item1));
//
//        assertEquals(1, virtualKitchen.getCategorySize(category));
//
//        //remove item
//        assertFalse(virtualKitchen.removeItem(category, "Non existent"));
//
//        //confirm that method worked correctly
//        assertEquals(1, virtualKitchen.getCategorySize(category));
//    }

//    @Test
//    public void testAddSpicesAndSauces() {
//        //confirm that initialization is correct
//        assertEquals(0, virtualKitchen.getSpicesAndSaucesSize());
//
//        //add item
//        virtualKitchen.addItem(virtualKitchen.getSpicesAndSauces(), "Salt");
//
//        //confirm that method worked correctly
//        assertEquals(1, virtualKitchen.getSpicesAndSaucesSize());
//        assertEquals("Salt", virtualKitchen.getSpicesAndSauces().get(0));
//
//        //add another item
//        virtualKitchen.addItem(virtualKitchen.getSpicesAndSauces(), "Tabasco");
//        assertEquals(2, virtualKitchen.getSpicesAndSaucesSize());
//        assertEquals("Tabasco", virtualKitchen.getSpicesAndSauces().get(1));
//
//    }
//
//    @Test
//    public void testAddProduce() {
//        //confirm that initialization is correct
//        assertEquals(0, virtualKitchen.getProduceSize());
//
//        //add item
//
//        virtualKitchen.addItem(virtualKitchen.getProduce(), "celery");
//
//        //confirm that method worked correctly
//        assertEquals(1, virtualKitchen.getProduceSize());
//        assertEquals("celery", virtualKitchen.getProduce().get(0));
//
//        //add another item
//        virtualKitchen.addItem(virtualKitchen.getProduce(), "apples");
//        assertEquals(2, virtualKitchen.getProduceSize());
//        assertEquals("apples", virtualKitchen.getProduce().get(1));
//
//    }
//
//    @Test
//    public void testAddMeat() {
//        //confirm that initialization is correct
//        assertEquals(0, virtualKitchen.getMeatSize());
//
//        //add item
//        virtualKitchen.addItem(virtualKitchen.getMeat(), "chicken thigh");
//
//        //confirm that method worked correctly
//        assertEquals(1, virtualKitchen.getMeatSize());
//        assertEquals("chicken thigh", virtualKitchen.getMeat().get(0));
//
//        //add another item
//
//        virtualKitchen.addItem(virtualKitchen.getMeat(), "pork loin");
//        assertEquals(2, virtualKitchen.getMeatSize());
//        assertEquals("pork loin", virtualKitchen.getMeat().get(1));
//    }
//
//    @Test
//    public void testAddDairy() {
//        //confirm that initialization is correct
//        assertEquals(0, virtualKitchen.getDairySize());
//
//        //add item
//        virtualKitchen.addDairy("milk");
//
//        //confirm that method worked correctly
//        assertEquals(1, virtualKitchen.getDairySize());
//        assertEquals("milk", virtualKitchen.getDairy().get(0));
//
//        //add another item
//        virtualKitchen.addDairy("gouda cheese");
//        assertEquals(2, virtualKitchen.getDairySize());
//        assertEquals("gouda cheese", virtualKitchen.getDairy().get(1));
//
//    }
//
//    @Test
//    public void testAddGrain() {
//        //confirm that initialization is correct
//        assertEquals(0, virtualKitchen.getGrainSize());
//
//        //add item
//        virtualKitchen.addGrain("rice");
//
//        //confirm that method worked correctly
//        assertEquals(1, virtualKitchen.getGrainSize());
//        assertEquals("rice", virtualKitchen.getGrain().get(0));
//
//        //add another item
//        virtualKitchen.addGrain("oats");
//        assertEquals(2, virtualKitchen.getGrainSize());
//        assertEquals("oats", virtualKitchen.getGrain().get(1));
//
//    }


}
