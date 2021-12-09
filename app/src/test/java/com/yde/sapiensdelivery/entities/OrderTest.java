package com.yde.sapiensdelivery.entities;

import com.yde.sapiensdelivery.use_cases.OutletManager;
import com.yde.sapiensdelivery.use_cases.ShoppingListManager;

import org.junit.*;

import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

public class OrderTest {
    private Order order;
    private ShoppingListManager shoppingListManager;

    @Before
    public void setUp() {
        ArrayList<Commodity> house = new ArrayList<>();
        house.add(new Commodity("TV", 1000, 1));
        house.add(new Commodity("Couch", 200, 1));
        Outlet friend = new Outlet("Friend's House", "NO ADDRESS", house);

        this.shoppingListManager = new ShoppingListManager(friend);
        System.out.println(shoppingListManager.getOutletName());
    }

    @Test
    public void UID_Test() {
        Outlet friendHouse = shoppingListManager.getOutlet();
        OutletManager outletManager = new OutletManager(friendHouse);

        shoppingListManager.setCommodity(outletManager.newCommodityAtIndex(1));
        // Set a $200 couch

        ShoppingList shoppingList = shoppingListManager.getShoppingList();
        ArrayList<ShoppingList> s = new ArrayList<>();
        s.add(shoppingList);

        s.add(shoppingList);
        order = new Order(null,null,s);
    }


    @Test
    public void OrderStatusTest() {
        DeliveryMan deliveryMan1 = new DeliveryMan("Samuel", "ADDRESS", "648", "Samuel", "12", 1234, "moto-bike");
        Customer customer1 = new Customer("Patrick", "ADDRESS", "647", "Pat", "123");
        ShoppingList shoppingList = shoppingListManager.getShoppingList();
        ArrayList<ShoppingList> s = new ArrayList<>();
        s.add(shoppingList);
        order = new Order(deliveryMan1,customer1,s);
        order.setStatusOTW();
        assertEquals("On the Way to get your order.", order.getStatusOTW());
    }

    @Test
    public void TotalPriceTest() {
        DeliveryMan deliveryMan1 = new DeliveryMan("Samuel", "ADDRESS", "648", "Samuel", "12", 1234, "moto-bike");
        Customer customer1 = new Customer("Patrick", "ADDRESS", "647", "Pat", "123");

        Outlet friendHouse = shoppingListManager.getOutlet();
        OutletManager outletManager = new OutletManager(friendHouse);

        shoppingListManager.setCommodity(outletManager.newCommodityAtIndex(1));
        // Set a $200 couch
        shoppingListManager.add1Commodity(0);
        // Added another couch
        ShoppingList shoppingList = shoppingListManager.getShoppingList();
        ArrayList<ShoppingList> s = new ArrayList<>();
        s.add(shoppingList);

        order = new Order(deliveryMan1,customer1,s);

        assertEquals(400.0, order.getTotalPrice(),0);
    }

}
