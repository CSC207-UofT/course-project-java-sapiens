package com.yde.sapiensdelivery.use_cases;

import static org.junit.Assert.*;

import com.yde.sapiensdelivery.entities.Commodity;
import com.yde.sapiensdelivery.entities.Outlet;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class OutletManagerTest {
    private OutletManager outletManager;

    @Before
    public void setUp() {
        ArrayList<Commodity> list = new ArrayList<>();
        list.add(new Commodity("Apple", 2.5, 1));
        list.add(new Commodity("Banana", 3, 1));
        Outlet walmart = new Outlet("Walmart", "NO ADDRESS", list);

        outletManager = new OutletManager(walmart);
    }

    @Test
    public void newCommodityAtIndex() {
        Commodity apple = new Commodity("Apple", 2.5, 1);
        Commodity copyApple = outletManager.newCommodityAtIndex(0);
        assertEquals(apple.getName(), copyApple.getName());
    }

    @Test
    public void getCommList() {
        ArrayList<Commodity> list = new ArrayList<>();
        list.add(new Commodity("Apple", 2.5, 1));
        list.add(new Commodity("Banana", 3, 1));
        assertEquals(list.size(), outletManager.getCommList().size());
    }

    @Test
    public void getCommNameAtIndex() {
        assertEquals("Apple", outletManager.getCommNameAtIndex(0));
    }

    @Test
    public void getOutletName() {
        assertEquals("Walmart", outletManager.getOutletName());
    }

    @Test
    public void getOutletAddress() {
        assertEquals("NO ADDRESS", outletManager.getOutletAddress());
    }
}