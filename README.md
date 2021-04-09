# Recipedia

## Description
My Personal Project will be a Cooking Manager. Other than 
 the basic functions of being able to add recipes,
 view recipes and rate them, you will also be able 
 to categorize recipes by category. Some will be programmed in 
 while others can be created.
 In addition, there will be an option to add 
 ingredients to your "Virtual Kitchen" that 
 will allow you to search your kitchen for recipes you can make with the 
 ingredients you have and recipes which you can 
 make if you substitute a few ingredients.

## Target Audience
This application will be useful to any person who cooks
which is a very general target demographic, so the application
has potential to be widely used. However, there is a specific
focus put on people with dietary restrictions because as the
app develops, a lot of focus will be put on the tag system.

## Why This Project?
In the past few years, cooking has become a constant in my life
that I wholeheartedly enjoy. While my father, 
my sister and I have no dietary restrictions, my mother follows a
very specific Buddhist diet which consists of no meat,
onion or garlic. As onion and garlic are two key ingredients
in most vegetarian diets, it is a constant struggle finding recipes
on websites, as there is no tag to get rid of those 
specific ingredients. 
As such, I'd love to make a Recipe Manager that I can use myself
and design with all the functionality that I think should be added
to recipe apps/websites.

## User Stories
- As a user, I want the Kitchen Manager to automatically save
whenever I quit the app. 

- As a user, I want the Kitchen Manager to automatically
load the previous save file, if there is one, when I start up
the app. 

- As a user, I want to be able to add a Recipe to my 
RecipeCollection.

- As a user, I want to be able to view, add, and remove
the items in my VirtualKitchen.

- As a user, I want to be able to view my recipe collection
as a whole, and when it is filtered for a specific category.

- As a user, I want to search for what recipes I
can make and those I can almost make based off of the items in my Virtual Kitchen.

## Additional User Stories
- As a user, I want to be able to rate my recipes on a scale
of 0 to 5 stars 
- As a user, I want to be able to view a recipe in detail.
- As a user, I want to be able to add a category to the
pre-existing list of categories.

## Phase 4: Task 2
Looking at the Recipe class that I have designed, you will
notice that it is robust. Its "rateRecipe" method throws an
"InvalidRating" exception. In the tests for the Recipe class, 
there are tests that check both when the exception is expected, 
and when the exception is not expected.

## Phase 4: Task 3
After doing an analysis of my code, I found three specific
areas that could easily be improved. Firstly, when reviewing 
the code I used to create my GUI in the RecipeCreationGUI class, 
I realized that I had created 3 headers for the Categories, Ingredients, 
and Steps sections of my GUI. They were all in the same row 
(I used a GridBagLayout) and were all labels. As such, I chose to decrease
this coupling by making one method for all three headers instead of
writing a lot of unnecessary duplicate code.

I found another instance of high coupling when I was creating
the UML diagram of my classes. As seen in the picture titled
"Before Changes UML Diagram", RecipeCollectionUI and
VirtualKitchenUI both had a VirtualKitchen in their fields.
As I wanted only one instance of a VirtualKitchen, this introduced
coupling to this part of my code. To remedy this, instead of both
VirtualKitchenUI and RecipeCollectionUI
having a VirtualKitchen field and the KitchenManagerApp class having both
a VirtualKitchenUI field and a RecipeCollectionUI field, I instead chose for only the 
VirtualKitchenUI to have a VirtualKitchen field. Then, I chose
for RecipeCollectionUI to have a VirtualKitchenUI field so that 
the KitchenManagerApp would only need RecipeCollectionUI as a field and
not both. As a result, this severely decreased coupling in my project. 
These changes can be seen in the "After Changes UML Diagram".

The last instance of coupling that I remedied was in the RecipeCollection class
and the RecipeCollectionUI class. In RecipeCollectionUI, I ran for loops 
using a getter method to get the list of Recipes. However, this method increases
coupling as RecipeCollectionUI becomes dependent on this additional getter method 
unnecessarily. Therefore, I chose to implement the iterator design method in RecipeCollection.
By doing this, RecipeCollectionUI could now loop over the entire list of Recipes found
in RecipeCollection without the need of a getter method, thus decreasing the dependency
found between the two classes. 
