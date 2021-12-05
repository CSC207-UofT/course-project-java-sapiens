package com.yde.sapiensdelivery.use_cases;

import com.yde.sapiensdelivery.entities.Commodity;
import com.yde.sapiensdelivery.entities.Customer;
import com.yde.sapiensdelivery.entities.DeliveryMan;
import com.yde.sapiensdelivery.entities.Order;
import com.yde.sapiensdelivery.entities.Outlet;
import com.yde.sapiensdelivery.entities.ShoppingList;
import com.yde.sapiensdelivery.use_cases.OrderManager;
import com.yde.sapiensdelivery.use_cases.ShoppingListManager;

import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class OrderManagerTest {
    OrderManager orderManager;
    ShoppingListManager shoppingListManager;

    @Before
    public void setUp() {
        ArrayList<Commodity> house = new ArrayList<>();
        house.add(new Commodity("TV", 1000, 1));
        house.add(new Commodity("Couch", 200, 1));
        Outlet friend = new Outlet("Friend's House", "NO ADDRESS", house);

        this.shoppingListManager = new ShoppingListManager(friend);
        this.orderManager = new OrderManager();
    }

    @Test(timeout = 50)
    public void testCreateOrder1() {
        DeliveryMan deliveryMan1 = new DeliveryMan("Samuel", "ADDRESS", "648", "Samuel", "12", 1234, "moto-bike", (float)4.5);
        Customer customer1 = new Customer("Patrick", "ADDRESS", "647", "Pat", "123");
        ShoppingList shoppingList = shoppingListManager.getShoppingList();
        ArrayList<ShoppingList> s = new ArrayList<>();
        s.add(shoppingList);

        Order order = orderManager.createOrder(deliveryMan1, customer1, s);
        assertEquals("Patrick", order.getCustomer().getName());
    }

    @Test(timeout = 50)
    public void testCreateOrder2() {
        DeliveryMan deliveryMan1 = new DeliveryMan("Samuel", "ADDRESS", "648", "Samuel", "12", 1234, "moto-bike", (float)4.5);
        Customer customer1 = new Customer("Patrick", "ADDRESS", "647", "Pat", "123");
        ShoppingList shoppingList = shoppingListManager.getShoppingList();
        ArrayList<ShoppingList> s = new ArrayList<>();
        s.add(shoppingList);
        Order order = orderManager.createOrder(deliveryMan1, customer1, s);

        assertEquals("648", order.getDeliveryMan().getNumber());
    }
}
