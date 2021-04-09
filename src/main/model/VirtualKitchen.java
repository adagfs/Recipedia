package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*
The virtual kitchen is made with the purpose of being a reflection of the food that someone has in their
physical kitchen. Its only field is foodGroups which is a list of Food Groups which each have a list of items
representing how many items of that food group are in the fridge.
 */
public class VirtualKitchen implements Serializable {

    private List<FoodGroup> foodGroups;


    //constructor
    //EFFECTS: Adds and names 6 Food Groups to foodGroups. Water is also added in Miscellaneous because
    // it is assumed that people using this app have access to water.
    public VirtualKitchen() {
        foodGroups = new ArrayList<>();
        foodGroups.add(new FoodGroup("Spices and Seasoning"));
        foodGroups.add(new FoodGroup("Produce"));
        foodGroups.add(new FoodGroup("Meat"));
        foodGroups.add(new FoodGroup("Dairy"));
        foodGroups.add(new FoodGroup("Grain"));
        foodGroups.add(new FoodGroup("Miscellaneous"));
        foodGroups.get(5).addItem("water");
    }

//    //EFFECTS: Print out the items in the Virtual Kitchen by category
//    public void viewVirtualKitchen() {
//        System.out.println("Virtual Kitchen View: " + "\n");
//
//        System.out.println("Spices and Seasoning:");
//        viewCategory(spicesAndSauces);
//
//        System.out.println("Produce:");
//        viewCategory(produce);
//
//        System.out.println("Meat: ");
//        viewCategory(meat);
//
//        System.out.println("Dairy: ");
//        viewCategory(grain);
//
//        System.out.println("Grains: ");
//        viewCategory(grain);
//    }
//
//    private void viewCategory(List<String> category) {
//        int i = 1;
//        for (String s: category) {
//            System.out.println(i + ". " + s);
//        }
//        System.out.println("\n");
//    }

//    //MODIFIES: this
//    //EFFECTS: adds item to category if the item isn't already there
//    public boolean addItem(FoodGroup foodGroup, String ing) {
//        return foodGroup.addItem(ing);
//    }
//
//
//
//    //MODIFIES: this
//    //EFFECTS: removes item if present and return true, else, return false
//    public boolean removeItem(FoodGroup foodGroup, String ing) {
//        return foodGroup.removeItem(ing);
//    }

    //getter
    public List<FoodGroup> getFoodGroups() {
        return foodGroups;
    }


}


