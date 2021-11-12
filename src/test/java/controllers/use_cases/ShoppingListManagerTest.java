package controllers.use_cases;

import entities.ShoppingList;
import org.junit.*;

import static org.junit.Assert.assertEquals;

public class ShoppingListManagerTest{
    ShoppingListManager shoppingListManager;
    @Before
    public void setUp() {
        shoppingListManager = new ShoppingListManager();
        shoppingListManager.newShoppingList("Best Buy");
        shoppingListManager.newShoppingList("KFC");
    }

    @Test(timeout = 50)
    public void testAddCommodity() {
        shoppingListManager.setCommodity(0, "iPad",700, 2);
        // Creates a Commodity iPad in Best Buy's shopping list at index 0
        shoppingListManager.setCommodity(0, "Charger",30, 1);
        ShoppingList shoppingList = shoppingListManager.addCommodity(0, "iPad");
        // Adds a commodity iPad in Best Buy's shopping list at index 0
        assertEquals(2130, shoppingList.getTotalPrice(), 0);
    }

    @Test(timeout = 50)
    public void testRemoveCommodity() {
        shoppingListManager.setCommodity(1, "10x Wings",20,1);
        // Creates a Commodity 10x Wings in Best Buy's shopping list at index 1
        ShoppingList shoppingList = shoppingListManager.removeCommodity(1, "10x Wings");
        assertEquals(0, shoppingList.getTotalPrice(), 0);
    }
}