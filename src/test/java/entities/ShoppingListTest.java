package entities;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ShoppingListTest {
    ShoppingListOld shoppingListOld;
    @Before
    public void setUp() throws Exception {
        shoppingListOld = new ShoppingListOld();
    }

    @Test
    public void addCommodity() {
        Commodity apple = new Commodity("Apple", 3.5);
        shoppingListOld.addCommodity("Walmart", apple);
        assertEquals( 3.5, shoppingListOld.getTotalPrice(), 0);
    }

    @Test
    public void removeCommodity() {
        Commodity apple = new Commodity("Apple", 3.5);
        shoppingListOld.addCommodity("Walmart", apple);
        shoppingListOld.addCommodity("Walmart", apple);
        shoppingListOld.removeCommodity("Walmart", apple);
        assertEquals( 3.5, shoppingListOld.getTotalPrice(), 0);
        shoppingListOld.removeCommodity("Walmart", apple);
        assertEquals( 0, shoppingListOld.getTotalPrice(), 0);

    }
}