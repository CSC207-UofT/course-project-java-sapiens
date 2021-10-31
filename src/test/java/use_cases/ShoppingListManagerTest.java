package use_cases;

import entities.ShoppingList;
import org.junit.*;


import static org.junit.Assert.assertEquals;

public class ShoppingListManagerTest{
    ShoppingListManager shoppingListManager;
    @Before
    public void setUp() {
        shoppingListManager = new ShoppingListManager();
    }

    @Test(timeout = 50)
    public void testAddCommodity() {
        String userID = "Dummy";
        shoppingListManager.newShoppingList(userID);
        shoppingListManager.addCommodity(userID, "Walmart", "Apple", 3.5);
        shoppingListManager.addCommodity(userID, "Walmart", "Banana", 2);
        ShoppingList shoppingList = shoppingListManager.addCommodity(userID, "Walmart", "Apple", 3.5);

        assertEquals(9, shoppingList.getTotalPrice(),0);
    }

    @Test(timeout = 50)
    public void testRemoveCommodity() {
        String userID = "Dummy";
        shoppingListManager.newShoppingList(userID);
        shoppingListManager.addCommodity(userID, "Walmart", "Apple", 3.5);
        shoppingListManager.addCommodity(userID, "Walmart", "Apple", 3.5);
        ShoppingList shoppingList = shoppingListManager.removeCommodity(userID, "Walmart", "Apple");

        System.out.println(shoppingList.getTotalPrice());
        assertEquals(3.5, shoppingList.getTotalPrice(),0);

        ShoppingList shoppingList2 = shoppingListManager.removeCommodity(userID, "Walmart", "Apple");
        assertEquals(0, shoppingList.getTotalPrice(),0);
    }
}