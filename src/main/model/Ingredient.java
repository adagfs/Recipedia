package model;

import java.io.Serializable;

/*
This class represents ingredients which are used in recipes. There is a field which represents the
name of the item, for example eggs, and a field which represents the quantity of said item. For now, the
quantity is a String, but could become its own class in the future.
 */
public class Ingredient implements Serializable {

    private String item;
    private String quantity;

    //constructor
    public Ingredient(String item, String quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    //getter
    public String getItem() {
        return this.item;
    }

    //getter
    public String getQuantity() {
        return this.quantity;
    }
}
