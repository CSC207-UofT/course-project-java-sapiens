package entities_tests;

import com.yde.sapiensdelivery.entities.Commodity;
import com.yde.sapiensdelivery.entities.ShoppingList;
import org.junit.*;

import static org.junit.Assert.assertEquals;

public class ShoppingListTest {
    ShoppingList walmartList;
    @Before
    public void setUp() throws Exception {
        walmartList = new ShoppingList("Walmart");
        Commodity apple = new Commodity("Apple", 3.5, 1);
        walmartList.setCommodity(apple, 1); // Apple #1
    }

    @Test
    public void addCommodity() {
        walmartList.addCommodity("Apple"); // Apple #2
        walmartList.addCommodity("Apple"); // Apple #3
        assertEquals(10.5, walmartList.getTotalPrice(),0);

        int numOfApples = walmartList.getShoppingList().get("Apple").getQuantity();
        assertEquals(3, numOfApples,0);
    }

    @Test
    public void removeCommodity() {
        walmartList.addCommodity("Apple"); // Apple #2
        walmartList.removeCommodity("Apple"); // Apple #1
        assertEquals(3.5, walmartList.getTotalPrice(),0);

        int numOfApples = walmartList.getShoppingList().get("Apple").getQuantity();
        assertEquals(1, numOfApples,0);
    }
}