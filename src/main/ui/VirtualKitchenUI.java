package ui;

import model.FoodGroup;
import model.VirtualKitchen;

import java.io.Serializable;
import java.util.Scanner;

/*
Class which handles all console-related Virtual Kitchen matters
 */

public class VirtualKitchenUI implements Serializable {

    VirtualKitchen virtualKitchen;
    Scanner input;

    public VirtualKitchenUI() {
        this.virtualKitchen = new VirtualKitchen();
        input = new Scanner(System.in);
    }

    //MODIFIES: this
    //EFFECTS: go to the menu of the Virtual Kitchen
    public void goToVirtualKitchen() {
        System.out.println("Nice of you to stop by your Virtual Kitchen. "
                 + "Here's what you have in stock right now:" + "\n");
        boolean keepGoing = true;

        while (keepGoing) {
            displayPersonalKitchen();

            System.out.println("What would you like to do? To add an item, input 'add'. To remove an item, input"
                    + " 'remove'. Input 'quit' to return to the Main Menu");
            String command = KitchenManagerApp.prepCommand(input.nextLine());

            if (command.equals("quit")) {
                keepGoing = false;
            } else {
                processVirtualKitchenCommands(command);
            }
        }
    }

    //EFFECTS: Display the contents of the Personal Kitchen
    private void displayPersonalKitchen() {
        System.out.println("\nVirtual Kitchen:");
        for (FoodGroup foodGroup : virtualKitchen.getFoodGroups()) {
            System.out.println(foodGroup.getNameofFoodGroup());

            for (String item : foodGroup.getFoodList()) {
                System.out.println("    -" + item);
            }

            if (foodGroup.getSizeofFoodList() == 0) {
                System.out.println("Empty" + "\n");
            }
        }

    }

    //MODIFIES: this
    //EFFECTS: Analyze input of the input given by user
    private void processVirtualKitchenCommands(String command) {
        if (command.equals("add")) {
            addItemtoVirtualKitchen();
        } else if (command.equals("remove")) {
            removeItemfromVirtualKitchen();
        }
    }

    //EFFECTS: adds item to virtual Kitchen. If the food Group inputted does not exist or item is already in
    // virtual kitchen, statement is printed out and nothing else happens
    private void addItemtoVirtualKitchen() {
        System.out.println("What item would you like to add to your kitchen?");
        String item = input.nextLine().trim().toLowerCase();

        System.out.println("What category of food does this belong to? Input the name of the category.");
        String foodCategory = input.nextLine().trim().toLowerCase();
        boolean foodCategoryMatches = false;

        for (FoodGroup foodGroup : virtualKitchen.getFoodGroups()) {
            if (foodGroup.getNameofFoodGroup().toLowerCase().equals(foodCategory)) {
                foodCategoryMatches = true;
                boolean isAdded = foodGroup.addItem(item);

                if (isAdded) {
                    System.out.println("Item Added to " + foodGroup.getNameofFoodGroup() + "!");
                } else {
                    System.out.println("Item already in Kitchen! returning to your Virtual Kitchen");
                }
            }
        }
        if (!foodCategoryMatches) {
            System.out.println("Invalid Food Group. Returning to your Virtual Kitchen");
        }
    }

    //MODIFIES
    //EFFECTS: If item is in kitchen, remove Item from virtual kitchen. If item is not there or food group not found,
    // a statement is printed out.
    private void removeItemfromVirtualKitchen() {
        System.out.println("What item would you like to remove from your kitchen?");
        String item = input.nextLine().trim().toLowerCase();

        System.out.println("What category of food does this belong to? Input the name of the category");
        String foodCategory = input.nextLine().trim().toLowerCase();
        boolean foodCategoryMatches = false;

        for (FoodGroup foodGroup : virtualKitchen.getFoodGroups()) {
            if (foodGroup.getNameofFoodGroup().toLowerCase().equals(foodCategory)) {
                foodCategoryMatches = true;

                boolean isRemoved = foodGroup.removeItem(item);

                if (isRemoved) {
                    System.out.println("Item removed from " + foodGroup.getNameofFoodGroup() + "!");
                } else {
                    System.out.println("Item not found in Kitchen! returning to your Virtual Kitchen");
                }
            }
        }
        if (!foodCategoryMatches) {
            System.out.println("Invalid Food Group. Returning to your Virtual Kitchen");
        }
    }

    //getter
    public VirtualKitchen getVirtualKitchen() {
        return virtualKitchen;
    }

    public void setVirtualKitchen(VirtualKitchen virtualKitchen) {
        this.virtualKitchen = virtualKitchen;
    }
}
