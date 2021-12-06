package com.yde.sapiensdelivery.use_cases_tests;

import com.yde.sapiensdelivery.entities.ShoppingListOld;
import com.yde.sapiensdelivery.use_cases.ShoppingListManagerOld;
import org.junit.*;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

public class ShoppingListOldManagerTest {
    ShoppingListManagerOld shoppingListManagerOld;
    ArrayList<ShoppingListOld> shoppingListOlds;

    @Before
    public void setUp() {
        shoppingListManagerOld = new ShoppingListManagerOld();
        shoppingListOlds = new ArrayList<>();
        shoppingListManagerOld.newShoppingList("Best Buy", shoppingListOlds);
        shoppingListManagerOld.newShoppingList("KFC", shoppingListOlds);
    }

    @Test(timeout = 50)
    public void testAddCommodity() {
        shoppingListManagerOld.setCommodity(0, "iPad",700, 2, shoppingListOlds);
        // Creates a Commodity iPad in Best Buy's shopping list at index 0
        shoppingListManagerOld.setCommodity(0, "Charger",50, 1, shoppingListOlds);
        ShoppingListOld shoppingListOld = shoppingListManagerOld.addCommodity(0, "iPad", shoppingListOlds);
        // Adds a commodity iPad in Best Buy's shopping list at index 0
        assertEquals(2150, shoppingListOld.getTotalPrice(), 0);
    }

    @Test(timeout = 50)
    public void testRemoveCommodity() {
        shoppingListManagerOld.setCommodity(1, "10x Wings",20,1, shoppingListOlds);
        // Creates a Commodity 10x Wings in Best Buy's shopping list at index 1
        ShoppingListOld shoppingListOld = shoppingListManagerOld.removeCommodity(1, "10x Wings", shoppingListOlds);
        assertEquals(0, shoppingListOld.getTotalPrice(), 0);
    }
}
