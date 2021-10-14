package use_cases;

import entities.ShoppingList;
import org.junit.*;

import java.util.HashMap;

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
        ShoppingList shoppingList = shoppingListManager.addCommodity(userID, "Walmart", "Apple", 3.5);

        System.out.println(shoppingList.getTotalPrice());
        assertEquals(7, shoppingList.getTotalPrice());
    }

    @Test(timeout = 50)
    public void testRemoveCommodity() {
    }
}