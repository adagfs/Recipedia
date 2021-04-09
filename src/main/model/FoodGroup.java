package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*
This class represents a group of food, like dairy, produce or meat. There is a field which is
the name of the food group and a list of Strings, which is a list of items that belong in said food group.
 */
public class FoodGroup implements Serializable {
    List<String> foodList;
    String nameOfFoodGroup;

    //constructor
    public FoodGroup(String name) {
        nameOfFoodGroup = name;
        foodList = new ArrayList<>();
    }

    //getter
    public String getNameofFoodGroup() {
        return nameOfFoodGroup;
    }

    //getter
    public List<String> getFoodList() {
        return foodList;
    }

    //get size of FoodGroup
    public int getSizeofFoodList() {
        return foodList.size();
    }

    //MODIFIES: this
    //EFFECTS: adds a new item of food group. Returns true if no item exists and adds item to food Group
    //Returns false if item already exists
    public boolean addItem(String item) {
        if (foodList.contains(item)) {
            return false;
        } else {
            foodList.add(item);
            return  true;
        }
    }

    //MODIFIES: this
    //EFFECTS: if item present, removes item from food group and returns true.
    // If item not present, returns false.
    public boolean removeItem(String item) {
        if (foodList.contains(item)) {
            foodList.remove(item);
            return true;
        } else {
            return false;
        }
    }
}

