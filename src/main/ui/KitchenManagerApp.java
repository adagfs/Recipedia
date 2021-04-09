package ui;

import model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.List;
import java.util.Scanner;

/*
creates instance of the Kitchen Manager App. Is responsible for starting and stopping the app, for
general main menu commands and for saving or loading states of the Kitchen Manager.
 */

public class KitchenManagerApp {
    private Scanner input;
    private RecipeCollectionUI recipeCollectionUI;
    public static final String fileName = "KitchenManagerFile";


    //constructor
    public KitchenManagerApp() {
        init();
        runKitchenManagerApp();
    }

    public static String prepCommand(String command) {
        command = command.trim();
        command = command.toLowerCase();

        return command;
    }

    //MODIFIES: this
    //EFFECTS: initalizes recipeCollection virtualKitchen, and categories.
    //Adds 6 of the most popular diet options as default categories to categories.
    private void init() {
        recipeCollectionUI = new RecipeCollectionUI(this);

    }

    //MODIFIES: this
    //EFFECTS: Starts up the Kitchen Manager App and processes user input
    private void runKitchenManagerApp() {
        boolean keepGoing = true;
        String command;
        input = new Scanner(System.in);

        System.out.println("Welcome to your Kitchen Manager!");
        loadCookingManager();
        while (keepGoing) {
            displayMenu();

            command = prepCommand(input.nextLine());

            if (command.equals("quit")) {
                keepGoing = false;
                System.out.println("Thank you for using Kitchen Manager! Goodbye for now.");
                saveRecipeCollectionAndVirtualKitchen();
            } else {
                processMainMenuCommands(command);
            }
        }
    }

    //EFFECTS: Display main menu by printing the 3 possible options that users can take
    private void displayMenu() {
        System.out.println("Main Menu: What would you like to do right now?" + "\n");
        System.out.println("1. Access your Recipe Collection");
        System.out.println("2. Access your Virtual Kitchen");
        System.out.println("\n" + "Input the number corresponding to your desired action or input 'quit' "
                + "to quit the application");
    }

    //EFFECTS: Reads command. If command is "1", the Recipe Collection will be displayed.
    //If command is "2", the Virtual Kitchen will be displayed
    //If command is something else, a statement will be printed saying the input was invalid.
    private void processMainMenuCommands(String command) {
        if (command.equals("1")) {
            recipeCollectionUI.goToRecipeCollection(recipeCollectionUI.getRecipeCollection(),
                    "Welcome to your Recipe Collection!");
        } else if (command.equals("2")) {
            recipeCollectionUI.getVirtualKitchenUI().goToVirtualKitchen();
        } else {
            System.out.println("Invalid Input. Please try again!" + "\n");
        }
    }


    //NOTE: Credits for this Idea go to lead TA (Eric Newton) in demo he showed during a lab
    //EFFECTS: Saves Recipe Collection
    public void saveRecipeCollectionAndVirtualKitchen() {
        try {
            File targetDir = new File("data");
            File targetFile = new File(targetDir, fileName);
            FileOutputStream fos = new FileOutputStream(targetFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(recipeCollectionUI.getRecipeCollection());
            oos.writeObject(recipeCollectionUI.getCategories());
            oos.writeObject(recipeCollectionUI.getVirtualKitchenUI().getVirtualKitchen());
            oos.close();
            System.out.println("The current state of your Kitchen Manager has been saved.");
        } catch (Exception e) {
            System.out.println("Something went wrong! :(");
        }
    }

    //NOTE: Credits for this Idea go to lead TA (Eric Newton) in demo he showed during a lab
    //EFFECTS: Load
    public void loadCookingManager() {
        try {
            File targetDir = new File("data");
            File targetFile = new File(targetDir, fileName);
            FileInputStream fis = new FileInputStream(targetFile);
            ObjectInputStream ois = new ObjectInputStream(fis);
            recipeCollectionUI.setRecipeCollection((RecipeCollection) ois.readObject());
            recipeCollectionUI.setCategories((List<String>) ois.readObject());
            recipeCollectionUI.getVirtualKitchenUI().setVirtualKitchen((VirtualKitchen) ois.readObject());
            ois.close();
            System.out.println("The previous state of your Kitchen Manager has been loaded. \n");
        } catch (FileNotFoundException e) {
            System.out.println("Welcome to your personal Kitchen Manager!");
        } catch (Exception e) {
            System.out.println("An error has occurred.");
        }
    }

    public class SaveListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //kitchenManagerApp.saveRecipeCollectionAndVirtualKitchen();
        }
    }


    public class LoadListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //kitchenManagerApp.loadCookingManager();
        }
    }

}


