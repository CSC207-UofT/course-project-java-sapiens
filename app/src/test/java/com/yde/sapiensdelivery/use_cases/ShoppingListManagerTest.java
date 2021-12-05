package com.yde.sapiensdelivery.use_cases;

import static org.junit.Assert.*;

import com.yde.sapiensdelivery.entities.Commodity;
import com.yde.sapiensdelivery.entities.Outlet;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ShoppingListManagerTest {
    private OutletManager outletManager;
    @Before
    public void setUp() {
        ShoppingListManager shoppingListManager = new ShoppingListManager();

        ArrayList<Commodity> house = new ArrayList<>();
        house.add(new Commodity("TV", 1000, 1));
        house.add(new Commodity("Couch", 200, 1));
        Outlet friend = new Outlet("Friend's House", "NO ADDRESS", house);

        shoppingListManager.newShoppingList(friend);

        outletManager = new OutletManager(shoppingListManager.getOutlet());
    }

    @Test
    public void setCommodity() {
        ArrayList<Commodity> house = new ArrayList<>();
        house.add(new Commodity("TV", 1000, 1));
        house.add(new Commodity("Couch", 200, 1));
        Outlet friend = new Outlet("Friend's House", "NO ADDRESS", house);

        // Added a couch
//        shoppingListManager.setCommodity(outletManager.newCommodityAtIndex(1));
//        assertEquals(200, shoppingListManager.getTotalPrice(), 0);
        assertEquals("Friend's House", outletManager.getOutletName());
    }

    @Test
    public void add1Commodity() {
    }

    @Test
    public void remove1Commodity() {
    }

    @Test
    public void newShoppingList() {
    }

    @Test
    public void calculateManagersTotal() {
    }

    @Test
    public void calculateListsTotal() {
    }
}