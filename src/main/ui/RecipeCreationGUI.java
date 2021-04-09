package ui;

import exceptions.InvalidRating;
import model.Ingredient;
import model.Recipe;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.io.File;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;

/*
This class creates a swing application that users can interact with to create a new recipe. There are
textfields to input name, rating, estimated cooking time, ingredient names and quantities, and steps. THere are also
checkboxes that assign the recipe categories.
 */

public class RecipeCreationGUI {

    //general constants
    private RecipeCollectionUI recipeCollectionUI;
    private static final String cancelString = "Cancel";
    protected static final String finishString = "Finish Recipe";
    private static final String loadString = "Load Past State";
    private static final String saveString = "Save Current State";
    private JTextField name;
    private String recipeName;
    private JTextField ratingField;
    private JTextField estimatedTime;
    private GridBagConstraints gbc;
    private JPanel body;
    private JPanel sideRecipeCollection;
    private JFrame jf;
    protected JButton finishButton;
    protected JButton cancelButton;
    private List<String> assignedCategories;
    private FinishListener finishListener;

    //constants related to Steps
    private static final String addStepString = "   (+) Add Another Step   ";
    private int stepYValue;
    private final int stepLabelXValue = 4;
    private final int stepFieldXValue = 5;
    private JButton addStepButton;
    private Map<Integer, String> steps;
    private int stepNumber;

    //constants related to Categories
    private static final String addCategoryString = "Add";
    private int categoryYValue;
    private final int categoryLabelXValue = 0;
    private final int categoryFieldXValue = 1;
    private JTextField addCategoryField;
    private JButton addCategoryButton;

    //constants related to Ingredients
    private static final String addIngredientString = "(+) Add Another Ingredient";
    private int ingredientYValue;
    private final int ingredientLabelXValue = 2;
    private final int ingredientFieldXValue = 3;
    private JButton addIngredientButton;
    private Map<Integer, String> ingredientNames;
    private Map<Integer, String> ingredientQuantities;
    private int ingredientNameNumber;
    private  int ingredientQuantityNumber;
    private int numberOfIngredients = 0;


    //MODIFIES: this
    //EFFECTS: creates a swing application that allows user to view entire Recipe Collection of the side,
    //create a new recipe collection entirely, and save current or load past state of the Cooking Manager
    public RecipeCreationGUI(RecipeCollectionUI recipeCollectionUI) {
        init(recipeCollectionUI);
        JPanel main = new JPanel(new BorderLayout());
        JPanel bar = new JPanel(new BorderLayout());
        JPanel buttonPane = new JPanel();

        createVisualInterface(body);
        createEndButtons(buttonPane);
        createMenuBar(bar);
        createRecipeCollectionViewer();

        main.add(body);
        jf.add(main, BorderLayout.CENTER);
        jf.add(bar, BorderLayout.NORTH);
        jf.add(buttonPane, BorderLayout.SOUTH);
        jf.add(sideRecipeCollection, BorderLayout.WEST);
        jf.setMinimumSize(new Dimension(1000, 200));
        jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jf.pack();
        jf.setVisible(true);

    }

    //CREDIT: Structure of this code (the DefaultLisModel and JList usage) was taken from List Demo,
    // on Java Oracle from one of the link given in the project phase 3 description.
    //EFFECTS: creates the side bar which shows the Recipes in the Recipe Collection
    private void createRecipeCollectionViewer() {
        sideRecipeCollection = new JPanel();
        DefaultListModel listModel = new DefaultListModel();
        for (Recipe recipe: recipeCollectionUI.getRecipeCollection().getRecipeCollection()) {
            listModel.addElement(recipe.getName());
        }
        if (listModel.size() == 0) {
            listModel.addElement("     No Recipes     ");
        }

        //Create the list and put it in a scroll pane.
        JList list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setVisibleRowCount(12);
        JScrollPane listScrollPane = new JScrollPane(list);
        sideRecipeCollection.add(listScrollPane);
    }

