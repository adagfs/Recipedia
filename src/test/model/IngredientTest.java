package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientTest {

    Ingredient ing;

    @BeforeEach
    public void setup() {
        ing = new Ingredient("celery", "1 stalk");
    }

    @Test
    public void testItemAndQuantityGetters() {
        assertEquals("celery", ing.getItem());
        assertEquals("1 stalk", ing.getQuantity());
    }
}
