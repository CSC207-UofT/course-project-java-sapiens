package com.yde.sapiensdelivery.use_cases;

import static org.junit.Assert.*;

import com.yde.sapiensdelivery.entities.Commodity;
import com.yde.sapiensdelivery.entities.Outlet;
import com.yde.sapiensdelivery.entities.ShoppingList;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ShoppingListManagerTest {
    private OutletManager outletManager;
    private ShoppingListManager shoppingListManager;
    @Before
    public void setUp() {
        ArrayList<Commodity> house = new ArrayList<>();
        house.add(new Commodity("TV", 1000, 1));
        house.add(new Commodity("Couch", 200, 1));
        Outlet friend = new Outlet("Friend's House", "NO ADDRESS", house);

        this.shoppingListManager = new ShoppingListManager(friend);

        outletManager = new OutletManager(shoppingListManager.getOutlet());
    }

    @Test
    public void setCommodity() {
        // Added a couch
        shoppingListManager.setCommodity(outletManager.newCommodityAtIndex(1));
        assertEquals(200, shoppingListManager.getTotalPrice(), 0);
    }

    @Test
    public void add1Commodity() {
        // Added a couch
        shoppingListManager.setCommodity(outletManager.newCommodityAtIndex(1));
        // Added another couch
        shoppingListManager.add1Commodity(0);
        assertEquals(400, shoppingListManager.getTotalPrice(), 0);
    }

    @Test
    public void remove1Commodity() {
        // Added a couch
        shoppingListManager.setCommodity(outletManager.newCommodityAtIndex(1));
        // Added another couch
        shoppingListManager.add1Commodity(0);
        // Removed a couch
        shoppingListManager.remove1Commodity(0);
        assertEquals(200, shoppingListManager.getTotalPrice(), 0);
    }

    @Test
    public void newShoppingList() {
        // Get a new empty shoppingList for an outlet
        ShoppingList shoppingList =
                shoppingListManager.newShoppingList(shoppingListManager.getOutlet());
        assertEquals(shoppingList.getOutletName(), "Friend's House");
    }

    @Test
    public void calculateManagersTotal() {
        ArrayList<ShoppingListManager> shoppingListManagers = new ArrayList<>();
        shoppingListManager.setCommodity(outletManager.newCommodityAtIndex(0));
        shoppingListManagers.add(shoppingListManager);
        assertEquals(1000, shoppingListManager.calculateManagersTotal(shoppingListManagers), 0);
    }

    @Test
    public void calculateListsTotal() {
        shoppingListManager.setCommodity(outletManager.newCommodityAtIndex(0));
        assertEquals(1000, shoppingListManager.getTotalPrice(), 0);
    }
}