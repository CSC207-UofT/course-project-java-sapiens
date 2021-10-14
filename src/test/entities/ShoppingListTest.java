package entities;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ShoppingListTest {
    ShoppingList shoppingList;
    @Before
    public void setUp() throws Exception {
        shoppingList = new ShoppingList();
    }

    @Test
    public void addCommodity() {
        Commodity apple = new Commodity("Apple", 3.5);
        shoppingList.addCommodity("Walmart", apple);
        assertEquals( 3.5, shoppingList.getTotalPrice(), 0);
    }

    @Test
    public void removeCommodity() {
        Commodity apple = new Commodity("Apple", 3.5);
        shoppingList.addCommodity("Walmart", apple);
        shoppingList.addCommodity("Walmart", apple);
        shoppingList.removeCommodity("Walmart", apple);
        assertEquals( 3.5, shoppingList.getTotalPrice(), 0);
        shoppingList.removeCommodity("Walmart", apple);
        assertEquals( 0, shoppingList.getTotalPrice(), 0);

    }
}