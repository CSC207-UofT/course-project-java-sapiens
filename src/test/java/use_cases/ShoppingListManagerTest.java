package use_cases;

import entities.ShoppingListOld;
import org.junit.*;


import static org.junit.Assert.assertEquals;

public class ShoppingListManagerTest{
    ShoppingListManagerOld shoppingListManager;
    @Before
    public void setUp() {
        shoppingListManager = new ShoppingListManagerOld();
    }

    @Test(timeout = 50)
    public void testAddCommodity() {
        String userID = "Dummy";
        shoppingListManager.newShoppingList(userID);
        shoppingListManager.addCommodity(userID, "Walmart", "Apple", 3.5);
        shoppingListManager.addCommodity(userID, "Walmart", "Banana", 2);
        ShoppingListOld shoppingListOld = shoppingListManager.addCommodity(userID, "Walmart", "Apple", 3.5);

        assertEquals(9, shoppingListOld.getTotalPrice(),0);
    }

    @Test(timeout = 50)
    public void testRemoveCommodity() {
        String userID = "Dummy";
        shoppingListManager.newShoppingList(userID);
        shoppingListManager.addCommodity(userID, "Walmart", "Apple", 3.5);
        shoppingListManager.addCommodity(userID, "Walmart", "Apple", 3.5);
        ShoppingListOld shoppingListOld = shoppingListManager.removeCommodity(userID, "Walmart", "Apple");

        System.out.println(shoppingListOld.getTotalPrice());
        assertEquals(3.5, shoppingListOld.getTotalPrice(),0);

        ShoppingListOld shoppingListOld2 = shoppingListManager.removeCommodity(userID, "Walmart", "Apple");
        assertEquals(0, shoppingListOld.getTotalPrice(),0);
    }
}