    //initializes a lot of fields
    private void init(RecipeCollectionUI recipeCollectionUI) {
        ingredientNames = new HashMap<>();
        ingredientQuantities = new HashMap<>();
        steps = new HashMap<>();
        ingredientNameNumber = 1;
        ingredientQuantityNumber = 1;
        stepNumber = 1;
        assignedCategories = new ArrayList<>();
        this.recipeCollectionUI = recipeCollectionUI;
        jf = new JFrame();
        jf.setTitle("Create Recipe");
        gbc = new GridBagConstraints();
        body = new JPanel(new GridBagLayout());
    }

    //MODIFIES: this
    //EFFECTS: creates the "Finish" and "Cancel" Buttons that appear at the bottom of the screen
    private void createEndButtons(JPanel buttonPane) {
        //When all fields are finished, can create recipe pressing button

        finishButton.setActionCommand(finishString);
        finishButton.addActionListener(finishListener);
        finishButton.setEnabled(false);

        //if want to cancel the recipe being created
        cancelButton = new JButton(cancelString);
        cancelButton.setActionCommand(cancelString);
        cancelButton.addActionListener(new CancelListener());

        //Create a panel that uses BoxLayout.

        buttonPane.setLayout(new BoxLayout(buttonPane,
                BoxLayout.LINE_AXIS));
        buttonPane.add(cancelButton);
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(finishButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
    }

    //EFFECTS: creates the interface users interact with the create new Recipe including
    //all the textfields and checkboxes relevant to recipe creation.
    private void createVisualInterface(JPanel body) {
        finishButton = new JButton(finishString);
        finishListener = new FinishListener(finishButton);
        createName(body);
        createEstimatedCookingTime(body);
        createRating(body);
        createCategories(body);
        createIngredients(body);
        createSteps(body);
    }

    //EFFECTS: creates the File Menu Bar item as well as the the options to save or load states of
    //the Cooking Manager
    private void createMenuBar(JPanel panel) {
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("File");
        //m1.addActionListener(this);
        mb.add(m1);
        JMenuItem m11 = new JMenuItem(loadString);
        m11.setActionCommand(loadString);
        m11.addActionListener(new LoadListener());
        JMenuItem m22 = new JMenuItem(saveString);
        m22.setActionCommand(saveString);
        m22.addActionListener(new SaveListener());
        m1.add(m11);
        m1.add(m22);

        panel.add(mb, BorderLayout.WEST);
    }

    //EFFECTS: creates the step labels, textfields, and add Step button
    private void createSteps(JPanel body) {
        createHeader(body, stepLabelXValue, "Steps: ");

        stepYValue = 2;
        createStepFieldAndLabel(body);


        addStepButton = new JButton(addStepString);
        addStepButton.setActionCommand(addStepString);
        addStepButton.addActionListener(new AddStepListener());

        gbc.gridx = 5;
        gbc.gridy = stepYValue;

        body.add(addStepButton, gbc);
    }

    //EFFECTS: creates JLabel header with certain text and at a certain position
    private void createHeader(JPanel body, int gridx, String header) {
        JLabel stepHeader = new JLabel(header);
        gbc.gridx = gridx;
        gbc.gridy = 1;

        body.add(stepHeader, gbc);
    }

    //EFFECTS: creates Step and TextField Labels
    private void createStepFieldAndLabel(JPanel one) {
        JLabel stepNumber = new JLabel(stepYValue - 1 + ".      "); //6 spaces
        gbc.gridx = stepLabelXValue;
        gbc.gridy = stepYValue;
        one.add(stepNumber, gbc);

        JTextField stepField = new JTextField(15); //6 spaces
        createStepListener(stepField, steps);
        stepNumber.setLabelFor(stepField);
        gbc.gridx = stepFieldXValue;
        gbc.gridy = stepYValue;
        one.add(stepField, gbc);
        stepYValue++;
    }

    //creates Ingredient related Labels, textfields and add Ingredient button
    private void createIngredients(JPanel body) {
        createHeader(body, ingredientLabelXValue, "Ingredients (Name, Quantity): ");

        ingredientYValue = 2;

        createIngredientLabelAndField(body);

        addIngredientButton = new JButton(addIngredientString);
        addIngredientButton.setActionCommand(addIngredientString);
        addIngredientButton.addActionListener(new AddIngredientListener());

        gbc.gridx = ingredientFieldXValue;
        gbc.gridy = ingredientYValue;

        body.add(addIngredientButton, gbc);
    }

    //EFFECTS: createes Ingredient Label and Field
    private void createIngredientLabelAndField(JPanel one) {
        JLabel ingredientNumber = new JLabel(ingredientYValue - 1 + ".      ");
        gbc.gridx = ingredientLabelXValue;
        gbc.gridy = ingredientYValue;

        one.add(ingredientNumber, gbc);

        gbc = new GridBagConstraints();
        JTextField ingredientName = new JTextField(10);
        createIngredientNameListener(ingredientName, ingredientNames);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = ingredientFieldXValue;
        gbc.gridy = ingredientYValue;

        one.add(ingredientName, gbc);

        JTextField ingredientQuantity = new JTextField(5);
        createIngredientQuantityListener(ingredientQuantity, ingredientQuantities);
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 3;
        gbc.gridy = ingredientYValue;

        one.add(ingredientQuantity, gbc);
        ingredientYValue++;
        numberOfIngredients++;
    }

    //EFFECTS: When finish button pressed, adds ingredient Quantity to ingredientQuantities Hashmap
    private void createIngredientQuantityListener(JTextField textField, Map<Integer, String> map) {
        finishButton.addActionListener(e -> {
            String getName = textField.getText();
            map.put(ingredientQuantityNumber, getName);
            ingredientQuantityNumber++;
        });
    }

    //EFFECTS: When finish button pressed, adds step to steps Hashmap
    private void createStepListener(JTextField textField, Map<Integer, String> map) {
        finishButton.addActionListener(e -> {
            String getName = textField.getText();
            map.put(stepNumber, getName);
            stepNumber++;
        });
    }

    //MODIFIES: this
    //EFFECTS: When finish button pressed, adds ingredient Name to ingredientNames Hashmap
    //This actionListener is called last, so when the last IngredientNameListener is called, we
    // create the Recipe
    private void createIngredientNameListener(JTextField textField, Map<Integer, String> map) {
        finishButton.addActionListener(e -> {
            String getName = textField.getText();
            map.put(ingredientNameNumber, getName);


            if (numberOfIngredients == ingredientNameNumber) {
                assignRecipeToRecipeCollectionGUI();

            }
            ingredientNameNumber++;
        });
    }

    //MODIFIES: this
    //EFFECTS: adds Recipe to Recipe CollectionUI using information from all the textfields and checkboxes
    private void assignRecipeToRecipeCollectionGUI() {
        List<Ingredient> ingredients = new ArrayList<>();
        for (int i = 0; i < ingredientNames.size(); i++) {
            ingredients.add(new Ingredient(ingredientNames.get(ingredientNames.size() - i),
                    ingredientQuantities.get(ingredientQuantities.size() - i)));
        }

        List<String> stepList = new ArrayList<>();
        for (int i = 0; i < steps.size(); i++) {
            stepList.add(steps.get(steps.size() - i));
        }
        try {
            int recipeEstimatedTime = Integer.parseInt(estimatedTime.getText().trim());

            int recipeRating = Integer.parseInt(ratingField.getText().trim());
            recipeCollectionUI.getRecipeCollection().addRecipeToCollection(new Recipe(recipeName,
                    ingredients, stepList, assignedCategories, recipeEstimatedTime, recipeRating));
            resetFrameVariables();
            playSound("data/RecipeAdded.wav");
        } catch (InvalidRating ex) {
            System.out.println("Entered an invalid rating. Ratings must be an integer from 0 to 5. "
                    + "Try again.");
            jf.dispose();
        } catch (Exception ex) {
            System.out.println("Did not enter a number for rating or estimated cooking time. Please try again");

        }
    }

    //MODIFIES: this
    //EFFECTS: resets fields relevant to remembering user inputted actions, so that the next time the
    //GUI is called, the variables do not carry any old information.
    private void resetFrameVariables() {
        ingredientNames = new HashMap<>();
        ingredientQuantities = new HashMap<>();
        steps = new HashMap<>();
        assignedCategories = new ArrayList<>();
        jf.dispose();
    }

    //MODIFIES: this
    //EFFECTS: creates labels and checkboxes for categories.
    private void createCategories(JPanel one) {
        gbc.anchor = GridBagConstraints.WEST;
        createHeader(one, categoryLabelXValue, "Categories: ");

        createCategoriesNameAndBox(one);


        addCategoryButton = new JButton("Add");
        AddCategoryListener addCategoryListener = new AddCategoryListener(addCategoryButton);
        addCategoryButton.setActionCommand(addCategoryString);
        addCategoryButton.addActionListener(addCategoryListener);
        addCategoryButton.setEnabled(false);

        addCategoryField = new JTextField("Add a Category!", 8);
        addCategoryField.addActionListener(addCategoryListener);
        addCategoryField.getDocument().addDocumentListener(addCategoryListener);
        createAddCategoryButtonAndField(one);


    }


    //MODIFIES: this
    //EFFECTS: creates the textfield for users to input new category and the add category button
    private void createAddCategoryButtonAndField(JPanel one) {
        gbc.gridx = categoryFieldXValue;
        gbc.gridy = categoryYValue;
        gbc.anchor = GridBagConstraints.CENTER;

        one.add(addCategoryButton, gbc);

        gbc.gridx = categoryLabelXValue;
        gbc.gridy = categoryYValue;
        gbc.anchor = GridBagConstraints.EAST;
        one.add(addCategoryField, gbc);
    }

    private void createCategoriesNameAndBox(JPanel one) {
        categoryYValue = 2;
        for (String category: recipeCollectionUI.getCategories()) {
            createSingleCategory(one, category);
        }
        gbc = new GridBagConstraints();
    }

    //MODIFIES: this
    //EFFECTS: create the label and checkbox for a given category
    private void createSingleCategory(JPanel one, String category) {
        JLabel l = new JLabel("          " + category); //10 spaces
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = categoryLabelXValue;
        gbc.gridy = categoryYValue;
        one.add(l, gbc);
        gbc.anchor = GridBagConstraints.CENTER;
        JCheckBox checkbox = new JCheckBox();
        l.setLabelFor(checkbox);
        checkbox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                assignedCategories.add(l.getText().trim());
            } else {
                assignedCategories.remove(l.getText().trim());
            }
        });
        gbc.gridx = categoryFieldXValue;
        gbc.gridy = categoryYValue;
        one.add(checkbox, gbc);
        categoryYValue++;
    }



    //MODIFIES: this
    //EFFECTS: creates rating label and textfield
    private void createRating(JPanel one) {
        JLabel ratingLabel = new JLabel("Rating (0 - 5): ");

        gbc.gridx = 4;
        gbc.gridy = 0;
        one.add(ratingLabel, gbc);

        ratingField = new JTextField(5);
        ratingField.addActionListener(finishListener);
        ratingField.getDocument().addDocumentListener(finishListener);
        gbc.gridx = 5;
        gbc.gridy = 0;
        one.add(ratingField, gbc);
        gbc = new GridBagConstraints();
    }


    //MODIFIES: this
    //EFFECTS: creates estimated cooking time label and textfield
    private void createEstimatedCookingTime(JPanel one) {
        gbc = new GridBagConstraints();
        estimatedTime = new JTextField(5);
        estimatedTime.addActionListener(finishListener);
        estimatedTime.getDocument().addDocumentListener(finishListener);
        JLabel estimatedTimeLabel = new JLabel("Estimated Cooking Time (in min): ");
        gbc.gridwidth = 1;
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        one.add(estimatedTimeLabel, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 3;
        gbc.gridy = 0;
        one.add(estimatedTime, gbc);
    }

    //MODIFIES: this
    //EFFECTS: create Name label and textfields
    private void createName(JPanel one) {
        name = new JTextField(10);
        name.addActionListener(finishListener);
        name.getDocument().addDocumentListener(finishListener);
        JLabel nameLabel = new JLabel("Recipe Name: ");
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        one.add(nameLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        one.add(name, gbc);
    }

    //MODIFIES: this
    //EFFECTS: when add step button pressed, creates new Label and textfield for new step
    private class AddStepListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            createStepFieldAndLabel(body);

            gbc.gridx = stepFieldXValue;
            gbc.gridy = stepYValue;
            body.add(addStepButton, gbc);


            jf.pack();
            jf.setVisible(true);
        }
    }

    //MODIFIES: this
    //EFFECTS: when Add Step Button pressed, new ingredient name textfield and new ingredient quantity
    //textfield while generating Add Step Button one grid lower.
    private class AddIngredientListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            createIngredientLabelAndField(body);

            gbc.gridx = ingredientFieldXValue;
            gbc.gridy = ingredientYValue;
            body.add(addIngredientButton, gbc);

            jf.pack();
            jf.setVisible(true);
        }
    }

    //CREDITS: the structure of this code was found on Java oracle in the List Demo which
    //belonged to a link given to us in the phase 3 project description.
    //MODIFIES: this
    //EFFECTS: when Add Category Button pressed, checks the textfield to see if it is empty or a
    //duplicate. If it is not, creates new label and checkbox for new category and generates
    //empty textfield and Add button one grid space lower.
    private class AddCategoryListener implements ActionListener, DocumentListener {

        private final JButton button;
        private boolean alreadyEnabled = false;

        public AddCategoryListener(JButton button) {
            this.button = button;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String category = addCategoryField.getText();

            if (category.equals("") || category.equals("Add a Category!")
                    || recipeCollectionUI.getCategories().contains(category)) {
                Toolkit.getDefaultToolkit().beep();
                addCategoryField.requestFocusInWindow();
                addCategoryField.selectAll();
                return;
            }

            createSingleCategory(body, category);
            recipeCollectionUI.getCategories().add(category);

            addCategoryField.setText("");
            createAddCategoryButtonAndField(body);
            jf.pack();
            jf.setVisible(true);
        }

        //EFFECTS: Inserts update
        @Override
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

        //EFFECTS: checks if textfield is empty
        @Override
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        //EFFECTS: if textfield not empty, button is enabled
        @Override
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }

        //MODIFIES: this
        //EFFECTS: enables button if not already enabled
        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }

        //MODIFIES: this
        //EFFECTS: returns true if textfield is empty and false if not.
        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }
    }


    private class CancelListener implements ActionListener {

        //MODIFIES: this
        //EFFECTS: plays sound and disposes of jframe.
        @Override
        public void actionPerformed(ActionEvent e) {
            jf.dispose();
            playSound("data/RecipeCreationCancelled.wav");
        }
    }

    class FinishListener implements ActionListener, DocumentListener {

        JButton button;
        private boolean alreadyEnabled = false;

        //EFFECTS: constructor
        public FinishListener(JButton button) {
            this.button = button;
        }

        //MODIFIES: this
        //EFFECTS: checks that name is not empty and gets the text from the name textfield.
        @Override
        public void actionPerformed(ActionEvent e) {

            recipeName = name.getText();



            //User didn't type in a unique name...
            if (recipeName.equals("")) {
                Toolkit.getDefaultToolkit().beep();
                name.requestFocusInWindow();
                name.selectAll();
            }


        }

        //NOTE: all specifications of the following methods are the same as in addCategoryListener
        @Override
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }

        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }

        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }
    }

    class SaveListener implements ActionListener {

        //EFFECTS: saves the KitchenManager App
        @Override
        public void actionPerformed(ActionEvent e) {
            recipeCollectionUI.getKitchenManagerApp().saveRecipeCollectionAndVirtualKitchen();
        }
    }


    class LoadListener implements ActionListener {

        //EFFECTS: loads past state of Kitchen Manager.
        @Override
        public void actionPerformed(ActionEvent e) {
            recipeCollectionUI.getKitchenManagerApp().loadCookingManager();
        }
    }

    //CREDIT: this code was found on stackoverflow on how to play Sounds
    //EFFECTS: tries to play audio clip.
    public void playSound(String soundName) {

        try {
            File wav = new File(soundName).getAbsoluteFile();
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(wav);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }
}
