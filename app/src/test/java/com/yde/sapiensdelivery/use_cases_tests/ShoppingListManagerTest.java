package com.yde.sapiensdelivery.use_cases_tests;

import com.yde.sapiensdelivery.entities.ShoppingList;
import com.yde.sapiensdelivery.use_cases.ShoppingListManager;
import org.junit.*;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

public class ShoppingListManagerTest{
    ShoppingListManager shoppingListManager;
    ArrayList<ShoppingList> shoppingLists;

    @Before
    public void setUp() {
        shoppingListManager = new ShoppingListManager();
        shoppingLists = new ArrayList<>();
        shoppingListManager.newShoppingList("Best Buy", shoppingLists);
        shoppingListManager.newShoppingList("KFC", shoppingLists);
    }

    @Test(timeout = 50)
    public void testAddCommodity() {
        shoppingListManager.setCommodity(0, "iPad",700, 2, shoppingLists);
        // Creates a Commodity iPad in Best Buy's shopping list at index 0
        shoppingListManager.setCommodity(0, "Charger",50, 1, shoppingLists);
        ShoppingList shoppingList = shoppingListManager.addCommodity(0, "iPad", shoppingLists);
        // Adds a commodity iPad in Best Buy's shopping list at index 0
        assertEquals(2150, shoppingList.getTotalPrice(), 0);
    }

    @Test(timeout = 50)
    public void testRemoveCommodity() {
        shoppingListManager.setCommodity(1, "10x Wings",20,1, shoppingLists);
        // Creates a Commodity 10x Wings in Best Buy's shopping list at index 1
        ShoppingList shoppingList = shoppingListManager.removeCommodity(1, "10x Wings", shoppingLists);
        assertEquals(0, shoppingList.getTotalPrice(), 0);
    }
}
